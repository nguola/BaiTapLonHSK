package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.ChiTietPhieuDat_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDat_DAO;
import dao.SanPham_DAO;
import entity.ChiTietPhieuDat;
import entity.NhanVien;
import entity.PhieuDat;
import entity.SanPham;
import entity.TaiKhoan;

public class NhapHang_GUI extends JPanel implements ActionListener, MouseListener, DocumentListener{
	private JTextField txtSearch;
	private DefaultTableModel dtmDSSP;
	private JTable tbDSSP;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtGiaSP;
	private JTextField txtSLSP;
	private JTextField txtDVSP;
	private DefaultTableModel dtmDSSPDC;
	private JTable tbDSSPDC;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaAll;
	private SanPham_DAO sanPham_DAO = new SanPham_DAO();
	private ArrayList<SanPham> listSP = new ArrayList<SanPham>();
	private ArrayList<ChiTietPhieuDat> listSPDC = new ArrayList<ChiTietPhieuDat>();
	private PhieuDat_DAO phieuDat_DAO = new PhieuDat_DAO();
	private ArrayList<PhieuDat> listPD = new ArrayList<PhieuDat>();
	private ChiTietPhieuDat_DAO chiTietPhieuDat_DAO = new ChiTietPhieuDat_DAO();
	private JTextField txtTongTien;
	private JButton btnNhapHang;
	private JComboBox<String> cbbNCC;
	private JTextField txtNVNhap;
	private JTextField txtMaPN;
	private NhanVien nv;
	private NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
	
	public NhapHang_GUI(TaiKhoan tk) {
		nv = nhanVien_dao.getNhanVienTheoMaNV(tk.getNhanvien().getMaNhanVien());
		setLayout(new BorderLayout());
		
		listSP = sanPham_DAO.getalltbSanPham();
		//pnEast
		JPanel pnEast = new JPanel();
		pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
		pnEast.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(pnEast, BorderLayout.EAST);
		
		listPD = phieuDat_DAO.getAllPhieuDat();
		JLabel lbMaPN = new JLabel("Mã Phiếu Nhập");
		txtMaPN = new JTextField(25);
		txtMaPN.setEditable(false);
		txtMaPN.setText(Integer.toString(listPD.get(listPD.size() - 1).getMaPhieu() + 1));
		JLabel lbNVNhap = new JLabel("Nhân Viên Nhập");
		txtNVNhap = new JTextField(25);
		txtNVNhap.setText(Integer.toString(nv.getMaNhanVien()));
		txtNVNhap.setEditable(false);
		JLabel lbNCC = new JLabel("Nhà Cung Cấp");
		cbbNCC = new JComboBox<String>();
		cbbNCC = new JComboBox<String>();
		Set<String> uniqueNCC = new HashSet<>();
		for (PhieuDat phieuDat : listPD) {
			uniqueNCC.add(phieuDat.getNhaCungCap());
		}
		for (String ncc : uniqueNCC) {
			cbbNCC.addItem(ncc);
		}
		cbbNCC.setSelectedIndex(-1);
		
		pnEast.add(lbMaPN);
		pnEast.add(Box.createVerticalStrut(10));
		pnEast.add(txtMaPN);
		pnEast.add(Box.createVerticalStrut(20));
		
		pnEast.add(lbNVNhap);
		pnEast.add(Box.createVerticalStrut(10));
		pnEast.add(txtNVNhap);
		pnEast.add(Box.createVerticalStrut(20));
		
		pnEast.add(lbNCC);
		pnEast.add(Box.createVerticalStrut(10));
		pnEast.add(cbbNCC);
		pnEast.add(Box.createVerticalStrut(20));
		
		pnEast.add(Box.createVerticalStrut(250));
		
		JLabel lbTongTien = new JLabel("Tổng Tiền");
		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new GridLayout(1, 1));
		btnNhapHang = new JButton("Nhập Hàng");
		btnNhapHang.addActionListener(this);
		pnButton.add(btnNhapHang);
		
		pnEast.add(lbTongTien);
		pnEast.add(Box.createVerticalStrut(10));
		pnEast.add(txtTongTien);
		pnEast.add(Box.createVerticalStrut(20));
		pnEast.add(pnButton);
		
		//pnCen
		JPanel pnCen = new JPanel();
		pnCen.setLayout(new BoxLayout(pnCen, BoxLayout.Y_AXIS));
		add(pnCen, BorderLayout.CENTER);
		
		//pnCenLeft
		JPanel pnCenLeft = new JPanel();
		pnCenLeft.setLayout(new BoxLayout(pnCenLeft, BoxLayout.Y_AXIS));
		pnCenLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box b1 = Box.createHorizontalBox();
		JLabel lbSearch = new JLabel("Tìm kiếm: ");
		txtSearch = new JTextField();
		txtSearch.getDocument().addDocumentListener(this);
		b1.add(lbSearch);
		b1.add(txtSearch);
		pnCenLeft.add(b1);
		pnCenLeft.add(Box.createVerticalStrut(20));
		
		Object[] obj = {
				"Mã Sản Phẩm",
				"Tên Sản Phẩm",
				"Số Lượng",
				"Đơn vị tính"
		};
		dtmDSSP = new DefaultTableModel(null, obj);
		tbDSSP = new JTable(dtmDSSP);
		tbDSSP.addMouseListener(this);
		updateTableSP(listSP);
		JScrollPane scrDSSP = new JScrollPane(tbDSSP);
		pnCenLeft.add(scrDSSP);
		
		//pnCenRight
		JPanel pnCenRight = new JPanel();
		pnCenRight.setLayout(new BoxLayout(pnCenRight, BoxLayout.Y_AXIS));
		pnCenRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box b2 = Box.createHorizontalBox();
		JLabel lbMaSP = new JLabel("Mã Sản Phẩm");
		lbMaSP.setPreferredSize(new Dimension(100, 50));
		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		b2.add(lbMaSP);
		b2.add(txtMaSP);
		pnCenRight.add(b2);
		pnCenRight.add(Box.createVerticalStrut(20));
		
		Box b3 = Box.createHorizontalBox();
		JLabel lbTenSP = new JLabel("Tên Sản Phẩm");
		lbTenSP.setPreferredSize(new Dimension(100, 50));
		txtTenSP = new JTextField();
		txtTenSP.setEditable(false);
		b3.add(lbTenSP);
		b3.add(txtTenSP);
		pnCenRight.add(b3);
		pnCenRight.add(Box.createVerticalStrut(20));
		
		Box b4 = Box.createHorizontalBox();
		JLabel lbDVSP = new JLabel("Đơn vị tính");
		lbDVSP.setPreferredSize(new Dimension(100, 50));
		txtDVSP = new JTextField();
		txtDVSP.setEditable(false);
		b4.add(lbDVSP);
		b4.add(txtDVSP);
		pnCenRight.add(b4);
		pnCenRight.add(Box.createVerticalStrut(20));
		
		Box b5 = Box.createHorizontalBox();
		JLabel lbGiaSP = new JLabel("Giá Nhập");
		lbGiaSP.setPreferredSize(new Dimension(100, 50));
		txtGiaSP = new JTextField();
		txtGiaSP.setEditable(false);
		b5.add(lbGiaSP);
		b5.add(txtGiaSP);
		pnCenRight.add(b5);
		pnCenRight.add(Box.createVerticalStrut(20));
		
		Box b6 = Box.createHorizontalBox();
		JLabel lbSLSP = new JLabel("Số Lượng");
		lbSLSP.setPreferredSize(new Dimension(100, 50));
		txtSLSP = new JTextField();
		b6.add(lbSLSP);
		b6.add(txtSLSP);
		pnCenRight.add(b6);
		pnCenRight.add(Box.createVerticalStrut(20));
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(this);
		JPanel pnBtCen = new JPanel();
		pnBtCen.setLayout(new GridLayout(1, 1));
		pnBtCen.add(btnThem);
		pnCenRight.add(pnBtCen);
		
		JSplitPane splCen = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnCenLeft, pnCenRight);
		splCen.setDividerSize(0);
		splCen.setResizeWeight(0.5);
		
		
		Box jCenTop = Box.createHorizontalBox();
		jCenTop.add(splCen);
		pnCen.add(jCenTop);
		//pnCenBot
		JPanel pnCenBot = new JPanel();
		pnCenBot.setLayout(new BoxLayout(pnCenBot, BoxLayout.Y_AXIS));
		pnCenBot.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Object[] obj1 = {"Mã Sản phẩm", "Số lượng", "Đơn Giá", "Thành tiền"};
		dtmDSSPDC = new DefaultTableModel(null, obj1);
		tbDSSPDC = new JTable(dtmDSSPDC);
		JScrollPane scrllDSSPDC = new JScrollPane(tbDSSPDC);
		pnCenBot.add(scrllDSSPDC);
		
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(this);
		btnXoaAll = new JButton("Xóa tất cả");
		btnXoaAll.addActionListener(this);
		JPanel pnChucNangBot = new JPanel();
		pnChucNangBot.add(btnXoa);
		pnChucNangBot.add(btnXoaAll);
		pnCenBot.add(Box.createVerticalStrut(10));
		pnCenBot.add(pnChucNangBot);
		
		
		pnCen.add(pnCenBot);
		
		setVisible(true);
	}
	
	public void updateTableSP(ArrayList<SanPham> list) {
		dtmDSSP.setRowCount(0);
		for (SanPham sanPham : list) {
			dtmDSSP.addRow(new Object[] {
					sanPham.getMaSanPham(),
					sanPham.getTen(),
					sanPham.getSoLuongTonKho(),
					sanPham.getDonVi()
					
			});
		}
	}
	
	public void updateTongTien() {
		double total = 0;
		for (ChiTietPhieuDat chiTietPhieuDat : listSPDC) {
			total += chiTietPhieuDat.getThanhTien();
		}
		txtTongTien.setText(Double.toString(total));
		
	}
	
	public void updateTableSPDC(ArrayList<ChiTietPhieuDat> list) {
		dtmDSSPDC.setRowCount(0);
		for (ChiTietPhieuDat chiTietPhieuDat : list) {
			dtmDSSPDC.addRow(new Object[] {
					Integer.toString(chiTietPhieuDat.getSanPham().getMaSanPham()),
					Integer.toString(chiTietPhieuDat.getSoLuong()),
					Double.toString(chiTietPhieuDat.getThanhTien() / chiTietPhieuDat.getSoLuong()),
					Double.toString(chiTietPhieuDat.getThanhTien())
				});
		}
	}
	
	public void refreshAll() {
		updateTableSP(listSP);
		txtSearch.setText("");
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDVSP.setText("");
		txtGiaSP.setText("");
		txtSLSP.setText("");
		listSPDC.removeAll(listSPDC);
		updateTableSPDC(listSPDC);
		listPD = phieuDat_DAO.getAllPhieuDat();
		txtMaPN.setText(Integer.toString(listPD.get(listPD.size() - 1).getMaPhieu() + 1));
		cbbNCC.setSelectedIndex(-1);
		txtTongTien.setText("");
	}
	
	public ArrayList<SanPham> searchByKeyWord() {
		String keyword = txtSearch.getText();
		ArrayList<SanPham> temp = new ArrayList<SanPham>();
		for (SanPham sanPham : listSP) {
			if (Integer.toString(sanPham.getMaSanPham()).contains(keyword) || sanPham.getTen().contains(keyword)) {
				temp.add(sanPham);
			}
		}
		return temp;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateTableSP(searchByKeyWord());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateTableSP(searchByKeyWord());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		if (src.equals(tbDSSP)) {
			int index = tbDSSP.getSelectedRow();
			if (index < 0) {
				return;
			}
			int maSP = Integer.parseInt(tbDSSP.getValueAt(index, 0).toString());
			int indexSP = listSP.indexOf(new SanPham(maSP));
			txtMaSP.setText(Integer.toString(listSP.get(indexSP).getMaSanPham()));
			txtTenSP.setText(listSP.get(indexSP).getTen());
			txtDVSP.setText(listSP.get(indexSP).getDonVi());
			double giaSP = listSP.get(indexSP).getGiaSanPham();
			txtGiaSP.setText(Double.toString(Math.round(giaSP - giaSP * 0.3)));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnThem)) {
			if (txtMaSP.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Chưa Chọn Sản Phẩm");
				return;
			}
			int sl = 0;
			if (txtSLSP.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số lượng");
				return;
			}
			try {
				sl = Integer.parseInt(txtSLSP.getText());
				if (sl <= 0) {
					throw new Exception();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
				return;
			}
			
			if (listSPDC.contains(new ChiTietPhieuDat(null, new SanPham(Integer.parseInt(txtMaSP.getText())), 0, 0))) {
				int index = listSPDC.indexOf(new ChiTietPhieuDat(null, new SanPham(Integer.parseInt(txtMaSP.getText())), 0, 0));
				listSPDC.get(index).setSoLuong(listSPDC.get(index).getSoLuong() + Integer.parseInt(txtSLSP.getText()));
				listSPDC.get(index).setThanhTien(listSPDC.get(index).getSoLuong() * Double.parseDouble(txtGiaSP.getText()));
			} else {
				listSPDC.add(new ChiTietPhieuDat(null, 
						new SanPham(Integer.parseInt(txtMaSP.getText())), 
						sl * Double.parseDouble(txtGiaSP.getText()), 
						sl
						));
			}
			
			updateTableSPDC(listSPDC);
			updateTongTien();
		} else if (src.equals(btnXoa)) {
			int index = tbDSSPDC.getSelectedRow();
			if (index < 0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm để xóa");
				return;
			}
			listSPDC.remove(index);
			updateTableSPDC(listSPDC);
			updateTongTien();
		} else if (src.equals(btnXoaAll)) {
			listSPDC.removeAll(listSPDC);
			updateTableSPDC(listSPDC);
			updateTongTien();
		} else if (src.equals(btnNhapHang)) {
			if (listSPDC.size() <= 0) {
				JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào được chọn");
				return;
			}
			if (cbbNCC.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Chọn nhà cung cấp");
				return;
			}
			
			int maPhieuDat = phieuDat_DAO.themPhieuDat(new PhieuDat(0, new NhanVien(Integer.parseInt(txtNVNhap.getText())), cbbNCC.getSelectedItem().toString(), new Date(System.currentTimeMillis()), Double.parseDouble(txtTongTien.getText())));
			for (ChiTietPhieuDat chiTietPhieuDat : listSPDC) {
				chiTietPhieuDat_DAO.themChiTietPhieuDat(
						new ChiTietPhieuDat(
							new PhieuDat(maPhieuDat), 
							chiTietPhieuDat.getSanPham(),
							chiTietPhieuDat.getThanhTien(), 
							chiTietPhieuDat.getSoLuong()));
				sanPham_DAO.updateSLSP(new SanPham(
						chiTietPhieuDat.getSanPham().getMaSanPham(), 
						null, 
						null, 
						"", 
						0, 
						"", 
						"", 
						listSP.get(listSP.indexOf(new SanPham(chiTietPhieuDat.getSanPham().getMaSanPham()))).getSoLuongTonKho() + chiTietPhieuDat.getSoLuong()));
			}
			listSP = sanPham_DAO.getalltbSanPham();
			updateTableSP(listSP);
			JOptionPane.showMessageDialog(this, "Thêm Phiếu đặt thành công");
			refreshAll();
		}
	}
}	

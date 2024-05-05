package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import dao.PhieuDat_DAO;
import entity.NhanVien;
import entity.PhieuDat;
import entity.TaiKhoan;

public class PhieuNhap_GUI extends JPanel implements ActionListener, DocumentListener{
	private JButton btnThem;
	private JButton btnChiTiet;
	private JButton btnXuatExcel;
	private JTextField txtSearch;
	private JButton btnLamMoi;
	private JDateChooser dateTuNgay;
	private JDateChooser dateDenNgay;
	private JTextField txtTuSoTien;
	private JTextField txtDenSoTien;
	private DefaultTableModel dtm;
	private JTable tb;
	private PhieuDat_DAO phieuDat_DAO = new PhieuDat_DAO();
	private ArrayList<PhieuDat> listPD = new ArrayList<PhieuDat>();
	private JComboBox<String> cbbNCC;
	private JComboBox<String> cbbMaNV;
	private JButton btnLoc;
	private JButton btnXoa;
	private JComboBox<String> cbbSort;

	public PhieuNhap_GUI(TaiKhoan tk) {
		setLayout(new BorderLayout());
		listPD = phieuDat_DAO.getAllPhieuDat();
		
		//PnNorth
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.X_AXIS));
		pnNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(pnNorth, BorderLayout.NORTH);
		
		//PnNorthCen
		JPanel pnNorthLeft = new JPanel();
		pnNorthLeft.setLayout(new BoxLayout(pnNorthLeft, BoxLayout.X_AXIS));
		JLabel title = new JLabel("Quản lý phiếu nhập");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		pnNorthLeft.add(title);
		pnNorthLeft.add(Box.createHorizontalStrut(50));
		pnNorth.add(pnNorthLeft);
		
		//pnNorthCen
		JPanel pnNorthCen = new JPanel();
		pnNorthCen.setLayout(new BoxLayout(pnNorthCen, BoxLayout.X_AXIS));
		pnNorth.add(pnNorthCen);
		
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(this);
		btnChiTiet = new JButton("Chi Tiết");
		btnChiTiet.addActionListener(this);
		btnXuatExcel = new JButton("Xuất Excel");
		btnXuatExcel.addActionListener(this);
		
		pnNorthCen.add(btnXoa);
		pnNorthCen.add(btnChiTiet);
		pnNorthCen.add(btnXuatExcel);


		//pnNorth Right
		JPanel pnNorthRight = new JPanel();
		JLabel lbSearch = new JLabel("Tìm kiếm: ");
		txtSearch = new JTextField(30);
		txtSearch.getDocument().addDocumentListener(this);
		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(this);
		pnNorthRight.add(lbSearch);
		pnNorthRight.add(txtSearch);
		pnNorthRight.add(btnLamMoi);
		pnNorthRight.add(Box.createHorizontalStrut(25));
		
		JLabel lbSort = new JLabel("Sắp xếp: ");
		cbbSort = new JComboBox<String>();
		
		cbbSort.addItem("Mã Phiếu");
		cbbSort.addItem("Mã Nhân Viên");
		cbbSort.addItem("Nhà Cung Cấp");
		cbbSort.addItem("Ngày đặt");
		cbbSort.addItem("Tổng Tiền");
		
		pnNorthRight.add(lbSort);
		pnNorthRight.add(Box.createHorizontalStrut(5));
		pnNorthRight.add(cbbSort);
		
		pnNorth.add(pnNorthRight);
		
		//PnWest 
		JPanel pnWest = new JPanel();
		pnWest.setBorder(BorderFactory.createTitledBorder("Lọc"));
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));
		add(pnWest, BorderLayout.WEST);
		
		Box b1 = Box.createHorizontalBox();
		
		JLabel lbNCC = new JLabel("Nhà Cung Cấp");
		lbNCC.setPreferredSize(new Dimension(100, HEIGHT));
		cbbNCC = new JComboBox<String>();
		Set<String> uniqueNCC = new HashSet<>();
		for (PhieuDat phieuDat : listPD) {
			uniqueNCC.add(phieuDat.getNhaCungCap());
		}
		for (String ncc : uniqueNCC) {
			cbbNCC.addItem(ncc);
		}
		cbbNCC.setSelectedIndex(-1);
		b1.add(lbNCC);
		b1.add(cbbNCC);
		pnWest.add(b1);
		pnWest.add(Box.createVerticalStrut(20));
		
		Box b2 = Box.createHorizontalBox();
		JLabel lbNV = new JLabel("Mã Nhân Viên");
		lbNV.setPreferredSize(new Dimension(100, HEIGHT));
		cbbMaNV = new JComboBox<String>();
		
		Set<String> uniqueMaNV = new HashSet<>();
		for (PhieuDat phieuDat : listPD) {
			uniqueMaNV.add(Integer.toString(phieuDat.getNhanVien().getMaNhanVien()));
		}
		for (String maNV : uniqueMaNV) {
			
			cbbMaNV.addItem(maNV);
		}
		cbbMaNV.setSelectedIndex(-1);
		b2.add(lbNV);
		b2.add(cbbMaNV);
		pnWest.add(b2);
		pnWest.add(Box.createVerticalStrut(20));
		
		JPanel pnTime = new JPanel();
		pnTime.setBorder(BorderFactory.createTitledBorder("Thời gian"));
		pnTime.setLayout(new BoxLayout(pnTime, BoxLayout.Y_AXIS));
		Box b3 = Box.createHorizontalBox();
		dateTuNgay = new JDateChooser();
		JLabel tuNgay = new JLabel("Từ Ngày");
		tuNgay.setPreferredSize(new Dimension(100, HEIGHT));
		b3.add(tuNgay);
		b3.add(dateTuNgay);
		pnTime.add(b3);
		pnWest.add(pnTime);
		pnTime.add(Box.createVerticalStrut(20));
		
		Box b4 = Box.createHorizontalBox();
		dateDenNgay = new JDateChooser();
		JLabel denNgay = new JLabel("Đến Ngày");
		denNgay.setPreferredSize(new Dimension(100, HEIGHT));
		b4.add(denNgay);
		b4.add(dateDenNgay);
		pnTime.add(b4);
		pnWest.add(Box.createVerticalStrut(20));
		
		Box b5 = Box.createVerticalBox();
		JLabel lbTuSoTien = new JLabel("Từ Số Tiền");
		txtTuSoTien = new JTextField(25);
		b5.add(lbTuSoTien);
		b5.add(Box.createVerticalStrut(10));
		b5.add(txtTuSoTien);
		pnWest.add(b5);
		pnWest.add(Box.createVerticalStrut(20));
		
		Box b6 = Box.createVerticalBox();
		JLabel lbDenSoTien = new JLabel("Đến Số Tiền");
		txtDenSoTien = new JTextField(25);
		b6.add(lbDenSoTien);
		b6.add(Box.createVerticalStrut(10));
		b6.add(txtDenSoTien);
		pnWest.add(b6);
		pnWest.add(Box.createVerticalStrut(20));
		
		JPanel groupBTN = new JPanel();
		groupBTN.setBorder(BorderFactory.createTitledBorder("Chức Năng"));
		pnWest.add(groupBTN);
		btnLoc = new JButton("Lọc");
		btnLoc.addActionListener(this);
		groupBTN.add(btnLoc);
		
		pnWest.add(Box.createVerticalStrut(250));
		
		JPanel pnCen = new JPanel();
		pnCen.setLayout(new BoxLayout(pnCen, BoxLayout.Y_AXIS));
		pnCen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(pnCen, BorderLayout.CENTER);
		
		
		String[] tbValue = {"Mã Phiếu", "Mã Nhân Viên", "Nhà Cung Cấp", "Ngày Đặt", "Tổng Tiền"};
		dtm = new DefaultTableModel(null, tbValue);
		tb = new JTable(dtm);
		JScrollPane srcll = new JScrollPane(tb);
		pnCen.add(srcll);
		updateTable(listPD);
		
		cbbSort.addActionListener(this);
		
		setVisible(true);
	}
	
	public void updateTable(ArrayList<PhieuDat> list) {
		dtm.setRowCount(0);
		for (PhieuDat phieuDat : list) {
			dtm.addRow(new Object[] {
					phieuDat.getMaPhieu(),
					phieuDat.getNhanVien().getMaNhanVien(),
					phieuDat.getNhaCungCap(),
					phieuDat.getNgayDat(),
					phieuDat.getTongTien()
			});
		}
	}
	
	public ArrayList<PhieuDat> searchByKeyWord() {
		String keyword = txtSearch.getText();
		ArrayList<PhieuDat> temp = new ArrayList<PhieuDat>();
		for (PhieuDat phieuDat : listPD) {
			if (Integer.toString(phieuDat.getMaPhieu()).contains(keyword)) {
				temp.add(phieuDat);
			}
		}
		return temp;
	}
	
	public void refresh() {
		listPD = phieuDat_DAO.getAllPhieuDat();
		updateTable(listPD);
		cbbMaNV.setSelectedIndex(-1);
		cbbNCC.setSelectedIndex(-1);
		dateTuNgay.setDate(null);
		dateDenNgay.setDate(null);
		txtTuSoTien.setText("");
		txtDenSoTien.setText("");
		txtSearch.setText("");
	}
	
	public void ExportFileExcel(ArrayList<PhieuDat> list, String filePath) {
		XSSFWorkbook wordkbook = new XSSFWorkbook();
        XSSFSheet sheet=wordkbook.createSheet("Danh sách Phiếu Nhập");
        XSSFRow row =null;
        Cell cell=null;
        
        row=sheet.createRow(0);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellValue("Mã Phiếu");
        cell=row.createCell(1,CellType.STRING);
        cell.setCellValue("Mã Nhân Viên");
        cell=row.createCell(2,CellType.STRING);
        cell.setCellValue("Nhà Cung Cấp");
        cell=row.createCell(3,CellType.STRING);
        cell.setCellValue("Ngày Đặt");
        cell=row.createCell(4,CellType.STRING);
        cell.setCellValue("Tổng tiền");
        
        for (int i = 0; i < list.size(); i++) {
        	row = sheet.createRow(i + 1);
        	cell=row.createCell(0,CellType.STRING);
            cell.setCellValue(list.get(i).getMaPhieu());
            cell=row.createCell(1,CellType.STRING);
            cell.setCellValue(list.get(i).getNhanVien().getMaNhanVien());
            cell=row.createCell(2,CellType.STRING);
            cell.setCellValue(list.get(i).getNhaCungCap());
            cell=row.createCell(3,CellType.STRING);
            cell.setCellValue(list.get(i).getNgayDat().toString());
            cell=row.createCell(4,CellType.STRING);
            cell.setCellValue(list.get(i).getTongTien());
        }
        try {
        	File f = new File(filePath + "//PhieuDat.xlsx");
            FileOutputStream fis = new FileOutputStream(f);
            wordkbook.write(fis);
            fis.close();
            JOptionPane.showMessageDialog(this, "Xuất thành công");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnLamMoi)) {
			refresh();
		} else if (src.equals(btnXuatExcel)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        int result = fileChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
	            ExportFileExcel(listPD, selectedFilePath);
	            
	        }
		} else if (src.equals(btnLoc)) {
			String ncc = "";
			int maNV = -1;
			Date tuNgay = null;
			Date denNgay = null;
			double tuSoTien = 0;
			double denSoTien = 0;
			if (cbbNCC.getSelectedItem() != null) {
				ncc = cbbNCC.getSelectedItem().toString();
			}
			if (cbbMaNV.getSelectedItem() != null) {
				maNV = Integer.parseInt(cbbMaNV.getSelectedItem().toString());
			}
			if (dateTuNgay.getDate() != null) {
				tuNgay = new Date(dateTuNgay.getDate().getTime());
			}
			if (dateDenNgay.getDate() != null) {
				denNgay = new Date(dateDenNgay.getDate().getTime());
			}
			
			if (tuNgay != null && denNgay != null && tuNgay.after(denNgay)) {
				JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
				return;
			}
			
			try {
				if (!txtTuSoTien.getText().trim().isEmpty()) {
					tuSoTien = Double.parseDouble(txtTuSoTien.getText());
				}
				if (!txtDenSoTien.getText().trim().isEmpty()) {
					denSoTien = Double.parseDouble(txtDenSoTien.getText());
				}
				
				if (tuSoTien < 0 || denSoTien < 0) {
					JOptionPane.showMessageDialog(this, "Số tiền Phải Lớn hơn hoặc bằng 0");
					return;
				}
				
				if (tuSoTien > denSoTien) {
					JOptionPane.showMessageDialog(this, "Số tiền đầu phải bé hơn số tiền sau");
					return;
				}
				
 			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Số Tiền Phải là Số nguyên");
			}
			
			listPD = phieuDat_DAO.PhieuDatFilter(tuNgay, denNgay, tuSoTien, denSoTien, ncc, maNV);
			updateTable(listPD);
		} else if (src.equals(btnChiTiet)) {
			int index = tb.getSelectedRow();
			if (index < 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một Phiếu Nhập");
				return;
			}
			PhieuDat phieuDat = new PhieuDat(Integer.parseInt(tb.getValueAt(index, 0).toString()));
			new ChiTietPhieuNhap_GUI(listPD.get(listPD.indexOf(phieuDat))).setVisible(true);
		} else if (src.equals(btnXoa)) {
			int index = tb.getSelectedRow();
			if (index < 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một Phiếu Nhập");
				return;
			}
			
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa ?") == JOptionPane.OK_OPTION) {
				if (!phieuDat_DAO.xoaPhieuDat(Integer.parseInt(tb.getValueAt(index, 0).toString()))) {
					JOptionPane.showMessageDialog(this, "Xóa Thành công");
				}
				listPD = phieuDat_DAO.getAllPhieuDat();
				updateTable(listPD);
			}
		} else if (src.equals(cbbSort)) {
			String val = cbbSort.getSelectedItem().toString();
			ArrayList<PhieuDat> sortedList = new ArrayList<>(listPD);
			switch (val) {
				case "Mã Phiếu": {
					Collections.sort(sortedList, Comparator.comparing(PhieuDat::getMaPhieu));
					break;
				}
				case "Mã Nhân Viên": {
					Collections.sort(sortedList, Comparator.comparing(phieuDat -> phieuDat.getNhanVien().getMaNhanVien()));
					break;
				}
				case "Nhà Cung Cấp": {
					Collections.sort(sortedList, Comparator.comparing(phieuDat -> phieuDat.getNhaCungCap()));
					break;
				}
				case "Ngày Đặt": {
					Collections.sort(sortedList, Comparator.comparing(phieuDat -> phieuDat.getNgayDat())); 
					break;
				}
				case "Tổng Tiền": {
					Collections.sort(sortedList, Comparator.comparing(phieuDat -> phieuDat.getTongTien()));
					break;
				}
			}
			updateTable(sortedList);
		}
 	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateTable(searchByKeyWord());
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateTable(searchByKeyWord());
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}

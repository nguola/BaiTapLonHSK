package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietPhieuDat_DAO;
import entity.ChiTietPhieuDat;
import entity.PhieuDat;

public class ChiTietPhieuNhap_GUI extends JDialog{
	private DefaultTableModel dtm;
	private JTable tb;
	private ChiTietPhieuDat_DAO chiTietPhieuDat_DAO = new ChiTietPhieuDat_DAO();
	private ArrayList<ChiTietPhieuDat> list = null;
	public ChiTietPhieuNhap_GUI(PhieuDat phieuDat) {
		// TODO Auto-generated constructor stub
		setModal(true);
		setSize(800, 400);
		setLocationRelativeTo(null);
		
		list = chiTietPhieuDat_DAO.getAllChiTietHoaDonTheoMaHoaDon(phieuDat.getMaPhieu());
		
		//pnNorth
		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(Color.blue);
		add(pnNorth, BorderLayout.NORTH);
		
		JLabel title = new JLabel("Thông tin Phiếu Nhập");
		title.setFont(new Font("Times New Roman", Font.BOLD, 25));
		title.setForeground(Color.white);
		pnNorth.add(title);
		
		//pnCen
		JPanel pnCen = new JPanel();
		pnCen.setLayout(new BoxLayout(pnCen, BoxLayout.Y_AXIS));
		pnCen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(pnCen, BorderLayout.CENTER);
		
		JPanel pnCenTop = new JPanel();
		pnCenTop.setLayout(new BoxLayout(pnCenTop, BoxLayout.X_AXIS));
		pnCen.add(Box.createVerticalStrut(20));
		pnCen.add(pnCenTop);
		pnCen.add(Box.createVerticalStrut(20));
		
		Box b1 = Box.createVerticalBox();
		JLabel lbMa = new JLabel("Mã Phiếu");
		JTextField txtMa = new JTextField(Integer.toString(phieuDat.getMaPhieu()));
		txtMa.setEditable(false);
		b1.add(lbMa);
		b1.add(Box.createVerticalStrut(10));
		b1.add(txtMa);
		pnCenTop.add(b1);
		pnCenTop.add(Box.createHorizontalStrut(20));
		
		Box b2 = Box.createVerticalBox();
		JLabel lbNV = new JLabel("Mã Nhân Viên");
		JTextField txtNV = new JTextField(Integer.toString(phieuDat.getNhanVien().getMaNhanVien()));
		txtNV.setEditable(false);
		b2.add(lbNV);
		b2.add(Box.createVerticalStrut(10));
		b2.add(txtNV);
		pnCenTop.add(b2);
		pnCenTop.add(Box.createHorizontalStrut(20));
			
		Box b3 = Box.createVerticalBox();
		JLabel lbNCC = new JLabel("Nhà Cung Cấp");
		JTextField txtNCC = new JTextField(phieuDat.getNhaCungCap());
		txtNCC.setEditable(false);
		b3.add(lbNCC);
		b3.add(Box.createVerticalStrut(10));
		b3.add(txtNCC);
		pnCenTop.add(b3);
		pnCenTop.add(Box.createHorizontalStrut(20));
		
		Box b4 = Box.createVerticalBox();
		JLabel lbThoiGian = new JLabel("Thời gian");
		JTextField txtThoiGian = new JTextField(phieuDat.getNgayDat().toString());
		txtThoiGian.setEditable(false);
		b4.add(lbThoiGian);
		b4.add(Box.createVerticalStrut(10));
		b4.add(txtThoiGian);
		pnCenTop.add(b4);
		
		Object[] obj = {"Mã Phiếu", "Mã Sản phẩm", "Thành tiền", "Số lượng"};
		dtm = new DefaultTableModel(null, obj);
		tb = new JTable(dtm);
		JScrollPane srrll = new JScrollPane(tb);
		pnCen.add(srrll);
		
		updateTable();
	}
	
	public void updateTable() {
		dtm.setRowCount(0);
		for (ChiTietPhieuDat chiTietPhieuDat : list) {
			dtm.addRow(new Object[] {
					chiTietPhieuDat.getPhieuDat().getMaPhieu(),
					chiTietPhieuDat.getSanPham().getMaSanPham(),
					chiTietPhieuDat.getThanhTien(),
					chiTietPhieuDat.getSoLuong()
			});
		}
	}
	public static void main(String[] args) {
		new PhieuNhap_GUI();
	}
}

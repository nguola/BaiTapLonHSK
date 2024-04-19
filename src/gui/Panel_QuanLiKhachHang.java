package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;
import entity.NhanVien;

public class Panel_QuanLiKhachHang extends JPanel implements ActionListener{
	private JTextField txtTimKiem;
	private TableModel khachhangModel;
	private JTable tableKhachHang;
	private KhachHang_DAO khachhang_dao;
	private ArrayList<KhachHang> listKhachHang = new ArrayList<KhachHang>();
	
	
	public Panel_QuanLiKhachHang() {
		try {
			ConnectDB.getInstance().connect();
			System.out.println("Connected!!!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		khachhang_dao = new KhachHang_DAO();
		//pNor
		JPanel pNor = new JPanel();
		pNor.setLayout(new BoxLayout(pNor, BoxLayout.Y_AXIS));
		JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTitle.setBackground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 34));
		pNor.add(lblTitle);
		pNor.add(Box.createVerticalStrut(20));
		
		//pCompon chứa các componet
		JPanel pCompon = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCompon.add(Box.createHorizontalStrut(20));
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(35, 177, 77));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThem.setPreferredSize(new Dimension(140, 40));
		pCompon.add(btnThem);
		pCompon.add(Box.createHorizontalStrut(10));

		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(35, 177, 77));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSua.setPreferredSize(new Dimension(140, 40));
		pCompon.add(btnSua);
		pCompon.add(Box.createHorizontalStrut(10));
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(35, 177, 77));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa.setPreferredSize(new Dimension(140, 40));
		pCompon.add(btnXoa);
		pCompon.add(Box.createHorizontalStrut(50));
		
		txtTimKiem = new JTextField();
		add(txtTimKiem);
		txtTimKiem.setColumns(20);
		txtTimKiem.setPreferredSize(new Dimension(70, 40));
		pCompon.add(txtTimKiem);
		pCompon.add(Box.createHorizontalStrut(10));
	
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(SystemColor.control);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setPreferredSize(new Dimension(70, 40));
		pCompon.add(btnTim);
		pNor.add(pCompon, BorderLayout.SOUTH);
		pNor.add(Box.createVerticalStrut(10));
		add(pNor, BorderLayout.NORTH);
		
		//table
		String [] col = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Loại khách hàng"};
		khachhangModel = new DefaultTableModel(col,0);
		tableKhachHang = new JTable(khachhangModel);
		JScrollPane scroll = new JScrollPane(tableKhachHang);
		add(scroll, BorderLayout.CENTER);
		
		
		//chừng sau xóa
		setSize(1000,680);
		setVisible(true);
		//------------
		
		
		
		//đọc dữ liệu vào bảng
		DocDuLieuDatabaseVaoTable();
		
		
	}
	public void DocDuLieuDatabaseVaoTable() {
		ArrayList<KhachHang> list = khachhang_dao.getalltbKhachHang();
		for(KhachHang kh : list) {
			((DefaultTableModel) khachhangModel).addRow(new Object[] {
					kh.getMaKhachHang(),
					kh.getTen(),
					kh.getSoDienThoai(),
					kh.getDiaChi(),
					kh.getLoaiKhachHang()
			});
		}
	}
	public static void main(String[] args) {
		new Panel_QuanLiKhachHang();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}

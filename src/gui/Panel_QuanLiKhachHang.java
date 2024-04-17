package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import entity.KhachHang;

public class Panel_QuanLiKhachHang extends JFrame{
	private JTextField txtTimKiem;
	private TableModel khachhangModel;
	private JTable tableKhachHang;
	private ArrayList<KhachHang> listKhachHang = new ArrayList<KhachHang>();
	
	
	public Panel_QuanLiKhachHang() {
		setLayout(null);
		//chừng sau xóa
		setSize(900,680);
		setVisible(true);
		//------------
		JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTitle.setBackground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblTitle.setBounds(212, 11, 374, 50);
		add(lblTitle);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(35, 177, 77));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThem.setBounds(10, 77, 172, 38);
		add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(35, 177, 77));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSua.setBounds(192, 77, 164, 38);
		add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(35, 177, 77));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa.setBounds(366, 77, 164, 38);
		add(btnXoa);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(609, 78, 172, 38);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
	
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(SystemColor.control);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setBounds(788, 77, 62, 38);
		add(btnTim);
		
		//table
		String [] col = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Loại khách hàng"};
		khachhangModel = new DefaultTableModel(col,0);
		tableKhachHang = new JTable(khachhangModel);
		JScrollPane scroll = new JScrollPane(tableKhachHang);
		scroll.setBounds(10, 126, 840, 468);
		add(scroll);
		
	}
	public static void main(String[] args) {
		new Panel_QuanLiKhachHang();
	}
	
}
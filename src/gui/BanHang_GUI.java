package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BanHang_GUI extends JFrame implements ActionListener{

	private JLabel lb_TieuDe;
	private JMenuBar mnubar;
	private JMenu mnuSanPham;
	private JMenu mnuNhanVien;
	private JMenu mnuNhaCungCap;
	private JMenu mnuHoaDon;
	private JMenu mnuKhachHang;

	public BanHang_GUI() throws HeadlessException {
		super();
		setSize(1500, 800);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");
		
		// code menu
		mnubar = new JMenuBar();
		
		mnuSanPham = new JMenu("Sản Phẩm");
		mnuSanPham.setFont(new Font("Arial", Font.BOLD, 15));
		
		mnuNhanVien = new JMenu("Nhân Viên");
		mnuNhanVien.setFont(new Font("Arial", Font.BOLD, 15));
		
		mnuHoaDon = new JMenu("Hóa đơn");
		mnuHoaDon.setFont(new Font("Arial", Font.BOLD, 15));
		
		mnuNhaCungCap = new JMenu("Nhà Cung Cấp");
		mnuNhaCungCap.setFont(new Font("Arial", Font.BOLD, 15));
		
		mnuKhachHang = new JMenu("Khách Hàng");
		mnuKhachHang.setFont(new Font("Arial", Font.BOLD, 15));
		
		
		mnuSanPham.add(new JMenuItem("SP"));
		mnuSanPham.add(new JMenuItem("SP"));
		mnuSanPham.add(new JMenuItem("SP"));
		
		mnubar.add(mnuSanPham);
		mnubar.add(mnuNhanVien);
		mnubar.add(mnuNhaCungCap);
		mnubar.add(mnuHoaDon);
		mnubar.add(mnuKhachHang);

		setJMenuBar(mnubar);
		//
		
		// code North
		JPanel jp_North = new JPanel();
		jp_North.setBackground(new Color(35, 177, 77));
		
		lb_TieuDe = new JLabel("Goods Store");
		lb_TieuDe.setForeground(Color.white);
		lb_TieuDe.setFont(new Font("Time new roman", Font.BOLD, 40));
		
		jp_North.add(lb_TieuDe);
		this.add(jp_North, BorderLayout.NORTH);
		//
		
		// code West
		
		//
		
		// code Center
		//
		
		// code South
		
		//
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new BanHang_GUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

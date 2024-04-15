package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class BanHang_GUI extends JFrame implements ActionListener {

	private JLabel lb_TieuDe;
	private JMenuBar mnubar;
	private JMenu mnuSanPham;
	private JMenu mnuNhanVien;
	private JMenu mnuNhaCungCap;
	private JMenu mnuHoaDon;
	private JMenu mnuKhachHang;
	private Box jp_Center;
	private Box jp_South;
	private JPanel jp_West;
	private JButton btn_Dangxuat;
	private JPanel jp_North;
	private JPanel jp_ThongTinDangNhap;
	private JLabel lb_MSNV;
	private JLabel lb_TenNhaVien;
	private Box jp_SouthThongTin;
	private DefaultTableModel model;
	private JTable table;

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

		// code North
		jp_North = new JPanel();
		jp_North.setBackground(new Color(35, 177, 77));

		lb_TieuDe = new JLabel("Goods Store");
		lb_TieuDe.setForeground(Color.white);
		lb_TieuDe.setFont(new Font("Time new roman", Font.BOLD, 40));

		jp_North.add(lb_TieuDe);
		this.add(jp_North, BorderLayout.NORTH);

		// code West
		jp_West = new JPanel();

		this.add(jp_West, BorderLayout.WEST);

		// code Center
		jp_Center = Box.createVerticalBox();
		jp_Center.setAlignmentX(SwingConstants.CENTER);
		jp_Center.add(Box.createVerticalStrut(10));
		JLabel lb_banHang = new JLabel("BÁN HÀNG");
		lb_banHang.setFont(new Font("Arial", Font.BOLD, 35));
		lb_banHang.setForeground(Color.BLUE);
		
		String[] colnames = {"a", "b", "c"};
		model = new DefaultTableModel(colnames, 0);
		table = new  JTable(model);
		
		jp_Center.add(lb_banHang);
		jp_Center.add(new JScrollPane(table));
		this.add(jp_Center, BorderLayout.CENTER);

		// code South

		// Code btn_DangXuat
		jp_South = Box.createVerticalBox();

		jp_SouthThongTin = Box.createHorizontalBox();
		jp_SouthThongTin.add(Box.createHorizontalStrut(100));

		ImageIcon dangXuat_icon = new ImageIcon("img/BanHangImg/DangXuatIcon.jpg");
		Image scaled = scaleImage(dangXuat_icon.getImage(), 30, 30);
		btn_Dangxuat = new JButton("Đăng xuất", new ImageIcon(scaled));
		btn_Dangxuat.setFont(new Font("Arial", Font.BOLD, 20));
		jp_SouthThongTin.add(btn_Dangxuat);

		jp_SouthThongTin.add(Box.createHorizontalGlue());

		jp_ThongTinDangNhap = new JPanel();

		lb_MSNV = new JLabel("fdfs");
		lb_MSNV.setFont(new Font("Arial", Font.BOLD, 20));

		lb_TenNhaVien = new JLabel("fsdfs");
		lb_TenNhaVien.setFont(new Font("Arial", Font.BOLD, 20));

		jp_ThongTinDangNhap.add(lb_MSNV);
		jp_ThongTinDangNhap.add(lb_TenNhaVien);

		jp_SouthThongTin.add(jp_ThongTinDangNhap);

		jp_South.add(jp_SouthThongTin);

		jp_South.add(Box.createVerticalStrut(20));
		this.add(jp_South, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	private Image scaleImage(Image image, int w, int h) {
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return scaled;
	}

	public static void main(String[] args) {
		new BanHang_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}

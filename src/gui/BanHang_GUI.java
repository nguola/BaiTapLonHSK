package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.SanPham_DAO;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

public class BanHang_GUI extends JFrame implements ActionListener {

	private TaiKhoan tk;
	private JLabel lb_TieuDe;
	private JMenuBar mnubar;
	private JMenu mnuSanPham;
	private JMenu mnuNhanVien;
	private JMenu mnuNhaCungCap;
	private JMenu mnuHoaDon;
	private JMenu mnuKhachHang;
	private JPanel jp_Center;
	private Box jp_South;
	private JPanel jp_West;
	private JButton btn_Dangxuat;
	private JPanel jp_North;
	private Box jp_ThongTinDangNhap;
	private JLabel lb_MSNV;
	private JLabel lb_TenNhaVien;
	private Box jp_SouthThongTin;
	private DefaultTableModel model;
	private JTable table;
	private JPanel jp_btnFunction;
	private JButton btn_them;
	private JButton btn_xua;
	private JButton btn_xoa;
	private JSplitPane splitpane;
	private JPanel jp_title;
	private Box jp_ThongTinSanPham;
	private DefaultTableModel model_sanPham;
	private JTable table_SanPham;
	SanPham_DAO sanPham_dao= new SanPham_DAO();
	private JPanel jp_tieuDeTrai;
	private JPanel jp_them;
	private JPanel jp_sua;
	private JPanel jp_xoa;

	public BanHang_GUI(TaiKhoan tk) throws HeadlessException {
		super();
		this.tk = tk;
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
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
		jp_West.setLayout(new BorderLayout());
		
		// code Tieu de
		jp_tieuDeTrai = new JPanel();
		JLabel Chon_sp = new JLabel("Chọn sản phẩm");
		Chon_sp.setFont(new Font("Time new roman", Font.BOLD, 30));
		jp_tieuDeTrai.add(Chon_sp);
		jp_West.add(jp_tieuDeTrai, BorderLayout.NORTH);
		
		// code Table SanPham
		String[] colnames_sanPham = {"Mã sản phầm", "Tên sản phầm", "Giá"};
		model_sanPham = new DefaultTableModel(colnames_sanPham, 0);
		table_SanPham = new  JTable(model_sanPham);
		
		ArrayList<SanPham> list_sanPham = sanPham_dao.getalltbSanPham();
		
		for(SanPham sp : list_sanPham) {
			model_sanPham.addRow(new Object[] {sp.getMaSanPham(), sp.getTen(), sp.getGiaSanPham()});
		}
		jp_West.add(new JScrollPane(table_SanPham), BorderLayout.CENTER);
		this.add(jp_West, BorderLayout.WEST);
		
		// code Center
		
		// code Center North
		jp_Center = new JPanel();
		jp_Center.setLayout(new BorderLayout());
		
		jp_title = new JPanel();
		JLabel lb_banHang = new JLabel("BÁN HÀNG");
		lb_banHang.setFont(new Font("Arial", Font.BOLD, 35));
		lb_banHang.setForeground(Color.BLUE);
		jp_title.add(lb_banHang);
		jp_Center.add(jp_title, BorderLayout.NORTH);
		
		// code Splitpane
		String[] colnames = {"a", "b", "c"};
		model = new DefaultTableModel(colnames, 0);
		table = new  JTable(model);
		
		jp_btnFunction = new JPanel();
		jp_them = new JPanel();
		btn_them = new JButton("Thêm");
		btn_them.setFont(new Font("Arial", Font.BOLD, 15));
		btn_them.setPreferredSize(new Dimension(70, 30));
		jp_them.add(btn_them);
		
		jp_sua = new JPanel();
		btn_xua = new JButton("Xửa");
		btn_xua.setFont(new Font("Arial", Font.BOLD, 15));
		btn_xua.setPreferredSize(new Dimension(70, 30));
		jp_sua.add(btn_xua);
		
		jp_xoa = new JPanel();
		btn_xoa = new JButton("Xóa");
		btn_xoa.setFont(new Font("Arial", Font.BOLD, 15));
		btn_xoa.setPreferredSize(new Dimension(70, 30));
		jp_xoa.add(btn_xoa);
		
		jp_btnFunction.add(Box.createVerticalStrut(20));
		jp_btnFunction.add(btn_them);
		jp_btnFunction.add(Box.createVerticalStrut(20));
		jp_btnFunction.add(btn_xua);
		jp_btnFunction.add(Box.createVerticalStrut(20));
		jp_btnFunction.add(btn_xoa);
		 
		jp_Center.add(jp_btnFunction, BorderLayout.WEST);
		jp_Center.add(new JScrollPane(table_SanPham), BorderLayout.CENTER);

		
		this.add(jp_Center, BorderLayout.CENTER);
		// code South

		// Code btn_DangXuat
		jp_South = Box.createVerticalBox();
		jp_South.add(Box.createVerticalStrut(10));

		jp_SouthThongTin = Box.createHorizontalBox();
		jp_SouthThongTin.add(Box.createHorizontalStrut(100));

		ImageIcon dangXuat_icon = new ImageIcon("img/BanHangImg/DangXuatIcon.jpg");
		Image scaled = scaleImage(dangXuat_icon.getImage(), 30, 30);
		btn_Dangxuat = new JButton("Đăng xuất", new ImageIcon(scaled));
		btn_Dangxuat.setFont(new Font("Arial", Font.BOLD, 20));
		jp_SouthThongTin.add(btn_Dangxuat);

		jp_SouthThongTin.add(Box.createHorizontalGlue());

		jp_ThongTinDangNhap = Box.createVerticalBox();

		lb_MSNV = new JLabel("Mã NV: " + tk.getNhanvien().getMaNhanVien());
		lb_MSNV.setFont(new Font("Arial", Font.BOLD, 20));
		
		lb_TenNhaVien = new JLabel(tk.getNhanvien().getTen());
		lb_TenNhaVien.setFont(new Font("Arial", Font.BOLD, 20));
		
		ImageIcon taiKhoan_icon = new ImageIcon("img/BanHangImg/TaiKhoanIcon.png");
		scaled = scaleImage(taiKhoan_icon.getImage(), 35, 35);
		JLabel taiKhoanIcon = new JLabel(new ImageIcon(scaled));

		jp_ThongTinDangNhap.add(lb_MSNV);
		jp_ThongTinDangNhap.add(lb_TenNhaVien);
		jp_ThongTinDangNhap.add(Box.createHorizontalStrut(20));
		jp_SouthThongTin.add(taiKhoanIcon);
		jp_SouthThongTin.add(jp_ThongTinDangNhap);
		jp_SouthThongTin.add(Box.createHorizontalStrut(100));

		jp_South.add(jp_SouthThongTin);

		jp_South.add(Box.createVerticalStrut(10));
		this.add(jp_South, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	private Image scaleImage(Image image, int w, int h) {
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return scaled;
	}

	public static void main(String[] args) {
		new BanHang_GUI(new TaiKhoan(new NhanVien(3000, "Toan Hao", "000000", true, 30000, "Admin")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}

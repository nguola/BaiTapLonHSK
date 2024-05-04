package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import connectDB.ConnectDB;
import dao.SanPham_DAO;
import entity.TaiKhoan;

public class KhungTrang_GUI extends JFrame implements WindowListener, ActionListener{

	private TaiKhoan tk;
	private JLabel lb_TieuDe;
	private JMenuBar mnubar;
	private JMenu mnuSanPham;
	private JMenu mnuNhanVien;
	private JMenu mnuNhaCungCap;
	private JMenu mnuHoaDon;
	private JMenu mnuKhachHang;
	private Box jp_South;
	private JButton btn_Dangxuat;
	private JPanel jp_North;
	private Box jp_ThongTinDangNhap;
	private JLabel lb_MSNV;
	private JLabel lb_TenNhaVien;
	private Box jp_SouthThongTin;
	SanPham_DAO sanPham_dao = new SanPham_DAO();
	private JMenuItem itemBanHang;
	private JMenuItem itemThongTin;
	private JMenuItem itemBaoCao;
	private Panel_QuanLiKhachHang panelQuanLiKhachHang = new Panel_QuanLiKhachHang();
	private Panel_BaoCao panel_BaoCao;
	private Pane_BanHang panel_BanHang;
	private Panel_ThongKe panel_thongKe;
	private JMenuItem itemThongKe;
	private JPanel pnCen = new JPanel();

	public KhungTrang_GUI(TaiKhoan tk) {
		super();
		this.tk = tk;

		setSize(1500, 800);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");

		panel_BanHang = new Pane_BanHang(tk);
		
		this.add(pnCen, BorderLayout.CENTER);
		pnCen.setLayout(new CardLayout());
		pnCen.add(panel_BanHang);
		
		
		// code menu
		mnubar = new JMenuBar();

		mnuNhanVien = new JMenu("Nhân Viên");
		mnuNhanVien.setFont(new Font("Arial", Font.BOLD, 15));
		
		mnuSanPham = new JMenu("Sản Phẩm");
		mnuSanPham.setFont(new Font("Arial", Font.BOLD, 15));

		mnuHoaDon = new JMenu("Hóa đơn");
		mnuHoaDon.setFont(new Font("Arial", Font.BOLD, 15));

		mnuNhaCungCap = new JMenu("Nhà Cung Cấp");
		mnuNhaCungCap.setFont(new Font("Arial", Font.BOLD, 15));

		mnuKhachHang = new JMenu("Khách Hàng");
		mnuKhachHang.setFont(new Font("Arial", Font.BOLD, 15));
		
		itemBanHang = new JMenuItem("Bán hàng");

		mnuNhanVien.add(itemBanHang);
		
		itemThongTin = new JMenuItem("Thông tin");

		mnuNhanVien.add(itemThongTin);
		
		itemThongKe = new JMenuItem("Thống kê");
		
		mnuNhanVien.add(itemThongKe);
		
		itemBaoCao = new JMenuItem("Báo cáo");
		mnuNhanVien.add(itemBaoCao);
		
		mnubar.add(mnuNhanVien);
		mnubar.add(mnuSanPham);
		mnubar.add(mnuNhaCungCap);
		mnubar.add(mnuHoaDon);
		mnubar.add(mnuKhachHang);
		
		itemBanHang.addActionListener(this);
		itemBaoCao.addActionListener(this);
		itemThongKe.addActionListener(this);
		mnuKhachHang.addActionListener(this);

		setJMenuBar(mnubar);

		// code North
		jp_North = new JPanel();
		jp_North.setBackground(new Color(35, 177, 77));

		lb_TieuDe = new JLabel("Goods Store");
		lb_TieuDe.setForeground(Color.white);
		lb_TieuDe.setFont(new Font("Time new roman", Font.BOLD, 40));

		jp_North.add(lb_TieuDe);
		this.add(jp_North, BorderLayout.NORTH);
		
		
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
		this.addWindowListener(this);
	}
	
	public void switchPage(JPanel panel) {
		pnCen.removeAll();
        pnCen.add(panel);
        pnCen.revalidate();
        pnCen.repaint();
	}

	private Image scaleImage(Image image, int w, int h) {
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return scaled;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance().disconnect();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(itemBanHang)) {
			panel_BanHang = new Pane_BanHang(tk); 
			switchPage(panel_BanHang);
		} else if (src.equals(itemThongKe)) {
			panel_thongKe = new Panel_ThongKe(tk);
			switchPage(panel_thongKe);
		} else if (src.equals(itemBaoCao)) {
			panel_BaoCao = new Panel_BaoCao(tk);
			switchPage(panel_BaoCao);
		} else if (src.equals(mnuKhachHang)) {
			panelQuanLiKhachHang = new Panel_QuanLiKhachHang();
			switchPage(panelQuanLiKhachHang);
		}
	}
	
}

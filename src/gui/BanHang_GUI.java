package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.SanPham_DAO;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

public class BanHang_GUI extends JFrame implements ActionListener, TableModelListener {

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
	private DefaultTableModel model_HoaDon;
	private Box jp_btnFunction;
	private JButton btn_them;
	private JPanel jp_title;
	private DefaultTableModel model_sanPham;
	private JTable table_SanPham;
	SanPham_DAO sanPham_dao = new SanPham_DAO();
	private JPanel jp_tieuDeTrai;
	private JPanel jp_them;
	private JPanel jp_xoa;
	private JTable table_HoaDon;
	private Box jp_loc;
	private JLabel lb_Msp;
	private JTextField tf_Msp;
	private JLabel lb_tensp;
	private JTextField tf_tensp;
	private JComboBox<String> cb_loai;
	private JLabel lb_loai;
	private JButton btn_loc;
	private JScrollPane scroll_tableSp;
	private JLabel lb_donvi;
	private JTextField tf_donvi;
	private JPanel jp_thongtin;
	private JScrollPane scroll_hoaDon;
	private JPanel maKH;
	private JLabel lb_maKH;
	private JTextField tf_maKH;
	private JPanel tenKH;
	private JLabel lb_tenKH;
	private JTextField tf_tenKH;
	private JPanel dtKH;
	private JLabel lb_dtKH;
	private JTextField tf_dtKH;
	private JPanel loaiKH;
	private JLabel lb_loaiKH;
	private JTextField tf_loaiKH;
	private Box jp_lapHoaDon;
	private Box jp_content_lapHoaDon;
	private JLabel lb_Tongthanhtien;
	private JPanel Tongthanhtien;
	private JPanel giamgia;
	private JLabel lb_giamgia;
	private JTextField tf_giamgia;
	private JPanel jp_LapHoaDon;
	private JButton btn_LapHoaDon;
	private JButton btn_xoa;
	private JPanel jp_InHoaDon;
	private JButton btn_InHoaDon;
	private JPanel jp_btnLoc;
	private JTextField tf_Tongthanhtien;
	private JPanel jp_banHang;
	private Panel_QuanLiKhachHang panelQuanLiKhachHang;

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
		this.setResizable(false);
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
//		panelQuanLiKhachHang = new Panel_QuanLiKhachHang();
//		this.add(panelQuanLiKhachHang, BorderLayout.CENTER);
//		panelQuanLiKhachHang.setVisible(false); // Ẩn Panel_QuanLiKhachHang ban đầu
//		mnuKhachHang.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// Hiển thị Panel_QuanLiKhachHang và ẩn các panel khác
//		        panelQuanLiKhachHang.setVisible(true);
//		        // Ẩn các panel khác
//		        jp_Center.setVisible(false); 
//				CardLayout cardLayout = (CardLayout) jp_banHang.getLayout();
//				cardLayout.show(jp_banHang, "Panel_QuanLiKhachHang");
//			}
//		});

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
		jp_banHang = new JPanel(new CardLayout());
		jp_West = new JPanel(new BorderLayout());

		// code Tieu de
		jp_tieuDeTrai = new JPanel();
		JLabel Chon_sp = new JLabel("Chọn sản phẩm");
		Chon_sp.setFont(new Font("Time new roman", Font.BOLD, 30));
		jp_tieuDeTrai.add(Chon_sp);
		jp_West.add(jp_tieuDeTrai, BorderLayout.NORTH);

		// code lọc sản phầm
		jp_loc = Box.createVerticalBox();

		JPanel msp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_Msp = new JLabel("Mã sản phẩm:");
		tf_Msp = new JTextField(30);
		msp.add(Box.createHorizontalStrut(20));
		msp.add(lb_Msp);
		msp.add(Box.createHorizontalStrut(20));
		msp.add(tf_Msp);
		jp_loc.add(msp);

		JPanel tensp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_tensp = new JLabel("Tên sản phẩm:");
		tf_tensp = new JTextField(30);
		tensp.add(Box.createHorizontalStrut(20));
		tensp.add(lb_tensp);
		tensp.add(Box.createHorizontalStrut(15));
		tensp.add(tf_tensp);
		jp_loc.add(tensp);

		JPanel donvi = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_donvi = new JLabel("Đơn vị tính:");
		tf_donvi = new JTextField(30);
		donvi.add(Box.createHorizontalStrut(20));
		donvi.add(lb_donvi);
		donvi.add(Box.createHorizontalStrut(30));
		donvi.add(tf_donvi);
		jp_loc.add(donvi);

		JPanel loaisanpham = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_loai = new JLabel("Loại sản phẩm:");
		cb_loai = new JComboBox<String>();
		cb_loai.setPreferredSize(new Dimension(300, 25));
		ArrayList<String> list = sanPham_dao.getAllLoaiSP();
		for (String loai : list) {
			cb_loai.addItem(loai);
		}

		loaisanpham.add(Box.createHorizontalStrut(20));
		loaisanpham.add(lb_loai);
		loaisanpham.add(Box.createHorizontalStrut(14));
		loaisanpham.add(cb_loai);
		jp_loc.add(loaisanpham);

		jp_btnLoc = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btn_loc = new JButton("Lọc");
		btn_loc.setFont(new Font("Arial", Font.BOLD, 15));
		btn_loc.setPreferredSize(new Dimension(80, 30));
		jp_btnLoc.add(Box.createHorizontalStrut(20));
		jp_btnLoc.add(btn_loc);
		jp_loc.add(jp_btnLoc);

		jp_loc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Lọc sản phẩm"));

		jp_West.add(jp_loc, BorderLayout.CENTER);

		// code Table SanPham
		String[] colnames_sanPham = { "Mã sản phầm", "Tên sản phầm", "Loại", "Giá", "Đơn vị" };
		model_sanPham = new DefaultTableModel(colnames_sanPham, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_SanPham = new JTable(model_sanPham);

		ArrayList<SanPham> list_sanPham = sanPham_dao.getalltbSanPham();

		for (SanPham sp : list_sanPham) {
			model_sanPham.addRow(new Object[] { sp.getMaSanPham(), sp.getTen(), sp.getLoaiSanPham(), sp.getGiaSanPham(),
					sp.getDonVi() });
		}

		scroll_tableSp = new JScrollPane(table_SanPham);
		scroll_tableSp.setPreferredSize(new Dimension(500, 330));
		jp_West.add(scroll_tableSp, BorderLayout.SOUTH);
		jp_banHang.add(jp_West, BorderLayout.WEST);

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

		// code chọn tác vụ
		jp_btnFunction = Box.createVerticalBox();

		jp_them = new JPanel();
		btn_them = new JButton("Thêm");
		btn_them.setFont(new Font("Arial", Font.BOLD, 15));
		btn_them.setPreferredSize(new Dimension(140, 40));
		jp_them.add(btn_them);
		jp_btnFunction.add(jp_them);

		jp_xoa = new JPanel();
		btn_xoa = new JButton("Xóa");
		btn_xoa.setFont(new Font("Arial", Font.BOLD, 15));
		btn_xoa.setPreferredSize(new Dimension(140, 40));
		jp_xoa.add(btn_xoa);
		jp_btnFunction.add(jp_xoa);

		jp_LapHoaDon = new JPanel();
		btn_LapHoaDon = new JButton("Lập hóa đơn");
		btn_LapHoaDon.setFont(new Font("Arial", Font.BOLD, 15));
		btn_LapHoaDon.setPreferredSize(new Dimension(140, 40));
		jp_LapHoaDon.add(btn_LapHoaDon);
		jp_btnFunction.add(jp_LapHoaDon);

		jp_InHoaDon = new JPanel();
		btn_InHoaDon = new JButton("In hóa đơn");
		btn_InHoaDon.setFont(new Font("Arial", Font.BOLD, 15));
		btn_InHoaDon.setPreferredSize(new Dimension(140, 40));
		jp_InHoaDon.add(btn_InHoaDon);
		jp_btnFunction.add(jp_InHoaDon);
		
		jp_btnFunction.add(Box.createVerticalStrut(300));

		jp_btnFunction
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Chọn tác vụ"));
		jp_Center.add(jp_btnFunction, BorderLayout.WEST);

		// code thông tin hóa đơn
		jp_thongtin = new JPanel();
		jp_thongtin.setLayout(new BorderLayout());

		Box NhapThongTin = Box.createVerticalBox();

		maKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_maKH = new JLabel("Mã khách hàng:");
		tf_maKH = new JTextField(50);
		maKH.add(Box.createHorizontalStrut(100));
		maKH.add(lb_maKH);
		maKH.add(Box.createHorizontalStrut(20));
		maKH.add(tf_maKH);
		NhapThongTin.add(maKH);

		tenKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_tenKH = new JLabel("Tên khách hàng:");
		tf_tenKH = new JTextField(50);
		tenKH.add(Box.createHorizontalStrut(100));
		tenKH.add(lb_tenKH);
		tenKH.add(Box.createHorizontalStrut(14));
		tenKH.add(tf_tenKH);
		NhapThongTin.add(tenKH);

		dtKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_dtKH = new JLabel("Số điện thoại:");
		tf_dtKH = new JTextField(50);
		dtKH.add(Box.createHorizontalStrut(100));
		dtKH.add(lb_dtKH);
		dtKH.add(Box.createHorizontalStrut(30));
		dtKH.add(tf_dtKH);
		NhapThongTin.add(dtKH);

		loaiKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_loaiKH = new JLabel("Loại khách hàng:");
		tf_loaiKH = new JTextField(50);
		loaiKH.add(Box.createHorizontalStrut(100));
		loaiKH.add(lb_loaiKH);
		loaiKH.add(Box.createHorizontalStrut(13));
		loaiKH.add(tf_loaiKH);
		NhapThongTin.add(loaiKH);

		NhapThongTin.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Nhập thông tin"));
		jp_thongtin.add(NhapThongTin, BorderLayout.NORTH);

		// Code table hóa đơn
		String[] colnames_HoaDon = { "Mã sản phầm", "Tên sản phầm", "Đơn vị", "Giá", "Số lượng", "Thành tiền"};
		model_HoaDon = new DefaultTableModel(colnames_HoaDon, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 4) return true;
				return false;
			}
		};
		table_HoaDon = new JTable(model_HoaDon);
		scroll_hoaDon = new JScrollPane(table_HoaDon);
		jp_thongtin.add(scroll_hoaDon, BorderLayout.CENTER);

		jp_Center.add(jp_thongtin, BorderLayout.CENTER);
		jp_banHang.add(jp_Center, BorderLayout.CENTER);

		// code lập hóa đơn
		jp_lapHoaDon = Box.createHorizontalBox();
		jp_lapHoaDon.add(Box.createHorizontalStrut(380));

		// code thông tin hóa đơn
		jp_content_lapHoaDon = Box.createVerticalBox();

		Tongthanhtien = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_Tongthanhtien = new JLabel("Tổng thành tiền:");
		tf_Tongthanhtien = new JTextField(30);
		tf_Tongthanhtien.setEditable(false);
		Tongthanhtien.add(lb_Tongthanhtien);
		Tongthanhtien.add(Box.createHorizontalGlue());
		Tongthanhtien.add(tf_Tongthanhtien);
		jp_content_lapHoaDon.add(Tongthanhtien);

		giamgia = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_giamgia = new JLabel("Giảm giá:");
		tf_giamgia = new JTextField(30);
		tf_giamgia.setEditable(false);
		giamgia.add(lb_giamgia);
		giamgia.add(Box.createHorizontalStrut(40));
		giamgia.add(tf_giamgia);
		jp_content_lapHoaDon.add(giamgia);
		jp_content_lapHoaDon
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Lập hóa đơn"));

		jp_lapHoaDon.add(jp_content_lapHoaDon);
		jp_thongtin.add(jp_lapHoaDon, BorderLayout.SOUTH);
		
		this.add(jp_banHang, BorderLayout.CENTER);
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

		btn_them.addActionListener(this);
		btn_xoa.addActionListener(this);
		model_HoaDon.addTableModelListener(this);
		
		this.setVisible(true);
	}

	private Image scaleImage(Image image, int w, int h) {
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return scaled;
	}
	
	public static void main(String[] args) {
		new BanHang_GUI(new TaiKhoan(new NhanVien(3000, "Toan Hao", "000000", true, 30000, "Admin")));
	}
	
	public void update_TableHoaDon(SanPham sp) {
		model_HoaDon.addRow(
				new Object[] { sp.getMaSanPham(), sp.getTen(), sp.getDonVi(), sp.getGiaSanPham(), 1 , sp.getGiaSanPham()});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btn_them)) {
			int selected_row = table_SanPham.getSelectedRow();
			int maSP = Integer.parseInt(model_sanPham.getValueAt(selected_row, 0).toString());
			SanPham sp = sanPham_dao.getSanPhamTheoMa(maSP);
			update_TableHoaDon(sp);
		}
		else if(o.equals(btn_xoa)) {
			int selected_row = table_HoaDon.getSelectedRow();
			model_HoaDon.removeRow(selected_row);
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		if(e.getType() == TableModelEvent.UPDATE) {
			int row = e.getFirstRow();
			try {
				double gia = Double.parseDouble(table_HoaDon.getValueAt(row, 3).toString());
				int soLuong = Integer.parseInt(table_HoaDon.getValueAt(row, 4).toString());
				Object Thanhtien = gia * soLuong;
				DefaultTableModel md = (DefaultTableModel) table_HoaDon.getModel();
				md.setValueAt(Thanhtien, row, 5);
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		int numRow = model_HoaDon.getRowCount();

		double TongTien = 0;

		for (int i = 0; i < numRow; i++) {
			
			TongTien += Double.parseDouble(table_HoaDon.getValueAt(i, 5).toString());
		}

		tf_Tongthanhtien.setText(String.valueOf(TongTien));
	}
}

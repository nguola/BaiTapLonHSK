package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;

public class Pane_BanHang extends JPanel implements ActionListener, TableModelListener, DocumentListener {
	private JPanel jp_tieuDeWest;
	private Box jp_loc;
	private JTextField tf_Msp;
	private JLabel lb_Msp;
	private JLabel lb_tensp;
	private JTextField tf_tensp;
	private JComboBox<String> cbo_donVi;
	private JLabel lb_donvi;
	private JLabel lb_loai;
	private JComboBox<String> cb_loai;
	private JTable table_SanPham;
	private JScrollPane scroll_tableSp;
	private JPanel jp_xoa;
	private JPanel jp_them;
	private JButton btn_them;
	private JButton btn_xoa;
	private DefaultTableModel model_sanPham;
	private JPanel jp_Center;
	private JPanel jp_title;
	private Box jp_btnFunction;
	private JPanel jp_LapHoaDon;
	private JButton btn_LapHoaDon;
	private JPanel jp_thongtin;
	private JPanel maKH;
	private JLabel lb_maKH;
	private JTextField tf_maKH;
	private JPanel tenKH;
	private JTextField tf_tenKH;
	private JLabel lb_tenKH;
	private JPanel dtKH;
	private JLabel lb_dtKH;
	private JTextField tf_dtKH;
	private JPanel loaiKH;
	private JLabel lb_loaiKH;
	private JTextField tf_loaiKH;
	private DefaultTableModel model_HoaDon;
	private JTable table_HoaDon;
	private JScrollPane scroll_hoaDon;
	private Box jp_lapHoaDon;
	private Box jp_content_lapHoaDon;
	private JPanel Tongthanhtien;
	private JLabel lb_Tongthanhtien;
	private JTextField tf_Tongthanhtien;
	private JPanel giamgia;
	private JLabel lb_giamgia;
	private JTextField tf_giamgia;
	private SanPham_DAO sanPham_dao = new SanPham_DAO();
	private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
	private NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
	private HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
	private ChiTietHoaDon_DAO chiTietHoaDon_dao = new ChiTietHoaDon_DAO();
	private JButton btn_hienTatCa;
	private JPanel pane_trangThaiKhachHang;
	private JRadioButton radio_moi;
	private JRadioButton radio_cu;
	private ButtonGroup radio_group;
	private JPopupMenu popupMenu_tableSanPham;
	private JMenuItem menuItemThem;
	private JMenuItem menuItemThongTin;
	private JPopupMenu popupMenu_tableHoaDon;
	private JMenuItem menuItemXoa;
	private JPanel diaChiKH;
	private JLabel lb_diaChiKH;
	private JTextField tf_diaChiKH;
	private JRadioButton radio_rong;
	private NhanVien nv;
	private double TongTien;
	
	public Pane_BanHang(TaiKhoan tk) {
		// Lấy nhân viên đang làm việc từ tài khoản đang đăng nhập
		nv = nhanVien_dao.getNhanVienTheoMaNV(tk.getNhanvien().getMaNhanVien());
		
		// Cấu hình cho trang
		setLayout(new BorderLayout());
		setSize(800, 600);

		// Code tiêu đề cho phần west
		JPanel jp_West = new JPanel(new BorderLayout());
		jp_tieuDeWest = new JPanel();
		JLabel Chon_sp = new JLabel("Chọn sản phẩm");
		Chon_sp.setFont(new Font("Time new roman", Font.BOLD, 30));
		jp_tieuDeWest.add(Chon_sp);
		jp_West.add(jp_tieuDeWest, BorderLayout.NORTH);

		// Code phần lọc sản phẩm
		jp_loc = Box.createVerticalBox();

		// Lọc theo mã sản phẩm
		JPanel msp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_Msp = new JLabel("Mã sản phẩm:");
		tf_Msp = new JTextField(30);
		msp.add(Box.createHorizontalStrut(60));
		msp.add(lb_Msp);
		msp.add(Box.createHorizontalStrut(20));
		msp.add(tf_Msp);
		jp_loc.add(msp);

		// Lọc theo tên sản phẩm
		JPanel tensp = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_tensp = new JLabel("Tên sản phẩm:");
		tf_tensp = new JTextField(30);
		tensp.add(Box.createHorizontalStrut(60));
		tensp.add(lb_tensp);
		tensp.add(Box.createHorizontalStrut(15));
		tensp.add(tf_tensp);
		jp_loc.add(tensp);

		// Lọc theo đơn vị tính tiền
		JPanel donvi = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_donvi = new JLabel("Đơn vị tính:");

		// Tạo 1 combobox để giữ các dơn vị sản phẩm
		cbo_donVi = new JComboBox<String>();
		cbo_donVi.setPreferredSize(new Dimension(302, 25));
		cbo_donVi.addItem("Tất cả");

		// Tạo 1 Arayllist để lưu các loại sản phẩm được trả về từ hàm getAllDonViSP
		ArrayList<String> list_donViSP = sanPham_dao.getAllDonViSP();

		// truyền list đơn vị vào trong combobox
		for (String donVi : list_donViSP) {
			cbo_donVi.addItem(donVi);
		}

		donvi.add(Box.createHorizontalStrut(60));
		donvi.add(lb_donvi);
		donvi.add(Box.createHorizontalStrut(32));
		donvi.add(cbo_donVi);
		jp_loc.add(donvi);

		// Lọc theo loại sản phẩm
		JPanel loaisanpham = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_loai = new JLabel("Loại sản phẩm:");

		// Tạo 1 combobox để giữ các loại sản phẩm
		cb_loai = new JComboBox<String>();
		cb_loai.setPreferredSize(new Dimension(302, 25));
		cb_loai.addItem("Tất cả");

		// Tạo 1 Arayllist để lưu các loại sản phẩm được trả về từ hàm getAllLoaiSP
		ArrayList<String> list_loaiSP = sanPham_dao.getAllLoaiSP();

		// truyền list loại vào trong combobox
		for (String loai : list_loaiSP) {
			cb_loai.addItem(loai);
		}

		loaisanpham.add(Box.createHorizontalStrut(60));
		loaisanpham.add(lb_loai);
		loaisanpham.add(Box.createHorizontalStrut(14));
		loaisanpham.add(cb_loai);
		jp_loc.add(loaisanpham);

		// Hiện tất cả các sản phẩm
		JPanel pane_btnHienTatca = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btn_hienTatCa = new JButton("Hiện tất cả");
		btn_hienTatCa.setFont(new Font("Arial", Font.BOLD, 15));
		btn_hienTatCa.setPreferredSize(new Dimension(120, 25));
		pane_btnHienTatca.add(Box.createHorizontalStrut(60));
		pane_btnHienTatca.add(btn_hienTatCa);
		jp_loc.add(pane_btnHienTatca);

		jp_loc.add(Box.createVerticalStrut(10));

		// Thêm title border cho phần lọc sản phẩm
		jp_loc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Lọc sản phẩm"));

		jp_West.add(jp_loc, BorderLayout.CENTER);

		// Code Table SanPham chứa danh sách các sản phẩm

		// Tạo một mảng chứa các
		String[] colnames_sanPham = { "Mã sản phầm", "Tên sản phầm", "Loại", "Giá", "Đơn vị", "Số lượng tồn kho" };

		// Tạo một DefaultTableModel cho bảng sản phẩm
		model_sanPham = new DefaultTableModel(colnames_sanPham, 0) {
			// Dùng hàm isCellEditable để chỉ định các trường được chỉnh sửa trong trường
			// hợp này là không cho phép
			// chỉnh sửa bất cứ cột nào
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 5)
					return true;
				return false;
			}
		};

		// Tạo bảng bằng model vừa tạo
		table_SanPham = new JTable(model_sanPham);

		// Sử lý sự kiện hiển thị JPopUpMenu khi click chuột phải vào 1 field trong
		// table sản phẩm
		table_SanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Kiểm tra người dùng có nhấn vào 1 field trong table hay không
				if (SwingUtilities.isRightMouseButton(e) && table_SanPham.getSelectedRow() != -1) {
					popupMenu_tableSanPham.show(table_SanPham, e.getX(), e.getY());
				}
			}
		});

		// Đổ dữ liệu từ list sản phẩm vào bảng sản phẩm
		update_TableSanPham(sanPham_dao.getalltbSanPham());

		// Tạo một scoll pane cho bảng sản phẩm
		scroll_tableSp = new JScrollPane(table_SanPham);
		scroll_tableSp.setPreferredSize(new Dimension(600, 330));
		jp_West.add(scroll_tableSp, BorderLayout.SOUTH);

		this.add(jp_West, BorderLayout.WEST);

		// Code title cho phần Center
		jp_Center = new JPanel(new BorderLayout());
		jp_title = new JPanel();
		JLabel lb_titleCenter = new JLabel("BÁN HÀNG");
		lb_titleCenter.setFont(new Font("Arial", Font.BOLD, 35));
		lb_titleCenter.setForeground(Color.BLUE);
		jp_title.add(Box.createHorizontalStrut(200));
		jp_title.add(lb_titleCenter);
		jp_Center.add(jp_title, BorderLayout.NORTH);

		// Code các nút chức năng
		jp_btnFunction = Box.createVerticalBox();

		// Code nút thêm
		jp_them = new JPanel();
		btn_them = new JButton("Thêm");
		btn_them.setFont(new Font("Arial", Font.BOLD, 15));
		btn_them.setPreferredSize(new Dimension(140, 40));
		jp_them.add(btn_them);
		jp_btnFunction.add(jp_them);

		// Code nút xóa
		jp_xoa = new JPanel();
		btn_xoa = new JButton("Xóa");
		btn_xoa.setFont(new Font("Arial", Font.BOLD, 15));
		btn_xoa.setPreferredSize(new Dimension(140, 40));
		jp_xoa.add(btn_xoa);
		jp_btnFunction.add(jp_xoa);

		// Code nút lập hóa đơn
		jp_LapHoaDon = new JPanel();
		btn_LapHoaDon = new JButton("Lập hóa đơn");
		btn_LapHoaDon.setFont(new Font("Arial", Font.BOLD, 15));
		btn_LapHoaDon.setPreferredSize(new Dimension(140, 40));
		jp_LapHoaDon.add(btn_LapHoaDon);
		jp_btnFunction.add(jp_LapHoaDon);

		jp_btnFunction.add(Box.createVerticalStrut(300));

		jp_btnFunction
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Chọn tác vụ"));
		jp_Center.add(jp_btnFunction, BorderLayout.WEST);

		// Code điền thông tin khách hàng
		jp_thongtin = new JPanel();
		jp_thongtin.setLayout(new BorderLayout());

		Box NhapThongTin = Box.createVerticalBox();

		// Điền mã khách hàng
		maKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_maKH = new JLabel("Mã khách hàng:");
		tf_maKH = new JTextField(30);
		tf_maKH.setEditable(false);
		maKH.add(Box.createHorizontalStrut(100));
		maKH.add(lb_maKH);
		maKH.add(Box.createHorizontalStrut(20));
		maKH.add(tf_maKH);
		NhapThongTin.add(maKH);

		// Điền tên khách hàng
		tenKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_tenKH = new JLabel("Tên khách hàng:");
		tf_tenKH = new JTextField(30);
		tf_tenKH.setEditable(false);
		tenKH.add(Box.createHorizontalStrut(100));
		tenKH.add(lb_tenKH);
		tenKH.add(Box.createHorizontalStrut(14));
		tenKH.add(tf_tenKH);
		NhapThongTin.add(tenKH);

		// Điền số điện thoại khách hàng
		dtKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_dtKH = new JLabel("Số điện thoại:");
		tf_dtKH = new JTextField(30);
		tf_dtKH.setEditable(false);
		dtKH.add(Box.createHorizontalStrut(100));
		dtKH.add(lb_dtKH);
		dtKH.add(Box.createHorizontalStrut(30));
		dtKH.add(tf_dtKH);
		NhapThongTin.add(dtKH);

		// Điền địa chỉ khách hàng
		diaChiKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_diaChiKH = new JLabel("Địa chỉ:");
		tf_diaChiKH = new JTextField(30);
		tf_diaChiKH.setEditable(false);
		diaChiKH.add(Box.createHorizontalStrut(100));
		diaChiKH.add(lb_diaChiKH);
		diaChiKH.add(Box.createHorizontalStrut(66));
		diaChiKH.add(tf_diaChiKH);
		NhapThongTin.add(diaChiKH);

		// Điền loại khách hàng
		loaiKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_loaiKH = new JLabel("Loại khách hàng:");
		tf_loaiKH = new JTextField(30);
		tf_loaiKH.setEditable(false);
		loaiKH.add(Box.createHorizontalStrut(100));
		loaiKH.add(lb_loaiKH);
		loaiKH.add(Box.createHorizontalStrut(13));
		loaiKH.add(tf_loaiKH);
		NhapThongTin.add(loaiKH);

		pane_trangThaiKhachHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
		radio_moi = new JRadioButton("Khách hàng mới");
		radio_cu = new JRadioButton("Khách hàng cũ");
		radio_rong = new JRadioButton("Rỗng");
		radio_rong.setSelected(true);

		radio_group = new ButtonGroup();
		radio_group.add(radio_moi);
		radio_group.add(radio_cu);
		radio_group.add(radio_rong);
		pane_trangThaiKhachHang.add(Box.createHorizontalStrut(100));
		pane_trangThaiKhachHang.add(radio_moi);
		pane_trangThaiKhachHang.add(radio_cu);
		pane_trangThaiKhachHang.add(radio_rong);
		NhapThongTin.add(pane_trangThaiKhachHang);

		// Set border cho panel nhập thông tin
		NhapThongTin.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Nhập thông tin"));
		jp_thongtin.add(NhapThongTin, BorderLayout.NORTH);

		// Code table hóa đơn chứa các sản phẩm khách hàng chọn

		// Tạo một mảng chứa các
		String[] colnames_HoaDon = { "Mã sản phầm", "Tên sản phầm", "Đơn vị", "Giá", "Số lượng", "Thành tiền" };

		// Tạo model hóa đơn cho bảng hóa đơn và set chỉ được chỉnh sửa cột thứ 4(số
		// lượng)
		model_HoaDon = new DefaultTableModel(colnames_HoaDon, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Tạo table hóa đơn bằng model mới tạo
		table_HoaDon = new JTable(model_HoaDon);

		// Sử lý sự kiện hiển thị JPopUpMenu khi click chuột phải vào 1 field trong
		// table hóa đơn
		table_HoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Kiểm tra người dùng có nhấn vào 1 field trong table hay không
				if (SwingUtilities.isRightMouseButton(e) && table_HoaDon.getSelectedRow() != -1) {
					popupMenu_tableHoaDon.show(table_HoaDon, e.getX(), e.getY());
				}
			}
		});

		// Tạo một scroll pane cho table hóa đơn
		scroll_hoaDon = new JScrollPane(table_HoaDon);
		jp_thongtin.add(scroll_hoaDon, BorderLayout.CENTER);

		jp_Center.add(jp_thongtin, BorderLayout.CENTER);

		// Code thông tin lập hóa đơn
		jp_lapHoaDon = Box.createHorizontalBox();
		jp_lapHoaDon.add(Box.createHorizontalStrut(380));

		// Code thông tin hóa đơn
		jp_content_lapHoaDon = Box.createVerticalBox();

		Tongthanhtien = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_Tongthanhtien = new JLabel("Tổng thành tiền:");
		tf_Tongthanhtien = new JTextField(20);
		tf_Tongthanhtien.setEditable(false);
		Tongthanhtien.add(lb_Tongthanhtien);
		Tongthanhtien.add(Box.createHorizontalGlue());
		Tongthanhtien.add(tf_Tongthanhtien);
		jp_content_lapHoaDon.add(Tongthanhtien);

		giamgia = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_giamgia = new JLabel("Giảm giá:");
		tf_giamgia = new JTextField(20);
		tf_giamgia.setEditable(false);
		giamgia.add(lb_giamgia);
		giamgia.add(Box.createHorizontalStrut(40));
		giamgia.add(tf_giamgia);
		jp_content_lapHoaDon.add(giamgia);
		jp_content_lapHoaDon
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Lập hóa đơn"));

		jp_lapHoaDon.add(jp_content_lapHoaDon);
		jp_thongtin.add(jp_lapHoaDon, BorderLayout.SOUTH);

		this.add(jp_Center, BorderLayout.CENTER);

		// Tạo JPopupMenu gồm 2 phần tử là thêm và thông tin sản phẩm cho table sản phẩm
		popupMenu_tableSanPham = new JPopupMenu();
		menuItemThem = new JMenuItem("Thêm");
		menuItemThongTin = new JMenuItem("Thông tin");
		popupMenu_tableSanPham.add(menuItemThem);
		popupMenu_tableSanPham.add(menuItemThongTin);

		// Tạo JPopupMenu gồm phần tử xóa cho table hóa đơn
		popupMenu_tableHoaDon = new JPopupMenu();
		menuItemXoa = new JMenuItem("Xóa");
		popupMenu_tableHoaDon.add(menuItemXoa);

		// add sự kiện
		btn_them.addActionListener(this);
		menuItemThem.addActionListener(this);
		btn_xoa.addActionListener(this);
		menuItemXoa.addActionListener(this);
		model_HoaDon.addTableModelListener(this);
		cb_loai.addActionListener(this);
		cbo_donVi.addActionListener(this);
		tf_tensp.getDocument().addDocumentListener(this);
		tf_Msp.addActionListener(this);
		btn_hienTatCa.addActionListener(this);
		radio_cu.addActionListener(this);
		radio_moi.addActionListener(this);
		radio_rong.addActionListener(this);
		tf_maKH.addActionListener(this);
		tf_tenKH.addActionListener(this);
		tf_dtKH.addActionListener(this);
		btn_LapHoaDon.addActionListener(this);
	}

	public void update_TableSanPham(ArrayList<SanPham> list_sanPham) {
		for (SanPham sp : list_sanPham) {
			model_sanPham.addRow(new Object[] { sp.getMaSanPham(), sp.getTen(), sp.getLoaiSanPham(), sp.getGiaSanPham(),
					sp.getDonVi(), sp.getSoLuongTonKho() });
		}
	}

	public void update_TableHoaDon(SanPham sp, int soLuong) {
		model_HoaDon.addRow(new Object[] { sp.getMaSanPham(), sp.getTen(), sp.getDonVi(), sp.getGiaSanPham(), soLuong,
				sp.getGiaSanPham() * soLuong });
	}

	public ArrayList<SanPham> Loc_SanPham(String ma, String ten, String loai, String donVi) {
		ArrayList<SanPham> list = new ArrayList<SanPham>();

		int maSp = ((ma.trim().isEmpty()) ? -1 : Integer.parseInt(ma));
		String loaiSP = ((loai.equalsIgnoreCase("Tất cả")) ? "" : loai);
		String donViTinh = ((donVi.equalsIgnoreCase("Tất cả")) ? "" : donVi);

		list = sanPham_dao.Loc_SanPham(maSp, ten, loaiSP, donViTinh);
		return list;
	}

	public int kiemTraThem(int maSP) {
		int rowIndex = -1;
		for (int i = 0; i < table_HoaDon.getRowCount(); i++) {
			int ma = Integer.parseInt(table_HoaDon.getValueAt(i, 0).toString());
			if (ma == maSP) {
				rowIndex = i;
				break;
			}
		}
		return rowIndex;
	}

	public void themHoaDonSQL() {
		int maNhanVien = nv.getMaNhanVien();
		int maKhachHang;
		Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();

        // Chuyển đổi thành java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
		
		if(radio_cu.isSelected()) {
			maKhachHang = Integer.parseInt(tf_maKH.getText());
		}
		else if(radio_moi.isSelected()) {
			khachHang_DAO.createKhachHangMoi(tf_tenKH.getText(), tf_dtKH.getText(), tf_diaChiKH.getText(), tf_loaiKH.getText());
			maKhachHang = khachHang_DAO.getMaKHVuaTao();
			
		}else {
			maKhachHang = -1;
		}
		
		System.out.println(maKhachHang);
		
		hoaDon_dao.create(maKhachHang, maNhanVien, sqlDate, TongTien);
		int maHD = hoaDon_dao.getMaHDVuaTao();
		System.out.println(maHD);
		
		int soLuongMoi;
		
		for(int i = 0; i <  model_HoaDon.getRowCount(); i++) {
			chiTietHoaDon_dao.create(maHD, (int)table_HoaDon.getValueAt(i, 0), (Double)table_HoaDon.getValueAt(i, 5), (int)table_HoaDon.getValueAt(i, 4));
			sanPham_dao.updateSoLuong((int)table_HoaDon.getValueAt(i, 0), (int)table_HoaDon.getValueAt(i, 4));
		}
		
		model_HoaDon.setRowCount(0);
		model_sanPham.setRowCount(0);
		update_TableSanPham(sanPham_dao.getalltbSanPham());
		
		JOptionPane.showMessageDialog(this, "Lập hóa đơn thành công");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btn_them) || o.equals(menuItemThem)) {
			int selected_row = table_SanPham.getSelectedRow();
			if (selected_row == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm muốn thêm");
			} else {
				try {
					int maSP = Integer.parseInt(model_sanPham.getValueAt(selected_row, 0).toString());
					int soLuong = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng muốn đặt"));
					if (soLuong > Integer.parseInt(table_SanPham.getValueAt(selected_row, 5).toString())) {
						JOptionPane.showMessageDialog(this, "Số lượng đặt không được lớn hơn số lượng tồn kho!!");
					} else {
						SanPham sp = sanPham_dao.getSanPhamTheoMa(maSP);
						int rowIndex = kiemTraThem(maSP);
						if (rowIndex == -1) {
							update_TableHoaDon(sp, soLuong);
						} else {
							int soLuongMoi = soLuong
									+ Integer.parseInt(table_HoaDon.getValueAt(rowIndex, 4).toString());
							if (soLuongMoi > Integer.parseInt(table_SanPham.getValueAt(selected_row, 5).toString())) {
								JOptionPane.showMessageDialog(this,
										"Số lượng đặt không được lớn hơn số lượng tồn kho!!");
							} else {
								Object newSoLuong = soLuongMoi;
								double thanhTienMoi = sp.getGiaSanPham() * soLuongMoi;
								Object newThanhTien = thanhTienMoi;

								model_HoaDon.setValueAt(newSoLuong, rowIndex, 4);
								model_HoaDon.setValueAt(newThanhTien, rowIndex, 5);
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Số lượng là số");
				}
			}
		} else if (o.equals(btn_xoa) || o.equals(menuItemXoa)) {
			int selected_row = table_HoaDon.getSelectedRow();
			if (selected_row == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn dòng muốn xóa");
			} else {
				int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa!!", "Cảnh báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (chon == JOptionPane.YES_OPTION) {
					model_HoaDon.removeRow(selected_row);
				}
			}
		} else if (o.equals(cbo_donVi)) {
			model_sanPham.setRowCount(0);
			ArrayList<SanPham> list = new ArrayList<SanPham>();
			list = Loc_SanPham(tf_Msp.getText(), tf_tensp.getText(), cb_loai.getSelectedItem().toString(),
					cbo_donVi.getSelectedItem().toString());
			update_TableSanPham(list);
		} else if (o.equals(cb_loai)) {
			model_sanPham.setRowCount(0);
			ArrayList<SanPham> list = new ArrayList<SanPham>();
			list = Loc_SanPham(tf_Msp.getText(), tf_tensp.getText(), cb_loai.getSelectedItem().toString(),
					cbo_donVi.getSelectedItem().toString());
			update_TableSanPham(list);
		} else if (o.equals(tf_Msp)) {
			if (tf_Msp.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Hãy nhập mã muốn tìm");
			} else {
				ArrayList<SanPham> list = new ArrayList<SanPham>();
				try {
					list = Loc_SanPham(tf_Msp.getText(), tf_tensp.getText(), cb_loai.getSelectedItem().toString(),
							cbo_donVi.getSelectedItem().toString());
					model_sanPham.setRowCount(0);
					update_TableSanPham(list);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Mã sản phẩm là số");
					tf_Msp.setText("");
				}
			}
		} else if (o.equals(btn_hienTatCa)) {
			model_sanPham.setRowCount(0);
			update_TableSanPham(sanPham_dao.Loc_SanPham(-1, "", "", ""));
			tf_Msp.setText("");
			tf_tensp.setText("");
			cb_loai.setSelectedIndex(0);
			cbo_donVi.setSelectedIndex(0);
		} else if (o.equals(radio_cu)) {
			tf_maKH.setEditable(true);
			tf_loaiKH.setEditable(false);
			tf_tenKH.setEditable(false);
			tf_dtKH.setEditable(false);
			tf_diaChiKH.setEditable(false);
			setRongTTKH();
		} else if (o.equals(radio_moi)) {
			tf_maKH.setEditable(false);
			tf_loaiKH.setText("");
			tf_tenKH.setEditable(true);
			tf_dtKH.setEditable(true);
			tf_diaChiKH.setEditable(true);
			setRongTTKH();
			tf_loaiKH.setText("Thường");
		} else if (o.equals(radio_rong)) {
			tf_maKH.setEditable(false);
			tf_loaiKH.setEditable(false);
			tf_tenKH.setEditable(false);
			tf_dtKH.setEditable(false);
			tf_diaChiKH.setEditable(false);
			setRongTTKH();
		} else if (o.equals(tf_maKH)) {
			String ma_text = tf_maKH.getText();
			if (ma_text.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Mã khách hàng không được rỗng");
				tf_maKH.setText("");
				tf_tenKH.setText("");
				tf_dtKH.setText("");
				tf_loaiKH.setText("");
			} else {
				try {
					int ma = Integer.parseInt(ma_text);

					KhachHang kh = khachHang_DAO.getKhachHangTheoMa(ma);
					if (kh == null) {
						JOptionPane.showMessageDialog(this, "Không có Khách hàng với mã " + ma_text);
						tf_maKH.setText("");
						tf_tenKH.setText("");
						tf_dtKH.setText("");
						tf_loaiKH.setText("");
					} else {
						tf_tenKH.setText(kh.getTen());
						tf_dtKH.setText(kh.getSoDienThoai());
						tf_diaChiKH.setText(kh.getDiaChi());
						tf_loaiKH.setText(kh.getLoaiKhachHang());
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số");
					// TODO: handle exception
				}
			}
		} else if (o.equals(tf_tenKH)) {
			String ten_text = tf_tenKH.getText();
			if (ten_text.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống");
				tf_tenKH.setText("");
			}
		} else if (o.equals(tf_dtKH)) {
			String dt_text = tf_dtKH.getText();
			if (dt_text.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
				tf_dtKH.setText("");
			} else if (!dt_text.matches("^(084||012)\\d{7}$")) {
				JOptionPane.showMessageDialog(this, "Số điện thoại bắt đầu bằng 084 hoặc 0123 và phải có 10 số");
				tf_dtKH.setText("");
			}
		}
		else if(o.equals(btn_LapHoaDon)) {
			themHoaDonSQL();
		}
	}
	
	public void setRongTTKH() {
		tf_maKH.setText("");
		tf_loaiKH.setText("");
		tf_tenKH.setText("");
		tf_dtKH.setText("");
		tf_diaChiKH.setText("");
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int numRow = model_HoaDon.getRowCount();

		TongTien = 0;

		for (int i = 0; i < numRow; i++) {

			TongTien += Double.parseDouble(table_HoaDon.getValueAt(i, 5).toString());
		}

		tf_Tongthanhtien.setText(String.valueOf(TongTien));
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		list = Loc_SanPham(tf_Msp.getText(), tf_tensp.getText(), cb_loai.getSelectedItem().toString(),
				cbo_donVi.getSelectedItem().toString());
		model_sanPham.setRowCount(0);
		update_TableSanPham(list);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		list = Loc_SanPham(tf_Msp.getText(), tf_tensp.getText(), cb_loai.getSelectedItem().toString(),
				cbo_donVi.getSelectedItem().toString());
		model_sanPham.setRowCount(0);
		update_TableSanPham(list);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}
}

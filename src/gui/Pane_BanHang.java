package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.SanPham_DAO;
import entity.SanPham;

public class Pane_BanHang extends JPanel implements ActionListener, TableModelListener {
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
	private JPanel jp_InHoaDon;
	private JButton btn_InHoaDon;
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
	private TableRowSorter<TableModel> sort;
	private JButton btn_hienTatCa;

	public Pane_BanHang() {

		// Cấu hình cho trang
		// setLayout(new CardLayout());
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
		String[] colnames_sanPham = { "Mã sản phầm", "Tên sản phầm", "Loại", "Giá", "Đơn vị", "Số lượng" };

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
		
		// khởi tạo TableSorter cho table sản phẩm
		sort = new TableRowSorter<TableModel>(model_sanPham);
		table_SanPham.setRowSorter(sort);
		
		//Xử lí sự kiện khi click vào tiêu đề cột
		table_SanPham.getTableHeader().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int colIndex = table_SanPham.columnAtPoint(e.getPoint());
						if(colIndex == -1) return; //Khi click không phải tiêu đề
						else {
							if(sort.getSortKeys().isEmpty() || sort.getSortKeys().get(0).getColumn() != colIndex) {
								// Nếu chưa được sắp xếphoặc đã được sắp xếp nhưng không theo thứ tự tăng dần,thì sắp xếp tăng dần
								sort.setSortKeys(List.of(new RowSorter.SortKey(colIndex, SortOrder.ASCENDING)));
		                        sort.sort();
							}
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

		// Code nút in hóa đơn
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

		// Code điền thông tin khách hàng
		jp_thongtin = new JPanel();
		jp_thongtin.setLayout(new BorderLayout());

		Box NhapThongTin = Box.createVerticalBox();

		// Điền mã khách hàng
		maKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_maKH = new JLabel("Mã khách hàng:");
		tf_maKH = new JTextField(30);
		maKH.add(Box.createHorizontalStrut(100));
		maKH.add(lb_maKH);
		maKH.add(Box.createHorizontalStrut(20));
		maKH.add(tf_maKH);
		NhapThongTin.add(maKH);

		// Điền tên khách hàng
		tenKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_tenKH = new JLabel("Tên khách hàng:");
		tf_tenKH = new JTextField(30);
		tenKH.add(Box.createHorizontalStrut(100));
		tenKH.add(lb_tenKH);
		tenKH.add(Box.createHorizontalStrut(14));
		tenKH.add(tf_tenKH);
		NhapThongTin.add(tenKH);

		// Điền số điện thoại khách hàng
		dtKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_dtKH = new JLabel("Số điện thoại:");
		tf_dtKH = new JTextField(30);
		dtKH.add(Box.createHorizontalStrut(100));
		dtKH.add(lb_dtKH);
		dtKH.add(Box.createHorizontalStrut(30));
		dtKH.add(tf_dtKH);
		NhapThongTin.add(dtKH);

		// Điền loại khách hàng
		loaiKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_loaiKH = new JLabel("Loại khách hàng:");
		tf_loaiKH = new JTextField(30);
		loaiKH.add(Box.createHorizontalStrut(100));
		loaiKH.add(lb_loaiKH);
		loaiKH.add(Box.createHorizontalStrut(13));
		loaiKH.add(tf_loaiKH);
		NhapThongTin.add(loaiKH);

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
		
		// khởi tạo TableSorter cho table sản phẩm
				sort = new TableRowSorter<TableModel>(model_HoaDon);
				table_HoaDon.setRowSorter(sort);
				
				//Xử lí sự kiện khi click vào tiêu đề cột
				table_HoaDon.getTableHeader().addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								int colIndex = table_HoaDon.columnAtPoint(e.getPoint());
								if(colIndex == -1) return; //Khi click không phải tiêu đề
								else {
									if(sort.getSortKeys().isEmpty() || sort.getSortKeys().get(0).getColumn() != colIndex) {
										// Nếu chưa được sắp xếphoặc đã được sắp xếp nhưng không theo thứ tự tăng dần,thì sắp xếp tăng dần
										sort.setSortKeys(List.of(new RowSorter.SortKey(colIndex, SortOrder.ASCENDING)));
				                        sort.sort();
									}
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

		// Code
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

		btn_them.addActionListener(this);
		btn_xoa.addActionListener(this);
		model_HoaDon.addTableModelListener(this);
		cb_loai.addActionListener(this);
		cbo_donVi.addActionListener(this);
		tf_tensp.addActionListener(this);
		tf_Msp.addActionListener(this);
		btn_hienTatCa.addActionListener(this);
	}

	public void update_TableSanPham(ArrayList<SanPham> list_sanPham) {
		for (SanPham sp : list_sanPham) {
			model_sanPham.addRow(new Object[] { sp.getMaSanPham(), sp.getTen(), sp.getLoaiSanPham(), sp.getGiaSanPham(),
					sp.getDonVi() });
		}
	}

	public void update_TableHoaDon(SanPham sp, int soLuong) {
		model_HoaDon.addRow(new Object[] { sp.getMaSanPham(), sp.getTen(), sp.getDonVi(), sp.getGiaSanPham(), soLuong,
				sp.getGiaSanPham() * soLuong });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btn_them)) {
			int selected_row = table_SanPham.getSelectedRow();
			if (selected_row == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm muốn thêm");
			} else if (model_sanPham.getValueAt(selected_row, 5) == null) {
				JOptionPane.showMessageDialog(this, "Hãy nhập số lượng sản phẩm");
			} else {
				int soLuong = Integer.parseInt(model_sanPham.getValueAt(selected_row, 5).toString());
				int maSP = Integer.parseInt(model_sanPham.getValueAt(selected_row, 0).toString());
				SanPham sp = sanPham_dao.getSanPhamTheoMa(maSP);
				update_TableHoaDon(sp, soLuong);
			}
		} else if (o.equals(btn_xoa)) {
			int selected_row = table_HoaDon.getSelectedRow();
			if (selected_row == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn dòng muốn xóa");
			} else {
				int chon = JOptionPane.showConfirmDialog(this, "Sure? You want to exit?", "Swing Tester",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (chon == JOptionPane.YES_OPTION) {
					model_HoaDon.removeRow(selected_row);
				}
			}
		} else if (o.equals(btn_LapHoaDon)) {

		} else if (o.equals(btn_InHoaDon)) {

		}else if (o.equals(btn_hienTatCa)) {
			tf_Msp.setText("");
			tf_tensp.setText("");
			cb_loai.setSelectedIndex(0);
			cbo_donVi.setSelectedIndex(0);
			model_sanPham.setRowCount(0);
			ArrayList<SanPham> list = sanPham_dao.getalltbSanPham();
			update_TableSanPham(list);
		} else if(o.equals(cb_loai)) {
			model_sanPham.setRowCount(0);
			ArrayList<SanPham> list = new ArrayList<SanPham>();
			if(cb_loai.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
				list = sanPham_dao.getalltbSanPham();
			}
			else {
				list = sanPham_dao.getSanPhamTheoLoai(cb_loai.getSelectedItem().toString());
			}
			update_TableSanPham(list);
		} else if(o.equals(cbo_donVi)) {
			model_sanPham.setRowCount(0);
			ArrayList<SanPham> list = new ArrayList<SanPham>();
			if(cbo_donVi.getSelectedItem().toString().equalsIgnoreCase("Tất cả")){
				list = sanPham_dao.getalltbSanPham();
			}
			else {
				list = sanPham_dao.getSanPhamTheoDonViTinh(cbo_donVi.getSelectedItem().toString());
			}
			update_TableSanPham(list);
		} else if(o.equals(tf_tensp)) {
			if(tf_tensp.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Hãy nhập tên muốn tìm");
			}
			else {
				ArrayList<SanPham> list = sanPham_dao.getSanPhamTheoTen(tf_tensp.getText());
				
				try {
					list.get(0);
					model_sanPham.setRowCount(0);
					update_TableSanPham(list);
					tf_tensp.setText("");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Không có sản phẩm với tên " + tf_tensp.getText());
					tf_tensp.setText("");
					// TODO: handle exception
				}
			}
		}
		else if(o.equals(tf_Msp)) {
			if(tf_Msp.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Hãy nhập mã muốn tìm");
			}
			else {
				ArrayList<SanPham> list = new ArrayList<SanPham>();
				try {
					int maSP = Integer.parseInt(tf_Msp.getText());
					SanPham sp = sanPham_dao.getSanPhamTheoMa(maSP);
					if(sp != null) {
						model_sanPham.setRowCount(0);
						list.add(sp);
						update_TableSanPham(list);
					}
					else {
						JOptionPane.showMessageDialog(this, "Không có sản phẩm nào có mã " + tf_Msp.getText());
						tf_Msp.setText("");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Mã sản phẩm là số");
					tf_Msp.setText("");
				}
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		if (e.getType() == TableModelEvent.UPDATE) {
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

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.KhachHang;
import entity.KhuVuc;
import entity.KhuyenMai;
import entity.SanPham;

public class Panel_SanPham extends JFrame {
	private JTextField txtMaSanPham, txtMaKhuyenMai, txtMaKhuVuc, txtTen, txtDonVi, txtGiaBan, txtsoLuong, txtTimKiem, txtLocGia;
	private JComboBox<String> cboxSanPham, cboxLocSanPham, cboxLocGia;
	private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnXuatFile;
	private JTextArea textArea;
	private JTable tableSanPham;
	private DefaultTableModel sanPhamModel;
	private ArrayList<SanPham> sanPhams = new ArrayList<SanPham>();
	private SanPham_DAO sanPhamDao;
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));

	public Panel_SanPham() {
		try {
			ConnectDB.getInstance().connect();
			System.out.println("Connected!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sanPhamDao = new SanPham_DAO();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));
		// setLocationRelativeTo(null);

		// Phần tiêu đề
		JPanel pNor = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
		pNor.setBackground(new Color(35, 177, 77));
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(new Font("Time new roman", Font.BOLD, 30));
		pNor.add(lblTitle);
		pNor.add(Box.createVerticalStrut(20));
		add(pNor, BorderLayout.NORTH);

		// phan center
		JPanel pCenterAll = new JPanel();
		// pCenter_1
		JPanel pCenter_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCenter_1.setLayout(new BoxLayout(pCenter_1, BoxLayout.Y_AXIS));

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSanPham = new JLabel("Sản phẩm: ");
		panel1.add(lblSanPham);
		// Tạo 1 combobox để giữ các loại sản phẩm
		String[] item = { "Tất cả", "Đồ ăn", "Đồ gia dụng", "Dụng cụ học tập", "Phụ kiện điện tử",
				"Phụ kiện thời trang", "Thức uống", "Thuốc lá", "Văn phòng phẩm" };
		cboxSanPham = new JComboBox<String>(item);
		cboxSanPham.setPreferredSize(new Dimension(220, 25));
		panel1.add(Box.createHorizontalStrut(20));
		panel1.add(cboxSanPham);
		pCenter_1.add(panel1);
		pCenter_1.add(Box.createVerticalStrut(20));

//		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JLabel lblMaSP = new JLabel("Mã sản phẩm: ");
//		panel2.add(lblMaSP);
//		txtMaSanPham = new JTextField(20);
//		txtMaSanPham.setPreferredSize(new Dimension(302, 25));
//		panel2.add(txtMaSanPham);
//		pCenter_1.add(panel2);
//		pCenter_1.add(Box.createVerticalStrut(20));

		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaKM = new JLabel("Mã khuyến mãi: ");
		panel2.add(lblMaKM);
		txtMaKhuyenMai = new JTextField(20);
		txtMaKhuyenMai.setPreferredSize(new Dimension(302, 25));
		panel2.add(txtMaKhuyenMai);
		pCenter_1.add(panel2);
		pCenter_1.add(Box.createVerticalStrut(20));
		
		JPanel panel2_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaKV = new JLabel("Mã khu vực: ");
		panel2_2.add(lblMaKV);
		txtMaKhuVuc = new JTextField(20);
		txtMaKhuVuc.setPreferredSize(new Dimension(302, 25));
		panel2_2.add(Box.createHorizontalStrut(15));
		panel2_2.add(txtMaKhuVuc);
		pCenter_1.add(panel2_2);
		pCenter_1.add(Box.createVerticalStrut(20));
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenSP = new JLabel("Tên sản phẩm:  ");
		panel3.add(lblTenSP);
		txtTen = new JTextField(20);
		txtTen.setPreferredSize(new Dimension(302, 25));
		panel3.add(txtTen);
		pCenter_1.add(panel3);
		pCenter_1.add(Box.createVerticalStrut(20));

		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDonVi = new JLabel("Đơn vị: ");
		panel4.add(lblDonVi);
		txtDonVi = new JTextField(20);
		txtDonVi.setPreferredSize(new Dimension(302, 25));
		panel4.add(Box.createHorizontalStrut(40));
		panel4.add(txtDonVi);
		pCenter_1.add(panel4);
		pCenter_1.add(Box.createVerticalStrut(20));

		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblGia = new JLabel("Giá Bán: ");
		panel5.add(lblGia);
		txtGiaBan = new JTextField(20);
		txtGiaBan.setPreferredSize(new Dimension(302, 25));
		panel5.add(Box.createHorizontalStrut(35));
		panel5.add(txtGiaBan);
		pCenter_1.add(panel5);
		pCenter_1.add(Box.createVerticalStrut(20));

		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSoLuong = new JLabel("Số lượng: ");
		panel6.add(lblSoLuong);
		txtsoLuong = new JTextField(20);
		txtsoLuong.setPreferredSize(new Dimension(302, 25));
		panel6.add(Box.createHorizontalStrut(27));
		panel6.add(txtsoLuong);
		pCenter_1.add(panel6);
		pCenter_1.add(Box.createVerticalStrut(20));

		pCenterAll.add(pCenter_1);
		pCenterAll.add(Box.createHorizontalStrut(100));
		// pCenter_2
		JPanel pCenter_2 = new JPanel();
		pCenter_2.setLayout(new BoxLayout(pCenter_2, BoxLayout.Y_AXIS));
		pCenter_2.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		btnThem = new JButton("   Thêm   ");
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		btnThem.setPreferredSize(new Dimension(140, 40));

		btnSua = new JButton("    Sửa    ");
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));
		btnSua.setPreferredSize(new Dimension(140, 40));

		btnXoa = new JButton("    Xóa    ");
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		btnXoa.setPreferredSize(new Dimension(140, 40));

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Arial", Font.BOLD, 15));
		btnLamMoi.setPreferredSize(new Dimension(140, 40));

		btnXuatFile = new JButton("Xuất File");
		btnXuatFile.setFont(new Font("Arial", Font.BOLD, 15));
		btnXuatFile.setPreferredSize(new Dimension(140, 40));

		// Căn chỉnh cho các button giữa các dòng
		btnThem.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSua.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnXoa.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLamMoi.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnXuatFile.setAlignmentX(Component.CENTER_ALIGNMENT);

		pCenter_2.add(Box.createVerticalStrut(20));
		pCenter_2.add(btnThem);
		pCenter_2.add(Box.createVerticalStrut(20));
		pCenter_2.add(btnSua);
		pCenter_2.add(Box.createVerticalStrut(20));
		pCenter_2.add(btnXoa);
		pCenter_2.add(Box.createVerticalStrut(20));
		pCenter_2.add(btnLamMoi);
		pCenter_2.add(Box.createVerticalStrut(20));
		pCenter_2.add(btnXuatFile);
		pCenterAll.add(pCenter_2);

		pCenterAll.add(Box.createHorizontalStrut(100));
		// pCenter 3
		JPanel pCenter_3 = new JPanel();
		pCenter_3.setLayout(new BoxLayout(pCenter_3, BoxLayout.Y_AXIS));
		JLabel lblThongTin = new JLabel("Thông Tin Chi Tiết");
		lblThongTin.setFont(new Font("Arial", Font.BOLD, 24));
		lblThongTin.setAlignmentX(Component.CENTER_ALIGNMENT);
		pCenter_3.add(lblThongTin);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setPreferredSize(new Dimension(400, 300));
		pCenter_3.add(textArea);
		pCenterAll.add(pCenter_3);

		// phần trung tâm 2
		JPanel pCenterLoc = new JPanel();
		pCenterLoc.setLayout(new BoxLayout(pCenterLoc, BoxLayout.X_AXIS));
		pCenterLoc.setBorder(BorderFactory.createTitledBorder("Lọc Sản Phẩm"));

		pCenterLoc.add(Box.createHorizontalStrut(100));
		JPanel pBox1 = new JPanel();
		pBox1.setLayout(new BoxLayout(pBox1, BoxLayout.Y_AXIS));
		JLabel lblLocSP = new JLabel("Lọc Sản phẩm");
		lblLocSP.setAlignmentX(Component.CENTER_ALIGNMENT);
		pBox1.add(lblLocSP);
		// Tạo 1 combobox để giữ các loại sản phẩm
		cboxLocSanPham = new JComboBox<String>();
		cboxLocSanPham.setPreferredSize(new Dimension(220, 25));
		cboxLocSanPham.addItem("Tất cả");
		// Tạo 1 Arayllist để lưu các loại sản phẩm được trả về từ hàm getAllLoaiSP
		ArrayList<String> list_loaiSP = sanPhamDao.getAllLoaiSP();
		// truyền list loại vào trong combobox
		for (String loai : list_loaiSP) {
			cboxLocSanPham.addItem(loai);
		}
		pBox1.add(Box.createVerticalStrut(5));
		pBox1.add(cboxLocSanPham);
		pBox1.add(Box.createVerticalStrut(5));
		pCenterLoc.add(pBox1);
		pCenterLoc.add(Box.createHorizontalStrut(100));

		JPanel pBox2 = new JPanel();
		pBox2.setLayout(new BoxLayout(pBox2, BoxLayout.Y_AXIS));
		JLabel lblTimkiem = new JLabel("Tìm kiếm sản phẩm");
		lblTimkiem.setAlignmentX(Component.CENTER_ALIGNMENT);
		pBox2.add(lblTimkiem);
		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(260, 25));
		pBox2.add(Box.createVerticalStrut(5));
		pBox2.add(txtTimKiem);
		pBox2.add(Box.createVerticalStrut(5));
		pCenterLoc.add(pBox2);
		pCenterLoc.add(Box.createHorizontalStrut(100));

		JPanel pBox3 = new JPanel();
		pBox3.setLayout(new BoxLayout(pBox3, BoxLayout.Y_AXIS));
		JLabel lblLocGia = new JLabel("Giá tiền");
		lblLocGia.setAlignmentX(Component.CENTER_ALIGNMENT);
		pBox3.add(lblLocGia);
		cboxLocGia = new JComboBox<String>();
		cboxLocGia.setPreferredSize(new Dimension(220, 25));
		cboxLocGia.addItem("Tất cả");
		// Tạo 1 Arayllist để lưu các loại sản phẩm được trả về từ hàm getAllLoaiSP
		ArrayList<String> list_giaSP = sanPhamDao.getAllLoaiSP();
		// truyền list loại vào trong combobox
		for (String gia : list_giaSP) {
			cboxSanPham.addItem(gia);
		}
		pBox3.add(Box.createVerticalStrut(5));
		pBox3.add(cboxLocGia);
		pBox3.add(Box.createVerticalStrut(5));
		pCenterLoc.add(pBox3);
		pCenterLoc.add(Box.createHorizontalStrut(100));
		pCenterAll.add(pCenterLoc, BorderLayout.NORTH);
		// table
		String[] col = { "Mã sản phẩm", "Mã khuyến mãi" ,"Mã khu vực", "Tên sản phẩm", "Giá bán", "Đơn vị", "Loại sản phẩm", "Số lượng tồn" };
		sanPhamModel = new DefaultTableModel(col, 0);
		tableSanPham = new JTable(sanPhamModel);
		JScrollPane scroll = new JScrollPane(tableSanPham);
		scroll.setPreferredSize(new Dimension(1100, 100));
		pCenterAll.add(scroll, BorderLayout.SOUTH);
		add(pCenterAll, BorderLayout.CENTER);

		setSize(1480, 680);
		setVisible(true);
		// Đọc dữ liệu từ cơ sở dữ liệu vào bảng
		docDuLieuVaoTable();

		// xử lí sự kiện
		txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				UpdateTable();
				sanPhams.clear();
				String keyWord = txtTimKiem.getText().trim();
				ArrayList<SanPham> temp = sanPhamDao.getalltbSanPham();
				for (SanPham sp : temp) {
					if (Integer.toString(sp.getMaSanPham()).contains(keyWord) || sp.getTen().contains(keyWord)
							|| sp.getLoaiSanPham().contains(keyWord)) {
						sanPhams.add(sp);
					}
				}
				sanPhamModel.setRowCount(0);
				// Thêm lại các khách hàng từ danh sách khachHangs vào bảng
				for (SanPham sp : sanPhams) {
					sanPhamModel.addRow(new Object[] { sp.getMaSanPham(), sp.getMaKhuyenMai().getMaKhuyenMai(), sp.getMaKhuVuc().getMaKhuVuc(), sp.getTen(), sp.getGiaSanPham(),
							sp.getDonVi(), sp.getLoaiSanPham(), sp.getSoLuongTonKho() });
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				UpdateTable();
				sanPhams.clear();
				String keyWord = txtTimKiem.getText().trim();
				ArrayList<SanPham> temp = sanPhamDao.getalltbSanPham();
				for (SanPham sp : temp) {
					if (Integer.toString(sp.getMaSanPham()).contains(keyWord) || sp.getTen().contains(keyWord)
							|| sp.getLoaiSanPham().contains(keyWord)) {
						sanPhams.add(sp);
					}
				}
				sanPhamModel.setRowCount(0);
				// Thêm lại các khách hàng từ danh sách khachHangs vào bảng
				for (SanPham sp : sanPhams) {
					sanPhamModel.addRow(new Object[] { sp.getMaSanPham(), sp.getMaKhuyenMai().getMaKhuyenMai(), sp.getMaKhuVuc().getMaKhuVuc(), sp.getTen(), sp.getGiaSanPham(),
							sp.getDonVi(), sp.getLoaiSanPham(), sp.getSoLuongTonKho() });
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		//sự kiện thêm
		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SanPham sanpham = new SanPham();
				sanpham.setMaKhuyenMai(new KhuyenMai(Integer.parseInt(txtMaKhuyenMai.getText())));
				sanpham.setMaKhuVuc(new KhuVuc(Integer.parseInt(txtMaKhuVuc.getText())));
				sanpham.setTen(txtTen.getText());
				sanpham.setGiaSanPham(Double.parseDouble(txtGiaBan.getText()));
				sanpham.setDonVi(txtDonVi.getText());
				sanpham.setLoaiSanPham(cboxSanPham.getSelectedItem().toString());
				sanpham.setSoLuongTonKho(Integer.parseInt(txtsoLuong.getText()));
				if (!sanPhamDao.create(sanpham)) {
					JOptionPane.showMessageDialog(null, "Thêm không thành công!");
				} else {
					JOptionPane.showMessageDialog(null, "Thêm thành công.");
					UpdateTable();
					docDuLieuVaoTable();
				}
			}
		});

	}

	// Phương thức đọc dữ liệu từ cơ sở dữ liệu vào bảng
	public void docDuLieuVaoTable() {
		ArrayList<SanPham> list = sanPhamDao.getalltbSanPham();
		for (SanPham sp : list) {
			((DefaultTableModel) sanPhamModel).addRow(new Object[] { sp.getMaSanPham(), sp.getMaKhuyenMai().getMaKhuyenMai(), sp.getMaKhuVuc().getMaKhuVuc(), sp.getTen(), currencyFormat.format(sp.getGiaSanPham()),
					sp.getDonVi(), sp.getLoaiSanPham(), sp.getSoLuongTonKho() });
		}
	}

	public void UpdateTable() {
		DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
		model.setRowCount(0);
	}

	public static void main(String[] args) {
		new Panel_SanPham();
	}
}
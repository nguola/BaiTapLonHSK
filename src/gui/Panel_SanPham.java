package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hpsf.Date;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.KhachHang;
import entity.KhuVuc;
import entity.KhuyenMai;
import entity.SanPham;

public class Panel_SanPham extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaSanPham, txtMaKhuyenMai, txtMaKhuVuc, txtTen, txtDonVi, txtGiaBan, txtsoLuong, txtTimKiem,
			txtLocGia;
	private JComboBox<String> cboxSanPham, cboxLocSanPham, cboxLocGia;
	private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnXuatFile;
	private JTextArea textArea;
	private JTable tableSanPham;
	private DefaultTableModel sanPhamModel;
	private ArrayList<SanPham> sanPhams = new ArrayList<SanPham>();
	private SanPham_DAO sanPhamDao;
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private SanPham sanpham;
	private TableRowSorter<TableModel> sort;

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
		pCenter_3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
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
		String[] item3 = { "Tất cả", "Dưới 100k", "100k - 500k", "500k - 1 triệu", "Trên 1 triệu" };
		cboxLocGia = new JComboBox<String>(item3);
		cboxLocGia.setPreferredSize(new Dimension(220, 25));
		pBox3.add(Box.createVerticalStrut(5));
		pBox3.add(cboxLocGia);
		pBox3.add(Box.createVerticalStrut(5));
		pCenterLoc.add(pBox3);
		pCenterLoc.add(Box.createHorizontalStrut(100));
		pCenterAll.add(pCenterLoc, BorderLayout.NORTH);
		// table
		String[] col = { "Mã sản phẩm", "Mã khuyến mãi", "Mã khu vực", "Tên sản phẩm", "Giá bán", "Đơn vị",
				"Loại sản phẩm", "Số lượng tồn" };
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
		// sự kiện click vào table đõ lên textFiled
		tableSanPham.addMouseListener(this);

		// Xử lí sự kiện khi click vào tiêu đề cột
		// khởi tạo TableSorter
		sort = new TableRowSorter<TableModel>(sanPhamModel);
		tableSanPham.setRowSorter(sort);
		tableSanPham.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int colIndex = tableSanPham.columnAtPoint(e.getPoint());
				if (colIndex == -1)
					return; // Khi click không phải tiêu đề
				else {
					if (sort.getSortKeys().isEmpty() || sort.getSortKeys().get(0).getColumn() != colIndex) {
						// Nếu chưa được sắp xếphoặc đã được sắp xếp nhưng không theo thứ tự tăng
						// dần,thì sắp xếp tăng dần
						sort.setSortKeys(List.of(new RowSorter.SortKey(colIndex, SortOrder.ASCENDING)));
						sort.sort();
					}
				}
			}
		});
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
					sanPhamModel.addRow(new Object[] { sp.getMaSanPham(), sp.getMaKhuyenMai().getMaKhuyenMai(),
							sp.getMaKhuVuc().getMaKhuVuc(), sp.getTen(), sp.getGiaSanPham(), sp.getDonVi(),
							sp.getLoaiSanPham(), sp.getSoLuongTonKho() });
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
				// Thêm lại các khách hàng từ danh sách sanphams vào bảng
				for (SanPham sp : sanPhams) {
					sanPhamModel.addRow(new Object[] { sp.getMaSanPham(), sp.getMaKhuyenMai().getMaKhuyenMai(),
							sp.getMaKhuVuc().getMaKhuVuc(), sp.getTen(), sp.getGiaSanPham(), sp.getDonVi(),
							sp.getLoaiSanPham(), sp.getSoLuongTonKho() });
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// sự kiện thêm
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
					docDuLieuVaoTable();
				}
			}
		});
		// sự kiện sửa
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Đổ dữ liệu textFiled
				int row = tableSanPham.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Chọn sản phẩm cần sửa");
					return;
				} else {
					int maSP = Integer.parseInt(sanPhamModel.getValueAt(row, 0).toString());
					SanPham sanpham = new SanPham(maSP, new KhuyenMai(Integer.parseInt(txtMaKhuyenMai.getText())),
							new KhuVuc(Integer.parseInt(txtMaKhuVuc.getText())), txtTen.getText(),
							Double.parseDouble(txtGiaBan.getText()), txtDonVi.getText(),
							cboxSanPham.getSelectedItem().toString(), Integer.parseInt(txtsoLuong.getText()));
					if (!sanPhamDao.update(sanpham)) {
						JOptionPane.showMessageDialog(null, "Sửa không thành công!");
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thành công.");
						sanPhamModel.setValueAt(sanpham.getMaKhuyenMai().getMaKhuyenMai(), row, 1);
						sanPhamModel.setValueAt(sanpham.getMaKhuVuc().getMaKhuVuc(), row, 2);
						sanPhamModel.setValueAt(sanpham.getTen(), row, 3);
						sanPhamModel.setValueAt(currencyFormat.format(sanpham.getGiaSanPham()), row, 4);
						sanPhamModel.setValueAt(sanpham.getDonVi(), row, 5);
						sanPhamModel.setValueAt(sanpham.getLoaiSanPham(), row, 6);
						sanPhamModel.setValueAt(sanpham.getSoLuongTonKho(), row, 7);
					}
				}
			}
		});
		// sự kiện xóa
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableSanPham.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa");
					return;
				}
				int masp = Integer.parseInt(tableSanPham.getValueAt(index, 0).toString());
				try {
					sanPhamDao.remove(masp);
					sanPhamModel.removeRow(index);
					JOptionPane.showMessageDialog(null, "Xóa thành công");
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Lỗi khi xóa" + err.getMessage());
				}
			}
		});
		// sự kiện làm mới
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				docDuLieuVaoTable();
				txtTimKiem.setText("");
				txtMaKhuyenMai.setText("");
				txtMaKhuVuc.setText("");
				txtTen.setText("");
				txtGiaBan.setText("");
				txtDonVi.setText("");
				txtsoLuong.setText("");
			}
		});
		// sự kiện lọc theo loại sản phẩm
		cboxLocSanPham.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String loai = cboxLocSanPham.getSelectedItem().toString();
				ArrayList<SanPham> list = sanPhamDao.getSanPhamTheoLoai(loai);
				sanPhams.clear();
				tableSanPham.clearSelection();
				sanPhamModel.setRowCount(0);
				if (loai.equalsIgnoreCase("Tất cả")) {
					docDuLieuVaoTable();
				} else {
					for (SanPham sp : list) {
						if (sp.getLoaiSanPham().equalsIgnoreCase(loai)) {
							sanPhams.add(sp);
						}
					}
					for (SanPham sp : sanPhams) {
						sanPhamModel.addRow(new Object[] { sp.getMaSanPham(), sp.getMaKhuyenMai().getMaKhuyenMai(),
								sp.getMaKhuVuc().getMaKhuVuc(), sp.getTen(), currencyFormat.format(sp.getGiaSanPham()),
								sp.getDonVi(), sp.getLoaiSanPham(), sp.getSoLuongTonKho() });
					}
				}
			}
		});
		// sự kiện lọc theo giá sản phẩm
		cboxLocGia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String gia = cboxLocGia.getSelectedItem().toString();
				double priceStart = 0;
				double priceEnd = Double.MAX_VALUE;
				switch (gia) {
				case "Dưới 100k": {
					priceEnd = 100000;
					break;
				}
				case "100k - 500k": {
					priceStart = 100000;
					priceEnd = 500000;
					break;
				}
				case "500k - 1 triệu": {
					priceStart = 500000;
					priceEnd = 1000000;
					break;
				}
				case "Trên 1 triệu": {
					priceStart = 1000000;
					break;
				}
				case "Tất cả": {
					docDuLieuVaoTable();
					// break;
					return;
				}
				default:
					break;
				}
				ArrayList<SanPham> temp = sanPhamDao.locSanPhamTheoGia(priceStart, priceEnd);
				tableSanPham.clearSelection();
				sanPhamModel.setRowCount(0);
				for (SanPham sp : temp) {
					sanPhamModel.addRow(new Object[] { sp.getMaSanPham(), sp.getMaKhuyenMai().getMaKhuyenMai(),
							sp.getMaKhuVuc().getMaKhuVuc(), sp.getTen(), currencyFormat.format(sp.getGiaSanPham()),
							sp.getDonVi(), sp.getLoaiSanPham(), sp.getSoLuongTonKho() });
				}
			}
		});
		// sự kiện hiển thị trong textArea
		tableSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = tableSanPham.getSelectedRow();
					if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
						int maSP = Integer.parseInt(tableSanPham.getValueAt(selectedRow, 0).toString());
						int maKM = Integer.parseInt(tableSanPham.getValueAt(selectedRow, 1).toString());
						int maKV = Integer.parseInt(tableSanPham.getValueAt(selectedRow, 2).toString());
						String ten = tableSanPham.getValueAt(selectedRow, 3).toString();
						String gia = tableSanPham.getValueAt(selectedRow, 4).toString();
						String donVi = tableSanPham.getValueAt(selectedRow, 5).toString();
						String loaiSP = tableSanPham.getValueAt(selectedRow, 6).toString();
						int soLuong = Integer.parseInt(tableSanPham.getValueAt(selectedRow, 7).toString());
						String tenKhuVuc = sanPhamDao.getTenKhuVuc(maKV);
						String dieuKien = sanPhamDao.getDieuKienKhuyenMai(maKM);

						// Tạo chuỗi định dạng cho nội dung trong TextArea
						String textContent = String.format(
								"\n	Chi Tiết Sản Phẩm	\n\n" + "==================================================\n"
										+ " -Mã sản phẩm: %d\n" + " -Tên sản phẩm: %s\n" + " -Giá bán: %s\n"
										+ " -Loại sản phẩm: %s\n" + " -Khu Vực: %s\n" + " -Điều Kiện Khuyến mãi:\n %s\n"
										+ "==================================================\n",
								maSP, ten, gia, loaiSP, tenKhuVuc, dieuKien);
						Font font = new Font("Arial", Font.PLAIN, 20);
						textArea.setFont(font);
						textArea.setText(textContent);

					}
				}
			}
		});
		// sự kiện xuất fille
		btnXuatFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableSanPham.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xuất PDF");
					return;
				}
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {

					int maSP = Integer.parseInt(tableSanPham.getValueAt(selectedRow, 0).toString());

					sanpham = sanPhamDao.getSanPhamTheoMa(maSP);
					String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
					exportPDF(selectedFilePath, sanpham);
				}
			}
		});
		// sự kiện popmenu cho table
		tableSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Kiểm tra nếu người dùng click chuột phải và có dòng được chọn
				if (SwingUtilities.isRightMouseButton(e) && tableSanPham.getSelectedRow() != -1) {
					// Lấy vị trí của dòng được chọn
					int row = tableSanPham.getSelectedRow();
					// Tạo JPopupMenu
					JPopupMenu popupMenu = new JPopupMenu();
					// Tạo JMenuItem cho tùy chọn "Sửa"
					JMenuItem menuItemXoa = new JMenuItem("Xóa");
					JMenuItem menuItemXuatFile = new JMenuItem("Xuất file");
					JMenuItem menuItemThongTinSP = new JMenuItem("Thông Tin Sản Phẩm");
					// Đặt ActionListener cho JMenuItem
					menuItemXoa.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int index = tableSanPham.getSelectedRow();
							int masp = Integer.parseInt(tableSanPham.getValueAt(index, 0).toString());
							try {
								sanPhamDao.remove(masp);
								sanPhamModel.removeRow(index);
								JOptionPane.showMessageDialog(null, "Xóa thành công");
							} catch (Exception err) {
								JOptionPane.showMessageDialog(null, "Lỗi khi xóa" + err.getMessage());
							}
						}
					});
					menuItemXuatFile.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int selectedRow = tableSanPham.getSelectedRow();
							JFileChooser fileChooser = new JFileChooser();
							fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
							int result = fileChooser.showOpenDialog(null);
							if (result == JFileChooser.APPROVE_OPTION) {

								int maSP = Integer.parseInt(tableSanPham.getValueAt(selectedRow, 0).toString());

								sanpham = sanPhamDao.getSanPhamTheoMa(maSP);
								String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
								exportPDF(selectedFilePath, sanpham);
							}
						}
					});
					menuItemThongTinSP.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int row = tableSanPham.getSelectedRow();
							int maSanPham = Integer.parseInt(tableSanPham.getValueAt(row, 0).toString());
							new ThongTinSanPham_GUI(maSanPham);
						}
					});
					// Thêm JMenuItem "Sửa" vào JPopupMenu
					popupMenu.add(menuItemXoa);
					popupMenu.add(menuItemXuatFile);
					popupMenu.add(menuItemThongTinSP);
					// Hiển thị JPopupMenu tại vị trí chuột
					popupMenu.show(tableSanPham, e.getX(), e.getY());
				}
			}
		});
	}

	public void exportPDF(String filePath, SanPham sanpham) {

		String inputFilePath = "data\\SanPham\\SanPham_Mau.docx";
		String outputFilePathWord = "data\\SanPham\\SanPham_" + sanpham.getMaSanPham() + ".docx";
		String outputFilePathPDF = filePath + "chitiet_" + sanpham.getMaSanPham() + ".pdf";

		String[] search = { "%SOLUONGTON1%", "%TENKHACHHANG%", "%MANHANVIEN%", "%TENNHANVIEN%", "%NGAYMUA%",
				"%MAKHACHHANG%", "%SOLUONGTON%" };
		String[] replace = { Integer.toString(sanpham.getMaSanPham()),
				Integer.toString(sanpham.getMaKhuyenMai().getMaKhuyenMai()),
				Integer.toString(sanpham.getMaKhuVuc().getMaKhuVuc()), sanpham.getTen().toString(),
				Double.toString(sanpham.getGiaSanPham()), sanpham.getLoaiSanPham().toString(),
				Integer.toString(sanpham.getSoLuongTonKho()) };

		try (FileInputStream fis = new FileInputStream(inputFilePath); XWPFDocument document = new XWPFDocument(fis)) {

			// Thay thế các token trong tệp mẫu Word
			for (XWPFParagraph paragraph : document.getParagraphs()) {
				for (XWPFRun run : paragraph.getRuns()) {
					String text = run.getText(0);
					if (text != null) {
						for (int i = 0; i < search.length; i++) {
							if (text.contains(search[i])) {
								text = text.replace(search[i], replace[i]);
							}
						}
						run.setText(text, 0);
					}
				}
			}
			try (FileOutputStream fos = new FileOutputStream(outputFilePathWord)) {
				document.write(fos);
			}
			Document doc = new Document(outputFilePathWord);
			// Lưu tài liệu PDF
			doc.save(outputFilePathPDF);
			// Mở tài liệu
			openFile(outputFilePathPDF);
			JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xử lý tệp Word!");
		}
	}

	public void openFile(String filePath) {
		try {
			File pdfFile = new File(filePath);
			if (!pdfFile.exists()) {
				return;
			}
			Desktop destop = Desktop.getDesktop();
			if (destop.isSupported(Desktop.Action.OPEN)) {
				destop.open(pdfFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Không thể mở tập tin PDF!");
		}
	}

	// Phương thức đọc dữ liệu từ cơ sở dữ liệu vào bảng
	public void docDuLieuVaoTable() {
		DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
		model.setRowCount(0); // Xóa dữ liệu cũ
		ArrayList<SanPham> list = sanPhamDao.getalltbSanPham();
		for (SanPham sp : list) {
			model.addRow(new Object[] { sp.getMaSanPham(), sp.getMaKhuyenMai().getMaKhuyenMai(),
					sp.getMaKhuVuc().getMaKhuVuc(), sp.getTen(), currencyFormat.format(sp.getGiaSanPham()),
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

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableSanPham.getSelectedRow();
		txtMaKhuyenMai.setText(sanPhamModel.getValueAt(row, 1).toString());
		txtMaKhuVuc.setText(sanPhamModel.getValueAt(row, 2).toString());
		txtTen.setText(sanPhamModel.getValueAt(row, 3).toString());
		txtGiaBan.setText(sanPhamModel.getValueAt(row, 4).toString());
		txtDonVi.setText(sanPhamModel.getValueAt(row, 5).toString());
		cboxSanPham.setSelectedItem(sanPhamModel.getValueAt(row, 6).toString());
		txtsoLuong.setText(sanPhamModel.getValueAt(row, 7).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}

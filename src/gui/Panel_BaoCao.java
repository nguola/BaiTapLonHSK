package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.HoaDon;
import entity.NhanVien;
import entity.TaiKhoan;

public class Panel_BaoCao extends JPanel implements ActionListener, MouseListener, TableModelListener {
	private NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
	private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
	private SanPham_DAO sanPham_dao = new SanPham_DAO();
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
	private JTextField tf_ma;
	private JTextField tf_ten;
	private JTextField tf_DT;
	private JButton btn_baoCao;
	private DefaultTableModel modelListHD;
	private JTable tbListHD;
	private JPopupMenu popupMenu;
	private JMenuItem popupXemChiTiet;
	private ArrayList<HoaDon> list;
	private JTextField tf_tongDoanhThu;
	private JTextField tf_sanPhamCaoNhat;
	private JTextField tf_sanPhamThapNhat;
	private JComboBox<String> cbbTime;
	public NhanVien nv;

	public Panel_BaoCao(TaiKhoan tk) {

		nv = nhanVien_dao.getNhanVienTheoMaNV(tk.getNhanvien().getMaNhanVien());

		// Cấu hình cho trang
		setLayout(new BorderLayout());
		setSize(800, 600);

		// Code North
		JPanel jNorth = new JPanel();
		JLabel tieuDe = new JLabel("Báo cáo");
		tieuDe.setFont(new Font("Time new roman", Font.BOLD, 30));
		jNorth.add(tieuDe);
		add(jNorth, BorderLayout.NORTH);

		// Code Center
		JPanel jCenter = new JPanel(new BorderLayout());

		Box thongTinNhanVien = Box.createVerticalBox();

		JPanel maNV = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ma = new JLabel("Mã nhân viên:");
		tf_ma = new JTextField(30);
		tf_ma.setEditable(false);
		tf_ma.setText(String.valueOf(nv.getMaNhanVien()));
		maNV.add(Box.createHorizontalStrut(20));
		maNV.add(ma);
		maNV.add(Box.createHorizontalStrut(10));
		maNV.add(tf_ma);

		JPanel tenNV = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel tennhanvien = new JLabel("Tên nhân viên:");
		tf_ten = new JTextField(30);
		tf_ten.setEditable(false);
		tf_ten.setText(nv.getTen());
		tenNV.add(Box.createHorizontalStrut(20));
		tenNV.add(tennhanvien);
		tenNV.add(Box.createHorizontalStrut(5));
		tenNV.add(tf_ten);

		JPanel soDT = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel DT = new JLabel("Số điện thoại:");
		tf_DT = new JTextField(30);
		tf_DT.setEditable(false);
		tf_DT.setText(nv.getSoDienThoai());
		soDT.add(Box.createHorizontalStrut(20));
		soDT.add(DT);
		soDT.add(Box.createHorizontalStrut(12));
		soDT.add(tf_DT);

		thongTinNhanVien.add(maNV);
		thongTinNhanVien.add(tenNV);
		thongTinNhanVien.add(soDT);

		jCenter.add(thongTinNhanVien, BorderLayout.NORTH);

		// Code hóa đơn
		JPanel hoaDonTitle = new JPanel();
		JLabel danhSachHD = new JLabel("Danh Sách Hóa Đơn");
		danhSachHD.setFont(new Font(getName(), Font.BOLD, 25));
		hoaDonTitle.add(danhSachHD);

		// Tạo table hóa đơn
		String[] header = { "Mã hóa đơn", "Khách hàng", "Ngày mua", "Tổng tiền" };
		modelListHD = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbListHD = new JTable(modelListHD);
		tbListHD.addMouseListener(this);

		// Đưa dữ liệu lên table
		list = hoaDon_DAO.getAllHoaDonNhanVien(tk.getNhanvien().getMaNhanVien());
		updateTable(list);

		// Đưa bảng vào GUI
		JScrollPane scroll_hoaDon = new JScrollPane(tbListHD);
		jCenter.add(scroll_hoaDon, BorderLayout.CENTER);

		add(jCenter, BorderLayout.CENTER);

		// Code thông tin báo cáo
		Box thongTin = Box.createVerticalBox();

		JPanel tongDoanhThu = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lb_tongDoanhThu = new JLabel("Tổng Doanh Thu");
		tf_tongDoanhThu = new JTextField(15);
		tf_tongDoanhThu.setEditable(false);
		double TongTien = 0;

		for(HoaDon hd : list) {
			TongTien += hd.getTongTien();
		}
		
		tf_tongDoanhThu.setText(currencyFormat.format(TongTien));
		tongDoanhThu.add(Box.createHorizontalStrut(20));
		tongDoanhThu.add(lb_tongDoanhThu);
		tongDoanhThu.add(Box.createHorizontalStrut(46));
		tongDoanhThu.add(tf_tongDoanhThu);

		JPanel sanPhamCaoNhat = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lb_sanPhamCaoNhat = new JLabel("Sản phẩm bán nhiều nhất:");
		tf_sanPhamCaoNhat = new JTextField(15);
		tf_sanPhamCaoNhat.setEditable(false);
		sanPhamCaoNhat.add(Box.createHorizontalStrut(20));
		sanPhamCaoNhat.add(lb_sanPhamCaoNhat);
		sanPhamCaoNhat.add(tf_sanPhamCaoNhat);
		tf_sanPhamCaoNhat.setText(sanPham_dao.SanPhamDoanhThuCaoNhat(nv.getMaNhanVien(), null, null));
		
		JPanel sanPhamThapNhat = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lb_sanPhamThapNhat = new JLabel("Sản phẩm bán thấp nhất:");
		tf_sanPhamThapNhat = new JTextField(15);
		tf_sanPhamThapNhat.setEditable(false);
		sanPhamThapNhat.add(Box.createHorizontalStrut(20));
		sanPhamThapNhat.add(lb_sanPhamThapNhat);
		sanPhamThapNhat.add(Box.createHorizontalStrut(3));
		sanPhamThapNhat.add(tf_sanPhamThapNhat);
		tf_sanPhamThapNhat.setText(sanPham_dao.SanPhamDoanhThuThapNhat(nv.getMaNhanVien(), null, null));

		thongTin.add(tongDoanhThu);
		thongTin.add(sanPhamCaoNhat);
		thongTin.add(sanPhamThapNhat);

		jCenter.add(thongTin, BorderLayout.SOUTH);

		// Code West

		// Tạo comboBox chọn theo ngày, theo tuần, theo tháng
		JPanel jWest = new JPanel();

		String[] time = { "Tất cả", "Ngày này", "Tuần này", "Tháng này" };
		cbbTime = new JComboBox<String>(time);
		cbbTime.setFont(new Font("Arial", Font.BOLD, 15));

		jWest.add(cbbTime);
		jWest.setBorder(BorderFactory.createTitledBorder("Chọn thời gian"));
		add(jWest, BorderLayout.WEST);

		// Code South
		JPanel jSouth = new JPanel();

		btn_baoCao = new JButton("Báo cáo");
		btn_baoCao.setFont(new Font("Arial", Font.BOLD, 25));

		jSouth.add(btn_baoCao);

		add(jSouth, BorderLayout.SOUTH);

		// Tạo popupmenu Chính
		popupMenu = new JPopupMenu();

		popupXemChiTiet = new JMenuItem("Xem chi tiết");

		popupMenu.add(popupXemChiTiet);

		// Add actionListener
		popupXemChiTiet.addActionListener(this);
		cbbTime.addActionListener(this);
		modelListHD.addTableModelListener(this);
		btn_baoCao.addActionListener(this);
	}

	public void updateTable(ArrayList<HoaDon> list) {
		modelListHD.setRowCount(0);
		try {
			for (HoaDon hoaDon : list) {
				modelListHD.addRow(new Object[] { hoaDon.getMaDon(), hoaDon.getKhachHang().getMaKhachHang(),
						hoaDon.getNgayMua(), currencyFormat.format(hoaDon.getTongTien()) });
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON3) {
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
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
		Object o = e.getSource();
		if (o.equals(popupXemChiTiet)) {
			int index = tbListHD.getSelectedRow();
			if (index < 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn");
				return;
			}
			HoaDon hoadon = new HoaDon(Integer.parseInt(tbListHD.getValueAt(index, 0).toString()));
			new ChiTietHoaDon_GUI(list.get(list.indexOf(hoadon))).setVisible(true);
		} else if (o.equals(cbbTime)) {
			list = FillterHoaDon();
			updateTable(list);
			Date timeStart = null;
			Date timeEnd = null;
			String item = (String) cbbTime.getSelectedItem();
			LocalDate now = LocalDate.now();
			switch (item) {
			case "Ngày này": {
				timeStart = Date.valueOf(now);
                timeEnd = Date.valueOf(now);
                break;
			}
			case "Tháng này": {
				LocalDate firstDate = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
				timeStart = Date.valueOf(firstDate);
				timeEnd = Date.valueOf(firstDate.plusMonths(1).minusDays(1));
				break;
			}
			case "Tuần này": {
				timeStart = Date.valueOf(now.with(DayOfWeek.MONDAY));
				timeEnd = Date.valueOf(now.with(DayOfWeek.SUNDAY));
				break;
			}
		}
			tf_sanPhamCaoNhat.setText(sanPham_dao.SanPhamDoanhThuCaoNhat(nv.getMaNhanVien(), timeStart, timeEnd));
			tf_sanPhamThapNhat.setText(sanPham_dao.SanPhamDoanhThuThapNhat(nv.getMaNhanVien(), timeStart, timeEnd));
		} else if (o.equals(btn_baoCao)) {
			//Định dạng là chọn thư mục để lưu file
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
	        int result = fileChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
	            ExportFileExcel(list, selectedFilePath);
	            
	        }
		}
	}

	public ArrayList<HoaDon> FillterHoaDon() {
		Date timeStart = null;
		Date timeEnd = null;
		double priceStart = -1;
		double priceEnd = -1;
		int maKH = -1;
		int maNV = -1;
		ArrayList<HoaDon> temp = null;
		String item = (String) cbbTime.getSelectedItem();
		LocalDate now = LocalDate.now();
		switch (item) {
		case "Ngày này": {
			timeStart = Date.valueOf(now);
			timeEnd = Date.valueOf(now);
			break;
		}
		case "Tháng này": {
			LocalDate firstDate = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
			timeStart = Date.valueOf(firstDate);
			timeEnd = Date.valueOf(firstDate.plusMonths(1).minusDays(1));
			break;
		}
		case "Tuần này": {
			timeStart = Date.valueOf(now.with(DayOfWeek.MONDAY));
			timeEnd = Date.valueOf(now.with(DayOfWeek.SUNDAY));
			break;
		}
		}
		maNV = nv.getMaNhanVien();

		temp = hoaDon_DAO.HoaDonFilter(timeStart, timeEnd, priceStart, priceEnd, maKH, maNV);
		return temp;
	}
	
	public void ExportFileExcel(ArrayList<HoaDon> list, String filePath) {
		XSSFWorkbook wordkbook = new XSSFWorkbook();
        XSSFSheet sheet=wordkbook.createSheet("Danh sách hóa đơn");
        XSSFRow row =null;
        Cell cell=null;
        
        String item = (String) cbbTime.getSelectedItem();
        String tieuDe = "Báo cáo bán hàng " + (item.equalsIgnoreCase("Tất cả") ? "" : item);
        row=sheet.createRow(1);
        cell=row.createCell(1,CellType.STRING);
        cell.setCellValue(tieuDe);
        
        row=sheet.createRow(3);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellValue("Mã Hóa Đơn");
        cell=row.createCell(1,CellType.STRING);
        cell.setCellValue("Khách Hàng");
        cell=row.createCell(2,CellType.STRING);
        cell.setCellValue("Ngày Mua");
        cell=row.createCell(3,CellType.STRING);
        cell.setCellValue("Tổng tiền");
        
        for (int i = 0; i < list.size(); i++) {
        	row = sheet.createRow(i + 4);
        	cell=row.createCell(0,CellType.STRING);
            cell.setCellValue(list.get(i).getMaDon());
            cell=row.createCell(1,CellType.STRING);
            cell.setCellValue(list.get(i).getKhachHang().getMaKhachHang() != -1 ? Integer.toString( list.get(i).getKhachHang().getMaKhachHang()) : "Không có");
            cell=row.createCell(2,CellType.STRING);
            cell.setCellValue(list.get(i).getNgayMua().toString());
            cell=row.createCell(3,CellType.STRING);
            cell.setCellValue(currencyFormat.format(list.get(i).getTongTien()) );
        }
        
        row=sheet.createRow(3 + list.size() + 2);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellValue("Tổng doanh thu");
        cell=row.createCell(2,CellType.STRING);
        cell.setCellValue(tf_tongDoanhThu.getText());
        
        row=sheet.createRow(3 + list.size() + 4);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellValue("Doanh thu cao nhất:");
        cell=row.createCell(2,CellType.STRING);
        cell.setCellValue(tf_sanPhamCaoNhat.getText());      
        
        row=sheet.createRow(3 + list.size() + 5);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellValue("Doanh thu thấp nhất:");
        cell=row.createCell(2,CellType.STRING);
        cell.setCellValue(tf_sanPhamThapNhat.getText());
        
        try {
        	File f = new File(filePath + "//BaoCaoBanHang.xlsx");
            FileOutputStream fis = new FileOutputStream(f);
            wordkbook.write(fis);
            fis.close();
            JOptionPane.showMessageDialog(this, "Xuất thành công");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		double TongTien = 0;

		for(HoaDon hd : list) {
			TongTien += hd.getTongTien();
		}

		tf_tongDoanhThu.setText(currencyFormat.format(TongTien));
	}

}

package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.sql.Date;

import javax.naming.ldap.LdapName;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.TaiKhoan;

public class HoaDon_GUI extends JFrame implements ActionListener, MouseListener, DocumentListener {
	
	private TaiKhoan tk;
	private JLabel title;
	private JTextField search;
	private JButton addHD;
	private JButton removeHD;
	private DefaultTableModel modelListHD;
	private JTable tbListHD;
	private JComboBox<String> cbbTime;
	private JRadioButton radioButtocbbTime;
	private JRadioButton radioButtonPeriod;
	private JDateChooser statrtDate;
	private JDateChooser enddate;
	private JComboBox<String> cbbPrice;
	private JTextField txtInputMaKH;
	private JTextField txtInputMaNV;
	private JButton btnFillter;
	private JButton btnUndo;
	private JButton btnXemChiTiet;
	private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
	private ArrayList<HoaDon> list = null;
	private JTextField txtCen1;
	private JTextField txtCen2;
	private JTextField txtCen21;
	private JTextField txtCen4;
	private JTextField txtCen41;
	private JTableHeader headerTable;
	private JButton exportHD;
	private JPopupMenu popupMenu;
	private JMenuItem refresh;
	private JMenuItem popupExportExcel;
	private JMenuItem popupXoa;
	private JMenuItem popupXemChiTiet;
	private ButtonGroup btGoupTime;
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
	private JButton importHD;
	private JMenuItem popupImportExcel;
	private String[] sort = {"ASC", "ASC", "ASC", "ASC", "ASC"};
	private String[] headerVal = {"maDon", "maKhachHang", "maNhanVien", "ngayMua", "tongTien"};
	
	public HoaDon_GUI() {
		super();
		setSize(1200, 800);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");
		
		//kết nối DB
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		//Tạo menu
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		JMenu trangChu = new JMenu("Trang Chủ");
		menu.add(trangChu);
		
		//Phần North
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.X_AXIS));
		pnNorth.setBorder(BorderFactory.createEmptyBorder(20, 10, 50, 10));
		
		// Tạo title
		title = new JLabel("Quản lý hóa Đơn");
		title.setFont(new Font(getName(), Font.BOLD, 20));
		title.setPreferredSize(new Dimension(250, HEIGHT + 20));
		title.setForeground(Color.BLUE);
		title.setAlignmentX(LEFT_ALIGNMENT);
		pnNorth.add(title);
		
		//Tạo ô nhập tìm kiếm
		JLabel searchTitle = new JLabel("Tìm kiếm: ");
		searchTitle.setAlignmentX(BOTTOM_ALIGNMENT);
		searchTitle.setFont(new Font(getName(), ABORT, 20));
		search = new JTextField();
		search.getDocument().addDocumentListener(this);
		pnNorth.add(searchTitle);
		pnNorth.add(search);
		pnNorth.add(Box.createHorizontalStrut(50));
		
		//Tạo chức năng
		addHD = new JButton("Thêm mới");
		addHD.setMnemonic('a');
		removeHD = new JButton("Xóa");
		removeHD.setMnemonic('d');
		removeHD.addActionListener(this);
		exportHD = new JButton("Xuất File Excel");
		exportHD.setMnemonic('e');
		exportHD.addActionListener(this);
		importHD = new JButton("Import File Excel");
		importHD.setMnemonic('i');
		importHD.addActionListener(this);
		
		pnNorth.add(addHD);
		pnNorth.add(removeHD);
		pnNorth.add(exportHD);
		pnNorth.add(importHD);
		
		//Phần Trung Tâm
		
		JPanel pnCen = new JPanel();
		pnCen.setLayout(new BoxLayout(pnCen, BoxLayout.Y_AXIS));
		pnCen.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		add(pnCen, BorderLayout.CENTER);
		
		//Tạo ô nhập liệu
		Box bCen1 = Box.createHorizontalBox();
		JLabel lbCen1 = new JLabel("Mã Hóa Đơn: ");
		lbCen1.setPreferredSize(new Dimension(120, HEIGHT));
		txtCen1 = new JTextField();
		bCen1.add(lbCen1);
		bCen1.add(txtCen1);
		pnCen.add(bCen1);
		pnCen.add(Box.createVerticalStrut(10));
		
		Box bCen2 = Box.createHorizontalBox();
		JLabel lbCen2 = new JLabel("Khách Hàng: ");
		lbCen2.setPreferredSize(new Dimension(120, HEIGHT));
		txtCen2 = new JTextField();
		JLabel lbCen21 = new JLabel("Nhân Viên: ");
		lbCen21.setPreferredSize(new Dimension(120, HEIGHT));
		txtCen21 = new JTextField();
		bCen2.add(lbCen2);
		bCen2.add(txtCen2);
		bCen2.add(Box.createHorizontalStrut(10));
		bCen2.add(lbCen21);
		bCen2.add(txtCen21);
		pnCen.add(bCen2);
		pnCen.add(Box.createVerticalStrut(10));
		
		Box bCen4 = Box.createHorizontalBox();
		JLabel lbCen4 = new JLabel("Ngày mua: ");
		lbCen4.setPreferredSize(new Dimension(120, HEIGHT));
		txtCen4 = new JTextField();
		JLabel lbCen41 = new JLabel("Tổng tiền: ");
		lbCen41.setPreferredSize(new Dimension(120, HEIGHT));
		txtCen41 = new JTextField();
		bCen4.add(lbCen4);
		bCen4.add(txtCen4);
		bCen4.add(Box.createHorizontalStrut(10));
		bCen4.add(lbCen41);
		bCen4.add(txtCen41);
		pnCen.add(bCen4);
		
		pnCen.add(Box.createVerticalStrut(30));
		JPanel pnTitle = new JPanel();
		JLabel title = new JLabel("Danh Sách Hóa Đơn");
		title.setFont(new Font(getName(), Font.BOLD, 25));
		pnTitle.add(title);

		pnCen.add(pnTitle);
		
		//Tạo table
		String[] header = {"Mã hóa đơn", "Khách hàng", "Nhân viên", "Ngày mua", "Tổng tiền"};
		modelListHD = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbListHD = new JTable(modelListHD);
		tbListHD.addMouseListener(this);
		tbListHD.setRowHeight(25);
		
		//Khởi tạo biến sắp xếp
		
		headerTable = tbListHD.getTableHeader();
		headerTable.addMouseListener(this);
		JScrollPane spListHD = new JScrollPane(tbListHD);
		pnCen.add(spListHD);
		
		//đưa dữ liệu lên table
		list = hoaDon_DAO.getAllHoaDon();
		updateTable(list);
		
		//Phần West
		JPanel pnWest = new JPanel();
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));
		pnWest.setPreferredSize(new Dimension(200, HEIGHT));
		add(pnWest, BorderLayout.WEST);
		
		//Tạo phần lọc theo thời gian
		JPanel fillterTime = new JPanel();
		fillterTime.setLayout(new BoxLayout(fillterTime, BoxLayout.Y_AXIS));
		fillterTime.setBorder(BorderFactory.createTitledBorder("Thời gian"));

		pnWest.add(fillterTime);
		pnWest.add(Box.createVerticalStrut(20));
		
		//Tạo comboBox chọn theo ngày, theo tuần, theo tháng
		Box b1= new Box(BoxLayout.X_AXIS);
		radioButtocbbTime = new JRadioButton();
		String[] time = {"Ngày này", "Tuần này", "Tháng này", "Năm này"};
		cbbTime = new JComboBox<String>(time);
		b1.add(radioButtocbbTime);
		b1.add(cbbTime);
		fillterTime.add(b1);
		fillterTime.add(Box.createVerticalStrut(20));
		
		//Tạo input chọn ngày cụ thể
		Box b2= new Box(BoxLayout.X_AXIS);
		radioButtonPeriod = new JRadioButton();
		Box b21 = new Box(BoxLayout.Y_AXIS);
		statrtDate = new JDateChooser();
		enddate = new JDateChooser();

		b21.add(statrtDate);
		b21.add(enddate);
		b21.add(Box.createVerticalStrut(10));
		b2.add(radioButtonPeriod);
		b2.add(b21);
		fillterTime.add(b2);
		
		//Tạo button group cho 2 Radiobutton ở panel thời gian
		btGoupTime = new ButtonGroup();
		btGoupTime.add(radioButtocbbTime);
		btGoupTime.add(radioButtonPeriod);
		
		//Tạo phần lọc theo giá tiền
		JPanel fillterPrice = new JPanel();
		fillterPrice.setLayout(new BoxLayout(fillterPrice, BoxLayout.Y_AXIS));
		fillterPrice.setBorder(BorderFactory.createTitledBorder("Giá tiền"));
		pnWest.add(fillterPrice);
		pnWest.add(Box.createVerticalStrut(20));
		
		String[] priceStrings = {"0 - 500.000", "500.000 - 1.000.000", "Trên 1.000.000"};
		cbbPrice = new JComboBox<String>(priceStrings);
		cbbPrice.setSelectedIndex(-1);
		fillterPrice.add(cbbPrice);
		
		//Tạo phần lọc theo đối tượng
		JPanel fillerObject = new JPanel();
		fillerObject.setLayout(new BoxLayout(fillerObject, BoxLayout.Y_AXIS));
		fillerObject.setBorder(BorderFactory.createTitledBorder("Đồi tượng"));
		pnWest.add(fillerObject);
		pnWest.add(Box.createVerticalStrut(20));
		
		Box box1 = Box.createVerticalBox();
		box1.add(new JLabel("Mã khách hàng"));
		txtInputMaKH = new JTextField();
		box1.add(txtInputMaKH);
		fillerObject.add(box1);
		fillerObject.add(Box.createVerticalStrut(20));
		
		Box box2 = Box.createVerticalBox();
		box2.add(new JLabel("Mã Nhân Viên"));
		txtInputMaNV = new JTextField();
		box2.add(txtInputMaNV);
		fillerObject.add(box2);
		
		//Tạo button lọc và button hoàn tác
		JPanel fillter = new JPanel();
		fillter.setLayout(new BoxLayout(fillter, BoxLayout.X_AXIS));
		fillter.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		pnWest.add(fillter);
		
		//button loc
		btnFillter = new JButton("Lọc");
		btnFillter.addActionListener(this);
		btnFillter.setMnemonic('f');
		fillter.add(Box.createHorizontalStrut(20));
		fillter.add(btnFillter);
		fillter.add(Box.createHorizontalStrut(20));
		btnUndo = new JButton("Hoàn tác");
		btnUndo.setMnemonic('R');
		btnUndo.addActionListener(this);
		fillter.add(btnUndo);
		fillter.add(Box.createHorizontalStrut(20));
		pnWest.add(Box.createVerticalStrut(400));
		
		//Tạo phần pnSouth
		JPanel pnSouth = new JPanel();
		pnSouth.setBorder(BorderFactory.createLineBorder(Color.black));
		pnSouth.setPreferredSize(new Dimension(WIDTH, 50));
		add(pnSouth, BorderLayout.SOUTH);
		
		btnXemChiTiet = new JButton("Xem Chi Tiết Hóa Đơn");
		btnXemChiTiet.addActionListener(this);
		pnSouth.add(btnXemChiTiet);
		
		
		add(pnNorth, BorderLayout.NORTH);
		setVisible(true);
		
		//Tạo popupmenu Chính
		popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(100, 140);
		
		JMenuItem popupThem = new JMenuItem("Thêm");
		popupXoa = new JMenuItem("Xóa");
		popupXoa.addActionListener(this);
		refresh = new JMenuItem("Refresh");
		refresh.addActionListener(this);
		popupXemChiTiet = new JMenuItem("Xem chi tiết");
		popupXemChiTiet.addActionListener(this);
		JMenu popupFile = new JMenu("File");
		popupExportExcel = new JMenuItem("Export file excel");
		popupExportExcel.addActionListener(this);
		popupImportExcel = new JMenuItem("Import file excel");
		popupImportExcel.addActionListener(this);
		
		popupMenu.add(popupThem);
		popupMenu.add(popupXoa);
		popupMenu.add(popupXemChiTiet);
		popupMenu.add(refresh);
		popupMenu.addSeparator();
		popupMenu.add(popupFile);
		popupFile.add(popupImportExcel);
		popupFile.add(popupExportExcel);
		
		this.addMouseListener(this);
		
	}

	public void updateTable(ArrayList<HoaDon> list) {
		modelListHD.setRowCount(0);
		try {
			for (HoaDon hoaDon : list) {
				modelListHD.addRow(new Object[] {
						hoaDon.getMaDon(),
						hoaDon.getKhachHang().getMaKhachHang(),
						hoaDon.getNhanVien().getMaNhanVien(),
						hoaDon.getNgayMua(),
						currencyFormat.format(hoaDon.getTongTien())
				});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void updateList() {
		list = hoaDon_DAO.getAllHoaDon();
	}
	
	public ArrayList<HoaDon> searchByKeyWord() {
		String keyword = search.getText();
		ArrayList<HoaDon> temp = new ArrayList<HoaDon>();
		for (HoaDon hoaDon : list) {
			if (Integer.toString(hoaDon.getMaDon()).contains(keyword)) {
				temp.add(hoaDon);
			}
		}
		return temp;
	}
	
	public void clearALL() {
		txtCen1.setText("");
		txtCen2.setText("");
		txtCen21.setText("");
		txtCen4.setText("");
		txtCen41.setText("");
		tbListHD.clearSelection();
		btGoupTime.clearSelection();
		cbbPrice.setSelectedIndex(-1);
		txtInputMaKH.setText("");
		txtInputMaNV.setText("");
		statrtDate.setDate(null);
		enddate.setDate(null);
	}
	
	public ArrayList<HoaDon> FillterHoaDon() {
		Date timeStart = null;
		Date timeEnd = null;
		double priceStart = -1;
		double priceEnd = -1;
		int maKH = -1;
		int maNV = -1;
		ArrayList<HoaDon> temp = null;
		if (radioButtocbbTime.isSelected()) {
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
				case "Năm này": {
					timeStart = Date.valueOf(LocalDate.of(now.getYear(), 1, 1));
					timeEnd = Date.valueOf(LocalDate.of(now.getYear(), 12, 31));
					break;
				}
			}
		} else if (radioButtonPeriod.isSelected()) {
			if (statrtDate.getDate() != null) {
				timeStart = new Date(statrtDate.getDate().getTime());
			}
			if (enddate.getDate() != null) {
				timeEnd = new Date(enddate.getDate().getTime());	
			}
		} 
		if (cbbPrice.getSelectedItem() != null) {
			String removeString = ((String) cbbPrice.getSelectedItem()).replaceAll("[^-\\d]", "");
			String[] price = removeString.split("-");
			try {
				priceStart = Double.parseDouble(price[0]);
				priceEnd = Double.parseDouble(price[1]);
			} catch (Exception e) {
			}
		}
		maKH = txtInputMaKH.getText().isEmpty() ? -1 : Integer.parseInt(txtInputMaKH.getText());
		maNV = txtInputMaNV.getText().isEmpty() ? -1 : Integer.parseInt(txtInputMaNV.getText());
		
		temp = hoaDon_DAO.HoaDonFilter(timeStart, timeEnd, priceStart, priceEnd, maKH, maNV);
		return temp;
	}
	
	public void ExportFileExcel(ArrayList<HoaDon> list, String filePath) {
		XSSFWorkbook wordkbook = new XSSFWorkbook();
        XSSFSheet sheet=wordkbook.createSheet("Danh sách hóa đơn");
        XSSFRow row =null;
        Cell cell=null;
        
        row=sheet.createRow(2);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellValue("DANH SÁCH HÓA ĐƠN");
        
        row=sheet.createRow(3);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellValue("Mã Hóa Đơn");
        cell=row.createCell(1,CellType.STRING);
        cell.setCellValue("Khách Hàng");
        cell=row.createCell(2,CellType.STRING);
        cell.setCellValue("Nhân viên");
        cell=row.createCell(3,CellType.STRING);
        cell.setCellValue("Ngày Mua");
        cell=row.createCell(4,CellType.STRING);
        cell.setCellValue("Tổng tiền");
        
        for (int i = 0; i < list.size(); i++) {
        	row = sheet.createRow(i + 4);
        	cell=row.createCell(0,CellType.STRING);
            cell.setCellValue(list.get(i).getMaDon());
            cell=row.createCell(1,CellType.STRING);
            cell.setCellValue(list.get(i).getKhachHang().getMaKhachHang() != -1 ? Integer.toString( list.get(i).getKhachHang().getMaKhachHang()) : "Không có");
            cell=row.createCell(2,CellType.STRING);
            cell.setCellValue(list.get(i).getNhanVien().getMaNhanVien());
            cell=row.createCell(3,CellType.STRING);
            cell.setCellValue(list.get(i).getNgayMua().toString());
            cell=row.createCell(4,CellType.STRING);
            cell.setCellValue(list.get(i).getTongTien());
        }
        try {
        	File f = new File(filePath + "//invoice.xlsx");
            FileOutputStream fis = new FileOutputStream(f);
            wordkbook.write(fis);
            fis.close();
            JOptionPane.showMessageDialog(this, "Xuất thành công");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public ArrayList<HoaDon> ImportFileExcel(String filePath) {
		ArrayList<HoaDon> data = new ArrayList<HoaDon>();
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            for (int i = 4; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                
                    int maDon = (int) row.getCell(0).getNumericCellValue();
                    int maKhachHang = -1;
                    if (!row.getCell(1).getStringCellValue().equals("Không có")) {
                    	maKhachHang = Integer.parseInt(row.getCell(1).getStringCellValue());
                    }
                    int maNhanVien = (int) row.getCell(2).getNumericCellValue();
                    String dateString = row.getCell(3).getStringCellValue();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date ngayMua = null;
					try {
						ngayMua = new Date(dateFormat.parse(dateString).getTime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
                    double tongtien = row.getCell(4).getNumericCellValue();
                    HoaDon hd = new HoaDon(maDon, new KhachHang(maKhachHang), new NhanVien(maNhanVien), ngayMua, tongtien);
                    data.add(hd);
                }
            }
            workbook.close();
            file.close();
            JOptionPane.showMessageDialog(this, "Import Thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Định dạng file không hợp lệ");
        }
        
        return data;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (e.getButton() == MouseEvent.BUTTON3){
			popupMenu.show(e.getComponent(), e.getX(), e.getY());		
		} else if (src.equals(tbListHD)) {
			int index = tbListHD.getSelectedRow();
			HoaDon hd = list.get(index);
			txtCen1.setText(Integer.toString(hd.getMaDon()));
			txtCen2.setText(Integer.toString(hd.getKhachHang().getMaKhachHang()));
			txtCen21.setText(Integer.toString(hd.getNhanVien().getMaNhanVien()));
			txtCen4.setText(hd.getNgayMua().toString());
			txtCen41.setText(currencyFormat.format(hd.getTongTien()));
		} else if (src.equals(headerTable)) {
			int index = headerTable.columnAtPoint(e.getPoint());
			ArrayList<HoaDon> temp = hoaDon_DAO.getAllHoaDonOrderBY(headerVal[index], sort[index]);
			sort[index] = sort[index].equals("ASC") ? "DESC" : "ASC";
			updateTable(temp);
		} 
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
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
		Object src = e.getSource();
		if (src.equals(addHD)) {
			
		} else if (src.equals(removeHD) || src.equals(popupXoa)){
			int index = tbListHD.getSelectedRow();
			if (index < 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn Hóa Đơn để xóa");
			} else {
				int maHD = list.get(index).getMaDon();
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa!") == JOptionPane.OK_OPTION) {
					hoaDon_DAO.xoaHoaDon(maHD);
					updateList();
					updateTable(list);
					clearALL();
				}
			}
		} else if (src.equals(exportHD) || src.equals(popupExportExcel)) {
			JFileChooser fileChooser = new JFileChooser();
			
			//Định dạng là chọn thư mục để lưu file
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
	        int result = fileChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
	            ExportFileExcel(list, selectedFilePath);
	            
	        }
		} else if (src.equals(refresh) || src.equals(btnUndo)) {
			updateTable(list);
			clearALL();
		} else if (src.equals(btnFillter)) {
			updateTable(FillterHoaDon());
		} else if (src.equals(importHD) || src.equals(popupImportExcel)) {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
			fileChooser.setFileFilter(filter);
			
	        int result = fileChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
	            ArrayList<HoaDon> data = ImportFileExcel(selectedFilePath);	
	            for (HoaDon hoaDon : data) {
					hoaDon_DAO.themHoaDon(hoaDon);
				}
	            updateList();
	            updateTable(list);
	        }
		} else if (src.equals(btnXemChiTiet) || src.equals(popupXemChiTiet)) {
			int index = tbListHD.getSelectedRow();
			if (index < 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn");
				return;
			}
			new ChiTietHoaDon_GUI(list.get(index)).setVisible(true);;;
		}
	}
	
	public static void main(String[] args) {
		new HoaDon_GUI();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateTable(searchByKeyWord());
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		updateTable(searchByKeyWord());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
	}

}

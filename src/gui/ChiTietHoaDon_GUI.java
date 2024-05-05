package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.aspose.words.Document;

import dao.ChiTietHoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class ChiTietHoaDon_GUI extends JDialog implements ActionListener{
	private HoaDon hoaDon;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt21;
	private JTextField txt3;
	private JTextField txt31;
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
	private DefaultTableModel dtm;
	private JTable tbChiTietHD;
	private ArrayList<ChiTietHoaDon> listCTHD;
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	private JButton printHD;
	private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
	private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
	private SanPham_DAO sanPham_DAO = new SanPham_DAO();
	
	public ChiTietHoaDon_GUI(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
		listCTHD = new ArrayList<ChiTietHoaDon>();
		chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
		listCTHD = chiTietHoaDon_DAO.getAllChiTietHoaDonTheoMaHoaDon(hoaDon.getMaDon());
		
		setModal(true);
		setTitle("Chi tiết hóa đơn");
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		JPanel pnNorth = new JPanel();
		JLabel title = new JLabel("Chi tiết hóa đơn");
		title.setFont(new Font(getName(), Font.BOLD, 20));
		pnNorth.add(title);
		add(pnNorth, BorderLayout.NORTH);
		
		//Panel Center
		JPanel pnCen = new JPanel();
		pnCen.setLayout(new BoxLayout(pnCen, BoxLayout.Y_AXIS));
		pnCen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box b1 = Box.createHorizontalBox();
		JLabel lb1 = new JLabel("Mã hóa đơn: ");
		lb1.setPreferredSize(new Dimension(120, HEIGHT));
		txt1 = new JTextField(Integer.toString(hoaDon.getMaDon()));
		txt1.setEditable(false);
		b1.add(lb1);
		b1.add(txt1);
		
		Box b2 = Box.createHorizontalBox();
		JLabel lb2 = new JLabel("Khách hàng: ");
		lb2.setPreferredSize(new Dimension(120, HEIGHT));
		txt2 = new JTextField(khachHang_DAO.getKhachHangTheoMa(hoaDon.getKhachHang().getMaKhachHang()).getTen());
		txt2.setEditable(false);
		b2.add(lb2);
		b2.add(txt2);
		b2.add(Box.createHorizontalStrut(10));
		
		JLabel lb21 = new JLabel("Nhân viên: ");
		lb21.setPreferredSize(new Dimension(120, HEIGHT));
		txt21 = new JTextField(nhanVien_DAO.getNhanVienTheoMaNV(hoaDon.getNhanVien().getMaNhanVien()).getTen());
		txt21.setEditable(false);
		b2.add(lb21);
		b2.add(txt21);
		
		Box b3 = Box.createHorizontalBox();
		JLabel lb3 = new JLabel("Ngày mua: ");
		lb3.setPreferredSize(new Dimension(120, HEIGHT));
		txt3 = new JTextField(hoaDon.getNgayMua().toString());
		txt3.setEditable(false);
		b3.add(lb3);
		b3.add(txt3);
		b3.add(Box.createHorizontalStrut(10));
		
		JLabel lb31 = new JLabel("Tổng tiền: ");
		lb31.setPreferredSize(new Dimension(120, HEIGHT));
		txt31 = new JTextField(currencyFormat.format(hoaDon.getTongTien()));
		txt31.setEditable(false);
		b3.add(lb31);
		b3.add(txt31);
		
		pnCen.add(b1);
		pnCen.add(Box.createVerticalStrut(10));
		pnCen.add(b2);
		pnCen.add(Box.createVerticalStrut(10));
		pnCen.add(b3);
		pnCen.add(Box.createVerticalStrut(20));
		
		Object[] obj = {"Mã Đơn", "Mã Sản phẩm", "Thành tiền", "Số lượng"};
		dtm = new DefaultTableModel(null, obj);
		tbChiTietHD = new JTable(dtm);
		JScrollPane scrTBChiTietHD = new JScrollPane(tbChiTietHD);
		updateTable();
		
		pnCen.add(scrTBChiTietHD);
		
		add(pnCen, BorderLayout.CENTER);
		
		//Panel West
		JPanel pnSouth = new JPanel();
		pnSouth.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		add(pnSouth, BorderLayout.SOUTH);
		
		printHD = new JButton("In hóa đơn");
		printHD.setMnemonic('p');
		printHD.addActionListener(this);
		
		pnSouth.add(printHD);
		
	}
	
	public void updateTable() {
		dtm.setRowCount(0);
		for (ChiTietHoaDon chiTietHoaDon : listCTHD) {
			dtm.addRow(new Object[] {
					chiTietHoaDon.getHoaDon().getMaDon(),
					sanPham_DAO.getSanPhamTheoMa(chiTietHoaDon.getSanPham().getMaSanPham()).getTen(),
					chiTietHoaDon.getThanhTien(),
					chiTietHoaDon.getSoLuong()
			});
		}
	}
	
	public void createHoaDon(String filePath, HoaDon hoaDon) {
		String inputFilePath = "data\\HoaDon\\HoaDon_Mau.docx";
		String outputFilePathWord = "data\\HoaDon\\HoaDon_" + hoaDon.getMaDon() + ".docx";
        String outputFilePathPDF = filePath + "invoice_" + hoaDon.getMaDon() +".pdf";
        
        String[] searchTokens = {"%MAHOADON%", "%MANHANVIEN%", "%TENNHANVIEN%", "%MAKHACHHANG%", "%TENKHACHHANG%", "%NGAYMUA%", "%TONGTIEN%"};
        String[] replacementTokens = {
	        		Integer.toString(hoaDon.getMaDon()), 
	        		Integer.toString(hoaDon.getNhanVien().getMaNhanVien()),
	        		nhanVien_DAO.getNhanVienTheoMaNV(hoaDon.getNhanVien().getMaNhanVien()).getTen(), 
	        		Integer.toString(hoaDon.getKhachHang().getMaKhachHang()), 
	        		khachHang_DAO.getKhachHangTheoMa(hoaDon.getKhachHang().getMaKhachHang()).getTen(), 
	        		hoaDon.getNgayMua().toString(), 
	        		Double.toString(hoaDon.getTongTien())
        		};

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             XWPFDocument document = new XWPFDocument(fis)) {
        	
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        for (int i = 0; i < searchTokens.length; i++) {
                            if (text.contains(searchTokens[i])) {
                                text = text.replace(searchTokens[i], replacementTokens[i]);
                            }
                        }
                        run.setText(text, 0);
                    }
                }
            }
            
            XWPFTable table = document.getTables().get(0);
            ArrayList<ChiTietHoaDon> listCTHD = chiTietHoaDon_DAO.getAllChiTietHoaDonTheoMaHoaDon(hoaDon.getMaDon());
            for (int i = 0; i < listCTHD.size(); i++ ) {
            	XWPFTableRow newRow = table.createRow();
                newRow.getCell(0).setText(Integer.toString(listCTHD.get(i).getSanPham().getMaSanPham()));
                newRow.getCell(1).setText(sanPham_DAO.getSanPhamTheoMa(listCTHD.get(i).getSanPham().getMaSanPham()).getTen());
                newRow.getCell(2).setText(Integer.toString(listCTHD.get(i).getSoLuong()));
                newRow.getCell(3).setText(Double.toString(sanPham_DAO.getSanPhamTheoMa(listCTHD.get(i).getSanPham().getMaSanPham()).getGiaSanPham()));
                newRow.getCell(4).setText(Double.toString(listCTHD.get(i).getThanhTien()));
            }

            try (FileOutputStream fos = new FileOutputStream(outputFilePathWord)) {
                document.write(fos);
            }
            
            Document doc = new Document(outputFilePathWord);
            doc.save(outputFilePathPDF);
            
            openFile(outputFilePathPDF);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		new HoaDon_GUI();
	}
	
	public void openFile(String filePath) {
		try {
			File wordFile = new File(filePath);

	        if (!wordFile.exists()) {
	            return;
	        }
	        Desktop desktop = Desktop.getDesktop();

	        if (desktop.isSupported(Desktop.Action.OPEN)) {
	            desktop.open(wordFile);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(printHD)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
	        int result = fileChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
	            createHoaDon(selectedFilePath, hoaDon);
	            JOptionPane.showMessageDialog(this, "In Hóa Đơn Thành Công");
	        }
		}
	}
}

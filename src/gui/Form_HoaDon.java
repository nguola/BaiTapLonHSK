package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Form_HoaDon extends JFrame{
	private JPanel jp_North;
	private JLabel lb_tieuDe;
	private JPanel jp_Center;
	private Box jp_South;
	private JPanel jp_thongTinSanPham;
	private Box jp_thongTinKhachHang;
	private DefaultTableModel modal_sanPham;
	private JTable table_sanPham;
	private JLabel lb_ten;
	private JLabel lb_tenKH;
	private JLabel lb_soDienThoaiKH;
	private JLabel lb_soDienThoai;
	private JLabel lb_loai;
	private JLabel lb_loaiKH;
	private JScrollPane scroll_sanPham;
	private JPanel jp_thanhTien;
	private JLabel lb_thanhTien;
	private JLabel lb_tien;
	private Box jp_thongTin;
	private JLabel lb_camOn;
	private Box jp_thongTinNguoiBan;
	private JLabel lb_ngayThang;
	private Box jp_thongTinHoaDon;
	private Box jp_thongTinNhanVien;
	private JLabel lb_thongTinNguoiBan;
	private JLabel lb_chuKi;

	public Form_HoaDon() {
		super();
		setSize(700, 800);
		this.setResizable(false);
		setLocationRelativeTo(null);
		
		// Code tiêu đề
		jp_North = new JPanel();
		jp_North.setBackground(Color.black);
		
		lb_tieuDe = new JLabel("Hóa đơn mua hàng");
		lb_tieuDe.setForeground(Color.white);
		lb_tieuDe.setFont(new Font("Arial", Font.BOLD, 35));
		
		jp_North.add(lb_tieuDe);
		add(jp_North, BorderLayout.NORTH);
		
		// Code chi tiết hóa đơn
		jp_Center = new JPanel(new BorderLayout());
		
		// Code thông tin khách hàng
		jp_thongTinKhachHang = Box.createVerticalBox();
		jp_thongTinKhachHang.add(Box.createVerticalStrut(20));
		
		JPanel jp_tenKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_ten = new JLabel("Tên khách hàng:");
		lb_ten.setFont(new Font("Arial", Font.PLAIN, 20));
		lb_tenKH = new JLabel("..............");
		jp_tenKH.add(Box.createHorizontalStrut(70));
		jp_tenKH.add(lb_ten);
		jp_tenKH.add(Box.createHorizontalStrut(20));
		jp_tenKH.add(lb_tenKH);
		
		JPanel jp_soDienThoai = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_soDienThoai = new JLabel("Số điện thoại:");
		lb_soDienThoai.setFont(new Font("Arial", Font.PLAIN, 20));
		lb_soDienThoaiKH = new JLabel("..............");
		jp_soDienThoai.add(Box.createHorizontalStrut(70));
		jp_soDienThoai.add(lb_soDienThoai);
		jp_soDienThoai.add(Box.createHorizontalStrut(20));
		jp_soDienThoai.add(lb_soDienThoaiKH);
		
		JPanel jp_loaiKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_loai = new JLabel("Khách hàng:");
		lb_loai.setFont(new Font("Arial", Font.PLAIN, 20));
		lb_loaiKH = new JLabel("..............");
		jp_loaiKH.add(Box.createHorizontalStrut(70));
		jp_loaiKH.add(lb_loai);
		jp_loaiKH.add(Box.createHorizontalStrut(20));
		jp_loaiKH.add(lb_loaiKH);
		
		
		jp_thongTinKhachHang.add(jp_tenKH);
		jp_thongTinKhachHang.add(jp_soDienThoai);
		jp_thongTinKhachHang.add(jp_loaiKH);
		
		jp_thongTinKhachHang.add(Box.createVerticalStrut(20));
		
		jp_Center.add(jp_thongTinKhachHang, BorderLayout.NORTH);
		
		// Code thông tin sản phẩm
		jp_thongTinSanPham = new JPanel();
		
		String[] table_header = {"STT", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Thành tiền"};
		modal_sanPham = new DefaultTableModel(table_header, 0);
		
		table_sanPham = new JTable(modal_sanPham);
		table_sanPham.setEnabled(false);
		
		scroll_sanPham = new JScrollPane(table_sanPham);
		scroll_sanPham.setPreferredSize(new Dimension(600, 400));
		
		jp_thongTinSanPham.add(scroll_sanPham, BorderLayout.CENTER);
		
		jp_thanhTien = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lb_thanhTien = new JLabel("Tổng tiền:");
		lb_thanhTien.setFont(new Font("Arial", Font.BOLD, 18));
		lb_tien = new JLabel("............");
		jp_thanhTien.add(lb_thanhTien);
		jp_thanhTien.add(Box.createHorizontalStrut(20));
		jp_thanhTien.add(lb_tien);
		
		jp_thongTinSanPham.add(jp_thanhTien);
		
		jp_Center.add(jp_thongTinSanPham, BorderLayout.CENTER);
		add(jp_Center, BorderLayout.CENTER);
		
		// Code thông tin hóa đơn
		jp_South = Box.createVerticalBox();
		
		jp_thongTin = Box.createHorizontalBox();
		lb_camOn = new JLabel("Cảm ơn quý khách!!");
		lb_camOn.setFont(new Font("Arial", Font.ITALIC, 25));
		
		jp_thongTinHoaDon = Box.createVerticalBox();
		lb_ngayThang = new JLabel("Ngày ..... tháng .... năm ....");
		lb_ngayThang.setFont(new Font("Arial", Font.ITALIC, 20));
		
		jp_thongTinNhanVien = Box.createVerticalBox();
		lb_thongTinNguoiBan = new JLabel("Thông tin người bán");
		lb_thongTinNguoiBan.setFont(new Font("Arial", Font.ITALIC, 17));
		lb_chuKi = new JLabel(".......");
		
		jp_thongTinNhanVien.add(lb_thongTinNguoiBan);
		jp_thongTinNhanVien.add(lb_chuKi);
		
		jp_thongTinHoaDon.add(lb_ngayThang);
		jp_thongTinHoaDon.add(jp_thongTinNhanVien);
		
		jp_thongTin.add(lb_camOn);
		jp_thongTin.add(Box.createHorizontalStrut(100));
		jp_thongTin.add(jp_thongTinHoaDon);
		
		jp_South.add(jp_thongTin);
		jp_South.add(Box.createVerticalStrut(50));
		
		add(jp_South, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Form_HoaDon();
	}
}

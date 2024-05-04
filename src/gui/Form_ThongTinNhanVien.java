package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class Form_ThongTinNhanVien extends JFrame{
	private JPanel jp_North;
	private JLabel lb_TieuDe;
	private JTextField tf_ma;
	private JTextField tf_ten;
	private JTextField tf_DT;
	private JTextField tf_gt;
	private JTextField tf_luong;
	private JTextField tf_loai;
	private NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));

	public Form_ThongTinNhanVien(TaiKhoan tk) {
		super();
		NhanVien nv = nhanVien_dao.getNhanVienTheoMaNV(tk.getNhanvien().getMaNhanVien());
		setSize(700, 500);
		setLocationRelativeTo(null);
		
		jp_North = new JPanel();
		jp_North.setBackground(new Color(35, 177, 77));

		lb_TieuDe = new JLabel("Thông tin nhân viên");
		lb_TieuDe.setForeground(Color.white);
		lb_TieuDe.setFont(new Font("Time new roman", Font.BOLD, 40));

		jp_North.add(lb_TieuDe);
		this.add(jp_North, BorderLayout.NORTH);
		
		Box jCenter = Box.createHorizontalBox();
		jCenter.add(Box.createHorizontalStrut(40));
		
		Box content_Trai = Box.createVerticalBox();
		ImageIcon NhanVien_icon = new ImageIcon("img/ThongTinNhanVienImg/NhanVien_icon.png");
		Image scaled = scaleImage(NhanVien_icon.getImage(), 140, 140);
		JLabel NhanVien = new JLabel(new ImageIcon(scaled));
		
		JLabel Ten = new JLabel();
		Ten.setText(nv.getTen());
		Ten.setFont(new Font("Arial", Font.BOLD, 15));
		
		content_Trai.add(NhanVien);
		content_Trai.add(Box.createVerticalStrut(10));
		content_Trai.add(Ten);
		content_Trai.add(Box.createVerticalStrut(150));
		jCenter.add(content_Trai);
		jCenter.add(Box.createHorizontalStrut(40));
		
		Box content_Phai = Box.createVerticalBox();
		
		JPanel maNV = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ma = new JLabel("Mã nhân viên:");
		tf_ma = new JTextField(30);
		tf_ma.setEditable(false);
		tf_ma.setText(String.valueOf(nv.getMaNhanVien()));
		maNV.add(ma);
		maNV.add(Box.createHorizontalStrut(10));
		maNV.add(tf_ma);
		
		JPanel tenNV = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel tennhanvien = new JLabel("Tên nhân viên:");
		tf_ten = new JTextField(30);
		tf_ten.setEditable(false);
		tf_ten.setText(nv.getTen());
		tenNV.add(tennhanvien);
		tenNV.add(Box.createHorizontalStrut(5));
		tenNV.add(tf_ten);

		
		JPanel soDT = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel DT = new JLabel("Số điện thoại:");
		tf_DT = new JTextField(30);
		tf_DT.setEditable(false);
		tf_DT.setText(nv.getSoDienThoai());
		soDT.add(DT);
		soDT.add(Box.createHorizontalStrut(12));
		soDT.add(tf_DT);

		
		JPanel gioiTinh = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel phai = new JLabel("Giới tính:");
		tf_gt = new JTextField(30);
		tf_gt.setEditable(false);
		tf_gt.setText((nv.getGioiTinh() == true) ? "Nam" : "Nữ");
		gioiTinh.add(phai);
		gioiTinh.add(Box.createHorizontalStrut(37));
		gioiTinh.add(tf_gt);

		
		JPanel Luong = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel luongNV = new JLabel("Lương:");
		tf_luong = new JTextField(30);
		tf_luong.setEditable(false);
		tf_luong.setText(currencyFormat.format(nv.getLuong()));
		Luong.add(luongNV);
		Luong.add(Box.createHorizontalStrut(48));
		Luong.add(tf_luong);
		
		JPanel Loai = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel loaiNV = new JLabel("Loại nhân viên:");
		tf_loai = new JTextField(30);
		tf_loai.setEditable(false);
		tf_loai.setText(nv.getLoai());
		Loai.add(loaiNV);
		Loai.add(Box.createHorizontalStrut(4));
		Loai.add(tf_loai);
		
		content_Phai.add(Box.createVerticalStrut(50));
		content_Phai.add(maNV);
		content_Phai.add(tenNV);
		content_Phai.add(soDT);
		content_Phai.add(gioiTinh);
		content_Phai.add(Luong);
		content_Phai.add(Loai);
		content_Phai.add(Box.createVerticalStrut(50));
		
		jCenter.add(content_Phai);
		add(jCenter);
		
		setVisible(true);
	}
	
	private Image scaleImage(Image image, int w, int h) {
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return scaled;
	}
}

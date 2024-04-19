package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.KhachHang;

public class DialogThemKhachHang extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblDiaChi, lblLoaiKhachHang;
	private boolean trangThaiThem = false;
	private KhachHang khachhang = new KhachHang();
	private JTextField txtMa, txtTen, txtSoDienThoai, txtDiaChi, txtLoai;
	private JButton btnThem;
	
	
}

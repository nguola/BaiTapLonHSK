package gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.KhuVuc_DAO;
import dao.KhuyenMai_DAO;
import dao.SanPham_DAO;
import entity.KhuVuc;
import entity.KhuyenMai;
import entity.SanPham;

public class ThongTinSanPham_GUI extends JDialog {
	private SanPham_DAO sanPham_dao = new SanPham_DAO();
	private KhuyenMai_DAO khuyenMai_dao = new KhuyenMai_DAO();
	private KhuVuc_DAO khuVuc_dao = new KhuVuc_DAO();
	private JTextField tf_ma;
	private JTextField tf_ten;
	private JTextField tf_gia;
	private JTextField tf_khuVuc;
	private JTextField tf_khuyenMai;
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
	
	public ThongTinSanPham_GUI(int maSanPham) {
		setModal(true);
		setSize(550, 300);
		setLocationRelativeTo(null);
		SanPham sp = sanPham_dao.getSanPhamTheoMa(maSanPham);
		
		Box panel = Box.createHorizontalBox();
		
		Box hinh = Box.createVerticalBox();
		String url = "img/ThongTinSanPhamimg/"+ sp.getTen() +".jpg";
		ImageIcon sanPham_icon = new ImageIcon(url);
		Image scaled = scaleImage(sanPham_icon.getImage(), 160, 180);
		JLabel sanPham = new JLabel(new ImageIcon(scaled));
		hinh.add(sanPham);
		
		Box thongTin = Box.createVerticalBox();
		
		JPanel maSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ma = new JLabel("Mã sản phẩm:");
		tf_ma = new JTextField(30);
		tf_ma.setEditable(false);
		tf_ma.setText(String.valueOf(sp.getMaSanPham()));
		maSP.add(ma);
		maSP.add(Box.createHorizontalStrut(10));
		maSP.add(tf_ma);
		
		JPanel tenSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel ten = new JLabel("Tên sản phẩm:");
		tf_ten = new JTextField(30);
		tf_ten.setEditable(false);
		tf_ten.setText(sp.getTen());
		tenSP.add(ten);
		tenSP.add(Box.createHorizontalStrut(10));
		tenSP.add(tf_ten);
		
		JPanel giaSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel gia = new JLabel("Giá:");
		tf_gia = new JTextField(30);
		tf_gia.setEditable(false);
		tf_gia.setText(currencyFormat.format(sp.getGiaSanPham()));
		giaSP.add(gia);
		giaSP.add(Box.createHorizontalStrut(10));
		giaSP.add(tf_gia);
		
		JPanel khuVucSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel khuVuc = new JLabel("Khu Vuc:");
		tf_khuVuc = new JTextField(30);
		tf_khuVuc.setEditable(false);
		KhuVuc kv = khuVuc_dao.getKhuVucTheoMa(sp.getMaKhuVuc().getMaKhuVuc());
		tf_khuVuc.setText(kv.getTenKhuVuc());
		khuVucSP.add(khuVuc);
		khuVucSP.add(Box.createHorizontalStrut(10));
		khuVucSP.add(tf_khuVuc);
		
		JPanel khuyenMaiSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel khuyenMai = new JLabel("Khuyến mãi:");
		tf_khuyenMai = new JTextField(30);
		tf_khuyenMai.setEditable(false);
		KhuyenMai km = khuyenMai_dao.getKhuyenMaiTheoMa(sp.getMaKhuyenMai().getMaKhuyenMai());
		tf_khuyenMai.setText("Giảm " + (int)(km.getGiamGia()*100) + "% " + km.getDieuKien());
		khuyenMaiSP.add(khuyenMai);
		khuyenMaiSP.add(Box.createHorizontalStrut(10));
		khuyenMaiSP.add(tf_khuyenMai);
		
		thongTin.add(maSP);
		thongTin.add(tenSP);
		thongTin.add(giaSP);
		thongTin.add(khuVucSP);
		thongTin.add(khuyenMaiSP);
		
		panel.add(Box.createHorizontalStrut(20));
		panel.add(hinh);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(thongTin);
		panel.add(Box.createHorizontalStrut(20));
		
		add(panel);
		
		setVisible(true);
	}
	
	private Image scaleImage(Image image, int w, int h) {
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return scaled;
	}
}
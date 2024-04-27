package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dao.KhachHang_DAO;
import entity.KhachHang;

public class DialogSuaKhachHang extends JDialog implements ActionListener {
	private final JPanel contentPanel = new JPanel();
	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblDiaChi, lblLoaiKhachHang, lblTitle;
	private boolean trangThaiSua = false;
	private JTextField txtMa, txtTen, txtSoDienThoai, txtDiaChi;
	private JButton btnSua;
	JComboBox<String> cbxLoai;
	private KhachHang khachhang = new KhachHang();
	private Panel_QuanLiKhachHang panelQuanLiKhachHang;

	public boolean isTrangThaiSua() {
		return trangThaiSua;
	}
	public void setTrangThaiSua(boolean trangThaiSua) {
		this.trangThaiSua = trangThaiSua;
	}
	public JTextField getTxtMa() {return txtMa;}
	
	public JTextField getTxtTen() {return txtTen;}
	
	public JTextField getTxtSoDienThoai() {return txtSoDienThoai;}
	
	public JTextField getTxtDiaChi() {return txtDiaChi;}
	
	public JComboBox<String> getTxtLoai() {return cbxLoai;}
	
	public static void main(String[] args) {
		Panel_QuanLiKhachHang panel = new Panel_QuanLiKhachHang();
		try {
			DialogSuaKhachHang dialog = new DialogSuaKhachHang("Sửa",2000,panel);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DialogSuaKhachHang(String type, int maKhachHang, Panel_QuanLiKhachHang panel) {
		setModal(true);//chặn tất cả các sự kiện tương tác với các cửa sổ khác của ứng dụng cho đến khi nó được đóng
		this.panelQuanLiKhachHang = panel;
		setSize(360, 500);
		this.setResizable(false);
		setLocationRelativeTo(null);
		
		lblTitle = new JLabel("Sửa Khách Hàng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));

		lblTenKhachHang = new JLabel("Tên khách hàng:");
		txtTen = new JTextField(30);
		txtTen.setPreferredSize(new Dimension(100, 30));

		lblSoDienThoai = new JLabel("Số điện thoại:");
		txtSoDienThoai = new JTextField(30);
		txtSoDienThoai.setPreferredSize(new Dimension(100, 30));

		lblDiaChi = new JLabel("Địa chỉ:");
		txtDiaChi = new JTextField(30);
		txtDiaChi.setPreferredSize(new Dimension(100, 30));

		lblLoaiKhachHang = new JLabel("Loại khách hàng:");
		String[] item = {"Thường","VIP"};
		cbxLoai = new JComboBox<String>(item);
		cbxLoai.setPreferredSize(new Dimension(100, 30));

		btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(0, 255, 204));
		btnSua.setPreferredSize(new Dimension(140,40));
		btnSua.setFont(new Font("Arial", Font.BOLD, 16));

		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		// title
		JPanel jpTitle = new JPanel();
		jpTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpTitle.setPreferredSize(new Dimension(100, 50));
		jpTitle.setBackground(new Color(7, 65, 115));
		jpTitle.add(lblTitle);
		add(jpTitle,BorderLayout.NORTH);

		// prow2
		JPanel prow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prow2.add(lblTenKhachHang);
		prow2.add(txtTen);
		contentPanel.add(prow2);

		// prow3
		JPanel prow3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prow3.add(lblSoDienThoai);
		prow3.add(Box.createHorizontalStrut(10));
		prow3.add(txtSoDienThoai);
		contentPanel.add(prow3);

		// prow4
		JPanel prow4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prow4.add(lblDiaChi);
		prow4.add(Box.createHorizontalStrut(40));
		prow4.add(txtDiaChi);
		contentPanel.add(prow4);

		// prow5
		JPanel prow5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prow5.add(lblLoaiKhachHang);
		prow5.add(cbxLoai);
		contentPanel.add(prow5);

		// Button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(btnSua);
		contentPanel.add(buttonPanel);

		add(contentPanel);
		if(type.equals("sua")) {
			btnSua.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					 	String ten = txtTen.getText();
				        String soDienThoai = txtSoDienThoai.getText();
				        String diaChi = txtDiaChi.getText();
				        String loaiKhachHang = (String) cbxLoai.getSelectedItem();
				        KhachHang khachHang = new KhachHang(maKhachHang, ten, soDienThoai, diaChi, loaiKhachHang);
					if(!new KhachHang_DAO().update(khachHang)) {
						JOptionPane.showMessageDialog(null, "Sửa không thành công!");
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thành công.");
						panelQuanLiKhachHang.UpdateTable();
						setTrangThaiSua(true);
						dispose();
					}
					docDuLieuVaoFormSua(maKhachHang);
				}
			});
		}
	}
	public void docDuLieuVaoFormSua(int maKhachHang) {
		khachhang = new KhachHang_DAO().getKhachHangTheoMa(maKhachHang);
		txtTen.setText(khachhang.getTen());
		txtSoDienThoai.setText(khachhang.getSoDienThoai());
		txtDiaChi.setText(khachhang.getDiaChi());
		cbxLoai.setSelectedItem(khachhang.loaiKhachHang);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
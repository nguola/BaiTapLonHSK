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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	private JTextField txtMa, txtTen, txtSoDienThoai, txtDiaChi, txtLoai;
	private JButton btnSua;
	private KhachHang khachhang = new KhachHang();

	public boolean isTrangThaiSua() {
		return trangThaiSua;
	}

	public void setTrangThaiSua(boolean trangThaiSua) {
		this.trangThaiSua = trangThaiSua;
	}

	public static void main(String[] args) {
		try {
			DialogSuaKhachHang dialog = new DialogSuaKhachHang("Sửa");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DialogSuaKhachHang(String type) {
		this.setResizable(false);
		setLocationRelativeTo(null);
		lblTitle = new JLabel("Sửa Khách Hàng");
		lblTitle.setBackground(Color.LIGHT_GRAY);
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
		txtLoai = new JTextField(30);
		txtLoai.setPreferredSize(new Dimension(100, 30));

		btnSua = new JButton("Sửa");

		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		// title
		add(Box.createHorizontalStrut(50));
		add(lblTitle, BorderLayout.NORTH);
		add(Box.createVerticalStrut(20));

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
		prow5.add(txtLoai);
		contentPanel.add(prow5);

		// Button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(btnSua);
		contentPanel.add(buttonPanel);

		add(contentPanel);
		setSize(400, 500);
		setVisible(true);
		if(type.equals("sua")) {
			btnSua.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					KhachHang khachHang = new KhachHang();
					khachHang.setTen(txtTen.getText());
					khachHang.setSoDienThoai(txtSoDienThoai.getText());
					khachHang.setDiaChi(txtDiaChi.getText());
					khachHang.setLoaiKhachHang(txtLoai.getText());
					if(!new KhachHang_DAO().update(khachHang)) {
						JOptionPane.showMessageDialog(null, "Sửa không thành công!");
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thành công.");
						setTrangThaiSua(true);
						dispose();
					}
					
				}
			});
		}
	}
	public void docDuLieuVaoFormSua(int maKhachHang) {
		khachhang = new KhachHang_DAO().getKhachHangTheoMa(maKhachHang);
		txtTen.setText(khachhang.getTen());
		txtSoDienThoai.setText(khachhang.getSoDienThoai());
		txtDiaChi.setText(khachhang.getDiaChi());
		txtLoai.setText(khachhang.getLoaiKhachHang());
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
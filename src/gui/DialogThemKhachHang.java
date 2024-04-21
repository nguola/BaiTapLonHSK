package gui;

import javax.swing.*;


import dao.KhachHang_DAO;
import entity.KhachHang;

import java.awt.*;
import java.awt.event.*;

public class DialogThemKhachHang extends JDialog implements MouseListener{
	private final JPanel contentPanel = new JPanel();
	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblDiaChi, lblLoaiKhachHang, lblTitle;
	private boolean trangThaiThem = false;
	private JTextField txtMa, txtTen, txtSoDienThoai, txtDiaChi, txtLoai;
	private JButton btnThem;
	private KhachHang khachhang = new KhachHang();

	public boolean isTrangThaiThem() {
		return trangThaiThem;
	}

	public void setTrangThaiThem(boolean trangThaiThem) {
		this.trangThaiThem = trangThaiThem;
	}

	public static void main(String[] args) {
		try {
			DialogThemKhachHang dialog = new DialogThemKhachHang("Thêm");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param type
	 */
	public DialogThemKhachHang(String type) {
		this.setResizable(false);
		setLocationRelativeTo(null);
		
		lblTitle = new JLabel("Thêm Khách Hàng");
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
		txtLoai = new JTextField(30);
		txtLoai.setPreferredSize(new Dimension(100, 30));
		
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(0, 255, 204));
		btnThem.setPreferredSize(new Dimension(140,40));
		btnThem.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		//title
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
		prow5.add(txtLoai);
		contentPanel.add(prow5);

		// Button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(btnThem);
		contentPanel.add(buttonPanel);

		add(contentPanel);
		setSize(360, 500);
		setVisible(true);
		if(type.equals("them")) {
			btnThem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					KhachHang khachHang = new KhachHang();
					khachHang.setTen(txtTen.getText());
					khachHang.setSoDienThoai(txtSoDienThoai.getText());
					khachHang.setDiaChi(txtDiaChi.getText());
					khachHang.setLoaiKhachHang(txtLoai.getText());
					if(!new KhachHang_DAO().create(khachHang)) {
						JOptionPane.showMessageDialog(null, "Thêm không thành công!");
					} else {
						JOptionPane.showMessageDialog(null, "Thêm thành công.");
						setTrangThaiThem(true);
						dispose();
					}
				}
			});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

}

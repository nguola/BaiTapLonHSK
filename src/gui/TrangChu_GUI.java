package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TrangChu_GUI extends JFrame implements ActionListener {
	private JPanel jp_North;
	private JLabel lb_Header;
	private JLabel background;
	private JPanel jp_Center;
	private JButton btn_dangNhap;

	public TrangChu_GUI() {
		// tạo giao diện chiều rộng 1500 chiều cao 800 và vị trí tương đối giữa màng
		// hình
		super();
		setSize(600, 600);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");	

		// code North
		jp_North = new JPanel();
		jp_North.setBackground(new Color(35, 177, 77));
		lb_Header = new JLabel("Goods Store");
		lb_Header.setForeground(Color.white);
		lb_Header.setFont(new Font("Time new roman", Font.BOLD, 40));
		jp_North.add(lb_Header);

		this.add(jp_North, BorderLayout.NORTH);

		// code center
		jp_Center = new JPanel();

		ImageIcon backgroundImg = new ImageIcon("img/trangCHuImg/background.jpg");
		Image img = backgroundImg.getImage();
		Image scaledImg = img.getScaledInstance(getWidth() - 100, getHeight() - 180, Image.SCALE_SMOOTH);
		ImageIcon scaledBackgroundImg = new ImageIcon(scaledImg);
		background = new JLabel("", scaledBackgroundImg, JLabel.CENTER);
		jp_Center.add(background);

		btn_dangNhap = new JButton("Đăng nhập");
		btn_dangNhap.setFont(new Font("Time new romance", Font.BOLD, 30));
		btn_dangNhap.setBackground(new Color(0, 255, 204));
		jp_Center.add(btn_dangNhap);
		this.add(jp_Center, BorderLayout.CENTER);

		btn_dangNhap.addActionListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new TrangChu_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn_dangNhap)) {
			this.setVisible(false);
			new DangNhap_GUI();
		}
	}
}

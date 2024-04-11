package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangNhap_GUI extends JFrame{
	private JPanel jp_North;
	private JLabel lb_Header;
	private JPanel jp_Center;
	private JPanel jp_username;
	private JPanel jp_password;
	private JLabel lb_username;
	private JTextField tf_username;
	private JLabel lb_password;
	private JPasswordField tpf_password;
	private JButton btn_login;
	private JPanel jp_South;

	public DangNhap_GUI(){
		// tạo giao diện chiều rộng 1500 chiều cao 800 và vị trí tương đối giữa màng hình
		super();
		setSize(400, 200);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");
				
		// code North
		jp_North = new JPanel();
		lb_Header = new JLabel("Goods Store");
		lb_Header.setForeground(Color.BLUE);
		lb_Header.setFont(new Font("Time new roman", Font.BOLD, 40));
		
		jp_North.add(lb_Header);
		
		this.add(jp_North, BorderLayout.NORTH);
		//
		
		// code Center
		jp_Center = new JPanel();
		jp_Center.setLayout(new BoxLayout(jp_Center, BoxLayout.Y_AXIS));
		
		jp_username = new JPanel();
		lb_username = new JLabel("User Name:");
		tf_username = new JTextField(20);
		jp_username.add(lb_username);
		jp_username.add(tf_username);
		
		jp_password = new JPanel();
		lb_password = new JLabel("Pass Word:");
		tpf_password = new JPasswordField(20);
		jp_password.add(lb_password);
		jp_password.add(tpf_password);
		
		jp_Center.add(jp_username);
		jp_Center.add(jp_password);
		
		this.add(jp_Center, BorderLayout.CENTER);
		//
		
		// code South
		jp_South = new JPanel();
		btn_login = new JButton("Đăng nhập");
		
		jp_South.add(btn_login);
		this.add(jp_South, BorderLayout.SOUTH);
		//
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DangNhap_GUI();
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangNhap_GUI extends JFrame implements ActionListener{
	private JPanel jp_North;
	private JLabel lb_Header;
	private JPanel jp_Center;
	private JPanel jp_tenDangNhap;
	private JPanel jp_password;
	private JTextField tf_tenDangNhap;
	private JPasswordField tpf_matKhau;
	private JButton btn_dangNhap;
	private JPanel jp_South;
	private JCheckBox cb_pass;
	private JTextField tf_tenDangNhap_loi;
	private JTextField tf_matKhau_loi;

	public DangNhap_GUI(){
		// tạo giao diện chiều rộng 1500 chiều cao 800 và vị trí tương đối giữa màng hình
		super();
		setSize(400, 220);
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
		
		jp_tenDangNhap = new JPanel();
		tf_tenDangNhap = new JTextField(20);
		tf_tenDangNhap_loi = new JTextField();
		tf_tenDangNhap_loi.setForeground(Color.red);
		tf_tenDangNhap_loi.setFont(new Font("Arial", Font.ITALIC, 15));
		jp_tenDangNhap.add(tf_tenDangNhap);
		
		jp_password = new JPanel();
		tpf_matKhau = new JPasswordField(20);
		tf_matKhau_loi = new JTextField();
		tf_matKhau_loi.setForeground(Color.red);
		tf_matKhau_loi.setFont(new Font("Arial", Font.ITALIC, 15));
		jp_password.add(tpf_matKhau);
		
		cb_pass = new JCheckBox("Show password");
		
		jp_Center.add(jp_tenDangNhap);
		jp_Center.add(jp_password);	
		jp_Center.add(cb_pass);
		
		this.add(jp_Center, BorderLayout.CENTER);
		//
		
		// code South
		jp_South = new JPanel();
		btn_dangNhap = new JButton("Đăng nhập");
		
		jp_South.add(btn_dangNhap);
		this.add(jp_South, BorderLayout.SOUTH);
		//
		
		// thêm actionlistener
		btn_dangNhap.addActionListener(this);
		//
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DangNhap_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btn_dangNhap)) {
			String tenDangNhap = tf_tenDangNhap.getText();
			String matKhau = String.valueOf(tpf_matKhau.getPassword());
			
			if(tenDangNhap.trim().isEmpty()) {
				tf_tenDangNhap_loi.setText("Tên đăng nhập không được để trống");
			}
			if(matKhau.trim().isEmpty()) {
				tf_matKhau_loi.setText("Nhập mật khẩu");
			}
		}
	}
}

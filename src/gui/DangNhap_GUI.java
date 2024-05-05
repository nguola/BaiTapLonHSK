package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;

public class DangNhap_GUI extends JFrame implements ActionListener {
	private JPanel jp_North;
	private JLabel lb_TieuDe;
	private JPanel jp_Center;
	private JPanel jp_tenDangNhap;
	private JPanel jp_password;
	private JTextField tf_tenDangNhap;
	private JPasswordField tpf_matKhau;
	private JButton btn_dangNhap;
	private JPanel jp_South;
	private JCheckBox cb_pass;
	TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();

	public DangNhap_GUI() {
		// tạo giao diện chiều rộng 1500 chiều cao 800 và vị trí tương đối giữa màng
		// hình
		super();
		setSize(300, 250);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");

		// code North
		jp_North = new JPanel();
		lb_TieuDe = new JLabel("Goods Store");
		lb_TieuDe.setForeground(Color.BLUE);
		lb_TieuDe.setFont(new Font("Time new roman", Font.BOLD, 40));

		jp_North.add(lb_TieuDe);

		this.add(jp_North, BorderLayout.NORTH);
		//

		// code Center
		jp_Center = new JPanel();

		jp_tenDangNhap = new JPanel();
		tf_tenDangNhap = new JTextField(20);
		jp_tenDangNhap.add(tf_tenDangNhap);

		jp_password = new JPanel();
		tpf_matKhau = new JPasswordField(20);
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
		cb_pass.addActionListener(this);
		//

		setVisible(true);
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btn_dangNhap)) {
			String tenDangNhap = tf_tenDangNhap.getText();
			String matKhau = String.valueOf(tpf_matKhau.getPassword());

			if (tenDangNhap.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống");
				tf_tenDangNhap.requestFocus();
			}else if(!tenDangNhap.matches("\\d{4}")) {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập chứa 4 chữ số");
				tf_tenDangNhap.requestFocus();
				tf_tenDangNhap.setText("");				
				tpf_matKhau.setText("");
			}else if (matKhau.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Hãy nhập mật khẩu");
				tpf_matKhau.requestFocus();
			} else {
				TaiKhoan tk = tk_dao.getTaiKhoanTheoMaNV(Integer.parseInt(tenDangNhap));
				if(tk == null) {
					JOptionPane.showMessageDialog(this, "Tài khoảng không tồn tại");
					tf_tenDangNhap.setText("");
					tf_tenDangNhap.requestFocus();
					tpf_matKhau.setText("");
				}
				else if(!tk.getMatKhau().equalsIgnoreCase(matKhau)) {
					JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
					tpf_matKhau.setText("");
					tpf_matKhau.requestFocus();
				}
				else {
					JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
					new KhungTrang_GUI(tk);
					dispose();
				}
			}
		} else if (o.equals(cb_pass)) {
			if (cb_pass.isSelected()) {
				tpf_matKhau.setEchoChar((char) 0);
			} else {
				tpf_matKhau.setEchoChar('*');
			}
		}
	}

	@Override
	public synchronized void addWindowListener(WindowListener l) {
		// TODO Auto-generated method stub
		super.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				TrangChu_GUI trangChu = new TrangChu_GUI();
				trangChu.setVisible(true);
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}

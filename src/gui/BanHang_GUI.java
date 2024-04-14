package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BanHang_GUI extends JFrame implements ActionListener{

	private JLabel lb_TieuDe;

	public BanHang_GUI() throws HeadlessException {
		super();
		setSize(1500, 800);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");

		// code North
		JPanel jp_North = new JPanel();
		lb_TieuDe = new JLabel("Goods Store");
		lb_TieuDe.setForeground(Color.BLUE);
		lb_TieuDe.setFont(new Font("Time new roman", Font.BOLD, 40));

		jp_North.add(lb_TieuDe);

		this.add(jp_North, BorderLayout.NORTH);
		//

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

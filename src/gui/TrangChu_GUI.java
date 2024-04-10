package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class TrangChu_GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	public TrangChu_GUI() {
		// tạo giao diện chiều rộng 1500 chiều cao 800 và vị trí tương đối giữa màng hình
		super();
		setSize(1500, 800);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");
		
		// code
		
		//
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

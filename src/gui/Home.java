package gui;

import javax.swing.JFrame;

public class Home extends JFrame{
	public Home() {
		// tạo giao diện chiều rộng 1500 chiều cao 800 và vị trí tương đối giữa màng hình
		super();
		setSize(1500, 800);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Home();
	}
}

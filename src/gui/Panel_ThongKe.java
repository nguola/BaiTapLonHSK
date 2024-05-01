package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import connectDB.ConnectDB;

public class Panel_ThongKe extends JPanel {
	private Container jp_tieuDe;

	public Panel_ThongKe() {
		// Cấu hình cho trang
		setLayout(new BorderLayout());
		setSize(800, 600);
		
		// Code tiêu đề
		JPanel jp_North = new JPanel(new BorderLayout());
		jp_North = new JPanel();
		JLabel TieuDe = new JLabel("Thống kê");
		TieuDe.setFont(new Font("Time new roman", Font.BOLD, 30));
		TieuDe.setForeground(Color.blue);
		jp_North.add(TieuDe);
		add(jp_North, BorderLayout.NORTH);
		
		// Code các lựa chọn thống kê
		
		// Code màng hình biểu đồ thống kê
		JPanel Do_Thi = new JPanel();
		try {
			ConnectDB.getInstance().connect();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.add(new Panel_ThongKe());
		test.setVisible(true);
	}
}

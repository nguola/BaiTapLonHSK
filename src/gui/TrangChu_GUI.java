package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class TrangChu_GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel jp_North;
	private JLabel lb_Header;
	private JPanel jp_West;
	private JMenuBar menu;

	public TrangChu_GUI() {
		// tạo giao diện chiều rộng 1500 chiều cao 800 và vị trí tương đối giữa màng hình
		super();
		setSize(1500, 800);
		setLocationRelativeTo(null);
		this.setTitle("Cửa hàng tiện lợi Goods Store");
		
		// code North
		jp_North = new JPanel();
		jp_North.setBackground(new Color(153, 234, 158));
		lb_Header = new JLabel("Goods Store");
		lb_Header.setForeground(Color.BLUE);
		lb_Header.setFont(new Font("Time new roman", Font.BOLD, 40));
		
		jp_North.add(lb_Header);
		
		this.add(jp_North, BorderLayout.NORTH);
		//
		
		// code West
		jp_West = new JPanel();
		menu = new JMenuBar();
		menu.setLayout(new GridLayout(0, 1));
		JMenu fileMenu = new JMenu("File");
	      fileMenu.setMnemonic(KeyEvent.VK_F);
	      menu.add(fileMenu);
	      JMenuItem menuItem1 = new JMenuItem("New", KeyEvent.VK_N);
	      fileMenu.add(menuItem1);
	      JMenuItem menuItem2 = new JMenuItem("Open File", KeyEvent.VK_O);
	      fileMenu.add(menuItem2);
	      JMenu editMenu = new JMenu("Edit");
	      editMenu.setMnemonic(KeyEvent.VK_E);
	      menu.add(editMenu);
	      JMenuItem menuItem3 = new JMenuItem("Cut", KeyEvent.VK_C);
	      editMenu.add(menuItem3);
	      JMenu sourceMenu = new JMenu("Source");
	      sourceMenu.setMnemonic(KeyEvent.VK_S);
	      menu.add(sourceMenu);
	      JMenu refactorMenu = new JMenu("Refactor");
	      refactorMenu.setMnemonic(KeyEvent.VK_R);
	      menu.add(refactorMenu);
	      JMenu navigateMenu = new JMenu("Navigate");
	      navigateMenu.setMnemonic(KeyEvent.VK_A);
	      menu.add(navigateMenu);
	      menu.setSize(new Dimension(300, 800));
	      //
	      jp_West.add(menu);
	      this.add(menu, BorderLayout.WEST);
		//
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TrangChu_GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

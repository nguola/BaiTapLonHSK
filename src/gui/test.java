package gui;

import javax.swing.UIManager;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class test {
	public static void main(String[] args) {
		try {
			ConnectDB.getInstance().connect();
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		new KhungTrang_GUI(new TaiKhoan(new NhanVien(3000, "Toan Hao", "000000", true, 30000, "Admin")));
//			new TrangChu_GUI();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}

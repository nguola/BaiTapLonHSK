package gui;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class test {
	public static void main(String[] args) {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		new BanHang_GUI(new TaiKhoan(new NhanVien(3000, "Toan Hao", "000000", true, 30000, "Admin")));
		
		connectDB.ConnectDB.getInstance().disconnect();
	}
}

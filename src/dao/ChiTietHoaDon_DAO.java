package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class ChiTietHoaDon_DAO {
	public ChiTietHoaDon_DAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<ChiTietHoaDon> getAllChiTietHoaDonTheoMaHoaDon(int maHD){
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			String query = "select * from ChiTietHoaDon where maDon = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getInt(1));
				SanPham sanPham = new SanPham(rs.getInt(2));
				double thanhTien = rs.getDouble(3);
				int soLuong = rs.getInt(4);
				ChiTietHoaDon cthd = new ChiTietHoaDon(hoaDon, sanPham, thanhTien, soLuong);
				dsChiTietHoaDon.add(cthd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}
	
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
		boolean status = false;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			String query = "insert into ChiTietHoaDon values (?, ?, ?, ?)";

			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, cthd.getHoaDon().getMaDon());
			statement.setInt(2, cthd.getSanPham().getMaSanPham());
			statement.setDouble(3, cthd.getThanhTien());
			statement.setInt(4, cthd.getSoLuong());
			status = statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean create(int maHD, int maSP, double thanhtien, int soLuong) {
		boolean status = false;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			String query = "insert into ChiTietHoaDon values (?, ?, ?, ?)";

			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maHD);
			statement.setInt(2, maSP);
			statement.setDouble(3, thanhtien);
			statement.setInt(4, soLuong);
			status = statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}

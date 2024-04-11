package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.KhuyenMai;

public class KhuyenMai_DAO {

	public KhuyenMai_DAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public KhuyenMai getKhuyenMaiTheoMa(String maKM) throws Exception {

		Connection con = ConnectDB.getInstance().getConnection();
		String query = "select * from KhuyenMai where maKhuyenMai = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, maKM);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				int maKhuyenMai = rs.getInt(1);
				Date ngayBatDau = rs.getDate(2);
				Date ngayKetThuc = rs.getDate(3);
				Double giamGia = rs.getDouble(4);
				Double dieuKien = rs.getDouble(5);
				double mucGiamToiDa = rs.getDouble(7);
				KhuyenMai km = new KhuyenMai(maKhuyenMai, ngayBatDau, ngayKetThuc, giamGia, dieuKien, mucGiamToiDa);
				return km;
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean themKhuyenMai(KhuyenMai khuyenMai) {
		boolean status = false;
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "insert into KhuyenMai values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setDate(1, khuyenMai.getNgayBatDau());
			statement.setDate(2, khuyenMai.getNgayKetThuc());
			statement.setDouble(3, khuyenMai.getGiamGia());
			statement.setDouble(4, khuyenMai.getDieuKien());
			statement.setDouble(5, khuyenMai.getMucGiamToiDa());
			status = statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean xoaKhuyenMai(int maKhuyenMai) {
		boolean status = false;
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "delete from KhuyenMai where maKhuyenMai = ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maKhuyenMai);
			status = statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean capNhatKhuyenMai(KhuyenMai khuyenMai) {
		boolean status = false;
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "update KhuyenMai set ngayBatDau = ?, ngayKetThuc = ?, giamGia = ?, dieuKien = ?, mucGiamToiDa = ? where maKhuyenMai = ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setDate(1, khuyenMai.getNgayBatDau());
			statement.setDate(2, khuyenMai.getNgayKetThuc());
			statement.setDouble(3, khuyenMai.getGiamGia());
			statement.setDouble(4, khuyenMai.getDieuKien());
			statement.setDouble(5, khuyenMai.getMucGiamToiDa());
			statement.setInt(6, khuyenMai.getMaKhuyenMai());
			status = statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}

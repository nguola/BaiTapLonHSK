package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDat;
import entity.HoaDon;
import entity.PhieuDat;
import entity.SanPham;

public class ChiTietPhieuDat_DAO {
	public ChiTietPhieuDat_DAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<ChiTietPhieuDat> getAllChiTietHoaDonTheoMaHoaDon(int maPhieu){
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<ChiTietPhieuDat> dsChiTietHoaDon = new ArrayList<ChiTietPhieuDat>();
		try {
			String query = "select * from ChiTietPhieuDat where maPhieu = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maPhieu);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				PhieuDat phieuDat = new PhieuDat(rs.getInt(1));
				SanPham sanPham = new SanPham(rs.getInt(2));
				double thanhTien = rs.getDouble(3);
				int soLuong = rs.getInt(4);
				ChiTietPhieuDat ctpd = new ChiTietPhieuDat(phieuDat, sanPham, thanhTien, soLuong);
				dsChiTietHoaDon.add(ctpd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}
	
	public void themChiTietPhieuDat(ChiTietPhieuDat chiTietPhieuDat) {
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			String query = "insert into ChiTietPhieuDat values (?, ?, ?, ?)";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, chiTietPhieuDat.getPhieuDat().getMaPhieu());
			statement.setInt(2, chiTietPhieuDat.getSanPham().getMaSanPham());
			statement.setDouble(3, chiTietPhieuDat.getThanhTien());
			statement.setInt(4, chiTietPhieuDat.getSoLuong());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

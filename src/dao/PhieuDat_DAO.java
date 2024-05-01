package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDat;

public class PhieuDat_DAO {
	public PhieuDat_DAO() {
		// TODO Auto-generated constructor stub
	}
	public int themPhieuDat(PhieuDat phieuDat) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "{CALL addPhieuDat(?, ?, ?, ?, ?)}";
		int maPhieuDat = -1;
		try {
			CallableStatement statement = con.prepareCall(query);
			statement.setInt(1, phieuDat.getNhanVien().getMaNhanVien());
			statement.setString(2, phieuDat.getNhaCungCap());
			statement.setDate(3, phieuDat.getNgayDat());
			statement.setDouble(4, phieuDat.getTongTien());
			statement.registerOutParameter(5, Types.INTEGER);
			statement.execute();
			maPhieuDat = statement.getInt(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maPhieuDat;
	}
	
	public ArrayList<PhieuDat> getAllPhieuDat() {
		ArrayList<PhieuDat> dsPhieuDat = new ArrayList<PhieuDat>();
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "select * from PhieuDat";
		
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				int maPhieuDat = rs.getInt(1);
				NhanVien nhanVien = new NhanVien(rs.getInt(2));
				String ncc = rs.getString(3);
				Date ngayDat = rs.getDate(4);
				double tongTien = rs.getDouble(5);
				
				PhieuDat pd = new PhieuDat(maPhieuDat, nhanVien, ncc, ngayDat, tongTien);
				
				dsPhieuDat.add(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhieuDat;
	}
	

	public PhieuDat getPhieuDatTheoMa(int maPD) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "select * from PhieuDat where maPhieu = ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maPD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int maPhieuDat = rs.getInt(1);
				NhanVien nhanVien = new NhanVien(rs.getInt(2));
				String ncc = rs.getString(3);
				Date ngayDat = rs.getDate(4);
				double tongTien = rs.getDouble(5);
				
				PhieuDat pd = new PhieuDat(maPhieuDat, nhanVien, ncc, ngayDat, tongTien);
				
				return pd;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<PhieuDat> PhieuDatFilter(Date dateStart, Date dateEnd, double priceStart, double priceEnd, String nCC, int maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<PhieuDat> list = new ArrayList<PhieuDat>();
		String query = "exec LocPhieuDat ?, ?, ?, ?, ?, ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setDate(1, dateStart);
			statement.setDate(2, dateEnd);
			statement.setDouble(3, priceStart);
			statement.setDouble(4, priceEnd);
			statement.setString(5, nCC);
			statement.setInt(6, maNV);
			ResultSet rs = statement.executeQuery();
			System.out.println(rs.getRow());
			while (rs.next()) {
				int maPhieuDat = rs.getInt(1);
				NhanVien nhanVien = new NhanVien(rs.getInt(2));
				String ncc = rs.getString(3);
				Date ngayDat = rs.getDate(4);
				double tongTien = rs.getDouble(5);
				
				PhieuDat pd = new PhieuDat(maPhieuDat, nhanVien, ncc, ngayDat, tongTien);
				list.add(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean xoaPhieuDat(int maPhieu) {
		boolean status = false;
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "delete PhieuDat where maPhieu = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maPhieu);
			status = statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
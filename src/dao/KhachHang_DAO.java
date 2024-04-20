package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;

public class KhachHang_DAO {
	public KhachHang_DAO() {
		
	}
	public ArrayList<KhachHang> getalltbKhachHang(){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from KhachHang";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				int maKH = rs.getInt(1);
				String ten  = rs.getString(2);
				String sdt = rs.getString(3);
				String dc = rs.getString(4);
				String loaiKH = rs.getString(5);
				KhachHang kh = new KhachHang(maKH, ten, sdt, dc, loaiKH);
				dsKH.add(kh);
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsKH;
	}
	public KhachHang getKhachHangTheoMa(int id) {
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		 	
		PreparedStatement stament = null;
		try {
			String sql = "select * from KhachHang where maKhachHang = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, id);
			
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				int maKH = rs.getInt(1);
				String ten  = rs.getString(2);
				String sdt = rs.getString(3);
				String dc = rs.getString(4);
				String loaiKH = rs.getString(5);
				kh = new KhachHang(maKH, ten, sdt, dc, loaiKH);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return kh;
	}
	
	public boolean create(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			//String sql = "insert into KhachHang values( ?, ?, ?, ?, ?)";
	        String sql = "insert into KhachHang (ten, soDienThoai, diaChi, loaiKhachHang) values (?, ?, ?, ?)";
			stament = con.prepareStatement(sql);
//			stament.setInt(1, kh.getMaKhachHang());
//			stament.setString(2, kh.getTen());
//			stament.setString(3, kh.getSoDienThoai());
//			stament.setString(4, kh.getDiaChi());
//			stament.setString(5, kh.getLoaiKhachHang());
			stament.setString(1, kh.getTen());
	        stament.setString(2, kh.getSoDienThoai());
	        stament.setString(3, kh.getDiaChi());
	        stament.setString(4, kh.getLoaiKhachHang());
			n = stament.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	public boolean remove(int maKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "Delete from KhachHang where maKhachHang = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, maKH);
			n = stament.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	public boolean update(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		
		try {
			String sql = "update KhachHang set ten = ?" + "soDienThoai = ?, diaChi = ?, loaiKhachHang = ? Where maKhachHang = ?";
			stament = con.prepareStatement(sql);
			stament.setString(1, kh.getTen());
			stament.setString(2, kh.getSoDienThoai());
			stament.setString(3, kh.getDiaChi());
			stament.setString(4, kh.getLoaiKhachHang());
			stament.setInt(5, kh.getMaKhachHang());
			n = stament.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return n > 0;
	}
}

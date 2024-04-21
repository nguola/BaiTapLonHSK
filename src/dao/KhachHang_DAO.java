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
	
	public ArrayList<KhachHang> getDanhSachKhachHangTheoMa(int id) {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from KhachHang where maKhachHang = "+id;
		String sql1 = "select * from KhachHang where ten like N'%n%'";
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			ResultSet rs1 = ps1.executeQuery();
			while (rs.next()) {
				KhachHang khachHang = new KhachHang();
				khachHang.setTen(rs.getString("ten"));
				khachHang.setSoDienThoai(rs.getString("soDienThoai"));
				khachHang.setDiaChi(rs.getString("diaChi"));
				khachHang.setLoaiKhachHang(rs.getString("loaiKhachHang"));
				list.add(khachHang);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<KhachHang> timKiemKhachHangTheoTen(String name) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		String sql = "select * from KhachHang where ten like ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int maKH = rs.getInt(1);
                String ten = rs.getString(2);
                String sdt = rs.getString(3);
                String dc = rs.getString(4);
                String loaiKH = rs.getString(5);
                KhachHang khachHang = new KhachHang(maKH, ten, sdt, dc, loaiKH);
				list.add(khachHang);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean create(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
	        String sql = "insert into KhachHang (ten, soDienThoai, diaChi, loaiKhachHang) values (?, ?, ?, ?)";
			stament = con.prepareStatement(sql);
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
			String sql = "update KhachHang set ten = ?, soDienThoai = ?, diaChi = ?, loaiKhachHang = ? Where maKhachHang = ?";
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
	public double getTotalPrice(int maKhachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stament = null;
		String sql = "select tongTien = Sum(tongTien) from HoaDon hd inner join KhachHang kh on kh.maKhachHang = hd.maKhachHang"
				+ " where kh.maKhachHang = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, maKhachHang);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble("tongTien");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}

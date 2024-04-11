package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<NhanVien> getalltbNhanVien(){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from NhanVien";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				int maNV = rs.getInt(1);
				String ten = rs.getNString(2);
				String sdt = rs.getString(3);
				boolean phai = rs.getBoolean(4);
				float luong = rs.getFloat(5);
				String loai = rs.getString(6);
				NhanVien nv = new NhanVien(maNV, ten, sdt, phai, luong, loai);
				dsNV.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsNV;
	}
	
	public NhanVien getNhanVienTheoMaNV (String id){
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		 	
		PreparedStatement stament = null;
		try {
			String sql = "select * from NhanVien where maNV = ?";
			stament = con.prepareStatement(sql);
			stament.setString(1, id);
			
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				int maNV = rs.getInt(1);
				String ten = rs.getNString(2);
				String sdt = rs.getNString(3);
				boolean phai = rs.getBoolean(4);
				float luong = rs.getFloat(5);
				String loai = rs.getString(6);
				nv = new NhanVien(maNV, ten, sdt, phai, luong, loai);
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
		return nv;
	}
	
	public boolean create(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "insert into NhanVien values(?, ?, ?, ?, ?, ?, ?)";
			stament = con.prepareStatement(sql);
			stament.setInt(1, nv.getMaNhanVien());
			stament.setString(2, nv.getTen());
			stament.setString(3, nv.getSoDienThoai());
			stament.setBoolean(4, nv.getGioiTinh());
			stament.setDouble(5, nv.getLuong());
			stament.setString(6,  nv.getLoai());
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
	
	public boolean remove(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "Delete from NhanVien where maNV = ?";
			stament = con.prepareStatement(sql);
			stament.setString(1, maNV);
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
	
	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		
		try {
			String sql = "update NhanVien set ho = ?, ten = ?" + "tuoi = ?, phai = ?, tienLuong = ?, maPhong = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, nv.getMaNhanVien());
			stament.setString(2, nv.getTen());
			stament.setString(3, nv.getSoDienThoai());
			stament.setBoolean(4, nv.getGioiTinh());
			stament.setDouble(5, nv.getLuong());
			stament.setString(6,  nv.getLoai());
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

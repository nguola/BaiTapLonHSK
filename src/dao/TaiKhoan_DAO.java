package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_DAO {

	public TaiKhoan_DAO() {
		
	}
	
	public ArrayList<TaiKhoan> getalltbTaiKhoan(){
		ArrayList<TaiKhoan> dsTK = new ArrayList<TaiKhoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from TaiKhoan";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				NhanVien maNhanVien = new NhanVien(rs.getInt(1));
				String matKhau  = rs.getString(2);
				TaiKhoan tk = new TaiKhoan(maNhanVien, matKhau);
				dsTK.add(tk);
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsTK;
	}
	
	public TaiKhoan getTaiKhoanTheoMaNV(int id) {
		TaiKhoan tk = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		 	
		PreparedStatement stament = null;
		try {
			String sql = "select * from TaiKhoan where maNhanVien = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, id);
			
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				NhanVien maNhanVien = new NhanVien(rs.getInt(1));
				String matKhau  = rs.getString(2);
				tk = new TaiKhoan(maNhanVien, matKhau);
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
		return tk;
	}
	
	public String getTKTheoMatKhau(int id) {
		String matKhau = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		 	
		PreparedStatement stament = null;
		try {
			String sql = "select matKhau from TaiKhoan where maNhanVien = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, id);
			
			ResultSet rs = stament.executeQuery();

			if(rs.next()) {
				matKhau = rs.getString("matKhau");
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
		return matKhau;
	}
	
	public boolean create(TaiKhoan tk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "insert into TaiKhoan values(?, ?)";
			stament = con.prepareStatement(sql);
			stament.setInt(1, tk.getNhanvien().getMaNhanVien());
			stament.setString(2, tk.getMatKhau());
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
	
	public boolean remove(int maTK) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "Delete from TaiKhoan where maNhanVien = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, maTK);
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
	
	public boolean update(TaiKhoan tk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		
		try {
			String sql = "update TaiKhoan set maNhanVien = ?, matKhau = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, tk.getNhanvien().getMaNhanVien());
			stament.setString(2, tk.getMatKhau());
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

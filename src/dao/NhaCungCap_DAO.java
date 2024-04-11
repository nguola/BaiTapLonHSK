package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhaCungCap;

public class NhaCungCap_DAO {
	public NhaCungCap_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<NhaCungCap> getalltbNhaCungCap(){
		ArrayList<NhaCungCap> dsNCC = new ArrayList<NhaCungCap>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from NhanVien";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				int maNCC = rs.getInt(1);
				String ten = rs.getNString(2);
				String diaChi = rs.getString(3);
				NhaCungCap ncc = new NhaCungCap(maNCC, ten, diaChi);
				dsNCC.add(ncc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsNCC;
	}
	
	public NhaCungCap getNhaCungCapTheoMa (int id){
		NhaCungCap ncc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		 	
		PreparedStatement stament = null;
		try {
			String sql = "select * from NhaCungCap where maNhaCungCap = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, id);
			
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				int maNCC = rs.getInt(1);
				String ten = rs.getNString(2);
				String diaChi = rs.getString(3);
				ncc = new NhaCungCap(maNCC, ten, diaChi);
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
		return ncc;
	}
	
	public boolean create(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "insert into NhaCungCap values(?, ?, ?)";
			stament = con.prepareStatement(sql);
			stament.setInt(1, ncc.getMaNhaCungCap());
			stament.setString(2, ncc.getTenNhaCungCap());
			stament.setString(3, ncc.getDiaChi());
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
	
//	public boolean remove(String maNCC) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		
//		PreparedStatement stament = null;
//		
//		int n = 0;
//		try {
//			String sql = "Delete from NhaCungCap where maNhaCungCap = ?";
//			stament = con.prepareStatement(sql);
//			stament.setString(1, maNCC);
//			n = stament.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				stament.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				// TODO: handle exception
//			}
//		}
//		return n > 0;
//	}
	
	public boolean update(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		
		try {
			String sql = "update NhaCungCap set maNhaCungCap = ?, tenNhaCungCap = ?, diaChi = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, ncc.getMaNhaCungCap());
			stament.setString(2, ncc.getTenNhaCungCap());
			stament.setString(3, ncc.getDiaChi());
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

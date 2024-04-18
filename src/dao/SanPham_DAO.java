package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.SanPham;

public class SanPham_DAO {
	public ArrayList<SanPham> getalltbSanPham(){
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from SanPham";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				int maSanPham = rs.getInt(1);
				String ten = rs.getString(2);
				double giaSanPham = rs.getDouble(3);
				String donVi = rs.getString(4);
				String loaiSanPham = rs.getString(5);
				SanPham sp = new SanPham(maSanPham, ten, giaSanPham, donVi, loaiSanPham);
				dsSP.add(sp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsSP;
	}
	
	public SanPham getSanPhamTheoMa(int id){
		SanPham sp = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stament = null;
		try {
			String sql = "select * from SanPham where maSanPham = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, id);
			
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				int maSanPham = rs.getInt(1);
				String ten = rs.getString(2);
				double giaSanPham = rs.getDouble(3);
				String donVi = rs.getString(4);
				String loaiSanPham = rs.getString(5);
				sp = new SanPham(maSanPham, ten, giaSanPham, donVi, loaiSanPham);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return sp;
	}
	
	public ArrayList<String> getAllLoaiSP(){
		ArrayList<String> list_loai = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select loaiSanPham\r\n"+ "from SanPham\r\n"+ "group by loaiSanPham";
			Statement stm = con.createStatement();			
			ResultSet rs = stm.executeQuery(sql);

			while(rs.next()) {
				String loai = rs.getString(1);
				list_loai.add(loai);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list_loai;
	}
	
	public boolean create(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "insert into SanPham values(?, ?, ?, ?, ?)";
			stament = con.prepareStatement(sql);
			stament.setInt(1, sp.getMaSanPham());
			stament.setString(2, sp.getTen());
			stament.setDouble(3, sp.getGiaSanPham());
			stament.setString(4, sp.getDonVi());
			stament.setString(5, sp.getLoaiSanPham());
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
	
	public boolean remove(int maSP) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "Delete from SanPham where maSanPham = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, maSP);
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
	
	public boolean update(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		
		try {
			String sql = "update SanPham set maSanPham = ?, ten = ?" + "giaSanPham = ?, donVi = ?, loaiSanPham = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, sp.getMaSanPham());
			stament.setString(2, sp.getTen());
			stament.setDouble(3, sp.getGiaSanPham());
			stament.setString(4, sp.getDonVi());
			stament.setString(5, sp.getLoaiSanPham());
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

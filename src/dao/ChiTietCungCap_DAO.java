package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietCungCap;
import entity.NhaCungCap;
import entity.SanPham;

public class ChiTietCungCap_DAO {
	public ChiTietCungCap_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<ChiTietCungCap> getalltbChiTietCungCap(){
		ArrayList<ChiTietCungCap> dsCtcc = new ArrayList<ChiTietCungCap>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from ChiTietCungCap";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				int maSanPham = rs.getInt(1);
				SanPham sanPham = new SanPham(maSanPham);
				int maNhaCungCap = rs.getInt(2);
				NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap);
				Date ngayGiao = rs.getDate(3);
				int soLuong = rs.getInt(4);
				double gia = rs.getDouble(5);
				String donVi = rs.getNString(6);
				ChiTietCungCap ctcc = new ChiTietCungCap(sanPham, nhaCungCap, ngayGiao, soLuong, gia, donVi);
				dsCtcc.add(ctcc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsCtcc;
	}
	
	public ChiTietCungCap getChiTietCungCapTheoNhaCungCap (int id){
		ChiTietCungCap ctcc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		 	
		PreparedStatement stament = null;
		try {
			String sql = "select * from ChiTietCungCap where maNhaCungCap = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, id);
			
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				int maSanPham = rs.getInt(1);
				SanPham sanPham = new SanPham(maSanPham);
				int maNhaCungCap = rs.getInt(2);
				NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap);
				Date ngayGiao = rs.getDate(3);
				int soLuong = rs.getInt(4);
				double gia = rs.getDouble(5);
				String donVi = rs.getNString(6);
				ctcc = new ChiTietCungCap(sanPham, nhaCungCap, ngayGiao, soLuong, gia, donVi);
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
		return ctcc;
	}
	
	public ChiTietCungCap getChiTietCungCapTheoSanPham (int id){
		ChiTietCungCap ctcc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		 	
		PreparedStatement stament = null;
		try {
			String sql = "select * from ChiTietCungCap where maSanPham = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, id);
			
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				int maSanPham = rs.getInt(1);
				SanPham sanPham = new SanPham(maSanPham);
				int maNhaCungCap = rs.getInt(2);
				NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap);
				Date ngayGiao = rs.getDate(3);
				int soLuong = rs.getInt(4);
				double gia = rs.getDouble(5);
				String donVi = rs.getNString(6);
				ctcc = new ChiTietCungCap(sanPham, nhaCungCap, ngayGiao, soLuong, gia, donVi);
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
		return ctcc;
	}
	
	public ChiTietCungCap getChiTietCungCapTheoSanPham_NhaCungCap (int id_sanPham,int id_nhaCungCap){
		ChiTietCungCap ctcc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		 	
		PreparedStatement stament = null;
		try {
			String sql = "select * from ChiTietCungCap where maSanPham = ? and maNhaCungCap = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, id_sanPham);
			stament.setInt(2, id_nhaCungCap);
			
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				int maSanPham = rs.getInt(1);
				SanPham sanPham = new SanPham(maSanPham);
				int maNhaCungCap = rs.getInt(2);
				NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap);
				Date ngayGiao = rs.getDate(3);
				int soLuong = rs.getInt(4);
				double gia = rs.getDouble(5);
				String donVi = rs.getNString(6);
				ctcc = new ChiTietCungCap(sanPham, nhaCungCap, ngayGiao, soLuong, gia, donVi);
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
		return ctcc;
	}
	
	public boolean create(ChiTietCungCap ctcc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "insert into ChiTietCungCap values(?, ?, ?, ?, ?, ?, ?)";
			stament = con.prepareStatement(sql);
			stament.setInt(1, ctcc.getSanPham().getMaSanPham());
			stament.setInt(2, ctcc.getNhaCungCap().getMaNhaCungCap());
			stament.setDate(3, ctcc.getNgayGiao());
			stament.setInt(4, ctcc.getSoLuong());
			stament.setDouble(5, ctcc.getGia());
			stament.setString(6, ctcc.getDonVi());
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
}

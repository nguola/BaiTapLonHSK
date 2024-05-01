package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.KhuVuc;
import entity.KhuyenMai;
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
				KhuyenMai maKhuyenMai = new KhuyenMai(rs.getInt(2));
				KhuVuc maKhuVuc = new KhuVuc(rs.getInt(3));
				String ten = rs.getString(4);
				double giaSanPham = rs.getDouble(5);
				String donVi = rs.getString(6);
				String loaiSanPham = rs.getString(7);
				int soLuongTonKho = rs.getInt(8);
				
				SanPham sp = new SanPham(maSanPham, maKhuyenMai, maKhuVuc, ten, giaSanPham, donVi, loaiSanPham, soLuongTonKho);
				
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
				KhuyenMai maKhuyenMai = new KhuyenMai(rs.getInt(2));
				KhuVuc maKhuVuc = new KhuVuc(rs.getInt(3));
				String ten = rs.getString(4);
				double giaSanPham = rs.getDouble(5);
				String donVi = rs.getString(6);
				String loaiSanPham = rs.getString(7);
				int soLuongTonKho = rs.getInt(8);
				
				sp = new SanPham(maSanPham, maKhuyenMai, maKhuVuc, ten, giaSanPham, donVi, loaiSanPham, soLuongTonKho);
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
	
	public ArrayList<SanPham> Loc_SanPham(int maSP, String tenSP, String loaiSP, String donViTinh){
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		PreparedStatement stament = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "exec dbo.loc_SanPham ?, ?, ?, ?";
			
			stament = con.prepareStatement(sql);
			stament.setInt(1, maSP);
			stament.setString(2, tenSP);
			stament.setString(3, loaiSP);
			stament.setString(4, donViTinh);
			ResultSet rs = stament.executeQuery();

			while(rs.next()) {
				int maSanPham = rs.getInt(1);
				KhuyenMai maKhuyenMai = new KhuyenMai(rs.getInt(2));
				KhuVuc maKhuVuc = new KhuVuc(rs.getInt(3));
				String ten = rs.getString(4);
				double giaSanPham = rs.getDouble(5);
				String donVi = rs.getString(6);
				String loaiSanPham = rs.getString(7);
				int soLuongTonKho = rs.getInt(8);
				
				SanPham sp = new SanPham(maSanPham, maKhuyenMai, maKhuVuc, ten, giaSanPham, donVi, loaiSanPham, soLuongTonKho);
				list.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
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
	
	public ArrayList<String> getAllDonViSP(){
		ArrayList<String> list_donVi = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select donVi\r\n"
						+ "from SanPham\r\n"
						+ "group by donVi";
			Statement stm = con.createStatement();			
			ResultSet rs = stm.executeQuery(sql);

			while(rs.next()) {
				String loai = rs.getString(1);
				list_donVi.add(loai);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list_donVi;
	}
	
	public boolean create(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		try {
			String sql = "insert into SanPham values(?, ?, ?, ?, ?, ?, ?)";
			stament = con.prepareStatement(sql);
			//stament.setInt(1, sp.getMaSanPham());
			stament.setInt(1, sp.getMaKhuyenMai().getMaKhuyenMai());
			stament.setInt(2, sp.getMaKhuVuc().getMaKhuVuc());
			stament.setString(3, sp.getTen());
			stament.setDouble(4, sp.getGiaSanPham());
			stament.setString(5, sp.getDonVi());
			stament.setString(6, sp.getLoaiSanPham());
			stament.setInt(7, sp.getSoLuongTonKho());
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
	
	public boolean updateSoLuong(int maSP, int soLuong) {
		SanPham sp = getSanPhamTheoMa(maSP);
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		int soLuongMoi = sp.getSoLuongTonKho() - soLuong;
		int n = 0;
		
		try {
			String sql = "update SanPham set soLuongTonKho = ? where maSanPham = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, soLuongMoi);
			stament.setInt(2, maSP);
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
	
	public boolean update(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		PreparedStatement stament = null;
		
		int n = 0;
		
		try {
			String sql = "update SanPham set maSanPham = ?, ten = ?" + "giaSanPham = ?, donVi = ?, loaiSanPham = ?, soLuongTonKho = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, sp.getMaSanPham());
			stament.setString(2, sp.getTen());
			stament.setDouble(3, sp.getGiaSanPham());
			stament.setString(4, sp.getDonVi());
			stament.setString(5, sp.getLoaiSanPham());
			stament.setInt(6,sp.getSoLuongTonKho());
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
	
	public boolean updateSLSP(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stament = null;
		
		int n = 0;
		
		try {
			String sql = "update SanPham set soLuongTonKho = ? where maSanPham = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, sp.getSoLuongTonKho());
			stament.setInt(2, sp.getMaSanPham());
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

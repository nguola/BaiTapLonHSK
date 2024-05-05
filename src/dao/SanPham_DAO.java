package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
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
	
	public ArrayList<SanPham> getAllSanPhamTheoMa(int id){
		SanPham sp = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<SanPham> dssp = new ArrayList<SanPham>();
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
		return dssp;
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
	
	//lọc sản phẩm theo loại
	public ArrayList<SanPham> getSanPhamTheoLoai(String loaiSanPham){
		ArrayList<SanPham> dssp = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "Select * from SanPham where loaiSanPham=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loaiSanPham);
			rs = stmt.executeQuery();
			while(rs.next()) {
				int maSanPham = rs.getInt(1);
				KhuyenMai maKhuyenMai = new KhuyenMai(rs.getInt(2));
				KhuVuc maKhuVuc = new KhuVuc(rs.getInt(3));
				String ten = rs.getString(4);
				double giaSanPham = rs.getDouble(5);
				String donVi = rs.getString(6);
				String loaiSP = rs.getString(7);
				int soLuongTonKho = rs.getInt(8);
				
				SanPham sp = new SanPham(maSanPham, maKhuyenMai, maKhuVuc, ten, giaSanPham, donVi, loaiSP, soLuongTonKho);
				dssp.add(sp);
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				// Đóng ResultSet
		        if(rs != null) {
		            rs.close();
		        }
		        // Đóng PreparedStatement
		        if(stmt != null) {
		            stmt.close();
		        }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return dssp;
	}
	//=====================================================
	
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

	public ArrayList<SanPham> locSanPhamTheoGia(double giaMin, double giaMax) {
        ArrayList<SanPham> danhSachLoc = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM SanPham WHERE giaSanPham BETWEEN ? AND ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, giaMin);
            stmt.setDouble(2, giaMax);
            rs = stmt.executeQuery();

            while (rs.next()) {
            	int maSanPham = rs.getInt(1);
				KhuyenMai maKhuyenMai = new KhuyenMai(rs.getInt(2));
				KhuVuc maKhuVuc = new KhuVuc(rs.getInt(3));
				String ten = rs.getString(4);
				double giaSanPham = rs.getDouble(5);
				String donVi = rs.getString(6);
				String loaiSP = rs.getString(7);
				int soLuongTonKho = rs.getInt(8);
				SanPham sp = new SanPham(maSanPham, maKhuyenMai, maKhuVuc, ten, giaSanPham, donVi, loaiSP, soLuongTonKho);
                danhSachLoc.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return danhSachLoc;
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
			String sql = "update SanPham set maKhuyenMai = ?, maKhuVuc = ?, ten = ?, giaSanPham = ?, donVi = ?, loaiSanPham = ?, soLuongTonKho = ? Where maSanPham = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, sp.getMaKhuyenMai().getMaKhuyenMai());
			stament.setInt(2, sp.getMaKhuVuc().getMaKhuVuc());
			stament.setString(3, sp.getTen());
			stament.setDouble(4, sp.getGiaSanPham());
			stament.setString(5, sp.getDonVi());
			stament.setString(6, sp.getLoaiSanPham());
			stament.setInt(7,sp.getSoLuongTonKho());
			stament.setInt(8, sp.getMaSanPham());
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
	
	public Map<String , Double> thongKeDoanhThuTheoLoai(int maNV, Date dateStart, Date dateEnd){
		Map<String , Double> data = new LinkedHashMap<String, Double>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stament = null;
		try {
			String sql = "exec doanhThuTheoLoaiSanPham ?, ?, ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, maNV);
			stament.setDate(2, dateStart);
			stament.setDate(3, dateEnd);
			ResultSet rs = stament.executeQuery();
			while(rs.next()) {
				data.put(rs.getString(1), rs.getDouble(2)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return data;
	}
	
	public Map<String , Double> thongKeDoanhThuTheoThoiGian(int maNV, Date dateStart, Date dateEnd){
		Map<String , Double> data = new LinkedHashMap<String, Double>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stament = null;
		try {
			String sql = "exec doanhThuTheoThoiGian ?, ?, ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, maNV);
			stament.setDate(2, dateStart);
			stament.setDate(3, dateEnd);
			ResultSet rs = stament.executeQuery();
			while(rs.next()) {
				data.put(rs.getString(1), rs.getDouble(2)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return data;
	}

	public String SanPhamDoanhThuCaoNhat(int maNV, Date dateStart, Date dateEnd){
		String loaiSP = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stament = null;
		try {
			String sql = "exec sanPhamDoanhThuCaoNhat ?, ?, ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, maNV);
			stament.setDate(2, dateStart);
			stament.setDate(3, dateEnd);
			ResultSet rs = stament.executeQuery();
			while(rs.next()) {
				loaiSP = rs.getString(1); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return loaiSP;
	}
	
	public String SanPhamDoanhThuThapNhat(int maNV, Date dateStart, Date dateEnd){
		String loaiSP = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stament = null;
		try {
			String sql = "exec sanPhamDoanhThuThapNhat ?, ?, ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, maNV);
			stament.setDate(2, dateStart);
			stament.setDate(3, dateEnd);
			ResultSet rs = stament.executeQuery();
			while(rs.next()) {
				loaiSP = rs.getString(1); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			try {
				stament.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return loaiSP;
	}
	
	public String getTenKhuVuc(int maKhuVuc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select tenKhuVuc from SanPham sp join KhuVuc kv on sp.maKhuVuc = kv.maKhuVuc" 
					+" where kv.maKhuVuc = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, maKhuVuc);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("tenKhuVuc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public String getDieuKienKhuyenMai(int maKhuyenMai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select dieuKien from SanPham sp join KhuyenMai km on sp.maKhuyenMai = km.maKhuyenMai" 
					+" where km.maKhuyenMai = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, maKhuyenMai);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("dieuKien");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}

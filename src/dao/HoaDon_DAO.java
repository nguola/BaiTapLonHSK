package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;

public class HoaDon_DAO {

	public HoaDon_DAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean themHoaDon(HoaDon hd) {
		boolean status = false;
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "insert into HoaDon values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, hd.getKhachHang().getMaKhachHang());
			statement.setInt(2, hd.getNhanVien().getMaNhanVien());
			statement.setDate(3, hd.getNgayMua());
			statement.setDouble(4, hd.getTongTien());
			status = statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean create(int maKH, int maNV, Date ngayMua, double tongTien) {
		boolean status = false;
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "insert into HoaDon values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setObject(1, ((maKH == -1) ? null : maKH));
			statement.setInt(2, maNV);
			statement.setDate(3, ngayMua);
			statement.setDouble(4, tongTien);
			status = statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public int getMaHDVuaTao() {
		int maHD = -1;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "select Top 1 maDon from HoaDon order by maDon DESC";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			rs.next();
			maHD = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return maHD;
	}
	
	public void xoaHoaDon(int maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "delete from HoaDon where maDon = ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maHD);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "select * from HoaDon";
		
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				int maHoaDon = rs.getInt(1);
				KhachHang khachHang = new KhachHang(rs.getInt(2));
				NhanVien nhanVien = new NhanVien(rs.getInt(3));
				Date ngayMua = rs.getDate(4);
				double tongTien = rs.getDouble(5);
				
				HoaDon hd = new HoaDon(maHoaDon, khachHang, nhanVien, ngayMua, tongTien);
				
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}
	

	public HoaDon getHoaDonTheoMa(int maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "select * from HoaDon where maHoaDon = ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int maHoaDon = rs.getInt(1);
				KhachHang khachHang = new KhachHang(rs.getInt(2));
				NhanVien nhanVien = new NhanVien(rs.getInt(3));
				Date ngayMua = rs.getDate(4);
				double tongTien = rs.getDouble(5);

				HoaDon hd = new HoaDon(maHoaDon, khachHang, nhanVien, ngayMua, tongTien);
				return hd;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<HoaDon> HoaDonFilter(Date dateStart, Date dateEnd, double priceStart, double priceEnd, int maKH, int maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		String query = "exec LocHoaDon ?, ?, ?, ?, ?, ?";
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setDate(1, dateStart);
			statement.setDate(2, dateEnd);
			statement.setDouble(3, priceStart);
			statement.setDouble(4, priceEnd);
			statement.setInt(5, maKH);
			statement.setInt(6, maNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int maHoaDon = rs.getInt(1);
				KhachHang khachHang = new KhachHang(rs.getInt(2));
				NhanVien nhanVien = new NhanVien(rs.getInt(3));
				Date ngayMua = rs.getDate(4);
				double tongTien = rs.getDouble(5);

				HoaDon hd = new HoaDon(maHoaDon, khachHang, nhanVien, ngayMua, tongTien);
				list.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<HoaDon> getAllHoaDonOrderBY(String value, String orderBY) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		String query = "select * from HoaDon ORDER BY " + value + " " + orderBY;
		
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				int maHoaDon = rs.getInt(1);
				KhachHang khachHang = new KhachHang(rs.getInt(2));
				NhanVien nhanVien = new NhanVien(rs.getInt(3));
				Date ngayMua = rs.getDate(4);
				double tongTien = rs.getDouble(5);
				
				HoaDon hd = new HoaDon(maHoaDon, khachHang, nhanVien, ngayMua, tongTien);
				
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}
	
	public ArrayList<HoaDon> getAllHoaDonNhanVien(int maNhanVien){
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		PreparedStatement stament = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "select * from HoaDon where maNhanVien = ?";
			stament = con.prepareStatement(sql);
			stament.setInt(1, maNhanVien);
			
			ResultSet rs = stament.executeQuery();
			while (rs.next()) {
				int maHoaDon = rs.getInt(1);
				KhachHang khachHang = new KhachHang(rs.getInt(2));
				NhanVien nhanVien = new NhanVien(rs.getInt(3));
				Date ngayMua = rs.getDate(4);
				double tongTien = rs.getDouble(5);

				HoaDon hd = new HoaDon(maHoaDon, khachHang, nhanVien, ngayMua, tongTien);
				list.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
	}
}

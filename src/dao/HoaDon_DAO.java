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
		String query = "insert into HoaDon values (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, hd.getKhachHang().getMaKhachHang());
			statement.setInt(2, hd.getNhanVien().getMaNhanVien());
			statement.setInt(3, hd.getKhuyenMai().getMaKhuyenMai());
			statement.setDate(4, hd.getNgayMua());
			statement.setDouble(5, hd.getTongTien());
			status = statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
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
				KhuyenMai khuyenMai = new KhuyenMai(rs.getInt(4));
				Date ngayMua = rs.getDate(5);
				double tongTien = rs.getDouble(6);
				
				HoaDon hd = new HoaDon(maHoaDon, khachHang, nhanVien, khuyenMai, ngayMua, tongTien);
				
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
				KhuyenMai khuyenMai = new KhuyenMai(rs.getInt(4));
				Date ngayMua = rs.getDate(5);
				double tongTien = rs.getDouble(6);

				HoaDon hd = new HoaDon(maHoaDon, khachHang, nhanVien, khuyenMai, ngayMua, tongTien);
				return hd;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

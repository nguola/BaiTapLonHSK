package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.KhuVuc;
import entity.KhuyenMai;

public class KhuVuc_DAO {
	public KhuVuc_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public KhuVuc getKhuVucTheoMa(int maKV){

		Connection con = ConnectDB.getInstance().getConnection();
		String query = "select * from KhuVuc where maKhuVuc = ?";
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, maKV);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				int maKhuVuc = rs.getInt(1);
				String ten = rs.getString(2);
				KhuVuc kv = new KhuVuc(maKhuVuc, ten);
				return kv;
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

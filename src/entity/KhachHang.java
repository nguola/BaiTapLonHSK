package entity;

import java.util.Objects;

public class KhachHang {
	public final int maKhachHang;
	public String ten;
	public String soDienThoai;
	public String diaChi;
	public String loaiKhachHang;
	public KhachHang(int maKhachHang, String ten, String soDienThoai, String diaChi, String loaiKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.loaiKhachHang = loaiKhachHang;
	}
	
	public KhachHang(int maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}

	public KhachHang() {
		this.maKhachHang = 0;
	}

	public String getTen() {
		return ten;
	}
	
	public void setTen(String ten) {
		this.ten = ten;
	}
	
	public String getSoDienThoai() {
		return soDienThoai;
	}
	
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	
	public String getDiaChi() {
		return diaChi;
	}
	
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	public String getLoaiKhachHang() {
		return loaiKhachHang;
	}
	
	public void setLoaiKhachHang(String loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}
	
	public int getMaKhachHang() {
		return maKhachHang;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}
}

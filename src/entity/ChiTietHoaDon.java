package entity;

import java.util.Objects;

public class ChiTietHoaDon {
	public final HoaDon hoaDon;
	public final SanPham sanPham;
	public double thanhTien;
	public int soLuong;
	public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, double thanhTien, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.thanhTien = thanhTien;
		this.soLuong = soLuong;
	}
	
	public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
	}
	
	public double getThanhTien() {
		return thanhTien;
	}
	
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	public int getSoLuong() {
		return soLuong;
	}
	
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	
	public SanPham getSanPham() {
		return sanPham;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(hoaDon, sanPham);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Objects.equals(hoaDon, other.hoaDon) && Objects.equals(sanPham, other.sanPham);
	}
}

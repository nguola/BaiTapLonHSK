package entity;

import java.util.Objects;

public class ChiTietPhieuDat {
	private PhieuDat phieuDat;
	private SanPham sanPham;
	private double thanhTien;
	private int soLuong;
	
	
	public ChiTietPhieuDat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChiTietPhieuDat(PhieuDat phieuDat, SanPham sanPham, double thanhTien, int soLuong) {
		super();
		this.phieuDat = phieuDat;
		this.sanPham = sanPham;
		this.thanhTien = thanhTien;
		this.soLuong = soLuong;
	}

	
	public ChiTietPhieuDat(PhieuDat phieuDat) {
		super();
		this.phieuDat = phieuDat;
	}

	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
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

	public PhieuDat getPhieuDat() {
		return phieuDat;
	}

	public void setPhieuDat(PhieuDat phieuDat) {
		this.phieuDat = phieuDat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(phieuDat, sanPham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietPhieuDat other = (ChiTietPhieuDat) obj;
		return Objects.equals(phieuDat, other.phieuDat) && Objects.equals(sanPham, other.sanPham);
	}
	
	
}

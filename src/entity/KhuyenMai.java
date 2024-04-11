package entity;

import java.sql.Date;
import java.util.Objects;

public class KhuyenMai {
	public final int maKhuyenMai;
	public Date ngayBatDau;
	public Date ngayKetThuc;
	public double giamGia;
	public double dieuKien;
	public double mucGiamToiDa;
	public KhuyenMai(int maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, double giamGia, double dieuKien,
			double mucGiamToiDa) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.giamGia = giamGia;
		this.dieuKien = dieuKien;
		this.mucGiamToiDa = mucGiamToiDa;
	}
	
	public KhuyenMai(int maKhuyenMai) {
		super();
		this.maKhuyenMai = maKhuyenMai;
	}
	
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	
	public double getGiamGia() {
		return giamGia;
	}
	
	public void setGiamGia(double giamGia) {
		this.giamGia = giamGia;
	}
	
	public double getDieuKien() {
		return dieuKien;
	}
	
	public void setDieuKien(double dieuKien) {
		this.dieuKien = dieuKien;
	}
	
	public double getMucGiamToiDa() {
		return mucGiamToiDa;
	}
	
	public void setMucGiamToiDa(double mucGiamToiDa) {
		this.mucGiamToiDa = mucGiamToiDa;
	}
	
	public int getMaKhuyenMai() {
		return maKhuyenMai;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maKhuyenMai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(maKhuyenMai, other.maKhuyenMai);
	}
}

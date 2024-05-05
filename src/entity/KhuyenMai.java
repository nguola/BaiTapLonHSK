package entity;

import java.sql.Date;
import java.util.Objects;

public class KhuyenMai {
	public final int maKhuyenMai;
	public Date ngayBatDau;
	public Date ngayKetThuc;
	public double giamGia;
	public String dieuKien;
	public KhuyenMai(int maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, double giamGia, String dieuKien) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.giamGia = giamGia;
		this.dieuKien = dieuKien;
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
	public String getDieuKien() {
		return dieuKien;
	}
	public void setDieuKien(String dieuKien) {
		this.dieuKien = dieuKien;
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
		return maKhuyenMai == other.maKhuyenMai;
	}
}

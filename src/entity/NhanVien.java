package entity;

import java.util.Objects;

public class NhanVien {
	public final int maNhanVien;
	public String ten;
	public String soDienThoai;
	public boolean gioiTinh;
	public double Luong;
	public String loai;
	
	public NhanVien(int maNhanVien, String ten, String soDienThoai, Boolean gioiTinh, double luong, String loai) {
		super();
		this.maNhanVien = maNhanVien;
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		Luong = luong;
		this.loai = loai;
	}

	public NhanVien(int maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
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

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public double getLuong() {
		return Luong;
	}

	public void setLuong(double luong) {
		Luong = luong;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
}

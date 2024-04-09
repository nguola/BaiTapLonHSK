package entity;

import java.util.Objects;

public class NhanVien {
	public final String maNhanVien;
	public String ten;
	public String soDienThoai;
	public String gioiTinh;
	public double Luong;
	public String loai;
	
	public NhanVien(String maNhanVien, String ten, String soDienThoai, String gioiTinh, double luong, String loai) {
		super();
		this.maNhanVien = maNhanVien;
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		Luong = luong;
		this.loai = loai;
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

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
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

	public String getMaNhanVien() {
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

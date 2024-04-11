package entity;

import java.util.Objects;

public class SanPham {
	public final int maSanPham;
	public String ten;
	public double giaSanPham;
	public String donVi;
	public String loaiSanPham;
	
	public SanPham(int maSanPham, String ten, double giaSanPham, String donVi, String loaiSanPham) {
		super();
		this.maSanPham = maSanPham;
		this.ten = ten;
		this.giaSanPham = giaSanPham;
		this.donVi = donVi;
		this.loaiSanPham = loaiSanPham;
	}

	public SanPham(int maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public double getGiaSanPham() {
		return giaSanPham;
	}

	public void setGiaSanPham(double giaSanPham) {
		this.giaSanPham = giaSanPham;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public String getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(String loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public int getMaSanPham() {
		return maSanPham;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}
}

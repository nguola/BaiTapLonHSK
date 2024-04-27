package entity;

import java.util.Objects;

public class SanPham {
	public final int maSanPham;
	public KhuyenMai maKhuyenMai;
	public KhuVuc maKhuVuc;
	public String ten;
	public double giaSanPham;
	public String donVi;
	public String loaiSanPham;
	public int soLuongTonKho;

	public SanPham(int maSanPham) {
		super();
		// TODO Auto-generated constructor stub
		this.maSanPham = maSanPham;
	}

	public SanPham(int maSanPham, KhuyenMai maKhuyenMai, KhuVuc maKhuVuc, String ten, double giaSanPham, String donVi,
			String loaiSanPham, int soLuongTonKho) {
		super();
		this.maSanPham = maSanPham;
		this.maKhuyenMai = maKhuyenMai;
		this.maKhuVuc = maKhuVuc;
		this.ten = ten;
		this.giaSanPham = giaSanPham;
		this.donVi = donVi;
		this.loaiSanPham = loaiSanPham;
		this.soLuongTonKho = soLuongTonKho;
	}

	public KhuyenMai getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(KhuyenMai maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public KhuVuc getMaKhuVuc() {
		return maKhuVuc;
	}

	public void setMaKhuVuc(KhuVuc maKhuVuc) {
		this.maKhuVuc = maKhuVuc;
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

	public int getSoLuongTonKho() {
		return soLuongTonKho;
	}

	public void setSoLuongTonKho(int soLuongTonKho) {
		this.soLuongTonKho = soLuongTonKho;
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
		return maSanPham == other.maSanPham;
	}
}

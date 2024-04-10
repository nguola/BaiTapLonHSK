package entity;

import java.util.Date;
import java.util.Objects;

public class ChiTietCungCap {
	public final SanPham sanPham;
	public final NhaCungCap nhaCungCap;
	public Date ngayGiao;
	public int soLuong;
	public double gia;
	public String donVi;
	
	public ChiTietCungCap(SanPham sanPham, NhaCungCap nhaCungCap, Date ngayGiao, int soLuong, double gia,
			String donVi) {
		super();
		this.sanPham = sanPham;
		this.nhaCungCap = nhaCungCap;
		this.ngayGiao = ngayGiao;
		this.soLuong = soLuong;
		this.gia = gia;
		this.donVi = donVi;
	}
	
	public ChiTietCungCap(String maSanPham, String maNhaCungCap) {
		super();
		SanPham sp = new SanPham(maNhaCungCap);
		NhaCungCap ncc = new NhaCungCap(maNhaCungCap);
		this.sanPham = sp;
		this.nhaCungCap = ncc;
	}

	public Date getNgayGiao() {
		return ngayGiao;
	}
	
	public void setNgayGiao(Date ngayGiao) {
		this.ngayGiao = ngayGiao;
	}
	
	public int getSoLuong() {
		return soLuong;
	}
	
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public double getGia() {
		return gia;
	}
	
	public void setGia(double gia) {
		this.gia = gia;
	}
	
	public String getDonVi() {
		return donVi;
	}
	
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	
	public SanPham getSanPham() {
		return sanPham;
	}
	
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nhaCungCap, sanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietCungCap other = (ChiTietCungCap) obj;
		return Objects.equals(nhaCungCap, other.nhaCungCap) && Objects.equals(sanPham, other.sanPham);
	}
}

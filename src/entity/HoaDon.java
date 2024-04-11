package entity;

import java.sql.Date;
import java.util.Objects;

public class HoaDon {
	public final String maDon;
	public KhachHang khachHang;
	public NhanVien nhanVien;
	public KhuyenMai khuyenMai;
	public Date ngayMua;
	public double tongTien;
	public HoaDon(String maDon, KhachHang khachHang, NhanVien nhanVien, KhuyenMai khuyenMai, Date ngayMua,
			double tongTien) {
		super();
		this.maDon = maDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.khuyenMai = khuyenMai;
		this.ngayMua = ngayMua;
		this.tongTien = tongTien;
	}

	public HoaDon(String maDon) {
		super();
		this.maDon = maDon;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}
	
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	
	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}
	
	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	
	public Date getNgayMua() {
		return ngayMua;
	}
	
	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}
	
	public double getTongTien() {
		return tongTien;
	}
	
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	
	public String getMaDon() {
		return maDon;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maDon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maDon, other.maDon);
	}
}

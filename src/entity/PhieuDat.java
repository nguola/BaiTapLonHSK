package entity;
import java.sql.Date;
import java.util.Objects;

public class PhieuDat {
	private int maPhieu;
	private NhanVien nhanVien;
	private String nhaCungCap;
	private Date ngayDat;
	private double tongTien;
	public PhieuDat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PhieuDat(int maPhieu, NhanVien nhanVien, String nhaCungCap, Date ngayDat, double tongTien) {
		super();
		this.maPhieu = maPhieu;
		this.nhanVien = nhanVien;
		this.nhaCungCap = nhaCungCap;
		this.ngayDat = ngayDat;
		this.tongTien = tongTien;
	}

	public PhieuDat(int maPhieu) {
		super();
		this.maPhieu = maPhieu;
	}
	
	public int getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(int maPhieu) {
		this.maPhieu = maPhieu;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public String getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhieu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDat other = (PhieuDat) obj;
		return maPhieu == other.maPhieu;
	}
	
	
}

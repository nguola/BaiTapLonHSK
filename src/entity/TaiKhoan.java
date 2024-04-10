package entity;

import java.util.Objects;

public class TaiKhoan {
	public final NhanVien nhanvien;
	public String matKhau;
	public TaiKhoan(NhanVien nhanvien, String matKhau) {
		super();
		this.nhanvien = nhanvien;
		this.matKhau = matKhau;
	}
	
	public TaiKhoan(NhanVien nhanvien) {
		super();
		this.nhanvien = nhanvien;
	}
	
	public String getMatKhau() {
		return matKhau;
	}
	
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	public NhanVien getNhanvien() {
		return nhanvien;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nhanvien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(nhanvien, other.nhanvien);
	}
}

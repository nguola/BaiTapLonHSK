package entity;

import java.util.Objects;

public class NhaCungCap {
	public final String maNhaCungCap;
	public String tenNhaCungCap;
	public String diaChi;
	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String diaChi) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
	}
	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}
	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNhaCungCap);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		return Objects.equals(maNhaCungCap, other.maNhaCungCap);
	}
}

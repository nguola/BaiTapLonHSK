package entity;

import java.util.Objects;

public class KhuVuc {
	public final int maKhuVuc;
	public String tenKhuVuc;
	
	public KhuVuc(int maKhuVuc) {
		super();
		this.maKhuVuc = maKhuVuc;
	}

	public KhuVuc(int maKhuVuc, String tenKhuVuc) {
		super();
		this.maKhuVuc = maKhuVuc;
		this.tenKhuVuc = tenKhuVuc;
	}

	public String getTenKhuVuc() {
		return tenKhuVuc;
	}

	public void setTenKhuVuc(String tenKhuVuc) {
		this.tenKhuVuc = tenKhuVuc;
	}

	public int getMaKhuVuc() {
		return maKhuVuc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhuVuc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuVuc other = (KhuVuc) obj;
		return Objects.equals(maKhuVuc, other.maKhuVuc);
	}
}

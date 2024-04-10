package dao;

import java.util.ArrayList;

import entity.NhanVien;

public class NhanVien_dao {
	private ArrayList<NhanVien> list;

	public NhanVien_dao() {
		super();
		list = new ArrayList<NhanVien>();
	}
	
	public boolean themNhanVien(NhanVien nv) {
		if(list.contains(nv))
			return false;
		
		list.add(nv);
		return true;
	}
	
	public boolean xoaNhanVien(String maNhanVien) {
		NhanVien nv = new NhanVien(maNhanVien);
		if(list.contains(nv)) {
			list.remove(nv);
			return true;
		}
		
		return false;
	}
	
	public NhanVien timNhanVien(String maNhanVien) {
		return list.stream().filter(nv -> nv.getMaNhanVien().equalsIgnoreCase(maNhanVien)).findFirst().orElse(null);
	}
}

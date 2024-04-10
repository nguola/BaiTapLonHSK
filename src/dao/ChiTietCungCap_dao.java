package dao;

import java.util.ArrayList;

import entity.ChiTietCungCap;

public class ChiTietCungCap_dao {
	private ArrayList<ChiTietCungCap> list;

	public ChiTietCungCap_dao() {
		super();
		list = new ArrayList<ChiTietCungCap>();
	}
	
	public boolean themChiTietCungCap(ChiTietCungCap o) {
		if(list.contains(o))
			return false;
		
		list.add(o);
		return true;
	}
	
	public boolean xoaChiTietCungCap(String maSanPham, String maNhaCungCap) {
		ChiTietCungCap o = new ChiTietCungCap(maSanPham, maNhaCungCap);
		if(list.contains(o)) {
			list.remove(o);
			return true;
		}
		
		return false;
	}
}

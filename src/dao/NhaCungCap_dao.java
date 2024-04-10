package dao;

import java.util.ArrayList;

import entity.NhaCungCap;

public class NhaCungCap_dao {
	private ArrayList<NhaCungCap> list;

	public NhaCungCap_dao() {
		super();
		list = new ArrayList<NhaCungCap>();
	}
	public boolean themNhaCungCap(NhaCungCap ncc) {
		if(list.contains(ncc))
			return false;
		
		list.add(ncc);
		return true;
	}
	public boolean xoaNhaCungCap(String maNhaCungCap) {
		NhaCungCap ncc = new NhaCungCap(maNhaCungCap);
		if(list.contains(ncc)) {
			list.remove(ncc);
			return true;
		}
		
		return false;
	}
	public NhaCungCap timNhaCungCap(String maNhaCungCap) {
		return list.stream().filter(ncc -> ncc.getMaNhaCungCap().equalsIgnoreCase(maNhaCungCap)).findFirst().orElse(null);
	}
}

package com.example.mugenmonkey;

public class Build {
	String name;
	String vit;
	String att;
	String end;
	String str;
	String dex;
	String res;
	String intel;
	String faith;
	String hum;
	
	int head;
	int chest; 
	int hands; 
	int legs;
	int ring1;
	int ring2;
	
	int lh1;
	int lh2;
	int rh1;
	int rh2;
	
	int lh1path;
	int lh2path;
	int rh1path; 
	int rh2path;
	public Build(String name, String vit, String att, String end, String str, String dex, String res, String intel, String faith, String hum,
			int head, int chest, int hands, int legs, int ring1, int ring2,
			int lh1, int lh2, int rh1, int rh2,
			int lh1path, int lh2path, int rh1path, int rh2path){
		this.name = name;
		this.vit = vit;
		this.att = att;
		this.end = end;
		this.str = str;
		this.dex = dex;
		this.res = res;
		this.intel = intel;
		this.faith = faith;
		this.hum = hum;
		
		this.head = head;
		this.chest = chest;
		this.hands = hands;
		this.legs = legs;
		this.ring1 = ring1;
		this.ring2 = ring2;
		
		this.lh1 = lh1;
		this.lh2 = lh2;
		this.rh1 = rh1;
		this.rh2 = rh2;
		
		this.lh1path = lh1path;
		this.lh2path = lh2path;
		this.rh1path = rh1path;
		this.rh2path = rh2path;
		
	}

	public String getName() {
		return name;
	}

	public String getVit() {
		return vit;
	}

	public String getAtt() {
		return att;
	}

	public String getEnd() {
		return end;
	}

	public String getStr() {
		return str;
	}

	public String getDex() {
		return dex;
	}

	public String getRes() {
		return res;
	}

	public String getIntel() {
		return intel;
	}

	public String getFaith() {
		return faith;
	}

	public String getHum() {
		return hum;
	}

	public int getHead() {
		return head;
	}

	public int getChest() {
		return chest;
	}

	public int getHands() {
		return hands;
	}

	public int getLegs() {
		return legs;
	}

	public int getRing1() {
		return ring1;
	}

	public int getRing2() {
		return ring2;
	}

	public int getLh1() {
		return lh1;
	}

	public int getLh2() {
		return lh2;
	}

	public int getRh1() {
		return rh1;
	}

	public int getRh2() {
		return rh2;
	}

	public int getLh1path() {
		return lh1path;
	}

	public int getLh2path() {
		return lh2path;
	}

	public int getRh1path() {
		return rh1path;
	}

	public int getRh2path() {
		return rh2path;
	}

}

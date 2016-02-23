package com.example.mugenmonkey;

public class SpecialWeapons implements Items{

	public int[] normal;
	public String[] normStats;
	public double[] normPercent;
	public double weight;
	
	public SpecialWeapons(int[] norm, String[] normStat, double[] normP, double heft) {
		normal = norm;
		normStats = normStat;
		normPercent = normP;
		weight = heft;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] getNormal() {
		// TODO Auto-generated method stub
		return normal;
	}

	@Override
	public String[] getNormalStats() {
		// TODO Auto-generated method stub
		return normStats;
	}

	@Override
	public int[] getMagic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getMagicStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getDivine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getDivineStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCrystal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getCrystalStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRaw() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getRawStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getEnchant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getEnchantStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getOccult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getOccultStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLightning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getFire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getChaos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getNormalPercent() {
		// TODO Auto-generated method stub
		return normPercent;
	}

	@Override
	public double[] getMagicPercent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getDivinePercent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double[] getRawPercent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getEnchantPercent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getOccultPercent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double getWeight(){
		return weight;
	}
}

package com.example.mugenmonkey;

public class HumanityWeapons implements Items{
	public int[] normal;
	public String[] normStats;
	public double[] normPercent;
	public int[] humDmg;					//COUNTS UNTIL THE DMG HITS CAP AMT OF HUMANITY
	public double weight;
	
	public HumanityWeapons(int[] norm, String[] normStat, double[] normP, int[] humanityDmg, double heft) {
		normal = norm;
		normStats = normStat;
		normPercent = normP;
		humDmg = humanityDmg;
		weight = heft;
		// TODO Auto-generated constructor stub
	}
	
	public int[] getNormal(){
		return normal;
	}
	
	public String[] getNormalStats(){
		return normStats;
	}
	
	public int[] getHumanityDmg(){
		return humDmg;
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

package com.example.mugenmonkey;

public class Weapons implements Items{
	//Its type dmg and then Stat Bonus
	//Normal, Crystal, Lightning, Raw, Magic, Enchanted, Divine, Occult, Fire, Chaos
	
	public int[] normal;
	public String[] normStats;
	public double[] normPercent;
	public int[] magic;
	public String[] magicStats;
	public double[] magPercent;
	public int[] divine;
	public String[] divStats;
	public double[] divPercent;
	public int[] crystal;
	public String[] crysStats;
	public double[] crysPercent;
	public int[] raw;
	public String[] rawStats;
	public double[] rawPercent;
	public int[] enchant;
	public String[] enchStats;
	public double[] enchPercent;
	public int[] occult;
	public String[] occStats;
	public double[] occPercent;
	public int[] lightning;
	public int[] fire;
	public int[] chaos;
	public double weight;
	
	public Weapons(int[] norm, String[] normStat, double[] normP, int[] cryst, String[] crystStat, int[] litning, int[] ra, String[] rawStat, double[] rawP,
			int[] mag, String[] magStat, double[] magP,int[] ench, String[] enchStat, double[] enchP, int[] div, String[] divStat, double[] divP,
			int[] occ, String[] occStat, double[] occP, int[] flame, int[] chao, double heft){
		normal = norm;
		normStats = normStat;
		normPercent = normP;
		crystal = cryst;
		crysStats = crystStat;
		lightning = litning;
		raw = ra;
		rawStats = rawStat;
		rawPercent = rawP;
		magic = mag;
		magicStats = magStat;
		magPercent = magP;
		enchant = ench;
		enchStats = enchStat;
		enchPercent = enchP;
		divine = div;
		divStats = divStat;		
		divPercent = divP;
		occult = occ;
		occStats = occStat;
		occPercent = occP;
		fire = flame;
		chaos = chao;
		weight = heft;
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
		return magic;
	}

	@Override
	public String[] getMagicStats() {
		// TODO Auto-generated method stub
		return magicStats;
	}

	@Override
	public int[] getDivine() {
		// TODO Auto-generated method stub
		return divine;
	}

	@Override
	public String[] getDivineStats() {
		// TODO Auto-generated method stub
		return divStats;
	}

	@Override
	public int[] getCrystal() {
		// TODO Auto-generated method stub
		return crystal;
	}

	@Override
	public String[] getCrystalStats() {
		// TODO Auto-generated method stub
		return crysStats;
	}

	@Override
	public int[] getRaw() {
		// TODO Auto-generated method stub
		return raw;
	}

	@Override
	public String[] getRawStats() {
		// TODO Auto-generated method stub
		return rawStats;
	}

	@Override
	public int[] getEnchant() {
		// TODO Auto-generated method stub
		return enchant;
	}

	@Override
	public String[] getEnchantStats() {
		// TODO Auto-generated method stub
		return enchStats;
	}

	@Override
	public int[] getOccult() {
		// TODO Auto-generated method stub
		return occult;
	}

	@Override
	public String[] getOccultStats() {
		// TODO Auto-generated method stub
		return occStats;
	}

	@Override
	public int[] getLightning() {
		// TODO Auto-generated method stub
		return lightning;
	}

	@Override
	public int[] getFire() {
		// TODO Auto-generated method stub
		return fire;
	}

	@Override
	public int[] getChaos() {
		// TODO Auto-generated method stub
		return chaos;
	}

	@Override
	public double[] getNormalPercent() {
		// TODO Auto-generated method stub
		return normPercent;
	}

	@Override
	public double[] getMagicPercent() {
		// TODO Auto-generated method stub
		return magPercent;
	}

	@Override
	public double[] getDivinePercent() {
		// TODO Auto-generated method stub
		return divPercent;
	}

	@Override
	public double[] getRawPercent() {
		// TODO Auto-generated method stub
		return rawPercent;
	}

	@Override
	public double[] getEnchantPercent() {
		// TODO Auto-generated method stub
		return enchPercent;
	}

	@Override
	public double[] getOccultPercent() {
		// TODO Auto-generated method stub
		return occPercent;
	}
	
	public double getWeight(){
		return weight;
	}
}

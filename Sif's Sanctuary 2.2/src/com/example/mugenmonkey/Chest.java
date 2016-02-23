package com.example.mugenmonkey;

public class Chest {
	//Array order is Poise, Weight, physDef, strikeDef, slashDef, thrustDef, magDef, fireDef, lightningDef, bleedRes, posRes, curseRes	
	
	static double[] Naked = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
	
	static double[] AntiquatedDress = {0.0, 1.0, 21.7,	21.7,	21.7,	21.7,	51.1,	9.3,	29.4,	17.8,	17.8,	33.0};
	static double[] ArmorOfArtorias = {15.0, 10.5, 65.1,	58.6,	74.9,	68.4,	34.1,	71.3,	34.1,	27.9,	27.9,	25.4};
	static double[] ArmorOfTheGlorious = {20.0, 13.3, 47.0, 44.6, 54.1, 47.0, 31.0, 34.0, 25.0, 28.0, 19.0, 24.0};
	static double[] ArmorOfTheSun = {16.0, 9.0, 84.7,	84.7,	84.7,	83.0,	50.8,	50.8,	46.0,	32.2,	22.4,	14.0};
	static double[] ArmorOfThorns = {16.0, 10.5, 69.7,	66.3,	76.0,	69.7,	43.4,	51.1,	31.0,	33.0,	22.9, 0.0};
	
	static double[] BalderArmor = {16.0, 10.9, 89.5,	87.7,	109.2,	86.0,	36.3,	43.6,	36.3,	28.0,	22.4, 0.0};
	static double[] BlackClericRobe = {0.0, 3.9, 35.6,	38.9,	35.6,	35.6,	44.9,	38.8,	29.4,	27.9,	27.9,	16.5};
	static double[] BlackIronArmor = {31.0, 15.6, 105.4,	100.1,	121.2,	105.4,	46.5,	79.0,	32.6,	54.6,	31.7,	16.5};
	static double[] BlackKnightArmor = {21.0, 13.0, 88.3,	83.9,	99.8,	86.6,	44.9,	74.4,	26.3,	40.6,	19.0, 0.0};
	static double[] BlackLeatherArmor = {0.0, 3.1, 55.7,	59.6,	55.7,	55.7,	48.4,	33.9,	38.7,	32.2,	43.4, 0.0};
	static double[] BlackSorcererCloack = {0.0, 1.8, 31.5,	32.4,	34.3,	31.5,	67.8,	50.8,	50.8,	43.4,	43.4,	5.6};
	static double[] BrassArmor = {16.0, 10.9, 74.4,	71.4,	84.1,	74.4, 41.8,	38.7,	38.7,	30.5,	25.4,	14.0};
	static double[] BrigandArmor = {0.0, 3.1, 55.7,	61.2,	55.7,	50.1,	48.4,	31.5,	41.1,	28.0,	28.0, 0.0};
	
	static double[] CatarinaArmor = {21.0, 11.7, 88.3,	87.5,	101.6,	88.3,	48.0,	57.3,	29.4,	43.2,	30.5, 0.0};
	static double[] ChainArmor = {9.0, 6.0, 72.6,	72.6,	87.1,	70.4,	50.8,	38.7,	16.9,	28.0,	16.8, 0.0};
	static double[] ChestersLongCoat = {0.0, 4.5, 41.8,	56.5,	41.8,	41.8,	46.5,	32.5,	43.4,	55.9,	40.6, 0.0};
	static double[] ClericArmor = {20.0, 12.5, 96.8,	96.8,	111.3,	96.8,	55.7,	50.8,	36.3,	32.2,	22.4, 0.0};
	static double[] CrimsonRobe = {0.0, 3.9, 49.6,	54.6,	49.6,	49.6,	51.1,	29.4,	37.2,	26.7,	26.7,	26.7};
	static double[] CrystallineArmor = {16.0, 10.9, 59.0, 50.1, 67.9, 59.0, 21.0, 15.0, 7.0, 15.0, 0.0, 0.0};
	
	static double[] DarkArmor = {16.0, 9.8, 69.8,	69.8,	76.7,	65.6,	46.5,	48.0,	41.8,	38.1,	26.7, 0.0};
	static double[] DingyRobe = {0.0, 3.0, 55.7,	69.6,	55.7,	55.7,	79.9,	46.0,	60.5,	49.0,	36.4,	49.0};
	
	static double[] EasternArmor = {20.0, 12.3, 69.7,	66.3,	86.5,	67.7,	23.2,	51.1,	27.9,	48.3,	35.6, 0.0 };
	static double[] EliteKnightArmor = {20.0, 11.7, 94.4, 91.5,	109.5,	94.4,	41.1,	43.6,	36.3,	32.2,	22.4, 0.0};
	static double[] EmbracedArmorOfFavor = {20.0, 11.7, 74.4,	70.7,	86.3,	74.4,	35.6,	46.5,	29.4,	35.6,	27.9,	27.9};
	
	static double[] GiantArmor = {35.0, 16.4, 111.6,	102.7,	126.1,	111.6,	49.6,	74.4,	51.1,	40.6,	24.1, 0.0};
	static double[] GoldHemmedBlackCloak = {0.0, 3.5, 31.0, 33.2, 31.0, 31.0, 38.0, 58.0, 21.0, 40.0, 94.0, 0.0};
	static double[] GolemArmor = {33.0, 16.4, 91.0, 83.7, 106.5, 91.0, 45.0, 41.0, 17.0, 45.0, 31.0, 0.0};
	static double[] GoughsArmor = {19.0, 13.0, 77.5,	77.5,	81.4,	73.6,	43.4,	63.5,	32.5,	26.7,	15.2, 0.0};
	static double[] GuardianArmor = {37.0, 17.0, 86.0, 80.8, 98.9, 86.0, 50.0, 42.0, 50.0, 42.0, 14.0, 0.0};
	
	static double[] HardLeatherArmor = {0.0, 5.9, 62.9, 66.7,	62.9,	62.9,	48.4,	31.5,	55.7,	32.2,	28.0, 0.0};
	static double[] HavelsArmor = {47.0, 19.5, 92.0, 78.2, 105.8, 92.0, 56.0, 54.0, 49.0, 45.0, 11.0, 22.0};
	static double[] HollowSoldierArmor = {12.0, 7.8, 62.9,	62.9,	69.8,	55.4,	38.7,	41.1,	24.2,	25.2,	18.2, 0.0};
	static double[] HollowThiefsLeatherArmor = {0.0, 2.8, 50.8,	55.4,	50.8,	52.9,	48.4,	31.5,	43.6,	21.0,	40.6, 0.0};
	static double[] HollowWarriorArmor = {8.0, 6.6, 58.1,	56.3,	59.8,	55.2,	38.7,	29.0,	41.1,	18.2,	18.2, 0.0};
	static double[] HolyRobe = {0.0, 4.0, 53.2,	62.8,	53.2,	53.2,	55.7,	33.9,	46.0,	33.6,	28.0, 0.0};
	
	static double[] KnightArmor = {20.0, 10.9, 89.5,	85.1,	103.9,	87.7,	38.7,	43.6,	31.5,	32.2,	22.4, 0.0};
	
	static double[] LeatherArmor = {0.0, 4.7, 58.1,	61.6,	58.1,	58.1,	48.4,	29.0,	55.7,	29.4,	28.0, 0.0};
	static double[] LordsBladeRobe = {8.0, 6.4, 52.7,	52.7,	55.3,	58.0,	40.3,	49.6,	31.0,	35.6,	62.2,	31.7};
	
	static double[] MaidenRobe = {0.0, 2.0, 46.0,	53.8,	46.0,	46.0,	53.2,	31.5,	41.1,	32.2,	25.2,	11.2};
	static double[] MoonlightRobe = {0.0, 3.1, 20.0, 20.0, 20.0, 20.0, 31.0, 17.0, 17.0, 23.0, 23.0, 31.0};
	
	static double[] OrnsteinsArmor = {19.0, 12.0, 70.0, 66.5, 79.1, 70.0, 36.0, 46.0, 40.0, 38.0, 18.0, 18.0};
	
	static double[] PaintingGuardianRobe = {0.0, 1.6, 13.9,	16.7,	13.9,	13.9,	52.7,	44.9,	32.5,	40.6,	99.1,	20.3}; 
	static double[] PaladinArmor = {20.0, 12.9, 91.5,	86.9,	105.2,	91.5,	48.1,	57.4,	29.5,	43.2,	30.5,	72.4};
	
	static double[] RobeOfTheChannelers = {12.0, 10.1, 53.0, 50.9, 60.4, 53.0, 45.0, 45.0, 35.0, 38.0, 33.0, 0.0};
	static double[] RobeOfTheGreatLord = {0.0, 6.0, 32.0, 35.0, 37.0, 29.0, 32.0, 41.0, 41.0, 30.0, 15.0, 40.0};
	
	static double[] SageRobe = {0.0, 4.0, 63.5,	68.6,	66.1,	63.5,	43.4,	44.9, 54.3,	62.2,	62.2, 0.0};
	static double[] ShadowGarb = {0.0, 2.3, 35.6,	39.2,	35.6,	35.6,	18.6,	24.8,	26.3,	49.5,	59.7, 0.0};
	static double[] SilverKnightArmor = {19.0, 12.0, 80.6,	75.8,	92.7,	79.0,	40.3,	44.9,	52.7,	30.5,	15.2,	2.5};
	static double[] SmoughsArmor = {35.0, 17.6, 106.0, 92.2, 121.9, 106.0, 52.0, 66.0, 59.0, 54.0, 28.0, 9.0};
	static double[] SorcererCloak = {0.0, 2.3, 48.4,	52.3,	50.3, 48.4,	65.3,	31.5,	41.1,	22.4,	16.8};
	static double[] SteelArmor = {23.0, 14.0, 101.6,	95.5,	116.9,	101.6,	53.2,	50.8,	31.5,	32.2,	22.4, 0.0};
	static double[] StoneArmor = {39.0, 17.6, 91.0, 85.5, 104.7, 91.0, 45.0, 54.0, 54.0, 45.0, 16.0, 0.0};
	
	static double[] TatteredClothRobe = {0.0, 2.7, 50.8,	56.4,	50.8,	50.8,	55.7,	72.6,	41.1,	32.2,	82.6,	2.8};
	
	static double[] WandererCoat = {0.0, 3.5,55.7,	59.6,	59.6,	55.7,	50.8,	62.9,	55.7,	29.4, 29.4, 0.0};
	static double[] WitchCloak = {0.0, 4.0, 51.1,	51.1,	56.8,	51.1,	66.7,	31.0,	40.3,	45.7,	45.7,	15.2};
	
	static double[] XanthousCoat = {0.0, 3.9, 49.6,	55.6,	49.6,	45.1,	57.3,	10.8,	35.6,	27.9,	36.8, 0.0};
	
	
	public static double[][] DoubleChest = {Naked, AntiquatedDress, ArmorOfArtorias, ArmorOfTheGlorious, ArmorOfTheSun, ArmorOfThorns,
		BalderArmor, BlackClericRobe, BlackIronArmor, BlackKnightArmor, BlackLeatherArmor, BlackSorcererCloack, BrassArmor, BrigandArmor,
		CatarinaArmor, ChainArmor, ChestersLongCoat, ClericArmor, CrimsonRobe, CrystallineArmor, 
		DarkArmor, DingyRobe,
		EasternArmor, EliteKnightArmor, EmbracedArmorOfFavor,
		GiantArmor, GoldHemmedBlackCloak, GolemArmor, GoughsArmor, GuardianArmor,
		HardLeatherArmor, HavelsArmor, HollowSoldierArmor, HollowThiefsLeatherArmor, HollowWarriorArmor, HolyRobe,
		KnightArmor,
		LeatherArmor, LordsBladeRobe,
		MaidenRobe, MoonlightRobe,
		OrnsteinsArmor,
		PaintingGuardianRobe, PaladinArmor,
		RobeOfTheChannelers, RobeOfTheGreatLord,
		SageRobe, ShadowGarb, SilverKnightArmor, SmoughsArmor, SorcererCloak, SteelArmor, StoneArmor,
		TatteredClothRobe,
		WandererCoat, WitchCloak,
		XanthousCoat};
	
	public static String[] StringChest = {"Naked", 
			"Antiquated Dress", "Armor of Artorias", "Armor of the Glorious", "Armor of the Sun", "Armor of Thorns",
			"Balder Armor", "Black Cleric Robe", "Black Iron Armor", "Black Knight Armor", "Black Leather Armor", "Black Sorcerer Cloak", "Brass Armor", "Brigand Armor",
			"Catarina Armor", "Chain Armor", "Chester's Long Coat", "Cleric Armor", "Crimson Robe", "Crystalline Armor",
			"Dark Armor", "Dingy Robe",
			"Eastern Armor", "Elite Knight Armor", "Embraced Armor of Favor",
			"Giant Armor", "Gold-Hemmed Black Cloak", "Golem Armor", "Gough's Armor", "Guardian Armor",
			"Hard Leather Armor", "Havel's Armor", "Hollow Soldier Armor", "Hollow Thief's Leather Armor", "Hollow Warrior Armor", "Holy Robe",
			"Knight Armor",
			"Leather Armor", "Lord's Blade Robe",
			"Maiden Robe", "Moonlight Robe",
			"Ornstein's Armor",
			"Painting Guardian Robe", "Paladin Armor",
			"Robe of the Channelers", "Robe of the Great Lord",
			"Sage Robe", "Shadow Garb", "Silver Knight Armor", "Smough's Armor", "Sorcerer Cloak", "Steel Armor", "Stone Armor",
			"Tattered Cloth Robe",
			"Wanderer Coat", "Witch Cloak",
			"Xanthous Overcoat"};
}

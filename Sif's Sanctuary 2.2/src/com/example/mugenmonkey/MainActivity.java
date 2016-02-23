package com.example.mugenmonkey;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import sifs.sanctuary.R;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;

public class MainActivity extends ActionBarActivity implements OnItemSelectedListener{

    static int[] HealthPoints = {0,400,415,433,451,471,490,511,530,552,572,594,616,638,658,682,698,718,742,766,792,821,849,878,908,938,970,1001,1034,1066,1100,1123,
			1147,1170,1193,1216,1239,1261,1283,1304,1325,1346,1366,1386,1405,1424,1442,1458,1474,1489,1500,1508,1517,1526,1535,1544,1553,1562,1571,1580,1588,
			1597,1606,1615,1623,1632,1641,1649,1658,1666,1675,1683,1692,1700,1709,1717,1725,1734,1742,1750,1758,1767,1775,1783,1791,1799,1807,1814,1822,1830,
			1837,1845,1852,1860,1867,1874,1881,1888,1894,1900};
    static int[] Stamina = {0,81,82,83,84,85,86,87,88,90,91,93,95,97,98,100,102,104,106,108,110,112,115,117,119,121,124,126,129,131,133,136,139,141,144,146,149,152,154,157,160};
	
	String[] paths = {"Normal +15", "Lightning +5","Magic +10","Divine +10","Fire +10","Crystal +5","Raw +5","Enchanted +5","Occult +5","Chaos +5"};

	
	public EditText Vitality, Attunement, Endurance, Strength, Dexterity, Resistance, Intelligence, Faith, Humanity; //SoulLevel;
	private Spinner SoulsClasses, head, chest, hands, legs, ring1, ring2, rh1, rh2, lh1, lh2, rh1path, rh2path, lh1path, lh2path, dsClass, spell1,spell2,spell3,spell4,spell5,spell6,spell7,spell8,spell9,spell10,
	item1, item2, item3, item4, item5;

    public TextView hp, poiseBois, stam, equipLoad, heft, physicalDefense, strikeDefense, slashDefense, thrustDefense, magicDefense, fireDefense, lightningDefense, bleedResistance, poisonResistance, curseResistance
	, ar1, ar2, ar3, ar4, attSlots, SL, SoulLevel;
    
    public Button save, load, clear;

    static String strEnteredVal = "";
    static TextWatcher tw;

	double hpPercent = 0.0;
	double stamPercent = 0.0;
	double equipPercent = 0.0;

    static boolean tiny = false;
    static boolean havels = false;
    static boolean fap = false;
	static boolean father = false;
    static boolean mother = false;

    static double currentHP = 0;
    static double currentStam = 0;
    static double currentEQ = 0.0;
    
    
    final static int mostTotal = 86;
    final static int sorcTotal = 82;
    final static int pyroTotal = 84;
    final static int clericTotal = 84;
    final static int deprivedTotal = 99;
    
    
    
    
    
	Storage db = new Storage(this);
	
	
	
	
	

    //TABLES
//    private static final String VITALITY_NAME = "Vitality";
//    private static final String ATTUNEMENT_NAME = "Attunement";
//    private static final String ENDURANCE_NAME = "Endurance";
//    private static final String STRENGTH_NAME = "Strength";
//    private static final String DEXTERITY_NAME = "Dexterity";





    double[] item;
	double[] humDef = {0.0, 0.0, 0.0, 0.0, 0.0};
	int[] importantStuff;
	
	//Equipment Values
	int poise = 0;
	double weight = 0;
	double physDef = 0.0;
	double strikeDef = 0.0;
	double slashDef = 0.0;
	double thrustDef = 0.0;
	double magDef = 0.0;
	double fireDef = 0.0;
	double lightningDef = 0.0;
	double bleedRes = 0.0;
	double posRes = 0.0;
	double curseRes = 0.0;

	//STAT RATING
	static double dexRating;
	static double strRating;
	static double intRating;
	static double faithRating;
	
	static String[] stringWeapons;
	static Items[] base;
	static CharSequence[] items = {};
	static ArrayList<String> allFiles = new ArrayList<String>();
	
	boolean newName = false;
	boolean classDone = false;
	
	DecimalFormat df = new DecimalFormat("####0.0");
	
	String FILENAME = "storage";
	File builds = new File("buildNames.txt");
    int tryCount = 0;
    
    ArrayAdapter<String> nothing;
    ArrayAdapter<String> path;
    
    private static double[] currentHelm = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    private static double[] currentChest = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    private static double[] currentHands = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    private static double[] currentLegs = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    
    private static double currentLH1 = 0.0;
    private static double currentLH2 = 0.0;
    private static double currentRH1 = 0.0;
    private static double currentRH2 = 0.0;
    
    private static double currentShield = 0.0;
    private static double currentTalisman = 0.0;
   
    
    
    //View outlook = this;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//Weapons ;_;
		//Normal, Crystal, Lightning, Raw, Magic, Enchanted, Divine, Occult, Fire, Chaos
		
		
		//FIST
		Weapons Caestus = new Weapons(new int[]{165,0,0,0}, new String[]{"C","C","0","0"}, new double[]{.61, .61, 0.0, 0.0}, new int[]{178,0,0,0}, new String[]{"C","C","0","0"}, new int[]{165,0,0,165},
				new int[]{152,0,0,0}, new String[]{"D","D","0","0"}, new double[]{.41, .41, 0.0, 0.0}, new int[]{125, 135, 0,0}, new String[]{"E","E","B","0"},
				new double[]{.16, .16, .87, 0.0}, new int[]{120,122,0,0}, new String[]{"E","E","A","0"}, new double[]{.12, .12, 1.29, 0.0}, new int[]{112,137,0,0}, new String[]{"D","D","0","B"},
				new double[]{.24, .24, 0.0, .87}, new int[]{116, 125, 0, 0}, new String[]{"D","D","0","A"}, new double[]{.21, .21, 0.0, 1.03},
				new int[]{158,0,158,0},new int[]{131,0,151,0}, .5);
		
		Weapons Claw = new Weapons(new int[]{180,0,0,0}, new String[]{"E","A","0","0"}, new double[]{.15, 1.02, 0.0, 0.0}, new int[]{194,0,0,0}, new String[]{"E","A","0","0"}, new int[]{180,0,0,180},
				new int[]{166,0,0,0}, new String[]{"E","C","0","0"}, new double[]{.11, .70, 0.0, 0.0}, new int[]{135, 140, 0,0}, new String[]{"E","D","B","0"},
				new double[]{0.4, 0.28, 0.85, 0.0}, new int[]{129,132,0,0}, new String[]{"E","E","A","0"}, new double[]{0.3, 0.2, 1.24, 0.0}, new int[]{120,147,0,0}, new String[]{"E","D","0","B"},
				new double[]{0.6, 0.39, 0.84, 0.0}, new int[]{130, 135, 0, 0},new String[]{"E","D","0","B"}, new double[]{0.6, 0.37, 0.98, 0.0}, new int[]{172,0,172,0},		new int[]{144,0,165,0}, 1.0);
		
		SpecialWeapons DarkHand = new SpecialWeapons(new int[]{200,0,0,0}, new String[]{"0","0","0","0"}, new double[]{0.0, 0.0, 0.0, 0.0}, .5);
		
		SpecialWeapons DragonBoneFist = new SpecialWeapons(new int[]{142,0,0,0}, new String[]{"S","0","0","0"}, new double[]{1.44, 0.0, 0.0, 0.0}, 8.0);
		
		
		//HAMMERS	
		SpecialWeapons BlacksmithGiantHammer = new SpecialWeapons(new int[]{180, 0, 0, 300}, new String[]{"D", "0", "0", "0"}, new double[]{0.35, 0.0, 0.0, 0.0}, 6.0);
		
		Weapons BlacksmithHammer = new Weapons(new int[]{217,0,0,0}, new String[]{"B","0","0","0"}, new double[]{0.9, 0.0, 0.0, 0.0}, new int[]{234,0,0,0}, new String[]{"B","0","0","0"}, 	new int[]{217,0,0,217},
				new int[]{200,0,0,0}, new String[]{"C","0","0","0"}, new double[]{0.61, 0.0, 0.0, 0.0}, new int[]{162, 177, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.25, 0.0, 0.64, 0.0}, new int[]{156,162,0,0}, new String[]{"E","B","0","0"},new double[]{0.16, 0.0, 0.95, 0.0}, new int[]{147,180,0,0}, new String[]{"D","0","0","C"}, new double[]{0.34, 0.0, 0.0, 0.64}, new int[]{156, 162, 0, 0},
				new String[]{"D","0","0","C"}, 	new double[]{0.32, 0.0, 0.0, 0.75}, new int[]{208,0,208,0},		new int[]{174,0,202,0}, 5.0);
		
		SpecialWeapons HammerOfVamos = new SpecialWeapons(new int[]{172, 0, 96, 0}, new String[]{"C","0","0","0"}, new double[]{0.75, 0.0, 0.0, 0.0}, 5.0);
		
		Weapons Club = new Weapons(new int[]{182,0,0,0}, new String[]{"A","0","0","0"},new double[]{1.32, 0.0, 0.0, 0.0}, new int[]{195,0,0,0}, new String[]{"A","0","0","0"}, 	new int[]{177,0,0,177},
				new int[]{185,0,0,0}, new String[]{"B","0","0","0"},new double[]{0.9, 0.0, 0.0, 0.0}, new int[]{136, 149, 0,0}, new String[]{"D","0","B","0"},
				new double[]{0.37, 0.0, 0.94, 0.0}, new int[]{126,135,0,0}, new String[]{"D","0","A","0"},new double[]{0.26, 0.0, 1.38, 0.0}, new int[]{123,151,0,0}, new String[]{"D","0","0","B"}, new double[]{0.5, 0.0, 0.0, 0.94}, new int[]{129, 136, 0, 0},
				new String[]{"D","0","0","A"}, new double[]{0.46, 0.0, 0.0, 1.1}, new int[]{169,0,169,0},		new int[]{142,0,165,0}, 3.0);
		
		Weapons Mace = new Weapons(new int[]{227,0,0,0}, new String[]{"A","0","0","0"},new double[]{1.05, 0.0, 0.0, 0.0}, new int[]{245,0,0,0}, new String[]{"A","0","0","0"}, 	new int[]{227,0,0,227},
				new int[]{210,0,0,0}, new String[]{"C","0","0","0"}, new double[]{0.72, 0.0, 0.0, 0.0}, new int[]{170, 185, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.3, 0.0, 0.75, 0.0}, new int[]{163,169,0,0}, new String[]{"D","0","A","0"},new double[]{0.21, 0.0, 1.11, 0.0}, new int[]{152,187,0,0}, new String[]{"D","0","0","C"},new double[]{0.4, 0.0, 0.0, 0.75}, new int[]{163, 170, 0, 0},
				new String[]{"D","0","0","B"}, new double[]{0.38, 0.0, 0.0, 0.88}, new int[]{218,0,218,0},		new int[]{181,0,209,0}, 4.0);
		
		Weapons MorningStar = new Weapons(new int[]{207,0,0,0}, new String[]{"B","0","0","0"}, 	new double[]{0.9, 0.0, 0.0, 0.0}, new int[]{224,0,0,0}, new String[]{"B","0","0","0"}, 	new int[]{207,0,0,207},
				new int[]{190,0,0,0}, new String[]{"C","0","0","0"}, new double[]{0.61, 0.0, 0.0, 0.0}, new int[]{155, 167, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.25, 0.0, 0.64, 0.0}, new int[]{148,153,0,0}, new String[]{"E","0","B","0"},new double[]{0.16, 0.0, 0.95, 0.0}, new int[]{140,170,0,0}, new String[]{"D","0","0","C"},new double[]{0.34, 0.0, 0.0, 0.64}, new int[]{148, 155, 0, 0},
				new String[]{"D","0","0","C"}, new double[]{0.32, 0.0, 0.0, 0.75}, new int[]{199,0,199,0},		new int[]{167,0,190,0}, 4.0);
		
		Weapons Pickaxe = new Weapons(new int[]{222,0,0,0}, new String[]{"A","0","0","0"}, new double[]{1.05, 0.0, 0.0, 0.0}, new int[]{240,0,0,0}, new String[]{"A","0","0","0"}, 	new int[]{222,0,0,222},
				new int[]{204,0,0,0}, new String[]{"C","0","0","0"},new double[]{0.72, 0.0, 0.0, 0.0}, new int[]{167, 180, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.3, 0.0, 0.75, 0.0}, new int[]{160,164,0,0}, new String[]{"D","0","A","0"},new double[]{0.21, 0.0, 1.11, 0.0}, new int[]{150,182,0,0}, new String[]{"D","0","0","C"},new double[]{0.4, 0.0, 0.0, 0.75}, new int[]{158, 167, 0, 0},
				new String[]{"D","0","0","B"}, new double[]{0.38, 0.0, 0.0, 0.88}, new int[]{213,0,213,0},		new int[]{177,0,204,0}, 5.0);
		
		Weapons ReinforcedClub = new Weapons(new int[]{242,0,0,0}, new String[]{"B","0","0","0"},new double[]{0.9, 0.0, 0.0, 0.0}, new int[]{261,0,0,0}, new String[]{"B","0","0","0"}, 	new int[]{242,0,0,242},
				new int[]{224,0,0,0}, new String[]{"C","0","0","0"},new double[]{0.61, 0.0, 0.0, 0.0}, new int[]{182, 197, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.25, 0.0, 0.64, 0.0}, new int[]{175,180,0,0}, new String[]{"E","0","B","0"},new double[]{0.16, 0.0, 0.95, 0.0}, new int[]{162,200,0,0}, new String[]{"D","0","0","C"}, new double[]{0.34, 0.0, 0.0, 0.64}, new int[]{174, 182, 0, 0},
				new String[]{"D","0","0","C"}, new double[]{0.32, 0.0, 0.0, 0.75}, new int[]{232,0,232,0},		new int[]{195,0,223,0}, 4.0);
		
		Weapons Warpick = new Weapons(new int[]{227,0,0,0}, new String[]{"C","D","0","0"}, 	new double[]{0.72, 0.36, 0.0, 0.0}, new int[]{245,0,0,0}, new String[]{"C","D","0","0"}, 	new int[]{227,0,0,227},
				new int[]{210,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.49, 0.25, 0.0, 0.0}, new int[]{170, 185, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.2, 0.09, 0.78, 0.0}, new int[]{163,169,0,0}, new String[]{"E","E","A","0"},new double[]{0.14, 0.07, 1.13, 0.0}, new int[]{152,187,0,0}, new String[]{"D","E","0","C"}, new double[]{0.27, 0.14, 0.0, .76}, new int[]{163, 170, 0, 0},
				new String[]{"D","E","0","B"}, new double[]{0.26, 0.13, 0.0, 0.91}, new int[]{218,0,218,0},		new int[]{181,0,209,0}, 3.5);
		
		
		//GREAT HAMMERS
		Weapons DemonsGreatHammer = new Weapons(new int[]{345,0,0,0}, new String[]{"B","0","0","0"},new double[]{0.97, 0.0, 0.0, 0.0}, new int[]{372,0,0,0}, new String[]{"B","0","0","0"}, 	new int[]{345,0,0,345},
				new int[]{318,0,0,0}, new String[]{"C","0","0","0"},new double[]{0.66, 0.0, 0.0, 0.0}, new int[]{260, 280, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.27, 0.0, 0.69, 0.0}, new int[]{249,255,0,0}, new String[]{"E","0","A","0"},new double[]{0.19, 0.0, 1.02, 0.0}, new int[]{232,285,0,0}, new String[]{"D","0","0","C"},new double[]{0.37, 0.0, 0.0, 0.69}, new int[]{246, 260, 0, 0},
				new String[]{"D","0","0","B"}, new double[]{0.34, 0.0, 0.0, 0.81}, new int[]{331,0,331,0},		new int[]{276,0,317,0}, 22.0);
		
		SpecialWeapons DragonTooth = new SpecialWeapons(new int[]{435,0,0,0}, new String[]{"D","0","0","0"}, new double[]{0.42, 0.0, 0.0, 0.0}, 18.0);
		
		SpecialWeapons Grant = new SpecialWeapons(new int[]{195,195,0,0}, new String[]{"B","0","0","A"}, new double[]{0.84, 0.0, 0.0, 1.16}, 24.0);
		
		Weapons GreatClub = new Weapons(new int[]{337,0,0,0}, new String[]{"A","0","0","0"},new double[]{1.05, 0.0, 0.0, 0.0}, new int[]{364,0,0,0}, new String[]{"A","0","0","0"}, 	new int[]{337,0,0,337},
				new int[]{310,0,0,0}, new String[]{"C","0","0","0"},new double[]{0.72, 0.0, 0.0, 0.0}, new int[]{252, 275, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.3, 0.0, 0.75, 0.0}, new int[]{242,250,0,0}, new String[]{"E","0","A","0"},new double[]{0.21, 0.0, 1.11, 0.0}, new int[]{227,277,0,0}, new String[]{"D","0","0","C"},new double[]{0.4, 0.0, 0.0, 0.75}, new int[]{241, 252, 0, 0},
				new String[]{"D","0","0","B"}, new double[]{0.38, 0.0, 0.0, 0.88}, new int[]{324,0,324,0},		new int[]{271,0,312,0}, 12.0);
		
		Weapons LargeClub = new Weapons(new int[]{300,0,0,0}, new String[]{"A","0","0","0"},new double[]{1.23, 0.0, 0.0, 0.0}, new int[]{324,0,0,0}, new String[]{"A","0","0","0"}, 	new int[]{300,0,0,300},
				new int[]{276,0,0,0}, new String[]{"B","0","0","0"}, new double[]{0.85, 0.0, 0.0, 0.0}, new int[]{225, 245, 0,0}, new String[]{"D","0","B","0"},
				new double[]{0.34, 0.0, 0.88, 0.0}, new int[]{216,222,0,0}, new String[]{"D","0","A","0"},new double[]{0.24, 0.0, 1.29, 0.0}, new int[]{202,247,0,0}, new String[]{"D","0","0","B"}, new double[]{0.46, 0.0, 0.0, 0.88}, new int[]{213, 225, 0, 0},
				new String[]{"D","0","0","A"}, new double[]{0.44, 0.0, 0.0, 1.04}, new int[]{288,0,288,0},		new int[]{241,0,278,0}, 11.0);
		
		SpecialWeapons SmoughsHammer = new SpecialWeapons(new int[]{450,0,0,0}, new String[]{"D","0","0","0"}, new double[]{0.5, 0.0, 0.0, 0.0}, 28.0);
		
		
		//AXES		
		Weapons BattleAxe = new Weapons(new int[]{237,0,0,0}, new String[]{"C","D","0","0"},new double[]{0.72, 0.36, 0.0, 0.0}, new int[]{256,0,0,0}, new String[]{"C","D","0","0"}, 	new int[]{237,0,0,237},
				new int[]{218,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.49, 0.25, 0.0, 0.0}, new int[]{177, 192, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.2, 0.09, 0.78, 0.0}, new int[]{170,176,0,0}, new String[]{"E","E","C","0"},new double[]{0.14, 0.07, 1.13, 0.0}, new int[]{160,195,0,0}, new String[]{"D","E","0","C"},new double[]{0.27, 0.14, 0.0, 0.76}, new int[]{169, 177, 0, 0},
				new String[]{"D","E","0","B"},new double[]{0.26, 0.13, 0.0, 0.91}, new int[]{228,0,228,0},		new int[]{190,0,218,0}, 22.0);
		
		Weapons ButcherKnife = new Weapons(new int[]{225,0,0,0}, new String[]{"A","0","0","0"},new double[]{1.05, 0.0, 0.0, 0.0}, new int[]{243,0,0,0}, new String[]{"A","0","0","0"}, 	new int[]{225,0,0,225},
				new int[]{208,0,0,0}, new String[]{"C","0","0","0"},new double[]{0.72, 0.0, 0.0, 0.0}, new int[]{170, 182, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.3, 0.0, 0.75, 0.0}, new int[]{163,167,0,0}, new String[]{"D","0","A","0"},new double[]{0.21, 0.0, 1.11, 0.0}, new int[]{152,185,0,0}, new String[]{"D","0","0","C"},new double[]{0.4, 0.0, 0.0, 0.75}, new int[]{161, 170, 0, 0},
				new String[]{"D","0","0","B"}, new double[]{0.38, 0.0, 0.0, 0.88}, new int[]{216,0,216,0},		new int[]{179,0,207,0}, 18.0);
		
		SpecialWeapons CrescentAxe = new SpecialWeapons(new int[]{172,172,0,0}, new String[]{"D","D","0","B"}, new double[]{0.24, 0.21, 0.0, 0.82}, 24.0);
		
		Weapons GargoyleTailAxe = new Weapons(new int[]{232,0,0,0}, new String[]{"D","B","0","0"},new double[]{0.26, 0.84, 0.0, 0.0}, new int[]{251,0,0,0}, new String[]{"D","B","0","0"}, 	new int[]{232,0,0,232},
				new int[]{214,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.18, 0.58, 0.0, 0.0}, new int[]{175, 190, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.07, 0.24, 0.79, 0.0}, new int[]{168,171,0,0}, new String[]{"E","E","A","0"},new double[]{0.04, 0.16, 1.17, 0.0}, new int[]{157,192,0,0}, new String[]{"E","D","0","C"}, new double[]{0.09, 0.32, 0.0, 0.79}, new int[]{166, 175, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.09, 0.30, 0.0, 0.92}, new int[]{223,0,223,0},		new int[]{186,0,213,0}, 12.0);
		
		SpecialWeapons GolemAxe = new SpecialWeapons(new int[]{255,0,0,0}, new String[]{"C","E","0","0"}, new double[]{0.74, 0.16, 0.0, 0.0}, 11.0);
		
		Weapons HandAxe = new Weapons(new int[]{200,0,0,0}, new String[]{"C","D","0","0"}, new double[]{0.72, 0.36, 0.0, 0.0}, new int[]{216,0,0,0}, new String[]{"C","D","0","0"}, 	new int[]{200,0,0,200},
				new int[]{184,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.49, 0.25, 0.0, 0.0}, new int[]{150, 162, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.2, 0.09, 0.78, 0.0}, new int[]{144,148,0,0}, new String[]{"E","E","A","0"},new double[]{0.14, 0.07, 1.13, 0.0}, new int[]{135,165,0,0}, new String[]{"D","E","0","C"}, new double[]{0.27, 0.14, 0.0, 0.76}, new int[]{143, 150, 0, 0},
				new String[]{"D","E","0","B"}, new double[]{0.26, 0.13, 0.0, 0.91}, new int[]{192,0,192,0},		new int[]{161,0,184,0}, 28.0);
		
		
		//GREATAXES
		SpecialWeapons BlackKnightGreataxe = new SpecialWeapons(new int[]{343,0,0,0}, new String[]{"B","E","0","0"}, new double[]{0.9, 0.04, 0.0, 0.0}, 16.0);
		
		Weapons DemonsGreataxe = new Weapons(new int[]{285,0,0,0}, new String[]{"S","0","0","0"}, new double[]{1.5, 0.0, 0.0, 0.0}, new int[]{307,0,0,0}, new String[]{"S","0","0","0"}, 	new int[]{285,0,0,285},
				new int[]{262,0,0,0}, new String[]{"A","0","0","0"},new double[]{1.03, 0.0, 0.0, 0.0}, new int[]{215, 232, 0,0}, new String[]{"D","0","A","0"},
				new double[]{0.42, 0.0, 1.08, 0.0}, new int[]{206,211,0,0}, new String[]{"D","0","S","0"},new double[]{0.28, 0.0, 1.58, 0.0}, new int[]{192,235,0,0}, new String[]{"C","0","0","A"}, new double[]{0.57, 0.0, 0.0, 1.06}, new int[]{202, 215, 0, 0},
				new String[]{"C","0","0","A"}, new double[]{0.54, 0.0, 0.0, 1.26}, new int[]{273,0,273,0},		new int[]{230,0,262,0}, 22.0);
		
		SpecialWeapons DragonKingGreataxe = new SpecialWeapons(new int[]{570,0,0,0}, new String[]{"0","0","0","0"}, new double[]{0.0, 0.0, 0.0, 0.0}, 24.0);
		
		Weapons Greataxe = new Weapons(new int[]{350,0,0,0}, new String[]{"B","E","0","0"},new double[]{0.81, 0.13, 0.0, 0.0}, new int[]{378,0,0,0}, new String[]{"B","E","0","0"}, 	new int[]{350,0,0,350},
				new int[]{322,0,0,0}, new String[]{"C","E","0","0"},new double[]{0.57, 0.08, 0.0, 0.0}, new int[]{262, 285, 0,0}, new String[]{"D","E","C","0"},
				new double[]{0.22, 0.03, 0.68, 0.0}, new int[]{252,259,0,0}, new String[]{"E","E","B","0"},	new double[]{0.16, 0.02, 0.99, 0.0}, new int[]{237,290,0,0}, new String[]{"D","E","0","C"}, new double[]{0.32, 0.04, 0.0, 0.67}, new int[]{249, 262, 0, 0},
				new String[]{"D","E","0","C"},new double[]{0.28, 0.04, 0.0, 0.79}, new int[]{336,0,336,0},		new int[]{280,0,322,0}, 14.0);
		
		
				//DLC UNKNOWN PERCENTS
		SpecialWeapons StoneGreataxe = new SpecialWeapons(new int[]{285,0,0,0}, new String[]{"B","E","0","0"}, new double[]{0.96, 0.1, 0.0, 0.0}, 24.0);
		
		
		//DAGGERS		
		Weapons BanditsKnife = new Weapons(new int[]{140,0,0,0}, new String[]{"E","A","0","0"},new double[]{0.15, 1.02, 0.0, 0.0}, new int[]{151,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{140,0,0,140},
				new int[]{128,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.11, 0.7, 0.0, 0.0}, new int[]{105, 115, 0,0}, new String[]{"E","D","B","0"},
				new double[]{0.04, 0.28, 0.85, 0.0}, new int[]{100,104,0,0}, new String[]{"E","E","B","0"},new double[]{0.03, 0.2, 1.24, 0.0}, new int[]{95,115,0,0}, new String[]{"E","D","0","B"}, new double[]{0.06, 0.39, 0.0, 0.84}, new int[]{98, 105, 0, 0},
				new String[]{"E","D","0","B"},new double[]{0.06, 0.37, 0.0, 0.98}, new int[]{134,0,134,0},		new int[]{112,0,131,0}, 1.0);
		
		Weapons Dagger = new Weapons(new int[]{140,0,0,0}, new String[]{"E","A","0","0"},new double[]{0.15, 1.02, 0.0, 0.0}, new int[]{151,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{140,0,0,140},
				new int[]{128,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.11, 0.70, 0.0, 0.0}, new int[]{105, 115, 0,0}, new String[]{"E","D","B","0"},
				new double[]{0.04, 0.28, 0.85, 0.0}, new int[]{100,104,0,0}, new String[]{"E","E","A","0"},new double[]{0.03, 0.2, 1.24, 0.0}, new int[]{95,115,0,0}, new String[]{"E","D","0","B"},new double[]{0.06, 0.39, 0.0, 0.84}, new int[]{98, 105, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.06, 0.37, 0.0, 0.98}, new int[]{134,0,134,0},		new int[]{112,0,131,0}, .5);
		
		
				//DLC WEAPON
		SpecialWeapons DarkSilverTracer = new SpecialWeapons(new int[]{112,0,0,0}, new String[]{"E","S","0","0"}, new double[]{0.1, 1.45, 0.0, 0.0}, 1.0);
		
		SpecialWeapons GhostBlade = new SpecialWeapons(new int[]{165,0,0,0}, new String[]{"E","0","0","0"}, new double[]{0.12, 0.0, 0.0, 0.0}, .5);
		
		Weapons ParryingDagger = new Weapons(new int[]{135,0,0,0}, new String[]{"E","A","0","0"}, new double[]{0.15, 1.02, 0.0, 0.0}, new int[]{145,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{135,0,0,135},
				new int[]{124,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.11, 0.7, 0.0, 0.0}, new int[]{102, 110, 0,0}, new String[]{"E","D","B","0"},
				new double[]{0.04, 0.28, 0.85, 0.0}, new int[]{98,99,0,0}, new String[]{"E","E","A","0"},new double[]{0.03, 0.2, 1.24, 0.0}, new int[]{90,112,0,0}, new String[]{"E","D","0","B"}, new double[]{0.06, 0.39, 0.0, 0.84}, new int[]{96, 102, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.06, 0.37, 0.0, 0.98}, new int[]{129,0,129,0},		new int[]{108,0,126,0}, .5);
		
		SpecialWeapons PriscillasDagger = new SpecialWeapons(new int[]{120,0,0,0}, new String[]{"0","S","0","0"}, new double[]{0.0, 1.46, 0.0, 0.0}, 1.0);
		
		
		//THRUSTING SWORDS
		
		Weapons Estoc = new Weapons(new int[]{187,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.26, 0.84, 0.0, 0.0}, new int[]{202,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{187,0,0,187},
				new int[]{172,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.18, 0.58, 0.0, 0.0}, new int[]{140, 152, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.07, 0.24, 0.79, 0.0}, new int[]{134,139,0,0}, new String[]{"E","E","A","0"},new double[]{0.4, 0.16, 1.17, 0.0}, new int[]{127,155,0,0}, new String[]{"E","D","0","C"},new double[]{0.09, 0.32, 0.0, 0.79} , new int[]{135, 140, 0, 0},
				new String[]{"E","D","0","B"},new double[]{0.09, 0.32, 0.0, 0.92}, new int[]{180,0,180,0},		new int[]{149,0,172,0}, 3.0);
		
		Weapons MailBreaker = new Weapons(new int[]{142,0,0,0}, new String[]{"D","B","0","0"}, new double[]{0.26, 0.84, 0.0, 0.0}, new int[]{153,0,0,0}, new String[]{"D","B","0","0"}, 	new int[]{142,0,0,142},
				new int[]{132,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.18, 0.58, 0.0, 0.0}, new int[]{107, 115, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.7, 0.24, 0.79, 0.0}, new int[]{103,106,0,0}, new String[]{"E","E","A","0"},new double[]{0.04, 0.16, 1.17, 0.0}, new int[]{95,117,0,0}, new String[]{"E","D","0","C"}, new double[]{0.09, 0.32, 0.0, 0.79}, new int[]{101, 107, 0, 0},
				new String[]{"E","D","0","B"},new double[]{0.09, 0.3, 0.0, 0.92}, new int[]{136,0,136,0},new int[]{112,0,131,0}, .5);
		
		Weapons Rapier = new Weapons(new int[]{182,0,0,0}, new String[]{"D","B","0","0"}, 	new double[]{0.26, 0.84, 0.0, 0.0}, new int[]{197,0,0,0}, new String[]{"D","B","0","0"}, 	new int[]{182,0,0,182},
				new int[]{168,0,0,0}, new String[]{"E","C","0","0"}, 	new double[]{0.18, 0.58, 0.0, 0.0}, new int[]{137, 147, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.07, 0.24, 0.79, 0.0}, new int[]{132,134,0,0}, new String[]{"E","E","A","0"},		new double[]{0.04, 0.16, 1.17, 0.0}, new int[]{122,150,0,0}, new String[]{"E","D","0","C"},new double[]{0.09, 0.32, 0.0, 0.79}, new int[]{130, 137, 0, 0},
				new String[]{"E","D","0","B"}, 		new double[]{0.09, 0.32, 0.0, 0.92}, new int[]{175,0,175,0},		new int[]{144,0,167,0}, 1.5);
		
		Weapons RicardsRapier = new Weapons(new int[]{175,0,0,0}, new String[]{"E","A","0","0"},new double[]{0.07, 1.02, 0.0, 0.0}, new int[]{189,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{175,0,0,175},
				new int[]{162,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.04, 0.7, 0.0, 0.0}, new int[]{132, 142, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.02, 0.27, 0.79, 0.0}, new int[]{127,129,0,0}, new String[]{"E","E","A","0"},new double[]{0.01, 0.2, 1.15, 0.0}, new int[]{117,145,0,0}, new String[]{"E","D","0","C"},new double[]{0.02, 0.39, 0.0, 0.78}, new int[]{124, 132, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.02, 0.37, 0.0, 0.91}, new int[]{168,0,168,0},		new int[]{140,0,163,0}, 2.0);
		
		SpecialWeapons VelkasRapier = new SpecialWeapons(new int[]{93,156,0,0}, new String[]{"E","C","B","0"}, new double[]{0.05, 0.53, 0.97, 0.0}, 2.0);
		
		
		//STRAIGHT SWORDS
		SpecialWeapons AstorasStraightSword = new SpecialWeapons(new int[]{80,80,0,0}, new String[]{"C","C","0","C"}, new double[]{0.51, 0.51, 0.0, 0.51}, 3.0);
		
		Weapons BalderSideSword = new Weapons(new int[]{200,0,0,0}, new String[]{"E","A","0","0"}, 	new double[]{0.15, 1.02, 0.0, 0.0}, new int[]{216,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{200,0,0,200},
				new int[]{184,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.11, 0.7, 0.0, 0.0}, new int[]{132, 142, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.04, 0.28, 0.85, 0.0}, new int[]{127,129,0,0}, new String[]{"E","E","A","0"},new double[]{0.03, 0.2, 1.24, 0.0}, new int[]{117,145,0,0}, new String[]{"E","D","0","C"},new double[]{0.06, 0.39, 0.0, 0.84}, new int[]{124, 132, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.06, 0.37, 0.0, 0.98}, new int[]{168,0,168,0},		new int[]{140,0,163,0}, 3.0); 
		
		Weapons BarbedStraightSword = new Weapons(new int[]{200,0,0,0}, new String[]{"D","C","0","0"}, 	new double[]{0.45, 0.6, 0.0, 0.0}, new int[]{216,0,0,0}, new String[]{"D","C","0","0"}, 	new int[]{200,0,0,200},
				new int[]{184,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.31, 0.41, 0.0, 0.0}, new int[]{150, 162, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.13, 0.16, 0.75, 0.0}, new int[]{144,148,0,0}, new String[]{"E","E","A","0"},new double[]{0.09, 0.12, 1.11, 0.0}, new int[]{135,165,0,0}, new String[]{"E","D","0","C"},new double[]{0.18, 0.22, 0.0, 0.75}, new int[]{143, 150, 0, 0},  
				new String[]{"E","D","0","B"}, new double[]{0.16, 0.21, 0.0, 0.88}, new int[]{192,0,192,0},		new int[]{161,0,184,0}, 3.0);
		
		Weapons BroadSword = new Weapons(new int[]{205,0,0,0}, new String[]{"C","C","0","0"},new double[]{0.61, 0.61, 0.0, 0.0}, new int[]{221,0,0,0}, new String[]{"C","C","0","0"}, 	new int[]{205,0,0,205},
				new int[]{188,0,0,0}, new String[]{"D","D","0","0"}, new double[]{0.41, 0.41, 0.0, 0.0}, new int[]{155, 167, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.16, 0.16, 0.87, 0.0}, new int[]{127,129,0,0}, new String[]{"E","E","A","0"},	new double[]{0.12, 0.12, 1.29, 0.0}, new int[]{117,145,0,0}, new String[]{"E","D","0","C"},new double[]{0.24, 0.24, 0.0, 0.87}, new int[]{124, 132, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.21, 0.21, 1.03, 0.0}, new int[]{168,0,168,0},		new int[]{140,0,163,0}, 3.0);
		
		Weapons BrokenStraightSword = new Weapons(new int[]{100,0,0,0}, new String[]{"D","D","0","0"}, 	new double[]{0.45, 0.6, 0.0, 0.0}, new int[]{108,0,0,0}, new String[]{"D","C","0","0"}, 	new int[]{100,0,0,100},
				new int[]{92,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.31, 0.41, 0.0, 0.0}, new int[]{72, 82, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.13, 0.16, 0.75, 0.0}, new int[]{72,74,0,0}, new String[]{"E","E","A","0"},new double[]{0.09, 0.12, 1.11, 0.0}, new int[]{67,82,0,0}, new String[]{"E","D","0","C"},new double[]{0.18, 0.22, 0.0, 0.75}, new int[]{70, 75, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.16, 0.22, 0.0, 0.88}, new int[]{96,0,96,0},		new int[]{80,0,94,0}, 2.0);
		
		SpecialWeapons CrystalStraightSword = new SpecialWeapons(new int[]{150,80,0,0}, new String[]{"C","C","0","0"}, new double[]{0.58, 0.52, 0.0, 0.0}, 6.0);
		
		Weapons DarkSword = new Weapons(new int[]{205,0,0,0}, new String[]{"C","C","0","0"},new double[]{0.61, 0.61, 0.0, 0.0}, new int[]{221,0,0,0}, new String[]{"C","C","0","0"}, 	new int[]{205,0,0,205},
				new int[]{188,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.41, 0.41, 0.0, 0.0}, new int[]{155, 167, 0,0}, new String[]{"E","E","B","0"},
				new double[]{0.16, 0.16, 0.87, 0.0}, new int[]{148,153,0,0}, new String[]{"E","E","A","0"},new double[]{0.12, 0.12, 1.29, 0.0}, new int[]{137,170,0,0}, new String[]{"D","D","0","B"}, new double[]{0.24, 0.24, 0.0, 0.87}, new int[]{145, 155, 0, 0},
				new String[]{"D","D","0","A"}, new double[]{0.21, 0.21, 1.03, 0.0}, new int[]{196,0,196,0},		new int[]{165,0,188,0}, 6.0);
		
		SpecialWeapons DrakeSword = new SpecialWeapons(new int[]{300,80,0,0}, new String[]{"0","0","0","0"}, new double[]{0.0, 0.0, 0.0, 0.0}, 6.0);
		
		Weapons Longsword = new Weapons(new int[]{200,0,0,0}, new String[]{"C","C","0","0"},new double[]{0.61, 0.61, 0.0, 0.0}, new int[]{216,0,0,0}, new String[]{"C","C","0","0"}, 	new int[]{200,0,0,200},
				new int[]{184,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.41, 0.41, 0.0, 0.0}, new int[]{150, 162, 0,0}, new String[]{"E","E","B","0"},
				new double[]{0.16, 0.16, 0.87, 0.0}, new int[]{144,158,0,0}, new String[]{"E","E","A","0"},new double[]{0.12, 0.12, 1.29, 0.0}, new int[]{135,165,0,0}, new String[]{"D","D","0","B"},new double[]{0.24, 0.24, 0.0, 0.87}, new int[]{143, 150, 0, 0},
				new String[]{"D","D","0","A"},new double[]{0.21, 0.21, 1.03, 0.0}, new int[]{192,0,192,0},		new int[]{161,0,184,0}, 3.0);
		
		Weapons Shortsword = new Weapons(new int[]{195,0,0,0}, new String[]{"C","C","0","0"},new double[]{0.61, 0.61, 0.0, 0.0}, new int[]{210,0,0,0}, new String[]{"C","C","0","0"}, 	new int[]{195,0,0,195},
				new int[]{180,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.41, 0.41, 0.0, 0.0}, new int[]{147, 157, 0,0}, new String[]{"E","E","B","0"},
				new double[]{0.16, 0.16, 0.87, 0.0}, new int[]{141,143,0,0}, new String[]{"E","E","A","0"},new double[]{0.12, 0.12, 1.29, 0.0}, new int[]{132,160,0,0}, new String[]{"D","D","0","B"},new double[]{0.24, 0.24, 0.0, 0.87}, new int[]{140, 147, 0, 0},
				new String[]{"D","D","0","B"}, new double[]{0.21, 0.21, 1.03, 0.0}, new int[]{187,0,187,0},		new int[]{156,0,179,0}, 2.0);
		
		SpecialWeapons SilverKnightStraightSword = new SpecialWeapons(new int[]{262,80,0,0}, new String[]{"E","C","0","0"}, new double[]{0.08, 0.52, 0.0, 0.0}, 6.0);
		
		Weapons StraightSwordHilt = new Weapons(new int[]{50,0,0,0}, new String[]{"E","E","0","0"},new double[]{0.09, 0.08, 0.0, 0.0}, new int[]{54,0,0,0}, new String[]{"E","E","0","0"}, 	new int[]{50,0,0,50},
				new int[]{46,0,0,0}, new String[]{"E","E","0","0"},new double[]{0.06, 0.06, 0.0, 0.0}, new int[]{37, 40, 0,0}, new String[]{"E","E","E","0"},
				new double[]{0.03, 0.02, 0.13, 0.0}, new int[]{36,37,0,0}, new String[]{"E","E","E","0"},new double[]{0.01, 0.01, 0.19, 0.0}, new int[]{35,42,0,0}, new String[]{"E","E","0","E"},new double[]{0.03, 0.03, 0.0, 0.13}, new int[]{36, 37, 0, 0},
				new String[]{"E","E","0","E"}, new double[]{0.03, 0.03, 0.0, 0.15}, new int[]{48,0,48,0},		new int[]{41,0,46,0}, 1.0);
		
		Weapons SunlightStraightSword = new Weapons(new int[]{205,0,0,0}, new String[]{"C","C","0","0"}, new double[]{0.61, 0.61, 0.0, 0.0}, new int[]{221,0,0,0}, new String[]{"C","C","0","0"}, 	new int[]{205,0,0,205},
				new int[]{188,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.41, 0.41, 0.0, 0.0}, new int[]{155, 167, 0,0}, new String[]{"E","E","B","0"},
				new double[]{0.16, 0.16, 0.87, 0.0}, new int[]{148,153,0,0}, new String[]{"E","E","A","0"},new double[]{0.12, 0.12, 1.29, 0.0}, new int[]{137,170,0,0}, new String[]{"D","D","0","B"},new double[]{0.24, 0.24, 0.0, 0.87}, new int[]{145, 155, 0, 0},
				new String[]{"D","D","0","A"},new double[]{0.21, 0.21, 1.03, 0.0}, new int[]{196,0,196,0},		new int[]{165,0,188,0}, 4.0);
		
		HumanityWeapons AbyssGreatsword = new HumanityWeapons(new int[]{240,80,0,0}, new String[]{"C","C","0","0"}, new double[]{0.55, 0.55, 0.6, 0.6}, new int[]{19, 26, 35, 41, 47, 53, 59, 68, 74, 80}, 9.0);
		
		Weapons BastardSword = new Weapons(new int[]{262,0,0,0}, new String[]{"C","C","0","0"}, new double[]{.61, .61, 0.0, 0.0}, new int[]{283,0,0,0}, new String[]{"C","C","0","0"}, 	new int[]{262,0,0,262},
				new int[]{242,0,0,0}, new String[]{"D","D","0","0"}, new double[]{.41, .41, 0.0, 0.0}, new int[]{197, 212, 0,0}, new String[]{"E","E","B","0"},
				new double[]{.16, .16, 0.87, 0.0}, new int[]{189,194,0,0}, new String[]{"E","E","B","0"}, new double[]{.12, .12, 1.29, 0.0}, new int[]{177,217,0,0}, new String[]{"D","D","0","B"}, new double[]{.24, .24, 0.0, 0.87}, new int[]{187, 197, 0, 0},
				new String[]{"E","E","0","A"}, new double[]{.21, .21, 0.0, 1.03}, new int[]{252,0,252,0},		new int[]{207,0,239,0}, 6.0);
		
		SpecialWeapons BlackKnightSword = new SpecialWeapons(new int[]{330,80,0,0}, new String[]{"C","E","0","0"}, new double[]{0.58, 0.07, 0.0, 0.0}, 8.0);
		
		Weapons Claymore = new Weapons(new int[]{257,0,0,0}, new String[]{"C","C","0","0"}, new double[]{0.61, 0.61, 0.0, 0.0}, new int[]{278,0,0,0}, new String[]{"C","C","0","0"}, 	new int[]{257,0,0,257},
				new int[]{236,0,0,0}, new String[]{"D","D","0","0"}, new double[]{0.41, 0.41, 0.0, 0.0}, new int[]{192, 210, 0,0}, new String[]{"E","E","B","0"},
				 new double[]{0.16, 0.16, 0.87, 0.0}, new int[]{184,190,0,0}, new String[]{"E","E","A","0"}, new double[]{0.12, 0.12, 1.29, 0.0}, new int[]{175,212,0,0}, new String[]{"D","E","0","B"},  new double[]{0.24, 0.24, 0.0, 0.87}, new int[]{184, 192, 0, 0},
				new String[]{"D","D","0","A"},  new double[]{0.21, 0.21, 0.0, 1.03}, new int[]{247,0,247,0},new int[]{209,0,241,0}, 6.0); 
		
		SpecialWeapons CrystalGreatsword = new SpecialWeapons(new int[]{190,80,0,0}, new String[]{"C","E","0","0"},  new double[]{0.58, 0.52, 0.0, 0.0}, 8.0);
		
		Weapons Flamberge = new Weapons(new int[]{250,0,0,0}, new String[]{"D","B","0","0"}, new double[]{0.26, 0.84, 0.0, 0.0}, new int[]{270,0,0,0}, new String[]{"D","B","0","0"}, 	new int[]{250,0,0,250},
				new int[]{230,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.18, 0.58, 0.0, 0.0}, new int[]{187, 202, 0,0}, new String[]{"E","D","C","0"},
				 new double[]{0.07, 0.24, 0.79, 0.0}, new int[]{180,185,0,0}, new String[]{"E","E","A","0"}, new double[]{0.04, 0.16, 1.17, 0.0}, new int[]{170,207,0,0}, new String[]{"E","D","0","C"}, new double[]{0.09, 0.32, 0.0, 0.79}, new int[]{179, 187, 0, 0},
				new String[]{"E","D","0","B"},  new double[]{0.09, 0.3, 0.0, 0.92}, new int[]{240,0,240,0},		new int[]{200,0,232,0}, 6.0);
		
		SpecialWeapons GreatLordGreatsword = new SpecialWeapons(new int[]{384,80,0,0}, new String[]{"D","D","0","0"},  new double[]{0.23, 0.24, 0.0, 0.0}, 8.0);
		
		SpecialWeapons GreatswordOfArtorias = new SpecialWeapons(new int[]{180,127,0,0}, new String[]{"C","C","B","B"},  new double[]{0.55, 0.55, 0.85, 0.85}, 10.0);
		
		SpecialWeapons CursedGreatswordOfArtorias = new SpecialWeapons(new int[]{267,80,0,0}, new String[]{"C","C","C","C"}, new double[]{0.6, 0.6, 0.6, 0.6}, 10.0);
		
		Weapons ManSerpentGreatsword = new Weapons(new int[]{275,0,0,0}, new String[]{"A","0","0","0"},new double[]{1.05, 0.0, 0.0, 0.0}, new int[]{297,0,0,0}, new String[]{"A","0","0","0"}, 	new int[]{275,0,0,275},
				new int[]{254,0,0,0}, new String[]{"C","0","0","0"}, new double[]{0.72, 0.0, 0.0, 0.0}, new int[]{207, 222, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.03, 0.0, 0.75, 0.0}, new int[]{199,204,0,0}, new String[]{"D","0","A","0"},new double[]{0.21, 0.0, 1.11, 0.0}, new int[]{185,227,0,0}, new String[]{"D","0","0","C"}, new double[]{0.04, 0.0, 0.0, 0.75}, new int[]{197, 207, 0, 0},
				new String[]{"D","0","0","B"}, new double[]{0.38, 0.0, 0.0, 0.88}, new int[]{264,0,264,0},		new int[]{220,0,253,0}, 10.0);
		
		SpecialWeapons MoonlightGreatsword = new SpecialWeapons(new int[]{198,80,0,0}, new String[]{"0","0","S","0"}, new double[]{0.0, 0.0, 1.63, 0.0}, 6.0);
		
		SpecialWeapons ObsidianGreatsword = new SpecialWeapons(new int[]{480,80,0,0}, new String[]{"0","0","0","0"}, new double[]{0.0, 0.0, 0.0, 0.0}, 8.0);
		
		SpecialWeapons StoneGreatsword = new SpecialWeapons(new int[]{222,150,0,0}, new String[]{"C","C","E","0"}, new double[]{0.6, 0.55, 0.15, 0.0}, 18.0);
		
		
		//ULTRA GREATSWORDS
		SpecialWeapons BlackKnightGreatsword = new SpecialWeapons(new int[]{330,0,0,0}, new String[]{"B","E","0","0"}, new double[]{0.09, 0.05, 0.0, 0.0}, 14.0);
		
		Weapons DemonGreatMachete = new Weapons(new int[]{332,0,0,0}, new String[]{"A","0","0","0"}, new double[]{1.08, 0.0, 0.0, 0.0}, new int[]{359,0,0,0}, new String[]{"A","0","0","0"}, 	new int[]{332,0,0,332},
				new int[]{306,0,0,0}, new String[]{"C","0","0","0"}, new double[]{0.74, 0.0, 0.0, 0.0}, new int[]{250, 270, 0,0}, new String[]{"D","0","C","0"},
				new double[]{0.3, 0.0, 0.78, 0.0}, new int[]{240,245,0,0}, new String[]{"D","0","A","0"},new double[]{0.21, 0.0, 1.13, 0.0}, new int[]{225,275,0,0}, new String[]{"D","0","0","C"},new double[]{0.42, 0.0, 0.0, 0.76}, new int[]{236, 250, 0, 0},
				new String[]{"D","0","0","B"}, new double[]{0.39, 0.0, 0.0, 0.91}, new int[]{319,0,319,0},		new int[]{266,0,308,0}, 18.0);
		
		SpecialWeapons DragonGreatsword = new SpecialWeapons(new int[]{585,0,0,0}, new String[]{"0","0","0","0"}, new double[]{0.0, 0.0, 0.0, 0.0}, 24.0);
		
		Weapons Greatsword = new Weapons(new int[]{325,0,0,0}, new String[]{"C","D","0","0"}, 	new double[]{0.72, 0.31, 0.0, 0.0}, new int[]{351,0,0,0}, new String[]{"C","D","0","0"}, 	new int[]{325,0,0,325},
				new int[]{300,0,0,0}, new String[]{"D","E","0","0"}, new double[]{0.49, 0.2, 0.0, 0.0}, new int[]{245, 265, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.2, 0.08, 0.74, 0.0}, new int[]{235,241,0,0}, new String[]{"E","E","A","0"},new double[]{0.15, 0.06, 1.08, 0.0}, new int[]{220,267,0,0}, new String[]{"D","E","0","C"},new double[]{0.27, 0.12, 0.0, 0.73}, new int[]{231, 245, 0, 0},
				new String[]{"D","E","0","B"}, new double[]{0.26, 0.1, 0.0, 0.86}, new int[]{312,0,312,0},		new int[]{259,0,301,0}, 12.0);
		
		Weapons Zweihander = new Weapons(new int[]{325,0,0,0}, new String[]{"C","D","0","0"}, 	new double[]{0.72, 0.31, 0.0, 0.0}, new int[]{351,0,0,0}, new String[]{"C","D","0","0"}, 	new int[]{325,0,0,325},
				new int[]{300,0,0,0}, new String[]{"D","E","0","0"},new double[]{0.49, 0.2, 0.0, 0.0}, new int[]{245, 265, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.2, 0.08, 0.74, 0.0}, new int[]{235,241,0,0}, new String[]{"E","E","A","0"},new double[]{0.15, 0.06, 1.08, 0.0}, new int[]{220,267,0,0}, new String[]{"D","E","0","C"}, new double[]{0.27, 0.12, 0.0, 0.73}, new int[]{231, 245, 0, 0},
				new String[]{"D","E","0","B"}, new double[]{0.26, 0.1, 0.0, 0.86}, new int[]{312,0,312,0},		new int[]{259,0,301,0}, 10.0);
		
		
		//KATANAS		
		HumanityWeapons ChaosBlade = new HumanityWeapons(new int[]{216,0,0,0}, new String[]{"0","B","0","0"}, new double[]{0.0, 0.85, 0.0, 0.0}, new int[]{20, 30, 40, 46, 53, 59, 65, 71, 78, 84}, 6.0);
		
		Weapons Iaito = new Weapons(new int[]{200,0,0,0}, new String[]{"0","A","0","0"}, 	new double[]{0.0, 1.02, 0.0, 0.0}, new int[]{237,0,0,0}, new String[]{"0","A","0","0"}, 	new int[]{220,0,0,220},
				new int[]{202,0,0,0}, new String[]{"0","C","0","0"},new double[]{0.0, 0.7, 0.0, 0.0}, new int[]{165, 180, 0,0}, new String[]{"","D","C","0"},
				new double[]{0.0, 0.27, 0.73, 0.0}, new int[]{158,162,0,0}, new String[]{"0","E","A","0"},new double[]{0.0, 0.19, 1.08, 0.0}, new int[]{147,182,0,0}, new String[]{"D","E","0","C"},new double[]{0.0, 0.38, 0.0, 0.73}, new int[]{156, 165, 0, 0},
				new String[]{"0","D","0","B"}, new double[]{0.0, 0.36, 0.0, 0.85}, new int[]{211,0,211,0},		new int[]{177,0,204,0}, 5.0);
		
		Weapons Uchigatana = new Weapons(new int[]{225,0,0,0}, new String[]{"0","A","0","0"}, new double[]{0.0, 1.02, 0.0, 0.0}, new int[]{243,0,0,0}, new String[]{"0","A","0","0"}, 	new int[]{225,0,0,225},
				new int[]{208,0,0,0}, new String[]{"0","C","0","0"}, new double[]{0.0, 0.7, 0.0, 0.0}, new int[]{170, 182, 0,0}, new String[]{"0","D","C","0"},
				new double[]{0.0, 0.27, 0.73, 0.0}, new int[]{163,167,0,0}, new String[]{"0","E","A","0"},new double[]{0.0, 0.19, 1.08, 0.0}, new int[]{152,185,0,0}, new String[]{"0","D","0","C"}, new double[]{0.0, 0.38, 0.0, 0.73}, new int[]{161, 170, 0, 0},
				new String[]{"0","D","0","B"}, new double[]{0.0, 0.36, 0.0, 0.85}, new int[]{216,0,216,0},		new int[]{179,0,207,0}, 5.0);
		
		Weapons WashingPole = new Weapons(new int[]{225,0,0,0}, new String[]{"D","C","0","0"}, 	new double[]{0.45, 0.6, 0.0, 0.0}, new int[]{243,0,0,0}, new String[]{"D","C","0","0"}, 	new int[]{225,0,0,225},
				new int[]{208,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.0, 0.31, 0.41, 0.0}, new int[]{170, 182, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.13, 0.16, 0.75, 0.0}, new int[]{163,167,0,0}, new String[]{"E","E","A","0"},	new double[]{0.09, 0.12, 1.11, 0.0}, new int[]{152,185,0,0}, new String[]{"E","D","0","C"},new double[]{0.18, 0.22, 0.0, 0.75}, new int[]{161, 170, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.16, 0.21, 0.0, 0.88}, new int[]{216,0,216,0},		new int[]{179,0,207,0}, 8.0);
		
		
		//CURVED SWORDS
		Weapons Falchion = new Weapons(new int[]{205,0,0,0}, new String[]{"E","A","0","0"},new double[]{0.15, 1.02, 0.0, 0.0}, new int[]{221,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{205,0,0,205},
				new int[]{186,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.11, 0.7, 0.0, 0.0}, new int[]{155, 167, 0,0}, new String[]{"E","D","B","0"},
				new double[]{0.04, 0.28, 0.85, 0.0}, new int[]{148,153,0,0}, new String[]{"E","E","A","0"},	new double[]{0.03, 0.2, 1.24, 0.0}, new int[]{137,170,0,0}, new String[]{"E","D","0","B"}, new double[]{0.06, 0.39, 0.0, 0.84}, new int[]{145, 155, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.06, 0.37, 0.0, 0.98}, new int[]{196,0,196,0},		new int[]{165,0,188,0}, 2.5);
		
		SpecialWeapons GoldTracer = new SpecialWeapons(new int[]{195,0,0,0}, new String[]{"E","A","0","0"}, new double[]{0.0, 0.0, 0.0, 0.0}, 2.0);
		
		SpecialWeapons JaggedGhostBlade = new SpecialWeapons(new int[]{232,0,0,0}, new String[]{"E","0","0","0"}, new double[]{0.13, 0.0, 0.0, 0.0}, 1.5);
		
		Weapons PaintingGuardianSword = new Weapons(new int[]{190,0,0,0}, new String[]{"E","A","0","0"}, new double[]{0.1, 1.3, 0.0, 0.0}, new int[]{205,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{190,0,0,190},
				new int[]{174,0,0,0}, new String[]{"E","B","0","0"}, new double[]{0.07, 0.89, 0.0, 0.0}, new int[]{142, 155, 0,0}, new String[]{"E","D","A","0"},
				new double[]{0.03, 0.36, 1.02, 0.0}, new int[]{136,141,0,0}, new String[]{"E","D","S","0"},	new double[]{0.02, 0.26, 1.49, 0.0}, new int[]{127,157,0,0}, new String[]{"E","D","0","B"},new double[]{0.03, 0.5, 0.0, 1.0}, new int[]{135, 142, 0, 0},
				new String[]{"E","D","0","A"}, new double[]{0.03, 0.46, 0.0, 1.18}, new int[]{182,0,182,0},		new int[]{151,0,174,0}, 1.5);
		
		//it goes pysdmg from humanity and then fire dmg from humanity
		HumanityWeapons QuelaagsFurysword = new HumanityWeapons(new int[]{90,0,255,0}, new String[]{"E","B","0","0"}, new double[]{0.08, 0.94, 0.0, 0.0}, new int[]{2,7,14,24,36,51,68,88,90,115, 13,33,60,91,126,165,208,256,308, 364}, 3.5);
		
		Weapons Scimitar = new Weapons(new int[]{200,0,0,0}, new String[]{"E","A","0","0"},new double[]{0.15, 1.02, 0.0, 0.0}, new int[]{216,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{200,0,0,200},
				new int[]{174184,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.11, 0.7, 0.0, 0.0}, new int[]{150, 162, 0,0}, new String[]{"E","E","B","0"},
				new double[]{0.04, 0.28, 0.85, 0.0}, new int[]{144,148,0,0}, new String[]{"E","E","A","0"},new double[]{0.03, 0.2, 1.24, 0.0}, new int[]{135,165,0,0}, new String[]{"E","D","0","B"}, new double[]{0.06, 0.39, 0.0, 0.84}, new int[]{143, 150, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.06, 0.37, 0.0, 0.98}, new int[]{192,0,192,0},		new int[]{161,0,184,0}, 1.5);
		
		Weapons Shotel = new Weapons(new int[]{205,0,0,0}, new String[]{"E","B","0","0"}, new double[]{0.18, 0.86, 0.0, 0.0}, new int[]{221,0,0,0}, new String[]{"E","B","0","0"}, 	new int[]{205,0,0,205},
				new int[]{188,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.12, 0.6, 0.0, 0.0}, new int[]{155, 167, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.04, 0.25, 0.75, 0.0}, new int[]{148,153,0,0}, new String[]{"E","E","A","0"},new double[]{0.03, 0.18, 1.09, 0.0}, new int[]{137,170,0,0}, new String[]{"E","D","0","C"}, new double[]{0.07, 0.33, 0.0, 0.74}, new int[]{145, 155, 0, 0},
				new String[]{"E","D","0","B"},new double[]{0.06, 0.31, 0.0, 0.87}, new int[]{196,0,196,0},		new int[]{165,0,188,0}, 2.5);
		
		
		//CURVED GREATSWORDS
		SpecialWeapons GravelordSword = new SpecialWeapons(new int[]{397,0,0,0}, new String[]{"E","E","0","0"}, new double[]{0.13, 0.13, 0.0, 0.0}, 10.0);
		
		Weapons Murakumo = new Weapons(new int[]{282,0,0,0}, new String[]{"E","A","0","0"}, new double[]{0.15, 1.02, 0.0, 0.0}, new int[]{305,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{282,0,0,282},
				new int[]{260,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.11, 0.7, 0.0, 0.0}, new int[]{212, 230, 0,0}, new String[]{"E","E","B","0"},
				new double[]{0.04, 0.28, 0.85, 0.0}, new int[]{204,208,0,0}, new String[]{"E","E","A","0"},new double[]{0.03, 0.2, 1.24, 0.0}, new int[]{190,232,0,0}, new String[]{"E","D","0","B"},new double[]{0.06, 0.39, 0.0, 0.84}, new int[]{202, 212, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.06, 0.37, 0.0, 0.98}, new int[]{271,0,271,0},		new int[]{227,0,259,0}, 12.0);
		
		Weapons Server = new Weapons(new int[]{267,0,0,0}, new String[]{"E","B","0","0"}, new double[]{0.18, 0.86, 0.0, 0.0}, new int[]{288,0,0,0}, new String[]{"E","B","0","0"}, 	new int[]{267,0,0,267},
				new int[]{246,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.12, 0.6, 0.0, 0.0}, new int[]{200, 217, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.04, 0.25, 0.75, 0.0}, new int[]{192,199,0,0}, new String[]{"E","E","A","0"},	new double[]{0.03, 0.18, 1.09, 0.0}, new int[]{180,220,0,0}, new String[]{"E","D","0","C"},new double[]{0.07, 0.33, 0.0, 0.74}, new int[]{192, 200, 0, 0},
				new String[]{"E","D","0","B"},new double[]{0.06, 0.31, 0.0, 0.87}, new int[]{256,0,256,0},		new int[]{213,0,246,0}, 10.0);
		
		
		//SPEARS
		SpecialWeapons ChannelersTrident = new SpecialWeapons(new int[]{105,172,0,0}, new String[]{"E","C","B","0"}, new double[]{0.11, 0.55, 0.88, 0.0}, 6.0);
		
		SpecialWeapons DemonsSpear = new SpecialWeapons(new int[]{150,0,0,180}, new String[]{"C","C","0","0"}, new double[]{0.6, 0.6, 0.0, 0.0}, 4.0);
		
		SpecialWeapons DragonslayerSpear = new SpecialWeapons(new int[]{142,0,0,97}, new String[]{"C","B","0","B"}, new double[]{0.6, 1.0, 0.0, 1.0}, 10.0);
		
		Weapons FourProngedPlow = new Weapons(new int[]{187,0,0,0}, new String[]{"D","B","0","0"}, 	new double[]{0.4, 0.72, 0.0, 0.0}, new int[]{202,0,0,0}, new String[]{"D","B","0","0"}, 	new int[]{187,0,0,187},
				new int[]{172,0,0,0}, new String[]{"D","C","0","0"}, 	new double[]{0.3, 0.54, 0.0, 0.0}, new int[]{140, 152, 0,0}, new String[]{"E","D","B","0"},
				new double[]{0.11, 0.2, 0.81, 0.0}, new int[]{134,139,0,0}, new String[]{"E","E","S","0"},		new double[]{0.08, 0.14, 0.79, 0.0}, new int[]{127,155,0,0}, new String[]{"E","D","0","B"}, new double[]{0.15, 0.28, 0.0, 0.8}, new int[]{135, 140, 0, 0},
				new String[]{"E","D","0","A"}, 		new double[]{0.14, 0.26, 0.0, 0.94}, new int[]{180,0,180,0},		new int[]{151,0,172,0}, 5.5);
		
		SpecialWeapons MoonlightButterflyHorn = new SpecialWeapons(new int[]{0,180,0,0}, new String[]{"0","0","0","B"}, new double[]{0.0, 0.0, 1.0, 0.0}, 4.0);
		
		Weapons Partizan = new Weapons(new int[]{200,0,0,0}, new String[]{"D","B","0","0"},new double[]{0.26, 0.84, 0.0, 0.0}, new int[]{216,0,0,0}, new String[]{"D","B","0","0"}, 	new int[]{200,0,0,200},
				new int[]{184,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.18, 0.58, 0.0, 0.0}, new int[]{150, 162, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.07, 0.24, 0.79, 0.0}, new int[]{144,148,0,0}, new String[]{"E","E","A","0"},	new double[]{0.04, 0.16, 1.17, 0.0}, new int[]{135,165,0,0}, new String[]{"E","D","0","C"},new double[]{0.09, 0.32, 0.0, 0.79}, new int[]{143, 150, 0, 0},
				new String[]{"E","D","0","B"},new double[]{0.09, 0.32, 0.0, 0.92}, new int[]{192,0,192,0},		new int[]{161,0,184,0}, 4.5);
		
		Weapons Pike = new Weapons(new int[]{215,0,0,0}, new String[]{"D","B","0","0"},new double[]{0.26, 0.84, 0.0, 0.0}, new int[]{232,0,0,0}, new String[]{"D","B","0","0"}, 	new int[]{215,0,0,215},
				new int[]{198,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.18, 0.58, 0.0, 0.0}, new int[]{162, 175, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.07, 0.24, 0.0, 0.79}, new int[]{156,160,0,0}, new String[]{"E","E","A","0"},new double[]{0.04, 0.16, 1.17, 0.0}, new int[]{145,177,0,0}, new String[]{"E","D","0","C"},new double[]{0.09, 0.32, 0.0, 0.79}, new int[]{153, 162, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.09, 0.3, 0.0, 0.92}, new int[]{206,0,206,0},		new int[]{172,0,200,0}, 10.0);
		
		SpecialWeapons SilverKnightSpear = new SpecialWeapons(new int[]{244,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.04, 0.6, 0.0, 0.0}, 6.0);
		
		Weapons Spear = new Weapons(new int[]{200,0,0,0}, new String[]{"D","B","0","0"}, 	new double[]{0.26, 0.84, 0.0, 0.0}, new int[]{216,0,0,0}, new String[]{"D","B","0","0"}, 	new int[]{200,0,0,200},
				new int[]{184,0,0,0}, new String[]{"E","C","0","0"}, 	new double[]{0.18, 0.58, 0.0, 0.0}, new int[]{150, 162, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.07, 0.24, 0.79, 0.0}, new int[]{144,148,0,0}, new String[]{"E","E","A","0"},		new double[]{0.04, 0.16, 1.17, 0.0}, new int[]{135,165,0,0}, new String[]{"E","D","0","C"},new double[]{0.09, 0.32, 0.0, 0.79}, new int[]{143, 150, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.09, 0.3, 0.0, 0.92}, new int[]{192,0,192,0},		new int[]{161,0,184,0}, 3.5);
		
		Weapons WingedSpear = new Weapons(new int[]{215,0,0,0}, new String[]{"E","B","0","0"}, 	new double[]{0.18, 0.86, 0.0, 0.0}, new int[]{232,0,0,0}, new String[]{"E","B","0","0"}, 	new int[]{215,0,0,215},
				new int[]{198,0,0,0}, new String[]{"E","C","0","0"},new double[]{0.12, 0.6, 0.0, 0.0}, new int[]{162, 175, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.04, 0.25, 0.75, 0.0}, new int[]{156,160,0,0}, new String[]{"E","E","A","0"},	new double[]{0.03, 0.18, 1.09, 0.0}, new int[]{145,177,0,0}, new String[]{"E","D","0","C"},new double[]{0.07, 0.33, 0.0, 0.74}, new int[]{153, 162, 0, 0},
				new String[]{"E","D","0","B"},new double[]{0.06, 0.31, 0.0, 0.87}, new int[]{206,0,206,0},		new int[]{172,0,200,0}, 4.5);
		
		
		//HALBERDS
		SpecialWeapons BlackKnightHalberd = new SpecialWeapons(new int[]{367,0,0,0}, new String[]{"D","E","0","0"}, new double[]{0.44, 0.02, 0.0, 0.0}, 14.0);
		
		Weapons GargoyleHalberd = new Weapons(new int[]{287,0,0,0}, new String[]{"D","D","0","0"}, 	new double[]{0.46, 0.48, 0.0, 0.0}, new int[]{310,0,0,0}, new String[]{"E","B","0","0"}, 	new int[]{287,0,0,287},
				new int[]{264,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.33, 0.33, 0.0, 0.0}, new int[]{215, 232, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.13, 0.13, 0.68, 0.0}, new int[]{206,213,0,0}, new String[]{"E","E","B","0"},new double[]{0.09, 0.09, 0.99, 0.0}, new int[]{195,237,0,0}, new String[]{"E","E","0","C"},new double[]{0.18, 0.19, 0.0, 0.67}, new int[]{205, 213, 0, 0},
				new String[]{"E","E","0","C"},new double[]{0.16, 0.16, 0.0, 0.79}, new int[]{276,0,276,0},		new int[]{230,0,266,0}, 6.0);
		
		SpecialWeapons GiantsHalberd = new SpecialWeapons(new int[]{210,0,0,202}, new String[]{"D","D","0","0"}, new double[]{0.38, 0.5, 0.0, 0.0}, 16.0);
		
		Weapons GreatScythe = new Weapons(new int[]{250,0,0,0}, new String[]{"E","A","0","0"}, 	new double[]{0.15, 1.02, 0.0, 0.0}, new int[]{270,0,0,0}, new String[]{"E","A","0","0"}, 	new int[]{250,0,0,250},
				new int[]{230,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.11, 0.7, 0.0, 0.0}, new int[]{187, 202, 0,0}, new String[]{"E","D","B","0"},
				new double[]{0.04, 0.28, 0.85, 0.0}, new int[]{180,185,0,0}, new String[]{"E","E","A","0"},	new double[]{0.03, 0.2, 1.24, 0.0}, new int[]{170,207,0,0}, new String[]{"E","D","0","B"}, new double[]{0.06, 0.39, 0.0, 0.84}, new int[]{179, 187, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.06, 0.37, 0.0, 0.98}, new int[]{240,0,240,0},		new int[]{200,0,232,0}, 5.0);
		
		Weapons Halberd = new Weapons(new int[]{275,0,0,0}, new String[]{"D","C","0","0"}, 	new double[]{0.45, 0.6, 0.0, 0.0}, new int[]{297,0,0,0}, new String[]{"D","C","0","0"}, 	new int[]{275,0,0,275},
				new int[]{254,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.31, 0.41, 0.0, 0.0}, new int[]{207, 222, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.13, 0.16, 0.75, 0.0}, new int[]{199,204,0,0}, new String[]{"E","E","A","0"},	new double[]{0.09, 0.12, 1.11, 0.0}, new int[]{185,227,0,0}, new String[]{"E","D","0","C"}, new double[]{0.18, 0.22, 0.0, 0.75}, new int[]{197, 207, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.16, 0.21, 0.0, 0.88}, new int[]{264,0,264,0},		new int[]{220,0,253,0}, 6.0);
		
		SpecialWeapons LifehuntScythe = new SpecialWeapons(new int[]{270,0,0,202}, new String[]{"E","B","0","0"}, new double[]{0.08, 0.94, 0.0, 0.0}, 6.0);
		
		Weapons Lucerne = new Weapons(new int[]{275,0,0,0}, new String[]{"D","C","0","0"}, 	new double[]{0.45, 0.6, 0.0, 0.0}, new int[]{297,0,0,0}, new String[]{"D","C","0","0"}, 	new int[]{275,0,0,275},
				new int[]{254,0,0,0}, new String[]{"D","D","0","0"},new double[]{0.31, 0.41, 0.0, 0.0}, new int[]{207, 222, 0,0}, new String[]{"E","E","C","0"},
				new double[]{0.13, 0.16, 0.75, 0.0}, new int[]{199,204,0,0}, new String[]{"E","E","A","0"},	new double[]{0.09, 0.12, 1.11, 0.0}, new int[]{185,227,0,0}, new String[]{"E","D","0","C"}, new double[]{0.18, 0.22, 0.0, 0.75}, new int[]{197, 207, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.16, 0.21, 0.0, 0.88}, new int[]{264,0,264,0},		new int[]{220,0,253,0}, 5.5);
		
		Weapons Scythe = new Weapons(new int[]{275,0,0,0}, new String[]{"E","B","0","0"}, 	new double[]{0.18, 0.86, 0.0, 0.0}, new int[]{297,0,0,0}, new String[]{"E","B","0","0"}, 	new int[]{275,0,0,275},
				new int[]{254,0,0,0}, new String[]{"E","C","0","0"}, new double[]{0.12, 0.6, 0.0, 0.0}, new int[]{207, 222, 0,0}, new String[]{"E","D","C","0"},
				new double[]{0.04, 0.25, 0.75, 0.0}, new int[]{199,204,0,0}, new String[]{"E","E","A","0"},	new double[]{0.03, 0.18, 1.09, 0.0}, new int[]{185,227,0,0}, new String[]{"E","D","0","C"}, new double[]{0.07, 0.33, 0.0, 0.74}, new int[]{197, 207, 0, 0},
				new String[]{"E","D","0","B"}, new double[]{0.06, 0.31, 0.0, 0.87}, new int[]{264,0,264,0},		new int[]{220,0,253,0}, 5.0);
		
		SpecialWeapons TitaniteCatchPole = new SpecialWeapons(new int[]{187,212,0,0}, new String[]{"D","D","D","0"}, new double[]{0.25, 0.45, 0.4, 0.0}, 6.0);
		
		
		//BOWS
		Weapons BlackBowOfPharis = new Weapons(new int[]{85,0,0,0}, new String[]{"E","S","0","0"}, 	new double[]{0.15, 1.98, 0.0, 0.0}, new int[]{91,0,0,0}, new String[]{"E","S","0","0"}, 	new int[]{156,0,0,170},
				new int[]{78,0,0,0}, new String[]{"E","A","0","0"},new double[]{0.11, 1.36, 0.0, 0.0}, new int[]{65, 70, 0,0}, new String[]{"E","C","S","0"},
				new double[]{0.04, 0.55, 1.53, 0.0}, new int[]{62,62,0,0}, new String[]{"E","D","S","0"},	new double[]{0.03, 0.39, 1.75, 0.0}, new int[]{57,70,0,0}, new String[]{"E","C","0","S"},new double[]{0.06, 0.76, 0.0, 1.52}, new int[]{59, 65, 0, 0},
				new String[]{"E","C","0","S"}, new double[]{0.06, 0.7, 0.0, 1.8}, new int[]{153,0,153,0},		new int[]{120,0,149,0}, 1.0);
		
		Weapons CompositeBow = new Weapons(new int[]{137,0,0,0}, new String[]{"C","C","0","0"}, 	new double[]{0.72, 0.72, 0.0, 0.0}, new int[]{148,0,0,0}, new String[]{"C","C","0","0"}, 	new int[]{181,0,0,203},
				new int[]{126,0,0,0}, new String[]{"D","D","0","0"}, new double[]{0.49, 0.49, 0.0, 0.0}, new int[]{102, 112, 0,0}, new String[]{"E","E","A","0"},
				new double[]{0.2, 0.2, 1.03, 0.0}, new int[]{98,102,0,0}, new String[]{"E","E","A","0"},new double[]{0.14, 0.14, 1.17, 0.0}, new int[]{92,112,0,0}, new String[]{"D","D","0","A"},new double[]{0.27, 0.27, 0.0, 1.03}, new int[]{98, 102, 0, 0},
				new String[]{"D","D","0","A"}, new double[]{0.26, 0.26, 0.0, 1.21}, new int[]{181,0,181,0},		new int[]{127,0,170,0}, 1.0);
		
		SpecialWeapons DarkmoonBow = new SpecialWeapons(new int[]{127,127,0,0}, new String[]{"E","D","0","D"}, new double[]{0.18, 0.32, 0.5, 0.0}, 1.0);
		
		Weapons LongBow = new Weapons(new int[]{90,0,0,0}, new String[]{"D","S","0","0"}, 	new double[]{0.45, 1.68, 0.0, 0.0}, new int[]{97,0,0,0}, new String[]{"D","S","0","0"}, 	new int[]{165,0,0,180},
				new int[]{82,0,0,0}, new String[]{"D","A","0","0"}, new double[]{0.31, 1.15, 0.0, 0.0}, new int[]{67, 72, 0,0}, new String[]{"E","D","S","0"},
				new double[]{0.13, 0.46, 1.53, 0.0}, new int[]{64,67,0,0}, new String[]{"E","D","S","0"},new double[]{0.09, 0.33, 1.75, 0.0}, new int[]{60,75,0,0}, new String[]{"E","C","0","S"}, new double[]{0.18, 0.64, 0.0, 1.52}, new int[]{65, 67, 0, 0},
				new String[]{"E","C","0","S"}, new double[]{0.16, 0.6, 0.0, 1.80}, new int[]{162,0,162,0},		new int[]{128,0,158,0}, 1.0); 
		
		Weapons ShortBow = new Weapons(new int[]{77,0,0,0}, new String[]{"D","S","0","0"}, 	new double[]{0.45, 1.68, 0.0, 0.0}, new int[]{83,0,0,0}, new String[]{"D","S","0","0"}, 	new int[]{142,0,0,155},
				new int[]{72,0,0,0}, new String[]{"D","A","0","0"}, new double[]{0.31, 1.15, 0.0, 0.0}, new int[]{57, 62, 0,0}, new String[]{"E","D","S","0"},
				new double[]{0.13, 0.46, 1.53, 0.0}, new int[]{55,58,0,0}, new String[]{"E","D","S","0"},new double[]{0.09, 0.33, 1.75, 0.0}, new int[]{52,65,0,0}, new String[]{"E","C","0","S"},new double[]{0.18, 0.64, 0.0, 1.52}, new int[]{54, 57, 0, 0},
				new String[]{"E","C","0","S"}, 	new double[]{0.16, 0.6, 0.0, 1.8}, new int[]{139,0,139,0},		new int[]{108,0,136,0}, .5);
		
		
		//CROSSBOWS
		Weapons Avelyn = new Weapons(new int[]{229,0,0,0}, new String[]{"0","0","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{251,0,0,0}, new String[]{"0","0","0","0"}, 	new int[]{140,0,0,133},
				new int[]{0,0,0,0}, new String[]{"0","0","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{151, 154, 0,0}, new String[]{"0","0","0","0"},
				new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{0,0,0,0}, new String[]{"0","0","0","0"},		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{137,170,0,0}, new String[]{"0","0","0","0"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{0, 0, 0, 0},
				new String[]{"0","0","0","0"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{153,0,153,0},		new int[]{0,0,0,0}, 6.0);
		
		Weapons HeavyCrossbow = new Weapons(new int[]{341,0,0,0}, new String[]{"0","0","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{374,0,0,0}, new String[]{"0","0","0","0"}, 	new int[]{209,0,0,198},
				new int[]{0,0,0,0}, new String[]{"0","0","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{221, 231, 0,0}, new String[]{"0","0","0","0"},
				new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{0,0,0,0}, new String[]{"0","0","0","0"},		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{203,247,0,0}, new String[]{"0","0","0","0"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{0, 0, 0, 0},
				new String[]{"0","0","0","0"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{228,0,228,0},		new int[]{0,0,0,0}, 5.0);
		
		Weapons LightCrossbow = new Weapons(new int[]{310,0,0,0}, new String[]{"0","0","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{340,0,0,0}, new String[]{"0","0","0","0"}, 	new int[]{190,0,0,180},
				new int[]{0,0,0,0}, new String[]{"0","0","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{205, 210, 0,0}, new String[]{"0","0","0","0"},
				new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{0,0,0,0}, new String[]{"0","0","0","0"},		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{187,225,0,0}, new String[]{"0","0","0","0"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{0, 0, 0, 0},
				new String[]{"0","0","0","0"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{207,0,207,0},		new int[]{0,0,0,0}, 3.0);
		
		Weapons SniperCrossbow = new Weapons(new int[]{322,0,0,0}, new String[]{"0","0","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{353,0,0,0}, new String[]{"0","0","0","0"}, 	new int[]{197,0,0,187},
				new int[]{0,0,0,0}, new String[]{"0","0","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{210, 215, 0,0}, new String[]{"0","0","0","0"},
				new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{0,0,0,0}, new String[]{"0","0","0","0"},		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{192,236,0,0}, new String[]{"0","0","0","0"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{0, 0, 0, 0},
				new String[]{"0","0","0","0"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{215,0,215,0},		new int[]{0,0,0,0}, 8.0);
		
		
		//GREATBOWS
		SpecialWeapons DragonslayerGreatbow = new SpecialWeapons(new int[]{135,0,0,0}, new String[]{"C","C","0","0"}, new double[]{0.64, 0.64, 0.0, 0.0}, 10.0);
		
		SpecialWeapons GoughsGreatbow = new SpecialWeapons(new int[]{127,212,0,0}, new String[]{"B","C","0","0"}, new double[]{0.0, 0.0, 0.0, 0.0}, 13.0);
		
		
		//WHIPS
		Weapons GuardianTail = new Weapons(new int[]{210,0,0,0}, new String[]{"0","C","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{226,0,0,0}, new String[]{"0","C","0","0"}, 	new int[]{210,0,0,210},
				new int[]{194,0,0,0}, new String[]{"0","D","0","0"}, 	new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{157, 170, 0,0}, new String[]{"0","E","C","0"},
				new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{151,155,0,0}, new String[]{"0","E","C","0"},		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{142,172,0,0}, new String[]{"0","D","0","C"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{150, 157, 0, 0},
				new String[]{"0","D","0","C"}, 		new double[]{0.0, 0.0, 0.0, 0.0}, new int[]{201,0,201,0},		new int[]{170,0,193,0}, 5.0);
		
		Weapons NotchedWhip = new Weapons(new int[]{190,0,0,0}, new String[]{"0","A","0","0"}, 	new double[]{0.0, 1.02, 0.0, 0.0}, new int[]{205,0,0,0}, new String[]{"0","A","0","0"}, 	new int[]{209,0,0,212},
				new int[]{174,0,0,0}, new String[]{"0","C","0","0"},new double[]{0.0, 0.7, 0.0, 0.0}, new int[]{142, 155, 0,0}, new String[]{"0","D","C","0"},
				new double[]{0.0, 0.27, 0.73, 0.0}, new int[]{136,141,0,0}, new String[]{"0","E","A","0"},	new double[]{0.0, 0.19, 1.08, 0.0}, new int[]{127,157,0,0}, new String[]{"0","D","0","C"}, new double[]{0.0, 0.38, 0.0, 0.73}, new int[]{135, 142, 0, 0},
				new String[]{"0","D","0","B"}, new double[]{0.0, 0.36, 0.0, 0.85}, new int[]{182,0,182,0},		new int[]{151,0,174,0}, 2.0);
		
		Weapons Whip = new Weapons(new int[]{210,200,0,0,0}, new String[]{"0","A","0","0"}, new double[]{0.0, 1.02, 0.0, 0.0}, new int[]{216,0,0,0}, new String[]{"0","A","0","0"}, 	new int[]{220,0,0,224},
				new int[]{184,0,0,0}, new String[]{"0","C","0","0"}, new double[]{0.0, 0.7, 0.0, 0.0}, new int[]{150, 162, 0,0}, new String[]{"0","D","C","0"},
				new double[]{0.0, 0.27, 0.73, 0.0}, new int[]{144,148,0,0}, new String[]{"0","E","A","0"},new double[]{0.0, 0.19, 1.08, 0.0}, new int[]{135,165,0,0}, new String[]{"0","D","0","C"}, 	new double[]{0.0, 0.38, 0.0, 0.73}, new int[]{143, 150, 0, 0},
				new String[]{"0","D","0","B"},new double[]{0.0, 0.36, 0.0, 0.85}, new int[]{192,0,192,0},		new int[]{161,0,184,0}, 1.5);
		
		final String[] stringWeapons = {"Caestus", "Claw", "Dragon Bone Fist", "DarkHand", "Blacksmith Giant Hammer", "Blacksmith Hammer", "Hammer of Vamos", "Club", "Mace", "Morning Star",
				"Pickaxe", "Reinforced Club", "Warpick", "Demon's Great Hammer", "Dragon Tooth","Grant","Great Club","Large Club","Smough's Hammer","Battle Axe",
				"Butcher Knife","Crescent Axe","Gargoyle Tail Axe","Golem Axe","Hand Axe","Black Knight Greataxe","Demon's Greataxe","Dragon King Greataxe","Greataxe",
				"Stone Greataxe","Bandit's Knife","Dagger","Dark Silver Tracer","Ghost Blade","Parrying Dagger","Priscilla's Dagger","Estoc","Mail Breaker","Rapier",
				"Ricard's Rapier","Velka's Rapier","Astora's Straight Sword","Balder Side Sword", "Barbed Straight Sword","Broad Sword","Broken Straight Sword",
				"Crystal Straight Sword","Dark Sword","Drake Sword","Longsword","Shortsword","Silver Knight Straight Sword","" ,"Sunlight Straight Sword","Abyss Greatsword",
		        "Bastard Sword","Black Knight Sword","Claymore","Crystal Greatsword","Flamberge","Great Lord Greatsword","Greatsword of Artorias","Greatsword of Artorias (Cursed)",
		        "Man Serpent Greatsword","Moonlight Greatsword","Obsidian Greatsword","Stone Greatsword","Black Knight Greatsword","Demon Great Machete","Dragon Greatsword","Greatsword",
		        "Zweihander","Chaos Blade","Iaito","Uchigatana","Washing Pole","Falchion","Gold Tracer" ,"Jagged Ghost Blade","Painting Guardian Sword","Quelaag's Fury Sword","Scimitar" ,"Shotel",
		        "Gravelord Sword","Murakumo","Server","Channeler's Trident","Demon's Spear","Dragonslayer Spear","Four-Pronged Plow","Moonlight Butterfly Horn","Partizan","Pike",
		        "Silver Knight Spear","Spear","Winged Spear","Black Knight Halberd","Gargoyle's Halberd","Giant's Halberd","Great Scythe","Halberd","Lifehunt Scythe","Lucerne","Scythe",
		        "Titanite Catch Pole","Black Bow of Pharis","Composite Bow","Darkmoon Bow","Long Bow","Short Bow","Avelyn","Heavy Crossbow","Light Crossbow","Sniper Crossbow" ,
		        "Dragonslayer Greatbow","Gough's Greatbow","Guardian Tail","Notched Whip","Whip"};
		
		final Items[] base = {Caestus,Claw,DragonBoneFist,DarkHand,BlacksmithGiantHammer,BlacksmithHammer,HammerOfVamos,Club,Mace,MorningStar,Pickaxe,ReinforcedClub,
        Warpick,DemonsGreatHammer,DragonTooth,Grant,GreatClub,LargeClub,SmoughsHammer,BattleAxe,ButcherKnife,CrescentAxe,GargoyleTailAxe,GolemAxe,HandAxe,
        BlackKnightGreataxe,DemonsGreataxe,DragonKingGreataxe,Greataxe,StoneGreataxe,BanditsKnife,Dagger,DarkSilverTracer,GhostBlade,ParryingDagger,PriscillasDagger,
        Estoc,MailBreaker,Rapier,RicardsRapier,VelkasRapier,AstorasStraightSword,BalderSideSword,BarbedStraightSword,BroadSword,BrokenStraightSword,CrystalStraightSword,
        DarkSword,DrakeSword,Longsword,Shortsword,SilverKnightStraightSword,StraightSwordHilt,SunlightStraightSword,AbyssGreatsword,BastardSword,BlackKnightSword,
        Claymore,CrystalGreatsword,Flamberge,GreatLordGreatsword,GreatswordOfArtorias,CursedGreatswordOfArtorias,ManSerpentGreatsword,MoonlightGreatsword,
        ObsidianGreatsword,StoneGreatsword,BlackKnightGreatsword,DemonGreatMachete,DragonGreatsword,Greatsword,Zweihander,ChaosBlade,Iaito,Uchigatana,WashingPole,
        Falchion,GoldTracer,JaggedGhostBlade,PaintingGuardianSword,QuelaagsFurysword,Scimitar,Shotel,GravelordSword,Murakumo,Server,ChannelersTrident,DemonsSpear,
        DragonslayerSpear,FourProngedPlow,MoonlightButterflyHorn,Partizan,Pike,SilverKnightSpear,Spear,WingedSpear,BlackKnightHalberd,GargoyleHalberd,GiantsHalberd,
        GreatScythe,Halberd,LifehuntScythe,Lucerne,Scythe,TitaniteCatchPole,BlackBowOfPharis,CompositeBow,DarkmoonBow,LongBow,ShortBow,Avelyn,HeavyCrossbow,LightCrossbow,
        SniperCrossbow,DragonslayerGreatbow,GoughsGreatbow,GuardianTail,NotchedWhip,Whip};
		
		
		hp = (TextView)findViewById(R.id.Health);
		stam = (TextView)findViewById(R.id.Stamina);
		equipLoad = (TextView)findViewById(R.id.EquipLoad);
		poiseBois = (TextView)findViewById(R.id.Poise);
		heft = (TextView)findViewById(R.id.Weight);
		physicalDefense = (TextView)findViewById(R.id.physDefValue);
		strikeDefense = (TextView)findViewById(R.id.strikeDefValue);
		slashDefense = (TextView)findViewById(R.id.slashDefValue);
		thrustDefense = (TextView)findViewById(R.id.thrustDefValue);
		magicDefense = (TextView)findViewById(R.id.magicDefValue);
		fireDefense = (TextView)findViewById(R.id.fireDefValue);
		lightningDefense = (TextView)findViewById(R.id.lightningDefValue);
		bleedResistance = (TextView)findViewById(R.id.bleedResValue);
		poisonResistance = (TextView)findViewById(R.id.poisonResValue);
		curseResistance = (TextView)findViewById(R.id.curseResValue);
		attSlots = (TextView)findViewById(R.id.AttunementSlots);
		SL = (TextView)findViewById(R.id.SL);
		SoulLevel = (TextView)findViewById(R.id.SoulLevel);
		
		spell1= (Spinner)findViewById(R.id.spell1);
		spell2= (Spinner)findViewById(R.id.spell2);
		spell3= (Spinner)findViewById(R.id.spell3);
		spell4= (Spinner)findViewById(R.id.spell4);
		spell5= (Spinner)findViewById(R.id.spell5);
		spell6= (Spinner)findViewById(R.id.spell6);
		spell7= (Spinner)findViewById(R.id.spell7);
		spell8= (Spinner)findViewById(R.id.spell8);
		spell9= (Spinner)findViewById(R.id.spell9);
		spell10= (Spinner)findViewById(R.id.spell10);
		
		item1 =(Spinner)findViewById(R.id.item1);
		item2 = (Spinner)findViewById(R.id.item2);
		item3 = (Spinner)findViewById(R.id.item3);
		item4 = (Spinner)findViewById(R.id.item4);
		item5 = (Spinner)findViewById(R.id.item5);
		
		
		
		ar1 = (TextView)findViewById(R.id.AR1);
		ar2 = (TextView)findViewById(R.id.AR2);
		ar3 = (TextView)findViewById(R.id.AR3);
		ar4 = (TextView)findViewById(R.id.AR4);
		rh1path = (Spinner)findViewById(R.id.rh1Path);
		rh2path = (Spinner)findViewById(R.id.rh2Path);
		lh1path = (Spinner)findViewById(R.id.lh1Path);
		lh2path = (Spinner)findViewById(R.id.lh2Path);
		
		nothing = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
		nothing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		path = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
		path.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		for(int i=0;i<paths.length;i++){
			path.add(paths[i]);
		}
		path.notifyDataSetChanged();

//        SQLiteDatabase db = null;
//        try{
//            db = this.openOrCreateDatabase("Builds", MODE_PRIVATE, null);
//
//            //creating tables
//            String vit = "CREATE TABLE" +
//
//            db.execSQL("CREATE TABLE IF NOT EXISTS " + Vitality.getText().toString() + "(Field1 VARCHAR, Field2");
//        }
//		catch(Exception e){
//
//        }

			
		getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        //EditTexts
        Vitality = (EditText)findViewById(R.id.Vitality);
        Vitality.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try {
					if (Integer.parseInt(Vitality.getText().toString()) >= 8) {
						currentHP = HealthPoints[Integer.parseInt(Vitality.getText().toString())];
						if (mother) {
							currentHP = (currentHP + (currentHP * .1));
						}
						if (fap) {
							currentHP = (currentHP + (currentHP * .2));
						}
						if (tiny) {
							currentHP = (currentHP + (currentHP * .05));
						}
						hp.setText((int) currentHP + "");
						SoulLevel.setText(addStats() + "");
					}
				} catch (NumberFormatException e) {

				}

			}
		});

        Attunement = (EditText)findViewById(R.id.Attunement);
        Attunement.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){
                try{
					if(Integer.parseInt(Attunement.getText().toString()) >= 8) {
						int num = Integer.parseInt(Attunement.getText().toString());
						if (num < 10) {
							attSlots.setText(0 + "");
							spell1.setVisibility(View.INVISIBLE);
							spell2.setVisibility(View.INVISIBLE);
							spell3.setVisibility(View.INVISIBLE);
							spell4.setVisibility(View.INVISIBLE);
							spell5.setVisibility(View.INVISIBLE);
							spell6.setVisibility(View.INVISIBLE);
							spell7.setVisibility(View.INVISIBLE);
							spell8.setVisibility(View.INVISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num == 10 || num == 11) {
							attSlots.setText(1 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.INVISIBLE);
							spell3.setVisibility(View.INVISIBLE);
							spell4.setVisibility(View.INVISIBLE);
							spell5.setVisibility(View.INVISIBLE);
							spell6.setVisibility(View.INVISIBLE);
							spell7.setVisibility(View.INVISIBLE);
							spell8.setVisibility(View.INVISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num == 12 || num == 13) {
							attSlots.setText(2 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.INVISIBLE);
							spell4.setVisibility(View.INVISIBLE);
							spell5.setVisibility(View.INVISIBLE);
							spell6.setVisibility(View.INVISIBLE);
							spell7.setVisibility(View.INVISIBLE);
							spell8.setVisibility(View.INVISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num == 14 || num == 15) {
							attSlots.setText(3 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.VISIBLE);
							spell4.setVisibility(View.INVISIBLE);
							spell5.setVisibility(View.INVISIBLE);
							spell6.setVisibility(View.INVISIBLE);
							spell7.setVisibility(View.INVISIBLE);
							spell8.setVisibility(View.INVISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num <= 18) {
							attSlots.setText(4 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.VISIBLE);
							spell4.setVisibility(View.VISIBLE);
							spell5.setVisibility(View.INVISIBLE);
							spell6.setVisibility(View.INVISIBLE);
							spell7.setVisibility(View.INVISIBLE);
							spell8.setVisibility(View.INVISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num <= 22) {
							attSlots.setText(5 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.VISIBLE);
							spell4.setVisibility(View.VISIBLE);
							spell5.setVisibility(View.VISIBLE);
							spell6.setVisibility(View.INVISIBLE);
							spell7.setVisibility(View.INVISIBLE);
							spell8.setVisibility(View.INVISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num <= 27) {
							attSlots.setText(6 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.VISIBLE);
							spell4.setVisibility(View.VISIBLE);
							spell5.setVisibility(View.VISIBLE);
							spell6.setVisibility(View.VISIBLE);
							spell7.setVisibility(View.INVISIBLE);
							spell8.setVisibility(View.INVISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num <= 33) {
							attSlots.setText(7 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.VISIBLE);
							spell4.setVisibility(View.VISIBLE);
							spell5.setVisibility(View.VISIBLE);
							spell6.setVisibility(View.VISIBLE);
							spell7.setVisibility(View.VISIBLE);
							spell8.setVisibility(View.INVISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num <= 40) {
							attSlots.setText(8 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.VISIBLE);
							spell4.setVisibility(View.VISIBLE);
							spell5.setVisibility(View.VISIBLE);
							spell6.setVisibility(View.VISIBLE);
							spell7.setVisibility(View.VISIBLE);
							spell8.setVisibility(View.VISIBLE);
							spell9.setVisibility(View.INVISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num <= 49) {
							attSlots.setText(9 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.VISIBLE);
							spell4.setVisibility(View.VISIBLE);
							spell5.setVisibility(View.VISIBLE);
							spell6.setVisibility(View.VISIBLE);
							spell7.setVisibility(View.VISIBLE);
							spell8.setVisibility(View.VISIBLE);
							spell9.setVisibility(View.VISIBLE);
							spell10.setVisibility(View.INVISIBLE);
						} else if (num <= 99) {
							attSlots.setText(10 + "");
							spell1.setVisibility(View.VISIBLE);
							spell2.setVisibility(View.VISIBLE);
							spell3.setVisibility(View.VISIBLE);
							spell4.setVisibility(View.VISIBLE);
							spell5.setVisibility(View.VISIBLE);
							spell6.setVisibility(View.VISIBLE);
							spell7.setVisibility(View.VISIBLE);
							spell8.setVisibility(View.VISIBLE);
							spell9.setVisibility(View.VISIBLE);
							spell10.setVisibility(View.VISIBLE);
						}
						SoulLevel.setText(addStats() + "");
					}
                }
                catch(NumberFormatException e){

                }

            }
        });

        Endurance = (EditText)findViewById(R.id.Endurance);
        Endurance.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try {
					if(Integer.parseInt(Endurance.getText().toString()) >= 8) {
						int num = Integer.parseInt(Endurance.getText().toString());
						if (Integer.parseInt(Endurance.getText().toString()) >= Stamina.length) {
							currentStam = Stamina[Stamina.length - 1];
						} else {
							currentStam = Stamina[Integer.parseInt(Endurance.getText().toString())];
						}
						currentEQ = Integer.parseInt(Endurance.getText().toString()) + 40.0;
						if (father) {
							currentEQ = (currentEQ + (currentEQ * .05));
						}
						if (havels) {
							currentEQ = (currentEQ + (currentEQ * .5));
						}
						if (fap) {
							currentEQ = (currentEQ + (currentEQ * .2));
							currentStam = (currentStam + (currentStam * .2));
						}
						stam.setText((int) currentStam + "");
						equipLoad.setText(Double.toString(currentEQ) + "");
						SoulLevel.setText(addStats() + "");
						
					}
				} catch (NumberFormatException e) {

				}

			}
		});

        Strength = (EditText)findViewById(R.id.Strength);
        Strength.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
					if(Integer.parseInt(Strength.getText().toString()) >= 8) {
						dynamicArChanger(stringWeapons, base);
						SoulLevel.setText(addStats() + "");
					}
                } catch (NumberFormatException e) {

                }
            }

        });

        Dexterity = (EditText)findViewById(R.id.Dexterity);
        Dexterity.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
					if(Integer.parseInt(Dexterity.getText().toString()) >= 8) {
						dynamicArChanger(stringWeapons, base);
						SoulLevel.setText(addStats() + "");
					}
                }
                catch (NumberFormatException e) {

                }
            }
        });

        Resistance = (EditText)findViewById(R.id.Resistance);
        Resistance.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
					if (Integer.parseInt(Resistance.getText().toString()) >= 8) {
						SoulLevel.setText(addStats() + "");
					}
				}
                catch (NumberFormatException e) {

                }
            }
        });

        Intelligence = (EditText)findViewById(R.id.Intelligence);
        Intelligence.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
					if(Integer.parseInt(Intelligence.getText().toString()) >= 8) {
						dynamicArChanger(stringWeapons, base);
						SoulLevel.setText(addStats() + "");
					}
                } catch (NumberFormatException e) {

                }
            }
        });

        Faith = (EditText)findViewById(R.id.Faith);
        Faith.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
					if(Integer.parseInt(Faith.getText().toString()) >= 8) {
						dynamicArChanger(stringWeapons, base);
						SoulLevel.setText(addStats() + "");
					}
                } catch (NumberFormatException e) {

                }
            }
        });

        Humanity = (EditText)findViewById(R.id.Humanity);
        Humanity.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try {
					//for(int w = 0; w <= 99; w++){
					int humCount = Integer.parseInt(Humanity.getText().toString());
					if (humCount == 1) {
						for (int q = 0; q <= 2; q++) {
							humDef[q] = 1.0;
						}
						humDef[3] = 2.0;
						humDef[4] = 10.0;
					} else if (humCount == 5) {
						humDef[0] = 6.0;
						humDef[1] = 6.0;
						humDef[2] = 6.0;
						humDef[3] = 8.0;
						humDef[4] = 50.0;
					} else if (humCount == 10) {
						humDef[0] = 13.0;
						humDef[1] = 13.0;
						humDef[2] = 12.0;
						humDef[3] = 15.0;
						humDef[4] = 90.0;
					} else if (humCount == 15) {
						humDef[0] = 20.0;
						humDef[1] = 19.0;
						humDef[2] = 19.0;
						humDef[3] = 22.0;
						humDef[4] = 98.0;
					} else if (humCount == 20) {
						humDef[0] = 26.0;
						humDef[1] = 26.0;
						humDef[2] = 25.0;
						humDef[3] = 30.0;
						humDef[4] = 105.0;
					} else if (humCount == 25) {
						humDef[0] = 33.0;
						humDef[1] = 33.0;
						humDef[2] = 31.0;
						humDef[3] = 37.0;
						humDef[4] = 113.0;
					} else if (humCount == 30) {
						humDef[0] = 39.0;
						humDef[1] = 37.0;
						humDef[2] = 37.0;
						humDef[3] = 44.0;
						humDef[4] = 120.0;
					} else if (humCount == 40) {
						humDef[0] = 52.0;
						humDef[1] = 50.0;
						humDef[2] = 49.0;
						humDef[3] = 58.0;
						humDef[4] = 120.0;
					} else if (humCount == 50) {
						humDef[0] = 65.0;
						humDef[1] = 61.0;
						humDef[2] = 61.0;
						humDef[3] = 72.0;
						humDef[4] = 120.0;
					} else if (humCount == 60) {
						humDef[0] = 77.0;
						humDef[1] = 73.0;
						humDef[2] = 73.0;
						humDef[3] = 85.0;
						humDef[4] = 120.0;
					} else if (humCount == 70) {
						humDef[0] = 88.0;
						humDef[1] = 84.0;
						humDef[2] = 84.0;
						humDef[3] = 97.0;
						humDef[4] = 120.0;
					} else if (humCount == 80) {
						humDef[0] = 99.0;
						humDef[1] = 94.0;
						humDef[2] = 94.0;
						humDef[3] = 105.0;
						humDef[4] = 120.0;
					} else if (humCount == 90) {
						humDef[0] = 104.0;
						humDef[1] = 101.0;
						humDef[2] = 102.0;
						humDef[3] = 110.0;
						humDef[4] = 120.0;
					} else if (humCount == 99) {
						humDef[0] = 109.0;
						humDef[1] = 106.0;
						humDef[2] = 106.0;
						humDef[3] = 116.0;
						humDef[4] = 120.0;
					} else {
						humDef[0] = (humCount - (humCount / 3)) + ((humCount / 3) * 2);
						if (humCount % 3 == 2) {
							humDef[0] += 2;
						}
					}

                    if(lh1.getSelectedItem() != null){
                        ar1.setText(calcSpecialHumanity(lh1, stringWeapons, base) + "");
                    }
                    if(rh1.getSelectedItem() != null){
                        ar2.setText(calcSpecialHumanity(rh1, stringWeapons, base) + "");
                    }
                    if(lh2.getSelectedItem() != null){
                        ar3.setText(calcSpecialHumanity(lh2, stringWeapons, base) + "");
                    }
                    if(rh2.getSelectedItem() != null){
                        ar4.setText(calcSpecialHumanity(rh2, stringWeapons, base) + "");
                    }

					physicalDefense.setText(df.format(physDef + humDef[0]) + "");
					strikeDefense.setText(df.format(strikeDef + humDef[0]) + "");
					slashDefense.setText(df.format(slashDef + humDef[0]) + "");
					thrustDefense.setText(df.format(thrustDef + humDef[0]) + "");
					magicDefense.setText(df.format(magDef + humDef[1]) + "");
					fireDefense.setText(df.format(fireDef + humDef[2]) + "");
					lightningDefense.setText(df.format(lightningDef + humDef[3]) + "");
                    SoulLevel.setText(addStats() + "");
				} catch (NumberFormatException e) {

				}
			}
		});


        //SoulLevel = (EditText)findViewById(R.id.SoulLevel);

		
		dsClass = (Spinner)findViewById(R.id.classes);
		dsClass.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
									   int position, long id) {
				// TODO Auto-generated method stub
				if (dsClass.getSelectedItem().toString().equals("Warrior")) {
					Vitality.setText(11 + "");
					Attunement.setText(8 + "");
					Endurance.setText(12 + "");
					Strength.setText(13 + "");
					Dexterity.setText(13 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(9 + "");
					Faith.setText(9 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(21.0 + "");
					strikeDefense.setText(21.0 + "");
					slashDefense.setText(21.0 + "");
					thrustDefense.setText(21.0 + "");
					magicDefense.setText(17.0 + "");
					fireDefense.setText(21.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(4+"");
				} else if (dsClass.getSelectedItem().toString().equals("Knight")) {
					Vitality.setText(14 + "");
					Attunement.setText(10 + "");
					Endurance.setText(10 + "");
					Strength.setText(11 + "");
					Dexterity.setText(11 + "");
					Resistance.setText(10 + "");
					Intelligence.setText(9 + "");
					Faith.setText(11 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(20.0 + "");
					strikeDefense.setText(20.0 + "");
					slashDefense.setText(20.0 + "");
					thrustDefense.setText(20.0 + "");
					magicDefense.setText(21.0 + "");
					fireDefense.setText(20.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(5+"");
				} else if (dsClass.getSelectedItem().toString().equals("Wanderer")) {
					Vitality.setText(10 + "");
					Attunement.setText(11 + "");
					Endurance.setText(10 + "");
					Strength.setText(10 + "");
					Dexterity.setText(14 + "");
					Resistance.setText(12 + "");
					Intelligence.setText(11 + "");
					Faith.setText(9 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(22.0 + "");
					strikeDefense.setText(22.0 + "");
					slashDefense.setText(22.0 + "");
					thrustDefense.setText(22.0 + "");
					magicDefense.setText(16.0 + "");
					fireDefense.setText(24.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(3+"");
				} else if (dsClass.getSelectedItem().toString().equals("Thief")) {
					Vitality.setText(9 + "");
					Attunement.setText(11 + "");
					Endurance.setText(9 + "");
					Strength.setText(9 + "");
					Dexterity.setText(15 + "");
					Resistance.setText(10 + "");
					Intelligence.setText(12 + "");
					Faith.setText(11 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(20.0 + "");
					strikeDefense.setText(20.0 + "");
					slashDefense.setText(20.0 + "");
					thrustDefense.setText(20.0 + "");
					magicDefense.setText(21.0 + "");
					fireDefense.setText(20.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(5+"");
				} else if (dsClass.getSelectedItem().toString().equals("Bandit")) {
					Vitality.setText(12 + "");
					Attunement.setText(8 + "");
					Endurance.setText(14 + "");
					Strength.setText(14 + "");
					Dexterity.setText(9 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(8 + "");
					Faith.setText(10 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(21.0 + "");
					strikeDefense.setText(21.0 + "");
					slashDefense.setText(21.0 + "");
					thrustDefense.setText(21.0 + "");
					magicDefense.setText(20.0 + "");
					fireDefense.setText(21.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(4+"");
				} else if (dsClass.getSelectedItem().toString().equals("Hunter")) {
					Vitality.setText(11 + "");
					Attunement.setText(9 + "");
					Endurance.setText(11 + "");
					Strength.setText(12 + "");
					Dexterity.setText(14 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(9 + "");
					Faith.setText(9 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(21.0 + "");
					strikeDefense.setText(21.0 + "");
					slashDefense.setText(21.0 + "");
					thrustDefense.setText(21.0 + "");
					magicDefense.setText(17.0 + "");
					fireDefense.setText(21.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(4+"");
				} else if (dsClass.getSelectedItem().toString().equals("Sorcerer")) {
					Vitality.setText(8 + "");
					Attunement.setText(15 + "");
					Endurance.setText(8 + "");
					Strength.setText(9 + "");
					Dexterity.setText(11 + "");
					Resistance.setText(8 + "");
					Intelligence.setText(15 + "");
					Faith.setText(8 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(12.0 + "");
					strikeDefense.setText(12.0 + "");
					slashDefense.setText(12.0 + "");
					thrustDefense.setText(12.0 + "");
					magicDefense.setText(11.0 + "");
					fireDefense.setText(11.0 + "");
					lightningDefense.setText(13.0 + "");
					SoulLevel.setText(3+"");
				} else if (dsClass.getSelectedItem().toString().equals("Pyromancer")) {
					Vitality.setText(10 + "");
					Attunement.setText(12 + "");
					Endurance.setText(11 + "");
					Strength.setText(12 + "");
					Dexterity.setText(9 + "");
					Resistance.setText(12 + "");
					Intelligence.setText(10 + "");
					Faith.setText(8 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(20.0 + "");
					strikeDefense.setText(20.0 + "");
					slashDefense.setText(20.0 + "");
					thrustDefense.setText(20.0 + "");
					magicDefense.setText(13.0 + "");
					fireDefense.setText(21.0 + "");
					lightningDefense.setText(16.0 + "");
					SoulLevel.setText(1+"");
				} else if (dsClass.getSelectedItem().toString().equals("Cleric")) {
					Vitality.setText(11 + "");
					Attunement.setText(11 + "");
					Endurance.setText(9 + "");
					Strength.setText(12 + "");
					Dexterity.setText(8 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(8 + "");
					Faith.setText(14 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(18.0 + "");
					strikeDefense.setText(18.0 + "");
					slashDefense.setText(18.0 + "");
					thrustDefense.setText(18.0 + "");
					magicDefense.setText(25.0 + "");
					fireDefense.setText(18.0 + "");
					lightningDefense.setText(16.0 + "");
					SoulLevel.setText(2+"");
				} else if (dsClass.getSelectedItem().toString().equals("Deprived")) {
					Vitality.setText(11 + "");
					Attunement.setText(11 + "");
					Endurance.setText(11 + "");
					Strength.setText(11 + "");
					Dexterity.setText(11 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(11 + "");
					Faith.setText(11 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(24.0 + "");
					strikeDefense.setText(24.0 + "");
					slashDefense.setText(24.0 + "");
					thrustDefense.setText(24.0 + "");
					magicDefense.setText(24.0 + "");
					fireDefense.setText(24.0 + "");
					lightningDefense.setText(22.0 + "");
					SoulLevel.setText(6+"");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
			
		
		
		
		
		//HEAD
		head = (Spinner) findViewById(R.id.Helm);
		head.setOnItemSelectedListener(new OnItemSelectedListener() {

			//Sets Helm stats
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
									   int position, long id) {
				for (int i = 0; i < Helmets.StringHelm.length; i++) {
					if (head.getSelectedItem().toString().equals(Helmets.StringHelm[i])) {
						//if(head.getSelectedItemPosition() == 0){
							
						//}
						//else{
							currentHelm = Helmets.DoubleHelm[i];
							armorAdder(currentHelm, currentChest, currentHands, currentLegs,currentLH1, currentLH2, currentRH1, currentRH2);
						//}
					}
				}

				//Special Bonuses
				if (head.getSelectedItem().toString().equals("Mask of the Mother")) {
					mother = true;
				}
				else{
					mother = false;
				}
				if (head.getSelectedItem().toString().equals("Mask of the Father")) {
					father = true;
				}
				else{
					father = false;
				}
				
				try {
					if (Integer.parseInt(Vitality.getText().toString()) >= 8) {
						currentHP = HealthPoints[Integer.parseInt(Vitality.getText().toString())];
						if (mother) {
							currentHP = (currentHP + (currentHP * .1));
						}
					}
					if(Integer.parseInt(Endurance.getText().toString()) >= 8) {
						int num = Integer.parseInt(Endurance.getText().toString());
						if (Integer.parseInt(Endurance.getText().toString()) >= Stamina.length) {
							currentStam = Stamina[Stamina.length - 1];
						} else {
							currentStam = Stamina[Integer.parseInt(Endurance.getText().toString())];
						}
						currentEQ = Integer.parseInt(Endurance.getText().toString()) + 40.0;
						if (father) {
							currentEQ = (currentEQ + (currentEQ * .05));
						}
					}
					hp.setText((int) currentHP + "");
					equipLoad.setText(Double.toString(currentEQ) + "");
				}
				catch(Exception e){
					
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		
		
		//CHEST
		chest = (Spinner)findViewById(R.id.Chestplate);
		chest.setOnItemSelectedListener(new OnItemSelectedListener() {

			//Sets Chest stats
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
									   int position, long id) {
				for (int i = 0; i < Chest.StringChest.length; i++) {
					if (chest.getSelectedItem().toString().equals(Chest.StringChest[i])) {
//						if(chest.getSelectedItemPosition() == 0){
//							
//						}
//						else{
							currentChest = Chest.DoubleChest[i];
							armorAdder(currentHelm, currentChest, currentHands, currentLegs,currentLH1, currentLH2, currentRH1, currentRH2);
						//}
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
		
		
		//HANDS
		hands = (Spinner)findViewById(R.id.Gauntlets);
		hands.setOnItemSelectedListener(new OnItemSelectedListener(){

			//Sets Hand stats
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				for(int i = 0; i < Hands.StringHands.length; i++){
					if(hands.getSelectedItem().toString().equals(Hands.StringHands[i])){
						//if(hands.getSelectedItemPosition() == 0){
						
						//}
						//else{
							currentHands = Hands.DoubleHands[i];
							armorAdder(currentHelm, currentChest, currentHands, currentLegs,currentLH1, currentLH2, currentRH1, currentRH2);
						//}
					}
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		//LEGS
		legs = (Spinner)findViewById(R.id.Leggings);
		legs.setOnItemSelectedListener(new OnItemSelectedListener(){

			//Sets Leg stats
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				for(int i = 0; i < Legs.StringLegs.length; i++){
					if(legs.getSelectedItem().toString().equals(Legs.StringLegs[i])){
						//if(legs.getSelectedItemPosition() == 0){
							
						//}
						//else{
							currentLegs = Legs.DoubleLegs[i];
							armorAdder(currentHelm, currentChest, currentHands, currentLegs,currentLH1, currentLH2, currentRH1, currentRH2);
						//}
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ring1 = (Spinner)findViewById(R.id.RingOne);
		ring1.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if(ring1.getSelectedItem().toString().equals("Tiny Being\'s Ring")){
					tiny = true;
				}
				else if(!ring2.getSelectedItem().toString().equals("Tiny Being\'s Ring")){
					tiny = false;
				}
				if(ring1.getSelectedItem().toString().equals("Ring of Favor and Protection")){
					fap = true;
				}else if(!ring2.getSelectedItem().toString().equals("Ring of Favor and Protection")){
					fap = false;
				}
				if(ring1.getSelectedItem().toString().equals("Havel\'s Ring")){
					havels = true;
				}
				else if(!ring1.getSelectedItem().toString().equals("Havel\'s Ring")){
					havels = false;
				}
				
				try {
					if(Integer.parseInt(Endurance.getText().toString()) >= 8) {
						int num = Integer.parseInt(Endurance.getText().toString());
						if (Integer.parseInt(Endurance.getText().toString()) >= Stamina.length) {
							currentStam = Stamina[Stamina.length - 1];
						} else {
							currentStam = Stamina[Integer.parseInt(Endurance.getText().toString())];
						}
						currentEQ = Integer.parseInt(Endurance.getText().toString()) + 40.0;
						currentHP = HealthPoints[Integer.parseInt(Vitality.getText().toString())];
						if (father) {
							currentEQ = (currentEQ + (currentEQ * .05));
						}
						if (havels) {
							currentEQ = (currentEQ + (currentEQ * .5));
						}
						if (fap) {
							currentEQ = (currentEQ + (currentEQ * .2));
							currentStam = (currentStam + (currentStam * .2));
							currentHP = (currentHP + (currentHP * .2));
						}
						if (tiny) {
							currentHP = (currentHP + (currentHP * .05));
						}
						hp.setText((int) currentHP + "");
						stam.setText((int) currentStam + "");
						equipLoad.setText(Double.toString(currentEQ) + "");
					}
				}
				catch(Exception e){
					
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}			
		});

		ring2 = (Spinner)findViewById(R.id.RingTwo);
		ring2.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(ring2.getSelectedItem().toString().equals("Tiny Being\'s Ring")){
					tiny = true;
				}
				else if(!ring1.getSelectedItem().toString().equals("Tiny Being\'s Ring")){
					tiny = false;
				}
				if(ring2.getSelectedItem().toString().equals("Ring of Favor and Protection")){
					fap = true;
				}
				else if(!ring1.getSelectedItem().toString().equals("Ring of Favor and Protection")){
					fap = false;
				}
				if(ring2.getSelectedItem().toString().equals("Havel\'s Ring")){
					havels = true;
				}
				else if(!ring1.getSelectedItem().toString().equals("Havel\'s Ring")){
					havels = false;
				}
				
				try {
					if(Integer.parseInt(Endurance.getText().toString()) >= 8) {
						int num = Integer.parseInt(Endurance.getText().toString());
						if (Integer.parseInt(Endurance.getText().toString()) >= Stamina.length) {
							currentStam = Stamina[Stamina.length - 1];
						} else {
							currentStam = Stamina[Integer.parseInt(Endurance.getText().toString())];
						}
						currentEQ = Integer.parseInt(Endurance.getText().toString()) + 40.0;
						currentHP = HealthPoints[Integer.parseInt(Vitality.getText().toString())];
						if (father) {
							currentEQ = (currentEQ + (currentEQ * .05));
						}
						if (havels) {
							currentEQ = (currentEQ + (currentEQ * .5));
						}
						if (fap) {
							currentEQ = (currentEQ + (currentEQ * .2));
							currentStam = (currentStam + (currentStam * .2));
							currentHP = (currentHP + (currentHP * .2));
						}
						if (tiny) {
							currentHP = (currentHP + (currentHP * .05));
						}
						hp.setText((int) currentHP + "");
						stam.setText((int) currentStam + "");
						equipLoad.setText(Double.toString(currentEQ) + "");
					}
				}
				catch(Exception e){
					
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});

		
		final String[] specialPath = {"Upgrade +5"};		
		
		
		lh1 = (Spinner)findViewById(R.id.LH1);
		lh1.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
//				currentShield = 0.0;
//				currentTalisman = 0.0;
				currentLH1 = 0.0;
					for(int i = 0; i < Shields.StringShields.length; i++){
						if(lh1.getSelectedItem().toString().equals(Shields.StringShields[i])){
							if(lh1.getSelectedItemPosition() == 0){
								
							}
							else{
								currentLH1 = Shields.DoubleShields[i];
								armorAdder(currentHelm, currentChest, currentHands, currentLegs,currentLH1, currentLH2, currentRH1, currentRH2);
							}
						}
					}
					
					for(int i = 0; i < Talismans.StringTalismans.length; i++){
						if(lh1.getSelectedItem().toString().equals(Talismans.StringTalismans[i])){
							if(lh1.getSelectedItemPosition() == 0){
								
							}
							else{
								currentLH1 = Talismans.DoubleTalismans[i];
								armorAdder(currentHelm, currentChest, currentHands, currentLegs, currentLH1, currentLH2, currentRH1, currentRH2);
							}
						}
					}
					
					for(int i = 0; i < base.length; i++){
						if(lh1.getSelectedItem().toString().equals(stringWeapons[i])){
							if(lh1.getSelectedItemPosition() == 0){
								
							}
							else{
								currentLH1 = base[i].getWeight();
								armorAdder(currentHelm, currentChest, currentHands, currentLegs, currentLH1, currentLH2, currentRH1, currentRH2);
							}
						}
					}
					// TODO Auto-generated method stub
					spinnerDisabler(stringWeapons, base);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		rh1 = (Spinner)findViewById(R.id.RH1);
		rh1.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentRH1 = 0.0;
					for(int i = 0; i < Shields.StringShields.length; i++){
						if(rh1.getSelectedItem().toString().equals(Shields.StringShields[i])){
							if(rh1.getSelectedItemPosition() == 0){
								
							}
							else{
								currentRH1 = Shields.DoubleShields[i];
								armorAdder(currentHelm, currentChest, currentHands, currentLegs,currentLH1, currentLH2, currentRH1, currentRH2);
							}
						}
					}
					
					for(int i = 0; i < Talismans.StringTalismans.length; i++){
						if(rh1.getSelectedItem().toString().equals(Talismans.StringTalismans[i])){
							if(rh1.getSelectedItemPosition() == 0){
								
							}
							else{
								currentRH1 = Talismans.DoubleTalismans[i];
								armorAdder(currentHelm, currentChest, currentHands, currentLegs, currentLH1, currentLH2, currentRH1, currentRH2);
							}
						}
					}
					
					for(int i = 0; i < base.length; i++){
						if(rh1.getSelectedItem().toString().equals(stringWeapons[i])){
							if(rh1.getSelectedItemPosition() == 0){
								
							}
							else{
								currentRH1 = base[i].getWeight();
								armorAdder(currentHelm, currentChest, currentHands, currentLegs, currentLH1, currentLH2, currentRH1, currentRH2);
							}
						}
					}
				// TODO Auto-generated method stub
				spinnerDisabler(stringWeapons, base);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		lh2 = (Spinner)findViewById(R.id.LH2);
		lh2.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentLH2 = 0.0;
				for(int i = 0; i < Shields.StringShields.length; i++){
					if(lh2.getSelectedItem().toString().equals(Shields.StringShields[i])){
						if(lh2.getSelectedItemPosition() == 0){
							
						}
						else{
							currentLH2 = Shields.DoubleShields[i];
							armorAdder(currentHelm, currentChest, currentHands, currentLegs,currentLH1, currentLH2, currentRH1, currentRH2);
						}
					}
				}
				
				for(int i = 0; i < Talismans.StringTalismans.length; i++){
					if(lh2.getSelectedItem().toString().equals(Talismans.StringTalismans[i])){
						if(lh2.getSelectedItemPosition() == 0){
							
						}
						else{
							currentLH2 = Talismans.DoubleTalismans[i];
							armorAdder(currentHelm, currentChest, currentHands, currentLegs, currentLH1, currentLH2, currentRH1, currentRH2);
						}
					}
				}
				
				for(int i = 0; i < base.length; i++){
					if(lh2.getSelectedItem().toString().equals(stringWeapons[i])){
						if(lh2.getSelectedItemPosition() == 0){
							
						}
						else{
							currentLH1 = base[i].getWeight();
							armorAdder(currentHelm, currentChest, currentHands, currentLegs, currentLH1, currentLH2, currentRH1, currentRH2);
						}
					}
				}
				// TODO Auto-generated method stub
				spinnerDisabler(stringWeapons, base);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		rh2 = (Spinner)findViewById(R.id.RH2);
		rh2.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				currentRH2 = 0.0;
				for(int i = 0; i < Shields.StringShields.length; i++){
					if(rh2.getSelectedItem().toString().equals(Shields.StringShields[i])){
						if(rh2.getSelectedItemPosition() == 0){
							
						}
						else{
							currentRH2 = Shields.DoubleShields[i];
							armorAdder(currentHelm, currentChest, currentHands, currentLegs,currentLH1, currentLH2, currentRH1, currentRH2);
						}
					}
				}
				
				for(int i = 0; i < Talismans.StringTalismans.length; i++){
					if(rh2.getSelectedItem().toString().equals(Talismans.StringTalismans[i])){
						if(rh2.getSelectedItemPosition() == 0){
							
						}
						else{
							currentRH2 = Talismans.DoubleTalismans[i];
							armorAdder(currentHelm, currentChest, currentHands, currentLegs, currentLH1, currentLH2, currentRH1, currentRH2);
						}
					}
				}
				
				for(int i = 0; i < base.length; i++){
					if(rh2.getSelectedItem().toString().equals(stringWeapons[i])){
						if(rh2.getSelectedItemPosition() == 0){
							
						}
						else{
							currentRH2 = base[i].getWeight();
							armorAdder(currentHelm, currentChest, currentHands, currentLegs, currentLH1, currentLH2, currentRH1, currentRH2);
						}
					}
				}
				// TODO Auto-generated method stub
				spinnerDisabler(stringWeapons, base);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		lh1path.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ar1.setText(arCalc(lh1, lh1path, stringWeapons, base) + "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				ar1.setText(arCalc(lh1, lh1path, stringWeapons, base) + "");
			}
		});
		
		rh1path.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id){
				ar2.setText(arCalc(rh1, rh1path, stringWeapons, base) + "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				ar2.setText(arCalc(rh1, rh1path, stringWeapons, base) + "");
			}
		});
		
		lh2path.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ar3.setText(arCalc(lh2, lh2path, stringWeapons, base) + "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				ar3.setText(arCalc(lh2, lh2path, stringWeapons, base) + "");
			}
		});
		
		rh2path.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ar4.setText(arCalc(rh2, rh2path, stringWeapons, base) + "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				ar4.setText(arCalc(rh2, rh2path, stringWeapons, base) + "");
			}
		});

//		build = new EditText(this);
//		build.setHint("Build Name");
//		build.setGravity(Gravity.CENTER);


		//Buttons
		save = (Button)findViewById(R.id.Save);
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater layout = LayoutInflater.from(MainActivity.this);
                View promptView = layout.inflate(R.layout.prompt, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(promptView);
                final EditText usrInput = (EditText)promptView.findViewById(R.id.alertPrompt);
				//allFiles = db.getBuilds();


//				try{
//					int[] importantStuff = {
//							//STATS
//							Integer.parseInt(Vitality.getText().toString()), Integer.parseInt(Attunement.getText().toString()), Integer.parseInt(Endurance.getText().toString())
//							, Integer.parseInt(Strength.getText().toString()), Integer.parseInt(Dexterity.getText().toString()), Integer.parseInt(Resistance.getText().toString()),
//							Integer.parseInt(Intelligence.getText().toString()), Integer.parseInt(Faith.getText().toString()), Integer.parseInt(Humanity.getText().toString())};
//				}
//				catch(NumberFormatException e){
//
//				}
				builder.setTitle("Save");
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//Log.d("name check", build.getText().toString());
						db.setName(usrInput.getText().toString());
						//Log.d("Names", db.getNames().get(0)+"");
						//STATS
						db.setVitality(Vitality.getText().toString());
						db.setAttunement(Attunement.getText().toString());
						db.setEndurance(Endurance.getText().toString());
						db.setStrength(Strength.getText().toString());
						db.setDexterity(Dexterity.getText().toString());
						db.setResistance(Resistance.getText().toString());
						db.setIntelligence(Intelligence.getText().toString());
						db.setFaith(Faith.getText().toString());
						db.setHumanity(Humanity.getText().toString());

						//ARMOR
						db.setHelm(head.getSelectedItemPosition());
						db.setChest(chest.getSelectedItemPosition());
						db.setHands(hands.getSelectedItemPosition());
						db.setLegs(legs.getSelectedItemPosition());
						db.setRing1(ring1.getSelectedItemPosition());
						db.setRing2(ring2.getSelectedItemPosition());

						//WEAPONS
						db.setLh1(lh1.getSelectedItemPosition());
						db.setLh2(lh2.getSelectedItemPosition());
						db.setRh1(rh1.getSelectedItemPosition());
						db.setRh2(rh2.getSelectedItemPosition());

						//WEAPON PATH
						db.setLh1path(lh1path.getSelectedItemPosition());
						db.setLh2path(lh2path.getSelectedItemPosition());
						db.setRh1path(rh1path.getSelectedItemPosition());
						db.setRh2path(rh2path.getSelectedItemPosition());

						//Log.e("Endurance", db.getEnd() + "hah");
						//db.getVitality();
						//for(int i = 0; i < db.getNames().size();i++) {
						//Log.d("JERRY BLAH", db.getNames());
						//}
						dialog.dismiss();
					}
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						db.deleteAll();
						dialog.dismiss();
					}
				});
//                try {
//                    FileOutputStream name = new FileOutputStream(builds);
//                    BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(name));
//
//                    buffWriter.write(build.getText().toString());
//                } catch (IOException e) {
//
//                }
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		});


		load = (Button)findViewById(R.id.Load);
		load.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(db.getNames().size() != 0){
					ArrayList<String> temp = new ArrayList<String>();
					temp = db.getNames();
					// TODO Auto-generated method stub
					final CharSequence[] items = temp.toArray(new CharSequence[temp.size()]);
		
					db.getVit();
					db.getAtt();
					db.getEnd();
					db.getStr();
					db.getDex();
					db.getRes();
					db.getIntel();
					db.getFaith();
					db.getHum();
		
					db.getHead();
					db.getChest();
					db.getHands();
					db.getLegs();
					db.getRing1();
					db.getRing2();
		
					db.getLH1();
					db.getLH2();
					db.getRH1();
					db.getRH2();
		
					db.getLH1Path();
					db.getLH2Path();
					db.getRH1Path();
					db.getRH2Path();
		
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
					alertBuilder.setTitle("Load");
					alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
		
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Build bob = db.getStuff(items[which].toString());
		                    Vitality.setText(bob.getVit());
		                    Attunement.setText(bob.getAtt());
		                    Endurance.setText(bob.getEnd());
		                    Strength.setText(bob.getStr());
		                    Dexterity.setText(bob.getDex());
		                    Resistance.setText(bob.getRes());
		                    Intelligence.setText(bob.getIntel());
		                    Faith.setText(bob.getFaith());
		                    Humanity.setText(bob.getHum());
		
		                    head.setSelection(bob.getHead());
		                    chest.setSelection(bob.getChest());
		                    hands.setSelection(bob.getHands());
		                    legs.setSelection(bob.getLegs());
		                    ring1.setSelection(bob.getRing1());
		                    ring2.setSelection(bob.getRing2());
		
		                    lh1.setSelection(bob.getLh1());
		                    lh2.setSelection(bob.getLh2());
		                    rh1.setSelection(bob.getRh1());
		                    rh2.setSelection(bob.getRh2());
		
		                    lh1path.setSelection(bob.getLh1path(), true);
		                    lh2path.setSelection(bob.getLh2path(), true);
		                    rh1path.setSelection(bob.getRh1path(), true);
		                    rh2path.setSelection(bob.getRh2path(), true);
		
		
		
							dialog.dismiss();
						}
		
					});
		
					alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
		
					alertBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
		
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							db.deleteAll();
							dialog.dismiss();
						}
					});
		
		
					AlertDialog alertDialog = alertBuilder.create();
					alertDialog.dismiss();
					alertDialog.show();
				}
			}

		});

        clear = (Button)findViewById(R.id.Clear);
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ar1.setText("0");
                ar2.setText("0");
                ar3.setText("0");
                ar4.setText("0");

                lh1.setSelection(0);
                rh1.setSelection(0);
                lh2.setSelection(0);
                rh2.setSelection(0);
            	
            	item1.setSelection(0);
            	item2.setSelection(0);
            	item3.setSelection(0);
            	item4.setSelection(0);
            	item5.setSelection(0);

            	poiseBois.setText(0+"");
            	curseResistance.setText(0.0 + "");
            	bleedResistance.setText(0.0 + "");
            	poisonResistance.setText(0.0 + "");
            	heft.setText(0.0+"");
            	attSlots.setText(0 + ""); 
            	
            	
            	
                head.setSelection(0);
                chest.setSelection(0);
                hands.setSelection(0);
                legs.setSelection(0);
                
                Arrays.fill(currentHelm, 0.0);
                Arrays.fill(currentChest, 0.0);
                Arrays.fill(currentHands, 0.0);
                Arrays.fill(currentLegs, 0.0);
                currentLH1 = 0.0;
                currentLH2 = 0.0;
                currentRH1 = 0.0;
                currentRH2 = 0.0;
                
                
            	if (dsClass.getSelectedItem().toString().equals("Warrior")) {
					Vitality.setText(11 + "");
					Attunement.setText(8 + "");
					Endurance.setText(12 + "");
					Strength.setText(13 + "");
					Dexterity.setText(13 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(9 + "");
					Faith.setText(9 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(21.0 + "");
					strikeDefense.setText(21.0 + "");
					slashDefense.setText(21.0 + "");
					thrustDefense.setText(21.0 + "");
					magicDefense.setText(17.0 + "");
					fireDefense.setText(21.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(4+"");
				} else if (dsClass.getSelectedItem().toString().equals("Knight")) {
					Vitality.setText(14 + "");
					Attunement.setText(10 + "");
					Endurance.setText(10 + "");
					Strength.setText(11 + "");
					Dexterity.setText(11 + "");
					Resistance.setText(10 + "");
					Intelligence.setText(9 + "");
					Faith.setText(11 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(20.0 + "");
					strikeDefense.setText(20.0 + "");
					slashDefense.setText(20.0 + "");
					thrustDefense.setText(20.0 + "");
					magicDefense.setText(21.0 + "");
					fireDefense.setText(20.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(5+"");
				} else if (dsClass.getSelectedItem().toString().equals("Wanderer")) {
					Vitality.setText(10 + "");
					Attunement.setText(11 + "");
					Endurance.setText(10 + "");
					Strength.setText(10 + "");
					Dexterity.setText(14 + "");
					Resistance.setText(12 + "");
					Intelligence.setText(11 + "");
					Faith.setText(9 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(22.0 + "");
					strikeDefense.setText(22.0 + "");
					slashDefense.setText(22.0 + "");
					thrustDefense.setText(22.0 + "");
					magicDefense.setText(16.0 + "");
					fireDefense.setText(24.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(3+"");
				} else if (dsClass.getSelectedItem().toString().equals("Thief")) {
					Vitality.setText(9 + "");
					Attunement.setText(11 + "");
					Endurance.setText(9 + "");
					Strength.setText(9 + "");
					Dexterity.setText(15 + "");
					Resistance.setText(10 + "");
					Intelligence.setText(12 + "");
					Faith.setText(11 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(20.0 + "");
					strikeDefense.setText(20.0 + "");
					slashDefense.setText(20.0 + "");
					thrustDefense.setText(20.0 + "");
					magicDefense.setText(21.0 + "");
					fireDefense.setText(20.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(5+"");
				} else if (dsClass.getSelectedItem().toString().equals("Bandit")) {
					Vitality.setText(12 + "");
					Attunement.setText(8 + "");
					Endurance.setText(14 + "");
					Strength.setText(14 + "");
					Dexterity.setText(9 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(8 + "");
					Faith.setText(10 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(21.0 + "");
					strikeDefense.setText(21.0 + "");
					slashDefense.setText(21.0 + "");
					thrustDefense.setText(21.0 + "");
					magicDefense.setText(20.0 + "");
					fireDefense.setText(21.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(4+"");
				} else if (dsClass.getSelectedItem().toString().equals("Hunter")) {
					Vitality.setText(11 + "");
					Attunement.setText(9 + "");
					Endurance.setText(11 + "");
					Strength.setText(12 + "");
					Dexterity.setText(14 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(9 + "");
					Faith.setText(9 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(21.0 + "");
					strikeDefense.setText(21.0 + "");
					slashDefense.setText(21.0 + "");
					thrustDefense.setText(21.0 + "");
					magicDefense.setText(17.0 + "");
					fireDefense.setText(21.0 + "");
					lightningDefense.setText(19.0 + "");
					SoulLevel.setText(4+"");
				} else if (dsClass.getSelectedItem().toString().equals("Sorcerer")) {
					Vitality.setText(8 + "");
					Attunement.setText(15 + "");
					Endurance.setText(8 + "");
					Strength.setText(9 + "");
					Dexterity.setText(11 + "");
					Resistance.setText(8 + "");
					Intelligence.setText(15 + "");
					Faith.setText(8 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(12.0 + "");
					strikeDefense.setText(12.0 + "");
					slashDefense.setText(12.0 + "");
					thrustDefense.setText(12.0 + "");
					magicDefense.setText(11.0 + "");
					fireDefense.setText(11.0 + "");
					lightningDefense.setText(13.0 + "");
					SoulLevel.setText(3+"");
				} else if (dsClass.getSelectedItem().toString().equals("Pyromancer")) {
					Vitality.setText(10 + "");
					Attunement.setText(12 + "");
					Endurance.setText(11 + "");
					Strength.setText(12 + "");
					Dexterity.setText(9 + "");
					Resistance.setText(12 + "");
					Intelligence.setText(10 + "");
					Faith.setText(8 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(20.0 + "");
					strikeDefense.setText(20.0 + "");
					slashDefense.setText(20.0 + "");
					thrustDefense.setText(20.0 + "");
					magicDefense.setText(13.0 + "");
					fireDefense.setText(21.0 + "");
					lightningDefense.setText(16.0 + "");
					SoulLevel.setText(1+"");
				} else if (dsClass.getSelectedItem().toString().equals("Cleric")) {
					Vitality.setText(11 + "");
					Attunement.setText(11 + "");
					Endurance.setText(9 + "");
					Strength.setText(12 + "");
					Dexterity.setText(8 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(8 + "");
					Faith.setText(14 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(18.0 + "");
					strikeDefense.setText(18.0 + "");
					slashDefense.setText(18.0 + "");
					thrustDefense.setText(18.0 + "");
					magicDefense.setText(25.0 + "");
					fireDefense.setText(18.0 + "");
					lightningDefense.setText(16.0 + "");
					SoulLevel.setText(2+"");
				} else if (dsClass.getSelectedItem().toString().equals("Deprived")) {
					Vitality.setText(11 + "");
					Attunement.setText(11 + "");
					Endurance.setText(11 + "");
					Strength.setText(11 + "");
					Dexterity.setText(11 + "");
					Resistance.setText(11 + "");
					Intelligence.setText(11 + "");
					Faith.setText(11 + "");
					Humanity.setText(0 + "");
					physicalDefense.setText(24.0 + "");
					strikeDefense.setText(24.0 + "");
					slashDefense.setText(24.0 + "");
					thrustDefense.setText(24.0 + "");
					magicDefense.setText(24.0 + "");
					fireDefense.setText(24.0 + "");
					lightningDefense.setText(22.0 + "");
					SoulLevel.setText(6+"");
				}
//            	while(poiseBois.getText().toString().equals("0") == false){
//
//	                ar1.setText("0");
//	                ar2.setText("0");
//	                ar3.setText("0");
//	                ar4.setText("0");
//	
//	                lh1.setSelection(0);
//	                rh1.setSelection(0);
//	                lh2.setSelection(0);
//	                rh2.setSelection(0);
//	            	
//	            	item1.setSelection(0);
//	            	item2.setSelection(0);
//	            	item3.setSelection(0);
//	            	item4.setSelection(0);
//	            	item5.setSelection(0);
//	
//	            	poiseBois.setText(0+"");
//	            	curseResistance.setText(0.0 + "");
//	            	bleedResistance.setText(0.0 + "");
//	            	poisonResistance.setText(0.0 + "");
//	            	heft.setText(0.0+"");
//	            	attSlots.setText(0 + "");  	
//            	}
	            	
            }

        });

		
		
		
		
		//MIGHT NOT UPDATE AFTER STATS CHANGE, ONLY AFTER RING/HELM CHANGE
		if(havels){
			currentEQ = (currentEQ + (currentEQ * .5));
		}
		if(fap){
			currentEQ = (currentEQ + (currentEQ * .2));
			currentHP = (currentHP + (currentHP * .2));
			currentStam = (currentStam + (currentStam * .2));
		}
		if(tiny){
			currentHP = (currentHP + (currentHP * .05));
		}
	}


	public void setRates(){
		//DEXTERITY
	    for(int w = 0; w < 100; w++){
	    	try{
	            if(Integer.parseInt(Dexterity.getText().toString()) < 10){
					if(Integer.parseInt(Dexterity.getText().toString())== w) {
						dexRating = .05 - (.005 * (10 - w));
					}
	            }
				else if(Integer.parseInt(Dexterity.getText().toString()) == 10){
					dexRating = .05;
				}
	            else if(Integer.parseInt(Dexterity.getText().toString()) < 20){
	                if(Integer.parseInt(Dexterity.getText().toString())== w){
	                    dexRating = .05 + ((w - 10) * .035);
	                }
	            }
	            else if(Integer.parseInt(Dexterity.getText().toString()) == 20){
	                dexRating = .4;
	            }
	            else if(Integer.parseInt(Dexterity.getText().toString()) < 40){
	                if(Integer.parseInt(Dexterity.getText().toString()) == w){
	                    dexRating = .4 + ((w - 20) * .0225);
	                }
	            }
	            else if(Integer.parseInt(Dexterity.getText().toString()) == 40){
	                dexRating = .85;
	            }
	            else if(Integer.parseInt(Dexterity.getText().toString()) == w){
	                dexRating = .85 + ((w - 40) * .0025);
	            }
	    	}
	        catch(NumberFormatException e){
					
			}        
	    }


	    //STRENGTH
	    //SAME AS DEX
	    for(int w = 0; w < 100; w++){
	    	try{
	            if(Integer.parseInt(Strength.getText().toString()) < 10){
					if(Integer.parseInt(Strength.getText().toString())== w) {
						strRating = .05 - (.005 * (10 - w));
					}
	            }
				else if(Integer.parseInt(Strength.getText().toString()) == 10){
					strRating = .05;
				}
	            else if(Integer.parseInt(Strength.getText().toString()) < 20){
	                if(Integer.parseInt(Strength.getText().toString())== w){
	                    strRating = .05 + ((w - 10) * .035);
	                }
	            }
	            else if(Integer.parseInt(Strength.getText().toString()) == 20){
	                strRating = .4;
	            }
	            else if(Integer.parseInt(Strength.getText().toString()) < 40){
	                if(Integer.parseInt(Strength.getText().toString()) == w){
	                    strRating = .4 + ((w - 20) * .0225);
	                }
	            }
	            else if(Integer.parseInt(Strength.getText().toString()) == 40){
	                strRating = .85;
	            }
	            else if(Integer.parseInt(Strength.getText().toString()) == w){
	                strRating = .85 + ((w - 40) * .0025);
	            }
	    	}
	    	catch(NumberFormatException e){
	    		
	    	}
	    }


	    //INTELLIGENCE
	    for(int w = 0; w < 100; w++){
	    	try{
	            if(Integer.parseInt(Intelligence.getText().toString()) <= 10){
					if(Integer.parseInt(Intelligence.getText().toString())== w) {
						intRating = .05 - (.005 * (10 - w));
					}
	            }
	            else if(Integer.parseInt(Intelligence.getText().toString()) < 30){
	                if(Integer.parseInt(Intelligence.getText().toString())== w){
	                    intRating = .05 + ((w - 10) * .0225);
	                }
	            }
	            else if(Integer.parseInt(Intelligence.getText().toString()) == 30){
	                intRating = .5;
	            }
	            else if(Integer.parseInt(Intelligence.getText().toString()) < 50){
	                if(Integer.parseInt(Intelligence.getText().toString()) == w){
	                    intRating = .5 + ((w - 30) * .015);
	                }
	            }
	            else if(Integer.parseInt(Intelligence.getText().toString()) == 50){
	                intRating = .85;
	            }
	            else if(Integer.parseInt(Intelligence.getText().toString()) == w){
	                intRating = .85 + ((w - 50) * .0041);
	            }
	    	}
	    	catch(NumberFormatException e){
	    		
	    	}
	    }


	    //FAITH
	    //SAME AS INT
	    for(int w = 0; w < 100; w++){
	    	try{
	            if(Integer.parseInt(Faith.getText().toString()) <= 10){
					if(Integer.parseInt(Faith.getText().toString()) == w){
						if(Integer.parseInt(Faith.getText().toString())== w) {
							strRating = .05 - (.005 * (10 - w));
						}
					}
	            }
	            else if(Integer.parseInt(Faith.getText().toString()) < 30){
	                if(Integer.parseInt(Faith.getText().toString())== w){
	                    faithRating = .05 + ((w - 10) * .0225);
	                }
	            }
	            else if(Integer.parseInt(Faith.getText().toString()) == 30){
	                faithRating = .5;
	            }
	            else if(Integer.parseInt(Faith.getText().toString()) < 50){
	                if(Integer.parseInt(Faith.getText().toString()) == w){
	                    faithRating = .5 + ((w - 30) * .015);
	                }
	            }
	            else if(Integer.parseInt(Faith.getText().toString()) == 50){
	                faithRating = .85;
	            }
	            else if(Integer.parseInt(Faith.getText().toString()) == w){
	                faithRating = .85 + ((w - 50) * .0041);
	            }
	    	}
	    	catch(NumberFormatException e){
	    		
	    	}
	    }
	}
	
	public int arCalc(Spinner wep, Spinner wepPath, String[] stringWep, Items[] items) {
        int attack = 0;
        setRates();
        for (int q = 0; q < stringWep.length; q++) {
            if (wep.getSelectedItem().toString().equals(stringWep[q])) {
                if (items[q] instanceof Weapons) {
                    if (wepPath.getSelectedItem().toString().equals("Crystal +5")) {
                        attack = (int) (items[q].getCrystal()[0] + (items[q].getCrystal()[0] * strRating * items[q].getNormalPercent()[0]) + (items[q].getCrystal()[0] * dexRating * items[q].getNormalPercent()[1])
                                + (items[q].getCrystal()[1] * intRating * items[q].getNormalPercent()[2]) + (items[q].getCrystal()[1] * faithRating * items[q].getNormalPercent()[3]));
                    } else if (wepPath.getSelectedItem().toString().equals("Lightning +5")) {
                        attack = (int) items[q].getLightning()[0] + items[q].getLightning()[3];
                    } else if (wepPath.getSelectedItem().toString().equals("Raw +5")) {
                        attack = (int) (items[q].getNormal()[0] + (items[q].getRaw()[0] * strRating * items[q].getRawPercent()[0]) + (items[q].getRaw()[0] * dexRating * items[q].getRawPercent()[1])
                                + (items[q].getRaw()[1] * intRating * items[q].getRawPercent()[2]) + (items[q].getRaw()[1] * faithRating * items[q].getRawPercent()[3]));
                    } else if (wepPath.getSelectedItem().toString().equals("Magic +10")) {
                        attack = (int) (items[q].getMagic()[0] + (items[q].getMagic()[1]) + (items[q].getMagic()[0] * strRating * items[q].getMagicPercent()[0]) + (items[q].getMagic()[0] * dexRating * items[q].getMagicPercent()[1])
                                + (items[q].getMagic()[1] * intRating * items[q].getMagicPercent()[2]) + (items[q].getMagic()[1] * faithRating * items[q].getMagicPercent()[3]));
                    } else if (wepPath.getSelectedItem().toString().equals("Enchanted +5")) {
                        attack = (int) (items[q].getEnchant()[0] + (items[q].getEnchant()[1]) + (items[q].getEnchant()[0] * strRating * items[q].getEnchantPercent()[0]) + (items[q].getEnchant()[0] * dexRating * items[q].getEnchantPercent()[1])
                                + (items[q].getEnchant()[1] * intRating * items[q].getEnchantPercent()[2]) + (items[q].getEnchant()[1] * faithRating * items[q].getEnchantPercent()[3]));
                    } else if (wepPath.getSelectedItem().toString().equals("Divine +10")) {
                        attack = (int) (items[q].getDivine()[0] + (items[q].getDivine()[1]) + (items[q].getDivine()[0] * strRating * items[q].getDivinePercent()[0]) + (items[q].getDivine()[0] * dexRating * items[q].getDivinePercent()[1])
                                + (items[q].getDivine()[1] * intRating * items[q].getDivinePercent()[2]) + (items[q].getDivine()[1] * faithRating * items[q].getDivinePercent()[3]));
                    } else if (wepPath.getSelectedItem().toString().equals("Occult +5")) {
                        attack = (int) (items[q].getOccult()[0] + items[q].getOccult()[1] + (items[q].getOccult()[0] * strRating * items[q].getOccultPercent()[0]) + (items[q].getOccult()[0] * dexRating * items[q].getOccultPercent()[1])
                                + (items[q].getOccult()[1] * intRating * items[q].getOccultPercent()[2]) + (items[q].getOccult()[1] * faithRating * items[q].getOccultPercent()[3]));
                    } else if (wepPath.getSelectedItem().toString().equals("Fire +10")) {
                        attack = (int) (items[q].getFire()[0] + items[q].getFire()[2]);
                    } else if (wepPath.getSelectedItem().toString().equals("Chaos +5")) {
                        attack = (int) (items[q].getChaos()[0] + items[q].getChaos()[2]);
                    } else if (wepPath.getSelectedItem().toString().equals("Normal +15")) {
                        attack = (int) (items[q].getNormal()[0] + (items[q].getNormal()[0] * strRating * items[q].getNormalPercent()[0]) + (items[q].getNormal()[0] * dexRating * items[q].getNormalPercent()[1])
                                + (items[q].getNormal()[1] * intRating * items[q].getNormalPercent()[2]) + (items[q].getNormal()[1] * faithRating * items[q].getNormalPercent()[3]));
                    }
                }
            }
        }
        return attack;
    }
	
	public int calcSpecialHumanity(Spinner wep, String[] stringWep, Items[] items){
		int diffAttack = 0;
		setRates();
		for(int q = 0; q < stringWep.length; q++){
			if(wep.getSelectedItem().toString().equals(stringWep[q])){
				if(items[q] instanceof HumanityWeapons){
					wep.setVisibility(1);
                    diffAttack = (int) ((items[q].getNormal()[0] + items[q].getNormal()[1] + items[q].getNormal()[2]) +
                            (items[q].getNormal()[0] * strRating * items[q].getNormalPercent()[0]) + (items[q].getNormal()[0] * dexRating * items[q].getNormalPercent()[1])
                            + (items[q].getNormal()[1] * intRating * items[q].getNormalPercent()[2]) + (items[q].getNormal()[1] * faithRating * items[q].getNormalPercent()[3]));
//                    if(wep.getSelectedItem() != null){
//                    	diffAttack = (int) ((items[q].getNormal()[0] + items[q].getNormal()[1] + items[q].getNormal()[2]) +
//                                (items[q].getNormal()[0] * strRating * items[q].getNormalPercent()[0]) + (items[q].getNormal()[0] * dexRating * items[q].getNormalPercent()[1])
//                                + (items[q].getNormal()[1] * intRating * items[q].getNormalPercent()[2]) + (items[q].getNormal()[1] * faithRating * items[q].getNormalPercent()[3]));
                    //}
                    if(wep.getSelectedItem().toString().equals("Chaos Blade")){
                        //20, 30, 40, 46, 53, 59, 65, 71, 78, 84
                        if(Integer.parseInt(Humanity.getText().toString()) == 1){
                            diffAttack += 20;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 2){
                            diffAttack += 30;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 3){
                            diffAttack += 40;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 4){
                            diffAttack += 46;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 5){
                            diffAttack += 53;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 6){
                            diffAttack += 59;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 7){
                            diffAttack += 65;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 8){
                            diffAttack += 71;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 9){
                            diffAttack += 78;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) >= 10){
                            diffAttack += 84;
                        }
                    }
                    else if(wep.getSelectedItem().toString().equals("Quelaag's Fury Sword")){
                        //2,7,14,24,36,51,68,88,90,115, 13,33,60,91,126,165,208,256,308,364
                        if(Integer.parseInt(Humanity.getText().toString()) == 1){
                            diffAttack += (2 + 13);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 2){
                            diffAttack += (7 + 33);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 3){
                            diffAttack += (14 + 60);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 4){
                            diffAttack += (24 + 91);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 5){
                            diffAttack += (36 + 126);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 6){
                            diffAttack += (51 + 165);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 7){
                            diffAttack += (68 + 208);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 8){
                            diffAttack += (88 + 256);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 9){
                            diffAttack += (90 + 308);
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) >= 10){
                            diffAttack += (115 + 364);
                        }
                    }
                    else if(wep.getSelectedItem().toString().equals("Abyss Greatsword")){
                        //19, 26, 35, 41, 47, 53, 59, 68, 74, 80
                        if(Integer.parseInt(Humanity.getText().toString()) == 1){
                            diffAttack += 19;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 2){
                            diffAttack += 26;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 3){
                            diffAttack += 35;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 4){
                            diffAttack += 41;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 5){
                            diffAttack += 47;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 6){
                            diffAttack += 53;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 7){
                            diffAttack += 59;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 8){
                            diffAttack += 68;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) == 9){
                            diffAttack += 74;
                        }
                        else if(Integer.parseInt(Humanity.getText().toString()) >= 10){
                            diffAttack += 80;
                        }
                    }
				}
				else if(items[q] instanceof SpecialWeapons){
					wep.setVisibility(1);
					diffAttack = (int) (items[q].getNormal()[0] + 
                    		(items[q].getNormal()[0] * strRating * items[q].getNormalPercent()[0]) + (items[q].getNormal()[0] * dexRating * items[q].getNormalPercent()[1])
                            + (items[q].getNormal()[1] * intRating * items[q].getNormalPercent()[2]) + (items[q].getNormal()[1] * faithRating * items[q].getNormalPercent()[3]));
                    diffAttack += items[q].getNormal()[1] + items[q].getNormal()[2] + items[q].getNormal()[3];
				}
			}
		}
		return diffAttack;
	}
	
	public int addStats(){
		int total = 0;
		try{
			total = Integer.parseInt(Vitality.getText().toString()) + Integer.parseInt(Attunement.getText().toString()) + Integer.parseInt(Endurance.getText().toString()) +
					Integer.parseInt(Strength.getText().toString()) + Integer.parseInt(Dexterity.getText().toString()) + Integer.parseInt(Resistance.getText().toString()) +
					Integer.parseInt(Intelligence.getText().toString()) + Integer.parseInt(Faith.getText().toString());
			
			if(dsClass.getSelectedItem().toString().equals("Warrior")){
				classDone = true;
				total += 4;
			}
			else if(dsClass.getSelectedItem().toString().equals("Knight")){
				classDone = true;
				total += 5;
			}
			else if(dsClass.getSelectedItem().toString().equals("Wanderer")){
				classDone = true;
				total += 3;
			}
			else if(dsClass.getSelectedItem().toString().equals("Thief")){
				classDone = true;
				total += 5;
			}
			else if(dsClass.getSelectedItem().toString().equals("Bandit")){
				classDone = true;
				total += 4;
			}
			else if(dsClass.getSelectedItem().toString().equals("Hunter")){
				classDone = true;
				total += 4;
			}
			else if(dsClass.getSelectedItem().toString().equals("Sorcerer")){
				classDone = true;
				total += 3;
			}
			else if(dsClass.getSelectedItem().toString().equals("Pyromancer")){
				classDone = true;
				total += 1;
			}
			else if(dsClass.getSelectedItem().toString().equals("Cleric")){
				classDone = true;
				total += 2;
			}
			else if(dsClass.getSelectedItem().toString().equals("Deprived")){
				classDone = true;
				total += 6;
			}
	
			if(dsClass.getSelectedItem().toString().equals("Sorcerer")) {
				total -= 82;
			}
			else if(dsClass.getSelectedItem().toString().equals("Pyromancer") || dsClass.getSelectedItem().toString().equals("Cleric")){
				total -= 84;
			}
			else if(dsClass.getSelectedItem().toString().equals("Deprived")){
				total -= 99;
			}
			else{
				total -= 86;
			}
		if(total < 0){
			total = 0;
		}
		}
		catch(NumberFormatException e){
			
		}
		return total;
	}
	
	public void dynamicArChanger(String[] stringWep, Items[] items){
		if (lh1path.getSelectedItem() != null) {
			for (int q = 0; q < stringWep.length; q++) {
	            if (lh1.getSelectedItem().toString().equals(stringWep[q])) {
					if(items[q] instanceof Weapons){
						ar1.setText(arCalc(lh1, lh1path, stringWep, items) + "");
					}
					else{
						ar1.setText(calcSpecialHumanity(lh1, stringWep, items) + "");
					}
	            }
			}
		}
		if(rh1path.getSelectedItem() != null){
			for (int q = 0; q < stringWep.length; q++) {
	            if (rh1.getSelectedItem().toString().equals(stringWep[q])) {
					if(items[q] instanceof Weapons){
						ar2.setText(arCalc(rh1, rh1path, stringWep, items) + "");
					}
					else{
						ar2.setText(calcSpecialHumanity(rh1, stringWep, items) + "");
					}
	            }
			}
		}
		if(lh2.getSelectedItem() != null){
			for (int q = 0; q < stringWep.length; q++) {
	            if (lh2.getSelectedItem().toString().equals(stringWep[q])) {
					if(items[q] instanceof Weapons){
						ar3.setText(arCalc(lh2, lh2path, stringWep, items) + "");
					}
					else{
						ar3.setText(calcSpecialHumanity(lh2, stringWep, items) + "");
					}
	            }
			}
		}
		if(rh2.getSelectedItem() != null){
			for (int q = 0; q < stringWep.length; q++) {
	            if (rh2.getSelectedItem().toString().equals(stringWep[q])) {
					if(items[q] instanceof Weapons){
						ar4.setText(arCalc(rh2, rh2path, stringWep, items) + "");
					}else{
						ar4.setText(calcSpecialHumanity(rh2, stringWep, items) + "");
					}
	            }
			}
		}
	}
	
	public void spinnerDisabler(String[] stringWep, Items[] items){
			for (int q = 0; q < stringWep.length; q++) {
	            if (lh1.getSelectedItem().toString().equals(stringWep[q])) {
					if(items[q] instanceof Weapons){
						lh1path.setAdapter(path);
					}
					else{
						lh1path.setAdapter(nothing);
						ar2.setText(calcSpecialHumanity(rh1, stringWep, items) + "");
					}
	            }
			}
			for (int q = 0; q < stringWep.length; q++) {
	            if (rh1.getSelectedItem().toString().equals(stringWep[q])) {
					if(items[q] instanceof Weapons){
						rh1path.setAdapter(path);
					}
					else{
						rh1path.setAdapter(nothing);
						ar2.setText(calcSpecialHumanity(rh1, stringWep, items) + "");
					}
	            }
			}
			for (int q = 0; q < stringWep.length; q++) {
	            if (lh2.getSelectedItem().toString().equals(stringWep[q])) {
					if(items[q] instanceof Weapons){
						lh2path.setAdapter(path);
					}
					else{
						lh2path.setAdapter(nothing);
						ar3.setText(calcSpecialHumanity(lh2, stringWep, items) + "");
					}
	            }
			}
			for (int q = 0; q < stringWep.length; q++) {
	            if (rh2.getSelectedItem().toString().equals(stringWep[q])) {
					if(items[q] instanceof Weapons){
						rh2path.setAdapter(path);
					}else{
						rh2path.setAdapter(nothing);
						ar4.setText(calcSpecialHumanity(rh2, stringWep, items) + "");
					}
	            }
			}
		}
	
	public void armorAdder(double[] helm, double[] chest, double[] hands, double[] legs, double lh1, double lh2, double rh1, double rh2){
		poise = (int)(helm[0] + chest[0] + hands[0] + legs[0]);
		poiseBois.setText(poise + "");
		weight = helm[1] + chest[1] + hands[1] + legs[1] + lh1 + lh2 + rh1 + rh2;
		heft.setText(df.format(weight));
		physDef = helm[2] + chest[2] + hands[2] + legs[2];
		physicalDefense.setText(df.format(physDef + humDef[0]) + "");
		strikeDef = helm[3] + chest[3] + hands[3] + legs[3];
		strikeDefense.setText(df.format(strikeDef + humDef[0]) + "");
		slashDef = helm[4] + chest[4] + hands[4] + legs[4];
		slashDefense.setText(df.format(slashDef + humDef[0]) + "");
		thrustDef = helm[5] + chest[5] + hands[5] + legs[5];
		thrustDefense.setText(df.format(thrustDef + humDef[0]) + "");
		magDef = helm[6] + chest[6] + hands[6] + legs[6];
		magicDefense.setText(df.format(magDef + humDef[1]) + "");
		fireDef = helm[7] + chest[7] + hands[7] + legs[7];
		fireDefense.setText(df.format(fireDef + humDef[2]) + "");
		lightningDef = helm[8] + chest[8] + hands[8] + legs[8];
		lightningDefense.setText(df.format(lightningDef + humDef[3]) + "");
		bleedRes = helm[9] + chest[9] + hands[9] + legs[9];
		bleedResistance.setText(df.format(bleedRes) + "");
		posRes = helm[10] + chest[10] + hands[10] + legs[10];
		poisonResistance.setText(df.format(posRes) + "");
		curseRes = helm[11] + chest[11] + hands[11] + legs[11];
		curseResistance.setText(df.format(curseRes) + "");
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
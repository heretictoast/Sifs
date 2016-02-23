package com.example.mugenmonkey;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Context;
import android.content.ContentValues;

/**
 * Created by LHC on 10/2/15.
 */
public class Storage extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "buildManager";

    // build table name
    private static final String TABLE_BUILDS = "builds";
    private static String RENAMED = "wowzer";

    // Contacts Table Columns names
    private static final String NAME = "name";
    
    //STATS
    private static final String VITALITY = "Vitality";
    private static final String ATTUNEMENT = "Attunement";
    private static final String ENDURANCE = "Endurance";
    private static final String STRENGTH = "Strength";
    private static final String DEXTERITY = "Dexterity";
    private static final String RESISTANCE = "Resistance";
    private static final String INTELLIGENCE = "Intelligence";
    private static final String FAITH = "Faith";
    private static final String HUMANITY = "Humanity";

    //ARMOR
    private static final String HELM = "Helm";
    private static final String CHEST = "Chest";
    private static final String HANDS = "Hands";
    private static final String LEGS = "Legs";
    private static final String RING1 = "Ring1";
    private static final String RING2 = "Ring2";
    
    //WEAPONS
    private static final String LH1 = "LH1";
    private static final String LH2 = "LH2";
    private static final String RH1 = "RH1";
    private static final String RH2 = "RH2";

    //Weapon Paths
    private static final String LH1PATH = "LH1Path";
    private static final String LH2PATH = "LH2Path";
    private static final String RH1PATH = "RH1Path";
    private static final String RH2PATH = "RH2Path";

    //ArrayLists
    private static ArrayList<String> stuff = new ArrayList<String>(); 
    private static ArrayList<String> names = new ArrayList<String>(); 
    private static ArrayList<String> vit = new ArrayList<String>(); 
    private static ArrayList<String> att = new ArrayList<String>(); 
    private static ArrayList<String> end = new ArrayList<String>(); 
    private static ArrayList<String> str = new ArrayList<String>(); 
    private static ArrayList<String> dex = new ArrayList<String>(); 
    private static ArrayList<String> res = new ArrayList<String>(); 
    private static ArrayList<String> intel = new ArrayList<String>(); 
    private static ArrayList<String> faith = new ArrayList<String>(); 
    private static ArrayList<String> hum = new ArrayList<String>(); 
    private static ArrayList<Integer> head = new ArrayList<Integer>(); 
    private static ArrayList<Integer> chest = new ArrayList<Integer>(); 
    private static ArrayList<Integer> hands = new ArrayList<Integer>(); 
    private static ArrayList<Integer> legs = new ArrayList<Integer>(); 
    private static ArrayList<Integer> ring1 = new ArrayList<Integer>(); 
    private static ArrayList<Integer> ring2 = new ArrayList<Integer>(); 
    private static ArrayList<Integer> lh1 = new ArrayList<Integer>(); 
    private static ArrayList<Integer> lh2 = new ArrayList<Integer>(); 
    private static ArrayList<Integer> rh1 = new ArrayList<Integer>(); 
    private static ArrayList<Integer> rh2 = new ArrayList<Integer>(); 
    private static ArrayList<Integer> lh1path = new ArrayList<Integer>(); 
    private static ArrayList<Integer> lh2path = new ArrayList<Integer>(); 
    private static ArrayList<Integer> rh1path = new ArrayList<Integer>(); 
    private static ArrayList<Integer> rh2path = new ArrayList<Integer>();

    private static Build build;
    
    
    
    private static ArrayList<String> buildNames = new ArrayList<String>();
    
    public Storage(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_BUILDS_TABLE = "CREATE TABLE " + TABLE_BUILDS + "(" + NAME + " TEXT," +VITALITY + " TEXT," +
                ATTUNEMENT + " TEXT," + ENDURANCE + " TEXT," + STRENGTH + " TEXT," + DEXTERITY + " TEXT," +
                RESISTANCE + " TEXT,"  + INTELLIGENCE + " TEXT," + FAITH + " TEXT," + HUMANITY + " TEXT," +

                HELM + " INTEGER," + CHEST + " INTEGER," + HANDS + " INTEGER," +
                LEGS + " INTEGER," + RING1 + " INTEGER," + RING2 + " INTEGER," +

                LH1 + " INTEGER," + LH2 + " INTEGER," + RH1 + " INTEGER," + RH2 + " INTEGER," +

                LH1PATH + " INTEGER," + LH2PATH + " INTEGER," + RH1PATH + " INTEGER," + RH2PATH + " INTEGER" +
                ")";
    	
    	
    	db.execSQL(CREATE_BUILDS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDS);

        // Create tables again
        onCreate(db);
    }

    //SETTERS

    public void setTable(String title){    	
//    	buildNames.add(title);
//        SQLiteDatabase db = this.getWritableDatabase();
//    	RENAMED = title;
//    	deleteBuildsInternal(TABLE_BUILDS);
//    	String ALTERED = "CREATE TABLE " + RENAMED + "(" + VITALITY + " TEXT," +
//                ATTUNEMENT + " TEXT," + ENDURANCE + " TEXT," + STRENGTH + " TEXT," + DEXTERITY + " TEXT," +
//                RESISTANCE + " TEXT,"  + INTELLIGENCE + " TEXT," + FAITH + " TEXT," + HUMANITY + " TEXT," +
//
//                HELM + " INTEGER," + CHEST + " INTEGER," + HANDS + " INTEGER," +
//                LEGS + " INTEGER," +
//
//                LH1 + " INTEGER," + LH2 + " INTEGER," + RH1 + " INTEGER," + RH2 + " INTEGER," +
//
//                LH1PATH + " INTEGER," + LH2PATH + " INTEGER," + RH1PATH + " INTEGER," + RH2PATH + " INTEGER" +
//                ")";
//    	db.execSQL(ALTERED);    
//    	Log.d("NAME", renamed);
    }
    
    public void setName(String name){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
        values.put(NAME, name); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    
    
    //STATS
    public void setVitality(String vit){
        Log.d("JERRY TIGHTEN YOUR GAME", vit);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VITALITY, vit); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }

    public void setAttunement(String att){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ATTUNEMENT, att); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setEndurance(String end){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ENDURANCE, end); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setStrength(String str){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STRENGTH, str); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setDexterity(String dex){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DEXTERITY, dex); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setResistance(String res){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESISTANCE, res); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setIntelligence(String intel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INTELLIGENCE, intel); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setFaith(String faith){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FAITH, faith); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setHumanity(String hum){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HUMANITY, hum); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }



    //ARMOR
    public void setHelm(int helm){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HELM, helm); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setChest(int chest){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CHEST, chest); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setHands(int hands){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HANDS, hands); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setLegs(int legs){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LEGS, legs); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setRing1(int ring1){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RING1, ring1); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setRing2(int ring2){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RING2, ring2); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }


    //WEAPONS
    public void setLh1(int lh1){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LH1, lh1); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setLh2(int lh2){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LH2, lh2); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setRh1(int rh1){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RH1, rh1); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setRh2(int rh2){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RH2, rh2); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }



    //WEAPON PATHS
    public void setLh1path(int lh1path){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LH1PATH, lh1path); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setLh2path(int lh2path){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LH2PATH, lh2path); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setRh1path(int rh1path){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RH1PATH, rh1path); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection
    }
    public void setRh2path(int rh2path){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RH2PATH, rh2path); // Contact Name

        // Inserting Row
        db.insert(TABLE_BUILDS, null, values);
        db.close(); // Closing database connection 
    }






    
    
    
    
    
    
    
    
    
    
    
    
    



    //GETTERS
    public ArrayList<String> getVit(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + VITALITY + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(VITALITY)) != null){
            		vit.add(cursor.getString(cursor.getColumnIndexOrThrow(VITALITY)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vit;
    }
    public ArrayList<String> getAtt(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + ATTUNEMENT + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(ATTUNEMENT)) != null){
            		att.add(cursor.getString(cursor.getColumnIndexOrThrow(ATTUNEMENT)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return att;
    }    
    public ArrayList<String> getEnd(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + ENDURANCE + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(ENDURANCE)) != null){
            		end.add(cursor.getString(cursor.getColumnIndexOrThrow(ENDURANCE)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return end;
    }
    public ArrayList<String> getStr(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + STRENGTH + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(STRENGTH)) != null){
            		str.add(cursor.getString(cursor.getColumnIndexOrThrow(STRENGTH)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return str;
    }
    
    public ArrayList<String> getDex(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + DEXTERITY + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(DEXTERITY)) != null){
            		dex.add(cursor.getString(cursor.getColumnIndexOrThrow(DEXTERITY)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dex;
    }
    public ArrayList<String> getRes(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + RESISTANCE + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(RESISTANCE)) != null){
            		res.add(cursor.getString(cursor.getColumnIndexOrThrow(RESISTANCE)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return res;
    }
    public ArrayList<String> getIntel(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + INTELLIGENCE + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(INTELLIGENCE)) != null){
            		intel.add(cursor.getString(cursor.getColumnIndexOrThrow(INTELLIGENCE)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return intel;
    }
    public ArrayList<String> getFaith(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + FAITH + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(FAITH)) != null){
            		faith.add(cursor.getString(cursor.getColumnIndexOrThrow(FAITH)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return faith;
    }
    public ArrayList<String> getHum(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + HUMANITY + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(HUMANITY)) != null){
            		hum.add(cursor.getString(cursor.getColumnIndexOrThrow(HUMANITY)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return hum;
    }
    
    
    
    
    
    //ARMOR
    public ArrayList<Integer> getHead(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + HELM + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(HELM)) != null){
            		head.add(cursor.getInt(cursor.getColumnIndexOrThrow(HELM)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return head;
    }
    public ArrayList<Integer> getChest(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + CHEST + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(CHEST)) != null){
            		chest.add(cursor.getInt(cursor.getColumnIndexOrThrow(CHEST)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return chest;
    }
    
    public ArrayList<Integer> getHands(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + HANDS + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(HANDS)) != null){
            		hands.add(cursor.getInt(cursor.getColumnIndexOrThrow(HANDS)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return hands;
    }
    public ArrayList<Integer> getLegs(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + LEGS + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(LEGS)) != null){
            		legs.add(cursor.getInt(cursor.getColumnIndexOrThrow(LEGS)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return legs;
    }
    public ArrayList<Integer> getRing1(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + RING1 + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(RING1)) != null){
            		ring1.add(cursor.getInt(cursor.getColumnIndexOrThrow(RING1)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ring1;
    }
    public ArrayList<Integer> getRing2(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + RING2 + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(RING2)) != null){
            		ring2.add(cursor.getInt(cursor.getColumnIndexOrThrow(RING2)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ring2;
    }
    
    
    
    
    //WEAPONS
    public ArrayList<Integer> getLH1(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + LH1 + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(LH1)) != null){
            		lh1.add(cursor.getInt(cursor.getColumnIndexOrThrow(LH1)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lh1;
    }
    public ArrayList<Integer> getLH2(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + LH2 + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(LH2)) != null){
            		lh2.add(cursor.getInt(cursor.getColumnIndexOrThrow(LH2)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lh2;
    }
    public ArrayList<Integer> getRH1(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + RH1 + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(RH1)) != null){
            		rh1.add(cursor.getInt(cursor.getColumnIndexOrThrow(RH1)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rh1;
    }
    public ArrayList<Integer> getRH2(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + RH2 + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(RH2)) != null){
            		rh2.add(cursor.getInt(cursor.getColumnIndexOrThrow(RH2)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rh2;
    }
    
    
    
    //WEAPON PATH
    public ArrayList<Integer> getLH1Path(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + LH1PATH + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(LH1PATH)) != null){
            		lh1path.add(cursor.getInt(cursor.getColumnIndexOrThrow(LH1PATH)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lh1path;
    }
    public ArrayList<Integer> getLH2Path(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + LH2PATH + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(LH2PATH)) != null){
            		lh2path.add(cursor.getInt(cursor.getColumnIndexOrThrow(LH2PATH)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lh2path;
    }
    public ArrayList<Integer> getRH1Path(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + RH1PATH + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(RH1PATH)) != null){
            		rh1path.add(cursor.getInt(cursor.getColumnIndexOrThrow(RH1PATH)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rh1path;
    }
    public ArrayList<Integer> getRH2Path(){
    	Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT " + RH2PATH + " FROM " + TABLE_BUILDS, null);
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            	if(cursor.getString(cursor.getColumnIndexOrThrow(RH2PATH)) != null){
            		rh2path.add(cursor.getInt(cursor.getColumnIndexOrThrow(RH2PATH)));
            	}
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rh2path;
    }    
    public ArrayList<String> getNames(){
        // Select All Query
        boolean unique = true;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + NAME + " FROM " + TABLE_BUILDS, null);
        // looping through all rows and adding to list
        if(cursor != null){
        	if(cursor.moveToFirst()){
		        do {
		        	if(cursor.getString(cursor.getColumnIndexOrThrow(NAME)) != null){
		                if(names.size() != 0) {
		                    for (int i = 0; i < names.size(); i++) {
		                        if(names.get(i).equals(cursor.getString(cursor.getColumnIndexOrThrow(NAME)))) {
		                           unique = false;
		                        }
		                    }
		                    if(unique){
		                        names.add(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
		                    }
		                    unique = true;
		                }
		                else{
		                    names.add(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
		                }
		        	}
		        } while (cursor.moveToNext());
		    }
        }
		return names;
    }
    
    public Build getStuff(String name){
    	int val = 0;
    	SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BUILDS, null);
     
        // looping through all rows and adding to list
        if(cursor != null){
        	cursor.moveToFirst();
            do {
            		for(int i = 0; i < names.size(); i++) {
                        if (name.equals(names.get(i))) {
                            val = i;
                        }
                    }

            		build = new Build(names.get(val),
                            vit.get(val),
                            att.get(val),
                            end.get(val),
                            str.get(val),
                            dex.get(val),
                            res.get(val),
                            intel.get(val),
                            faith.get(val),
                            hum.get(val),
                            head.get(val),
                            chest.get(val),
                            legs.get(val),
                            hands.get(val),
                            ring1.get(val),
                            ring2.get(val),
                            lh1.get(val),
                            lh2.get(val),
                            rh1.get(val),
                            rh2.get(val),
                            lh1path.get(val),
                            lh2path.get(val),
                            rh1path.get(val),
                            rh2path.get(val));
            } while (cursor.moveToNext());
        }
		return build;
    }
      
    
    
    //RESET
    
//    public void deleteBuildsInternal(String table) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(table, null, null);
//    }
    
    public void deleteAll(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(TABLE_BUILDS, null, null);
    	names.clear();
    }
    
    

    //FINISH GETTERS IF IT WORKS


}

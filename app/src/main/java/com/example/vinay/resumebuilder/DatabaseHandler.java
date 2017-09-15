package com.example.vinay.resumebuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 9;

    // Database Name
    private static final String DATABASE_NAME = "resumebuilder";

    // profile table name
    private static final String TABLE_PROFILE_NAMES = "profiles";
    //private static final String TABLE_SHOPPER = "shopper";


    // profile Table Columns names

    private static final String KEY_CID = "cid";
    private static final String KEY_NAME = "cname";
    private static final String KEY_DATE = "cdate";
    private static final String KEY_RTYPE = "ctype";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE_NAMES + "("
                + KEY_CID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT, " + KEY_RTYPE + " TEXT " + ")";

        db.execSQL(CREATE_PROFILE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE_NAMES);
        // Create tables again
        onCreate(db);
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new profile
    long addProfile(CardDetails cd) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_NAME,cd.getCname() );
        values.put(KEY_DATE,getDateTime() );
        values.put(KEY_RTYPE,cd.getCtype() );

        // Inserting Row
        long n = db.insert(TABLE_PROFILE_NAMES, null, values);
        //db.close(); // Closing database connection
        return n;
    }

    // delete a profile
    boolean deleteProfile( int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor cursor = db.rawQuery(" delete from " + TABLE_PROFILE_NAMES + " where " + KEY_CID + " = " + id, null);

        boolean n= db.delete(TABLE_PROFILE_NAMES, KEY_CID + " = " + id, null) >0 ;
        db.close();
        return  n;
    }


    // Getting All Contacts
    public List<CardDetails> getAllCardDetails() {
        List<CardDetails> cardDetailsList = new ArrayList<CardDetails>();
        SQLiteDatabase db = this.getReadableDatabase();

        //for reverse order
        //Cursor cursor = db.rawQuery("select * from " + TABLE_PROFILE_NAMES + " order by cid desc", null);

        Cursor cursor = db.rawQuery("select * from " + TABLE_PROFILE_NAMES, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CardDetails cardDetails = new CardDetails();
                cardDetails.setCid(Integer.parseInt(cursor.getString(0)));
                cardDetails.setCname(cursor.getString(1));
                cardDetails.setDate(cursor.getString(2));
                cardDetails.setCtype(cursor.getString(3));
                // Adding profiles to list
                cardDetailsList.add(cardDetails);
            } while (cursor.moveToNext());
        }

        // return contact list
        return cardDetailsList;
    }


}
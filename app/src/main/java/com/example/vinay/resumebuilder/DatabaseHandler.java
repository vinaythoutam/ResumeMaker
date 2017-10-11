package com.example.vinay.resumebuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.vinay.resumebuilder.model.CardDetails;
import com.example.vinay.resumebuilder.model.PersonalInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 17;

    // Database Name
    private static final String DATABASE_NAME = "resumebuilder";

    // profile table name
    private static final String TABLE_PROFILE_NAMES = "profiles";
    private static final String TABLE_PERSONAL_INFO = "personal_info";


    // profile Table Columns names
    private static final String KEY_CID = "cid";
    private static final String KEY_NAME = "cname";
    private static final String KEY_DATE = "cdate";
    private static final String KEY_RTYPE = "ctype";

    //Personal info table
    private static final String KEY_FOREIGN = "fid";
    private static final String PI_KEY_ID = "pid";
    private static final String PI_KEY_FULLNAME = "pfullname";
    private static final String PI_KEY_PHONE = "pphone";
    private static final String PI_KEY_EMAIL = "pemail";
    private static final String PI_KEY_HOUSE = "phouse";
    private static final String PI_KEY_STREET = "pstreet";
    private static final String PI_KEY_ADDRESS = "paddress";
    private static final String PI_KEY_COUNTRY = "pcountry";
    private static final String PI_KEY_CITY = "pcity";
    private static final String PI_KEY_DOB = "pdob";
    private static final String PI_KEY_GENDER = "pgender";
    private static final String PI_KEY_MARITAL_STATUS = "pmarital";
    private static final String PI_KEY_PHOTO = "ppicture";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE_NAMES + "("
                + KEY_CID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT, " + KEY_RTYPE + " TEXT " + ")";

        String CREATE_PINFO_TABLE = "CREATE TABLE " + TABLE_PERSONAL_INFO + "("
                + PI_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PI_KEY_FULLNAME + " TEXT,"
                + PI_KEY_PHONE + " INTEGER, " + PI_KEY_EMAIL + " TEXT, " +
                PI_KEY_HOUSE + " TEXT, " + PI_KEY_STREET + " TEXT, " + PI_KEY_ADDRESS + " TEXT, " +
                PI_KEY_COUNTRY + " TEXT, " + PI_KEY_CITY + " TEXT, " + PI_KEY_DOB + " TEXT, " + PI_KEY_GENDER + " TEXT, " +
                PI_KEY_MARITAL_STATUS + " TEXT, " + PI_KEY_PHOTO + " BLOB, " + KEY_CID + " INTEGER " + ")";

        //+ KEY_FOREIGN + " INTEGER, "+ "FOREIGN KEY(" + KEY_FOREIGN + ") REFERENCES "+ TABLE_PROFILE_NAMES + " (fid) "

        db.execSQL(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_PINFO_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE_NAMES);
        // Create tables again

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAL_INFO);

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
        values.put(KEY_NAME, cd.getCname());
        values.put(KEY_DATE, getDateTime());
        values.put(KEY_RTYPE, cd.getCtype());

        // Inserting Row
        long n = db.insert(TABLE_PROFILE_NAMES, null, values);
        //db.close(); // Closing database connection
        return n;
    }

    // delete a profile
    boolean deleteProfile(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor cursor = db.rawQuery(" delete from " + TABLE_PROFILE_NAMES + " where " + KEY_CID + " = " + id, null);

        boolean n = db.delete(TABLE_PROFILE_NAMES, KEY_CID + " = " + id, null) > 0;
        db.close();
        return n;
    }


    // Getting All Profiles
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

        // return profile list
        return cardDetailsList;
    }


    //Add Personal Info
    public long addPersonalInfo(PersonalInfo pi) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(PI_KEY_FULLNAME, pi.getpFullname());
        values.put(PI_KEY_PHONE, pi.getpPhoneNumber());
        values.put(PI_KEY_EMAIL, pi.getpEmail());
        values.put(PI_KEY_HOUSE, pi.getpHouse());
        values.put(PI_KEY_STREET, pi.getpStreet());
        values.put(PI_KEY_ADDRESS, pi.getpAddress());
        values.put(PI_KEY_COUNTRY, pi.getpCountry());
        values.put(PI_KEY_CITY, pi.getpCity());
        values.put(PI_KEY_DOB, pi.getpDob());
        values.put(PI_KEY_GENDER, pi.getpGender());
        values.put(PI_KEY_MARITAL_STATUS, pi.getpMStatus());
        values.put(PI_KEY_PHOTO, pi.getpImage());

        // Inserting Row
        long n = db.insert(TABLE_PERSONAL_INFO, null, values);
        //db.close(); // Closing database connection
        return n;
    }


    // Getting single contact
    public String getContact() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select *from personal_info where pid = 1 ",null);

        String name = cursor.getString(1);


        return  name;


    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PERSONAL_INFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    private byte[] getBlob(Cursor cursor, String colName, byte[] defaultValue) {
        try {
            int colIndex;
            if (cursor != null && (colIndex = cursor.getColumnIndex(colName)) > -1
                    && !cursor.isNull(colIndex))
                return cursor.getBlob(colIndex);
            return defaultValue;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

}
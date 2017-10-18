package com.example.vinay.resumebuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.vinay.resumebuilder.model.AcademicInfo;
import com.example.vinay.resumebuilder.model.CardDetails;
import com.example.vinay.resumebuilder.model.CareerObjective;
import com.example.vinay.resumebuilder.model.PersonalInfo;
import com.example.vinay.resumebuilder.model.WorkExperience;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 50;

    // Database Name
    private static final String DATABASE_NAME = "resumebuilder";

    // Tables names
    private static final String TABLE_PROFILE_NAMES = "profiles";
    private static final String TABLE_PERSONAL_INFO = "personal_info";
    private static final String TABLE_CAREER_OBJ = "career_obj";
    private static final String TABLE_WORK_EXPERIENCE = "work_experience";
    private static final String TABLE_ACADEMIC_INFO = "academic_info";


    // profile Table Columns names
    private static final String KEY_CID = "cid";
    private static final String KEY_NAME = "cname";
    private static final String KEY_DATE = "cdate";
    private static final String KEY_RTYPE = "ctype";
    private static final String KEY_UDATE = "cudate";

    //Personal info table columns
    private static final String KEY_FOREIGN = "fid";
    private static final String PI_KEY_ID = "pid";
    private static final String PI_KEY_FULLNAME = "pfullname";
    private static final String PI_KEY_PHONE = "pphone";
    private static final String PI_KEY_ALT_PHONE = "paltphone";
    private static final String PI_KEY_EMAIL = "pemail";
    private static final String PI_KEY_ALT_EMAIL = "paltemail";
    private static final String PI_KEY_HOUSE = "phouse";
    private static final String PI_KEY_STREET = "pstreet";
    private static final String PI_KEY_ADDRESS = "paddress";
    private static final String PI_KEY_COUNTRY = "pcountry";
    private static final String PI_KEY_CITY = "pcity";
    private static final String PI_KEY_PINCODE = "ppincode";
    private static final String PI_KEY_PAN = "ppan";
    private static final String PI_KEY_PASSPORT = "ppassport";
    private static final String PI_KEY_DOB = "pdob";
    private static final String PI_KEY_GENDER = "pgender";
    private static final String PI_KEY_MARITAL_STATUS = "pmarital";
    private static final String PI_KEY_PHOTO = "ppicture";


    // career objective Table Columns names
    private static final String CO_KEY_ID = "pid";
    private static final String CO_KEY_CID = "cid";
    private static final String CO_KEY_CO = "careeobj";

    //Experience Table Columns names
    private static final String EX_KEY_ID = "pid";
    private static final String EX_JOBTITLE = "exJobtitle";
    private static final String EX_JOBDESCRIPTION ="exJobdescription";
    private static final String EX_COMPANYNAME = "exCompanyname";
    private static final String EX_STARTDATE = "exStartdate";
    private static final String EX_ENDDATE = "exEnddate";

    // academic info Table Columns names
    private static final String AI_KEY_ID = "pid";
    private static final String AI_KEY_CID = "cid";
    private static final String AI_KEY_PGNAME = "pgname";
    private static final String AI_KEY_PGYEAR = "pgyear";
    private static final String AI_KEY_PGPERCENTAGE = "pgpercentage";
    private static final String AI_KEY_GNAME = "gname";
    private static final String AI_KEY_GYEAR = "gyear";
    private static final String AI_KEY_GPERCENTAGE = "gpercentage";
    private static final String AI_KEY_CNAME = "cname";
    private static final String AI_KEY_CYEAR = "cyear";
    private static final String AI_KEY_CPERCENTAGE = "cpercentage";
    private static final String AI_KEY_SNAME = "sname";
    private static final String AI_KEY_SYEAR = "syear";
    private static final String AI_KEY_SPERCENTAGE = "spercentage";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE_NAMES + "("
                + KEY_CID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT, " + KEY_RTYPE + " TEXT, " + KEY_UDATE + " TEXT " + ")";

        String CREATE_PINFO_TABLE = "CREATE TABLE " + TABLE_PERSONAL_INFO + "("
                + PI_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PI_KEY_FULLNAME + " TEXT,"
                + PI_KEY_PHONE + " TEXT, " + PI_KEY_ALT_PHONE + " TEXT, " + PI_KEY_EMAIL + " TEXT, "
                + PI_KEY_ALT_EMAIL + " TEXT, " +
                PI_KEY_HOUSE + " TEXT, " + PI_KEY_STREET + " TEXT, " + PI_KEY_ADDRESS + " TEXT, " +
                PI_KEY_COUNTRY + " TEXT, " + PI_KEY_CITY + " TEXT, " + PI_KEY_PINCODE + " TEXT, " + PI_KEY_PAN + " TEXT, "
                + PI_KEY_PASSPORT + " TEXT, " + PI_KEY_DOB + " TEXT, " + PI_KEY_GENDER + " TEXT, " +
                PI_KEY_MARITAL_STATUS + " TEXT, " + PI_KEY_PHOTO + " TEXT, " + KEY_CID + " INTEGER " + ")";

        //+ KEY_FOREIGN + " INTEGER, "+ "FOREIGN KEY(" + KEY_FOREIGN + ") REFERENCES "+ TABLE_PROFILE_NAMES + " (fid) "

        String CREATE_CO_TABLE = "CREATE TABLE " + TABLE_CAREER_OBJ + "("
                + CO_KEY_ID + " INTEGER PRIMARY KEY, " + CO_KEY_CID + " INTEGER,"
                + CO_KEY_CO + " TEXT " + ")";

        String CREATE_AI_TABLE = "CREATE TABLE " + TABLE_ACADEMIC_INFO + "("
                + AI_KEY_ID + " INTEGER PRIMARY KEY, " + AI_KEY_CID + " INTEGER,"
                + AI_KEY_PGNAME + " TEXT, "  + AI_KEY_PGYEAR + " TEXT, " + AI_KEY_PGPERCENTAGE + " TEXT, "
                + AI_KEY_GNAME + " TEXT, "  + AI_KEY_GYEAR + " TEXT, " + AI_KEY_GPERCENTAGE + " TEXT, "
                + AI_KEY_CNAME + " TEXT, "  + AI_KEY_CYEAR + " TEXT, " + AI_KEY_CPERCENTAGE + " TEXT, "
                + AI_KEY_SNAME + " TEXT, "  + AI_KEY_SYEAR + " TEXT, " + AI_KEY_SPERCENTAGE + " TEXT "
                + ")";
        String CREATE_WORK_EX_TABLE = "CREATE TABLE " + TABLE_WORK_EXPERIENCE + "("
                + EX_KEY_ID + " INTEGER PRIMARY KEY, " + EX_JOBTITLE + " TEXT, " + EX_JOBDESCRIPTION + " TEXT," + EX_COMPANYNAME
                + " TEXT, " + EX_STARTDATE + " TEXT, " + EX_ENDDATE+ " TEXT " + ")" ;

        db.execSQL(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_PINFO_TABLE);
        db.execSQL(CREATE_CO_TABLE);
        db.execSQL(CREATE_AI_TABLE);
        db.execSQL(CREATE_WORK_EX_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE_NAMES);
        // Create tables again

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAL_INFO);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAREER_OBJ);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORK_EXPERIENCE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACADEMIC_INFO);

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
        values.put(KEY_UDATE,getDateTime());

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
                cardDetails.setCudate(cursor.getString(4));
                // Adding profiles to list
                cardDetailsList.add(cardDetails);
            } while (cursor.moveToNext());
        }

        // return profile list
        return cardDetailsList;
    }


    //getting single record from personal info
    public PersonalInfo getSinglePI(int cid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PERSONAL_INFO + " WHERE "
                + KEY_CID + " = " + cid;

        Cursor c = db.rawQuery(selectQuery, null);
        PersonalInfo personalInfo = new PersonalInfo();
        if (c != null && c.moveToFirst()) {

            personalInfo.setpFullname(c.getString(c.getColumnIndex(PI_KEY_FULLNAME)));
            personalInfo.setpPhoneNumber(c.getString(c.getColumnIndex(PI_KEY_PHONE)));
            personalInfo.setpAltPhoneNumber(c.getString(c.getColumnIndex(PI_KEY_ALT_PHONE)));
            personalInfo.setpEmail(c.getString(c.getColumnIndex(PI_KEY_EMAIL)));
            personalInfo.setpAltEmail(c.getString(c.getColumnIndex(PI_KEY_ALT_EMAIL)));
            personalInfo.setpHouse(c.getString(c.getColumnIndex(PI_KEY_HOUSE)));
            personalInfo.setpStreet(c.getString(c.getColumnIndex(PI_KEY_STREET)));
            personalInfo.setpAddress(c.getString(c.getColumnIndex(PI_KEY_ADDRESS)));
            personalInfo.setpCountry(c.getString(c.getColumnIndex(PI_KEY_COUNTRY)));
            personalInfo.setpCity(c.getString(c.getColumnIndex(PI_KEY_CITY)));
            personalInfo.setpPincode(c.getString(c.getColumnIndex(PI_KEY_PINCODE)));
            personalInfo.setpPan(c.getString(c.getColumnIndex(PI_KEY_PAN)));
            personalInfo.setpPassport(c.getString(c.getColumnIndex(PI_KEY_PASSPORT)));
            personalInfo.setpDob(c.getString(c.getColumnIndex(PI_KEY_DOB)));
            personalInfo.setpGender(c.getString(c.getColumnIndex(PI_KEY_GENDER)));
            personalInfo.setpMStatus(c.getString(c.getColumnIndex(PI_KEY_MARITAL_STATUS)));
            personalInfo.setCid(c.getInt(c.getColumnIndex(KEY_CID)));
            personalInfo.setpImage(c.getBlob(17));

            c.close();
            return personalInfo;
        }
        return null;
    }

    //Add Personal Info
    public long addPersonalInfo(PersonalInfo pi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PI_KEY_FULLNAME, pi.getpFullname());
        values.put(PI_KEY_PHONE, pi.getpPhoneNumber());
        values.put(PI_KEY_ALT_PHONE, pi.getpAltPhoneNumber());
        values.put(PI_KEY_EMAIL, pi.getpEmail());
        values.put(PI_KEY_ALT_EMAIL, pi.getpAltEmail());
        values.put(PI_KEY_HOUSE, pi.getpHouse());
        values.put(PI_KEY_STREET, pi.getpStreet());
        values.put(PI_KEY_ADDRESS, pi.getpAddress());
        values.put(PI_KEY_COUNTRY, pi.getpCountry());
        values.put(PI_KEY_CITY, pi.getpCity());
        values.put(PI_KEY_PINCODE, pi.getpPincode());
        values.put(PI_KEY_PAN, pi.getpPan());
        values.put(PI_KEY_PASSPORT, pi.getpPassport());
        values.put(PI_KEY_DOB, pi.getpDob());
        values.put(PI_KEY_GENDER, pi.getpGender());
        values.put(PI_KEY_MARITAL_STATUS, pi.getpMStatus());
        values.put(PI_KEY_PHOTO, pi.getpImage());
        values.put(KEY_CID, pi.getCid());

        // Inserting Row
        long n = db.insert(TABLE_PERSONAL_INFO, null, values);
        //db.close(); // Closing database connection
        return n;
    }

    //Update Personal Info
    public long updatePersonalInfo(PersonalInfo pi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PI_KEY_FULLNAME, pi.getpFullname());
        values.put(PI_KEY_PHONE, pi.getpPhoneNumber());
        values.put(PI_KEY_ALT_PHONE, pi.getpAltPhoneNumber());
        values.put(PI_KEY_EMAIL, pi.getpEmail());
        values.put(PI_KEY_ALT_EMAIL, pi.getpAltEmail());
        values.put(PI_KEY_HOUSE, pi.getpHouse());
        values.put(PI_KEY_STREET, pi.getpStreet());
        values.put(PI_KEY_ADDRESS, pi.getpAddress());
        values.put(PI_KEY_COUNTRY, pi.getpCountry());
        values.put(PI_KEY_CITY, pi.getpCity());
        values.put(PI_KEY_PINCODE, pi.getpPincode());
        values.put(PI_KEY_PAN, pi.getpPan());
        values.put(PI_KEY_PASSPORT, pi.getpPassport());
        values.put(PI_KEY_DOB, pi.getpDob());
        values.put(PI_KEY_GENDER, pi.getpGender());
        values.put(PI_KEY_MARITAL_STATUS, pi.getpMStatus());
        values.put(PI_KEY_PHOTO, pi.getpImage());

        // Inserting Row
        long n = db.update(TABLE_PERSONAL_INFO, values, "cid=" + pi.getCid(), null);
        ;
        //db.close(); // Closing database connection
        return n;
    }

    // Getting single contact
    public String getContact() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select *from personal_info where pid = 1 ", null);

        String name = cursor.getString(1);


        return name;


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


    // Adding Career Objective
    public long addCO(CareerObjective co) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(CO_KEY_CID, co.getCid());
        values.put(CO_KEY_CO, co.getCo());

        // Inserting Row
        long n = db.insert(TABLE_CAREER_OBJ, null, values);
        //db.close(); // Closing database connection
        return n;
    }
    //Adding Work Experience
    public long addWorkExperience(WorkExperience we){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EX_JOBTITLE, we.getexJobtitle());
        values.put(EX_JOBDESCRIPTION, we.getexJobdescription());
        values.put(EX_COMPANYNAME, we.getexCompanyname());
        values.put(EX_STARTDATE, we.getexStartdate());
        values.put(EX_ENDDATE, we.getexEnddate());
        long n = db.insert(TABLE_WORK_EXPERIENCE, null, values);
        return n;

    }

    //Update Career Objective
    public long updateCO(CareerObjective co) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CO_KEY_CID, co.getCid());
        values.put(CO_KEY_CO, co.getCo());

        // Inserting Row
        long n = db.update(TABLE_CAREER_OBJ, values, "cid=" + co.getCid(), null);
        ;
        //db.close(); // Closing database connection
        return n;
    }

    public CareerObjective getSingleCO(int cid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CAREER_OBJ + " WHERE "
                + KEY_CID + " = " + cid;

        Cursor c = db.rawQuery(selectQuery, null);
        CareerObjective careerObjective = new CareerObjective();
        if (c != null && c.moveToFirst()) {

            careerObjective.setCid(c.getInt(c.getColumnIndex(CO_KEY_CID)));
            careerObjective.setCo(c.getString(c.getColumnIndex(CO_KEY_CO)));


            c.close();
            return careerObjective;
        }
        return null;
    }

    //Add Academic Info
    public long addAcademicInfo(AcademicInfo ai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = aiContentValues(ai);
        // Inserting Row
        long n = db.insert(TABLE_ACADEMIC_INFO, null, cv);
        //db.close(); // Closing database connection
        return n;
    }

    public ContentValues aiContentValues(AcademicInfo ai){
        ContentValues values = new ContentValues();
        values.put(AI_KEY_CID, ai.getCid());
        values.put(AI_KEY_PGNAME, ai.getPgname());
        values.put(AI_KEY_PGYEAR, ai.getPgyear());
        values.put(AI_KEY_PGPERCENTAGE, ai.getPgpercentage());
        values.put(AI_KEY_GNAME, ai.getGname());
        values.put(AI_KEY_GYEAR, ai.getGyear());
        values.put(AI_KEY_GPERCENTAGE, ai.getGpercentage());
        values.put(AI_KEY_CNAME, ai.getCname());
        values.put(AI_KEY_CYEAR, ai.getCyear());
        values.put(AI_KEY_CPERCENTAGE, ai.getCpercentage());
        values.put(AI_KEY_SNAME, ai.getSname());
        values.put(AI_KEY_SYEAR, ai.getSyear());
        values.put(AI_KEY_SPERCENTAGE, ai.getSpercentage());
        return values;
    }

    //Update Personal Info
    public long updateAcademicInfo(AcademicInfo ai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = aiContentValues(ai);
        // Inserting Row
        long n = db.update(TABLE_PERSONAL_INFO, cv, "cid=" + ai.getCid(), null);

        //db.close(); // Closing database connection
        return n;
    }

    //getting single record of Academic info
    public AcademicInfo getSingleAI(int cid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ACADEMIC_INFO + " WHERE "
                + AI_KEY_CID + " = " + cid;

        Cursor c = db.rawQuery(selectQuery, null);
        AcademicInfo academicInfo = new AcademicInfo();
        if (c != null && c.moveToFirst()) {

            academicInfo.setCid(c.getInt(c.getColumnIndex(AI_KEY_CID)));
            academicInfo.setPgname(c.getString(c.getColumnIndex(AI_KEY_PGNAME)));
            academicInfo.setPgyear(c.getString(c.getColumnIndex(AI_KEY_PGYEAR)));
            academicInfo.setPgpercentage(c.getString(c.getColumnIndex(AI_KEY_PGPERCENTAGE)));
            academicInfo.setGname(c.getString(c.getColumnIndex(AI_KEY_GNAME)));
            academicInfo.setGyear(c.getString(c.getColumnIndex(AI_KEY_GYEAR)));
            academicInfo.setGpercentage(c.getString(c.getColumnIndex(AI_KEY_GPERCENTAGE)));
            academicInfo.setCname(c.getString(c.getColumnIndex(AI_KEY_CNAME)));
            academicInfo.setCyear(c.getString(c.getColumnIndex(AI_KEY_CYEAR)));
            academicInfo.setCpercentage(c.getString(c.getColumnIndex(AI_KEY_CPERCENTAGE)));
            academicInfo.setSname(c.getString(c.getColumnIndex(AI_KEY_SNAME)));
            academicInfo.setSyear(c.getString(c.getColumnIndex(AI_KEY_SYEAR)));
            academicInfo.setSpercentage(c.getString(c.getColumnIndex(AI_KEY_SPERCENTAGE)));

            c.close();
            return academicInfo;
        }
        return null;
    }

}
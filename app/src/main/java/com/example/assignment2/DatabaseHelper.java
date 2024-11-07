package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Here we define the database name and version constants
    private static final String DATABASE_NAME = "locationFinder.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_NAME = "location_table";
    private static final String COL_ID = "id";
    private static final String COL_ADDRESS = "address";
    private static final String COL_LATITUDE = "latitude";
    private static final String COL_LONGITUDE = "longitude";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the database table when the app is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create the location table
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ADDRESS + " TEXT, " +
                COL_LATITUDE + " REAL, " +
                COL_LONGITUDE + " REAL)";
        db.execSQL(createTable);

        // Pre-populate the table with sample locations
        insertSampleLocations(db);
    }

    // This method is to insert sample locations into the database
    private void insertSampleLocations(SQLiteDatabase db) {
        // Sample data for 100 locations (address, latitude, longitude)
        String[][] locations = {
                {"Oshawa", "43.8976", "-78.8673"},
                {"Whitby", "43.8998", "-78.9401"},
                {"Ajax", "43.8500", "-79.0205"},
                {"Pickering", "43.8505", "-79.0832"},
                {"Scarborough", "43.7705", "-79.2578"},
                {"Downtown Toronto", "43.65107", "-79.347015"},
                {"Downtown Toronto", "43.65107", "-79.347015"},
                {"Mississauga", "43.5890", "-79.6441"},
                {"Brampton", "43.7315", "-79.7626"},
                {"Markham", "43.8668", "-79.2666"},
                {"Vaughan", "43.8395", "-79.5084"},
                {"Richmond Hill", "43.8777", "-79.4367"},
                {"North York", "43.7547", "-79.4497"},
                {"Etobicoke", "43.5890", "-79.5784"},
                {"York", "43.6536", "-79.4185"},
                {"East York", "43.6833", "-79.3155"},
                {"Toronto Islands", "43.6167", "-79.3833"},
                {"Scarborough Village", "43.7404", "-79.2249"},
                {"Weston", "43.7012", "-79.5262"},
                {"Leaside", "43.7085", "-79.3620"},
                {"High Park", "43.6448", "-79.4636"},
                {"Cabbagetown", "43.6640", "-79.3687"},
                {"The Beaches", "43.6667", "-79.2912"},
                {"Bloor West Village", "43.6600", "-79.4859"},
                {"Queen Street West", "43.6515", "-79.4094"},
                {"Little Italy", "43.6548", "-79.4248"},
                {"Chinatown", "43.6533", "-79.3981"},
                {"Kensington Market", "43.6547", "-79.4015"},
                {"The Annex", "43.6696", "-79.4047"},
                {"Rosedale", "43.6785", "-79.3764"},
                {"Forest Hill", "43.7044", "-79.4095"},
                {"Danforth", "43.6875", "-79.3200"},
                {"St. Lawrence Market", "43.6510", "-79.3718"},
                {"Bayview Village", "43.7773", "-79.3844"},
                {"Don Mills", "43.7731", "-79.3410"},
                {"Willowdale", "43.7569", "-79.4096"},
                {"The Junction", "43.6767", "-79.4649"},
                {"Mount Pleasant", "43.7015", "-79.3832"},
                {"Bloor-Yorkville", "43.6701", "-79.3897"},
                {"King's Club", "43.6447", "-79.4094"},
                {"Liberty Village", "43.6363", "-79.4200"},
                {"St. James Town", "43.6666", "-79.3739"},
                {"Corso Italia", "43.6883", "-79.4386"},
                {"Cedarvale", "43.6943", "-79.4322"},
                {"Wexford", "43.7559", "-79.2817"},
                {"Bayview Village", "43.7773", "-79.3844"},
                {"Eglinton West", "43.7114", "-79.4329"},
                {"Parkdale", "43.6411", "-79.4632"},
                {"Yorkville", "43.6701", "-79.3897"},
                {"York Mills", "43.7663", "-79.3801"},
                {"Richmond Hill", "43.8777", "-79.4367"},
                {"Aurora", "44.0000", "-79.4667"},
                {"Georgina", "44.3083", "-79.4539"},
                {"Markham", "43.8668", "-79.2666"},
                {"Thornhill", "43.8045", "-79.3880"},
                {"Oakville", "43.4674", "-79.6877"},
                {"Burlington", "43.3250", "-79.7990"},
                {"Milton", "43.5247", "-79.9023"},
                {"Halton Hills", "43.5240", "-79.8534"},
                {"Caledon", "43.8587", "-79.8839"},
                {"Woodbridge", "43.8595", "-79.5935"},
                {"Etobicoke", "43.5890", "-79.5784"},
                {"Humber Bay", "43.6210", "-79.5107"},
                {"Newmarket", "44.0501", "-79.4628"},
                {"Stouffville", "44.0958", "-79.2470"},
                {"East Gwillimbury", "44.1424", "-79.3748"},
                {"King City", "44.0085", "-79.5677"},
                {"Caledon East", "43.8240", "-79.7660"},
                {"Richmond Hill", "43.8777", "-79.4367"},
                {"Vaughan", "43.8395", "-79.5084"},
                {"Aurora", "44.0000", "-79.4667"},
                {"Bolton", "43.8836", "-79.7204"},
                {"Guelph", "43.5330", "-80.2495"},
                {"Cambridge", "43.1896", "-80.3582"},
                {"Waterloo", "43.4665", "-80.5164"},
                {"Kitchener", "43.4516", "-80.4925"},
                {"Barrie", "44.3894", "-79.6903"},
                {"Orillia", "44.6074", "-79.4160"},
                {"Collingwood", "44.4999", "-80.2148"},
                {"Wasaga Beach", "44.4973", "-81.4975"},
                {"Blue Mountains", "44.5023", "-80.3154"},
                {"Owen Sound", "44.5653", "-81.3743"},
                {"Simcoe", "42.8321", "-79.8762"},
                {"St. Catharines", "43.1596", "-79.2460"},
                {"Welland", "42.9833", "-79.2470"},
                {"Niagara Falls", "43.0896", "-79.0849"},
                {"Grimsby", "43.2006", "-79.5660"},
                {"Brockville", "44.5896", "-75.6823"},
                {"Kingston", "44.2312", "-76.4800"},
                {"Belleville", "44.1689", "-77.3774"},
                {"Peterborough", "44.3021", "-78.3210"},
                {"Cobourg", "43.9606", "-78.1621"},
                {"Port Hope", "43.9484", "-78.3179"},
                {"Trenton", "44.1001", "-77.5785"},
                {"North Bay", "45.9794", "-79.4613"},
                {"Sudbury", "46.4900", "-80.9915"},
                {"Sault Ste. Marie", "46.5219", "-83.7828"}
        };

        // Loop through the locations array and insert each one into the database
        for (String[] location : locations) {
            ContentValues values = new ContentValues();
            values.put(COL_ADDRESS, location[0]);
            values.put(COL_LATITUDE, Double.parseDouble(location[1]));
            values.put(COL_LONGITUDE, Double.parseDouble(location[2]));
            db.insert(TABLE_NAME, null, values);
        }
    }

    // Method to handle upgrading the database (e.g., if schema changes)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add a new location to the database
    public boolean addLocation(String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ADDRESS, address);
        values.put(COL_LATITUDE, latitude);
        values.put(COL_LONGITUDE, longitude);
        long result = db.insert(TABLE_NAME, null, values);

        // Log success or failure of the insert
        if (result == -1) {
            Log.e("DatabaseHelper", "Failed to insert location: " + address);
            return false;
        } else {
            Log.d("DatabaseHelper", "Successfully inserted location: " + address);
            return true;
        }
    }

    //Here we make our query case insensitive (In this way using upper case or lower case letters will not affect the result)
    public Cursor getLocationByAddress(String address) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Convert address to lowercase for case-insensitive comparison
        address = address.toLowerCase();
        return db.query(TABLE_NAME, new String[]{COL_LATITUDE, COL_LONGITUDE},
                "LOWER(" + COL_ADDRESS + ")=?", new String[]{address.toLowerCase()}, null, null, null);
    }

    // Method to update an existing location's latitude and longitude
    public boolean updateLocation(String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LATITUDE, latitude);
        values.put(COL_LONGITUDE, longitude);
        return db.update(TABLE_NAME, values, COL_ADDRESS + "=?", new String[]{address}) > 0;
    }

    // Method to delete a location by address
    public boolean deleteLocation(String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ADDRESS + "=?", new String[]{address}) > 0;
    }
}

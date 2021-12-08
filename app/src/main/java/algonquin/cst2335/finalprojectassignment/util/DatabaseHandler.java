package algonquin.cst2335.finalprojectassignment.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.finalprojectassignment.model.Photo;

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Favourite_data.db";
    // table name
    private static final String TABLE_NAME = "Favourites";
    // Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_PHOTO = "photo";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        KEY_ID + " INTEGER PRIMARY KEY , " +
                        KEY_PHOTO + " TEXT);";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);

    }

    //Insert values to the table
    public boolean addToFavourite(Photo photo) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, photo.getId());
        values.put(KEY_PHOTO, new Gson().toJson(photo));
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public List<Photo> getAllFavourites() {
        List<Photo> favouriteList = new ArrayList<Photo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Photo photo = new Gson().fromJson(cursor.getString(1),Photo.class);
                favouriteList.add(photo);
            } while (cursor.moveToNext());
        }
        return favouriteList;
    }

    public  Photo getFavourite(long _id) {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + _id;;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Photo photo = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                  photo = new Gson().fromJson(cursor.getString(1),Photo.class);
            } while (cursor.moveToNext());
        }
        return photo;
    }

    public boolean removeFromFavourite(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, KEY_ID + "=" + _id, null) > 0;
    }
}

package algonquin.cst2335.finalprojectassignment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CovidDatabaseHelper extends SQLiteOpenHelper {

    private static final String DatabaseName = "database_name";

    private static final String TableName = "table_name";

    CovidDatabaseHelper(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + TableName + "(id INTEGER PRIMARY KEY, txt TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public void addData(CasesHolder holder) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Gson gson = new Gson();
        String s = gson.toJson(holder);
        contentValues.put("txt", s);
        sqLiteDatabase.insert(TableName, null, contentValues);
        sqLiteDatabase.close();
    }


    public void removeData(CasesHolder holder){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Gson gson = new Gson();
        String s = gson.toJson(holder);

        sqLiteDatabase.delete(TableName, "txt" + "=?",new String[]{s});

    }
    @SuppressLint("Range")
    public boolean getRow(CasesHolder holder){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Gson gson = new Gson();
        String s = gson.toJson(holder);

        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TableName + " WHERE " + "txt" + "=?", new String[]{s});
        String txt="";
        if (cursor.moveToNext()){
            txt = cursor.getString(cursor.getColumnIndex("txt"));
        }
        cursor.close();


        return s.equals(txt);
    }

    int point = 0;

    @SuppressLint("Range")
    public ArrayList<CasesHolder> getAllData() {
        point = 0;
        ArrayList<CasesHolder> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TableName, null);
        if (cursor.moveToNext()) {
            while (!cursor.isAfterLast()) {
                Gson gson = new Gson();

                CasesHolder casesHolder = gson.fromJson(cursor.getString(cursor.getColumnIndex("txt")), CasesHolder.class);
                list.add(casesHolder);

                point++;
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

}


package org.techtown.betweenus_android.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class QR extends SQLiteOpenHelper {

    CurrentUser user;
    Context context;

    public QR(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        user = new CurrentUser(context,name,factory,version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String url) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("CREATE TABLE QR (idx Integer PRIMARY KEY, url TEXT, currentUser TEXT);");
        Integer idx = Integer.parseInt(url.split("/")[4]);
        db.execSQL("INSERT INTO QR VALUES(" + idx + "  ,  '" + url + "'  ,  '" +  user.getResult().getId() + "');");
        db.close();
    }

    public void delete(String url) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM QR WHERE url='" + url + "';");
        db.close();
    }

    public String getResult(Integer idx) {

        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM QR WHERE idx="+idx+" AND currentUser='"+user.getResult().getId()+"'", null);
            while (cursor.moveToNext()) {
                result = cursor.getString(cursor.getColumnIndex("url"));
            }
        }
        catch (Exception exception) {
        }

        return result;
    }
}


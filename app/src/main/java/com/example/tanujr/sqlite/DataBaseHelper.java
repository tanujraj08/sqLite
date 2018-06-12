package com.example.tanujr.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable=" CREATE TABLE "+ Constants.TABLE_NAME + "("
        +Constants.ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
        +Constants.BOOK_ID +" TEXT, "
        +Constants.BOOK_NAME +"TEXT, "
        +Constants.AUTHOR_NAME + "TEXT) ";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int getRecordCount() {
        int row_count = 0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(false, Constants.TABLE_NAME, null, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            row_count = cursor.getCount();
        }
        return row_count;

        }

    public void insertRecordsInDb(ContentValues contentValues) {
        try{
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.insert(Constants.TABLE_NAME,null,contentValues);

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public ArrayList<Book_Model> getAllBook() {
        ArrayList<Book_Model>arrayList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String query="select * from "+Constants.TABLE_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        if((cursor!=null)&& cursor.moveToFirst()){
            //cursor.moveToFirst();
            do {
                Book_Model bookmodel=new Book_Model();
                bookmodel.setId(cursor.getInt(0));
                bookmodel.setBook_id(cursor.getString(1));
                bookmodel.setBook_name(cursor.getString(2));
                bookmodel.setAuthor_name(cursor.getString(3));
                arrayList.add(bookmodel);
            }while(cursor.moveToNext());
            //cursor.close();
        }
        return arrayList;
    }
}



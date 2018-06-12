package com.example.tanujr.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DataBaseHelper mDataBaseHelper;
    String[] book_names = new String[]{"abc", "def", "ghi", "jkl", "mno"};
    String[] author_names = new String[]{"tanuj", "yash", "ankur", "Swetabh", "praneet"};
    String[] book_ids = new String[]{"101", "102", "103", "104", "105"};
    private ListView mListView;
    private ArrayList<Book_Model>modelArrayList;
    private ArrayList<String> mAuthorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBaseHelper = new DataBaseHelper(MainActivity.this, Constants.TABLE_NAME, null, Constants.DataBase_Version);
        mListView = findViewById(R.id.listview);
        modelArrayList=new ArrayList<>();
        mAuthorName=new ArrayList<>();

        int no_of_records = mDataBaseHelper.getRecordCount();

        if (no_of_records == 0) {
            insertRecordsInDb();
        }
        getRecordsFromDb();
        for(int i=0;i<modelArrayList.size();i++)
            mAuthorName.add(modelArrayList.get(i).getAuthor_name());
        ArrayAdapter<String>arrayAdapter= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, mAuthorName);
        mListView.setAdapter(arrayAdapter);




    }

    private void getRecordsFromDb() {
        modelArrayList=mDataBaseHelper.getAllBook();
    }

    private void insertRecordsInDb() {
        for (int i = 0; i < book_names.length; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.BOOK_NAME, book_names[i]);
            contentValues.put(Constants.AUTHOR_NAME, author_names[i]);
            contentValues.put(Constants.BOOK_ID, book_ids[i]);

            mDataBaseHelper.insertRecordsInDb(contentValues);
        }


    }

}

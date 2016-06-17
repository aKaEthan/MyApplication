package com.example.lihao.myapplication;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mainac extends AppCompatActivity {

    public final static String TABLE_NAME_ENTRIES = "com.mycompany.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainac);
    }
    public void newsqlc(View view) {
        String result = "";
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "TEST");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT, "WER BIST DU");


// Insert the new row, returning the primary key value of the new row
        db.insert(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID,
                values);

        Cursor cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, null, null, null, null, null, FeedReaderContract.FeedEntry._ID+" asc");

        //获取id列的索引
        int idIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry._ID);
        //获取title列的索引
        int titleIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE);
        //获取level列的索引
        int contIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT);
        for (cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()) {
            result = result + cursor.getString(idIndex)+ "\t\t";
            result = result + cursor.getString(titleIndex)+ "\t\t";
            result = result + cursor.getString(contIndex)+"       \n";
        }

        new AlertDialog.Builder(this)
                .setTitle("Alerting Message")
                .setMessage(result)
                .setNegativeButton("OKi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing - it will close on its own
                    }
                })
                .show();

    }

    public void smes(View view){
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.onUpgrade(db,1,2);

        new AlertDialog.Builder(this)
                .setTitle("Alerting Message")
                .setMessage("Clear succcess!")
                .setNegativeButton("OKi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mainac.this, MainActivity.class);
                        startActivity(intent);
                        //do nothing - it will close on its own
                    }
                })
                .show();


    }
}

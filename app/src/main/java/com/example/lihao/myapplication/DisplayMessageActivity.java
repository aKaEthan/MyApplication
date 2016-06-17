package com.example.lihao.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // Get the message from the intent
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);



        //Datenbank Class
        String result = "";
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
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
        String message =result;





        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);


        // Set the text view as the activity layout
        setContentView(textView);
    }

    public void backb(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}

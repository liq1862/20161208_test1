package com.example.user.a20161208_test1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String DB_FILE = getFilesDir() + File.separator + "student.sqlite";
            InputStream is = MainActivity.this.getAssets().open("student.sqlite");
            OutputStream os = new FileOutputStream(DB_FILE);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0)
            {
                os.write(buffer, 0, length);
            }
            os.flush();
            os.close();
            is.close();

            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_FILE, null);
            Cursor c = db.rawQuery("Select * from phone", null);
            c.moveToFirst();
            String str = c.getString(1);
            Log.d("DB", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
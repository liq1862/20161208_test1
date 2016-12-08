package com.example.user.a20161208_test1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    final String DB_NAME = "student.sqlite";
    String DB_FILE;
    EditText ed1, ed2, ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_FILE = getFilesDir() + File.separator + DB_NAME;
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);

        copyDataBaseFile();
    }

    public void clickAdd(View v)
    {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_FILE, null);
        String SName = ed1.getText().toString();
        String tel = ed2.getText().toString();
        String addr = ed3.getText().toString();
        String sql = "insert into phone (SName, tel, addr) values ('" + SName + "','" + tel + "','" + addr + "')";
        db.execSQL(sql);
        db.close();
    }

    public void clickList(View v)
    {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_FILE, null);
        Cursor c = db.rawQuery("Select * from phone", null);
        if (c.moveToFirst())
        {
            Log.d("DB", c.getString(1));
        }

        while(c.moveToNext())
        {
            Log.d("DB", c.getString(1));
        }
        c.close();
        db.close();
    }

    private void copyDataBaseFile()
    {
        File f = new File(DB_FILE);

        if (!f.exists())
        {
            InputStream is = null;
            try {
                is = MainActivity.this.getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(f);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0)
                {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

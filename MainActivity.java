package com.example.pro2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    MyDBHandler dbHandler;
    private SQLiteDatabase db;
    EditText Password;
    EditText name;
    Button enter;
    static String user, pa;
    Cursor cursor;
    String TempPassword = "NOT_FOUND";
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new MyDBHandler(this);
        db = dbHandler.getWritableDatabase();
        TextView txreg = (TextView) findViewById(R.id.rg);
        Password = (EditText) findViewById(R.id.etpas);
        name = (EditText) findViewById(R.id.etName);
        Button enter = (Button) findViewById(R.id.log);


    }


    public void reg(View v) {

        Intent i = new Intent(getApplicationContext(), insTsk.class);
        startActivity(i);
        dbHandler.close();
        finish();
    }


    public void btnShwDbData(View v) {
        // To store table info
        String dbData = "";

        String query = "SELECT * FROM " + dbHandler.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            dbData += c.getString(c.getColumnIndex(dbHandler.COLUMN_RECID));
            dbData += " | " + c.getString(c.getColumnIndex(dbHandler.COLUMN_NAME));
            dbData += " | " + c.getString(c.getColumnIndex(dbHandler.COLUMN_Pas));
            dbData += " | " + c.getString(c.getColumnIndex(dbHandler.COLUMN_PHONE));
            dbData += " | " + c.getString(c.getColumnIndex(dbHandler.COLUMN_EMAIL));
            dbData += " | " + c.getString(c.getColumnIndex(dbHandler.COLUMN_CREDIT));
            dbData += "\n";

            c.moveToNext();
        }

        c.close();
        Toast.makeText(getApplicationContext(), dbData, Toast.LENGTH_LONG).show();

    }


    public void log(View view) {
        EditText usr = (EditText) findViewById(R.id.etName);
        EditText PassInput = (EditText) findViewById(R.id.etpas);

        user = usr.getText().toString();
        pa = PassInput.getText().toString();

        SharedPreferences userAccounts = getSharedPreferences(" UserAccounts", 0);
        if (!userAccounts.contains(user)) {
            Toast.makeText(getApplicationContext(), "  The user account is not existent", Toast.LENGTH_LONG).show();
            return;
        }

        if ((userAccounts.getString(user, null)).equals(pa)) {
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();

            enter = (Button) findViewById(R.id.log);
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            intent.putExtra("message", user);
            startActivity(intent);



        } else
            Toast.makeText(getApplicationContext(), "  The password entered is wrong", Toast.LENGTH_LONG).show();
    }





}

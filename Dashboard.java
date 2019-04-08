package com.example.pro2;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static java.nio.file.Paths.get;

public class Dashboard extends MainActivity{
    MyDBHandler dbHandler;
    private TextView etName, etPhone , etPass , etEmail , etCredit ;
    private EditText M ,etID;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        etID = (EditText) findViewById(R.id.idInp);
        name = (EditText) findViewById(R.id.etName);
        etName = (TextView) findViewById(R.id.edName);
        etPhone = (TextView) findViewById(R.id.edPhone);
        etPass = (TextView) findViewById(R.id.edPas);
        etEmail = (TextView) findViewById(R.id.edEmil);
        etCredit = (TextView) findViewById(R.id.edCredit);


        dbHandler = new MyDBHandler(getApplicationContext());
        db = dbHandler.getWritableDatabase();//getReadableDatabase



    }

    public void shwIDInfo(View v){
        String id = etID.getText().toString();




        String sqlStmt = "SELECT * FROM "+ dbHandler.TABLE_NAME
                + " where " + dbHandler.COLUMN_RECID + " = ?";

        Cursor c = db.rawQuery(sqlStmt, new String[] {id});


        if(!c.moveToFirst()) {
            Toast.makeText(getApplicationContext(), "No ID has matched ", Toast.LENGTH_LONG).show();
            return; }


        etName.setText(c.getString(1));
        etPhone.setText(c.getString(5));
        etEmail.setText(c.getString(3));



        c.close(); }




    public void delTsk(View v){

        String id = etID.getText().toString();

        if ( id.isEmpty()  ) {
            Toast.makeText(getApplicationContext(), "PLease Fill missing data ", Toast.LENGTH_LONG).show();
            return; }

        // HomeWork



        Cursor c=db.rawQuery("SELECT * FROM "+dbHandler.TABLE_NAME+
                " where " +dbHandler.COLUMN_RECID+" = ? ",new String[] {id});
        if(c.moveToFirst()) {

            db.execSQL(" delete from " + dbHandler.TABLE_NAME +
                    " where " + dbHandler.COLUMN_RECID + " = ? ", new String[]{id});

            String tstMsg;
            tstMsg = " id: " + id + "  is deleted  ";

            Toast.makeText(getApplicationContext(), tstMsg, Toast.LENGTH_LONG).show();
            etID.setText(" ");
        }
        else {
            Toast.makeText(getApplicationContext()," Invalid ID ",Toast.LENGTH_LONG )
                    .show();
        }



    }


    public void out(View v) {

        Intent i = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(i);
        dbHandler.close();
        finish();
    }



}
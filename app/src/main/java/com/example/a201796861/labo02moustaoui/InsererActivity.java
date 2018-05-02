package com.example.a201796861.labo02moustaoui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

/**
 * Created by 201796861 on 18-03-16.
 */

public class InsererActivity extends AppCompatActivity {


     EditText nomTxt, prenomTxt, telephoneTxt;
     Button inserer ;

    protected EditText searchText;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserer);

        db = (new DatabaseHelper(this)).getWritableDatabase();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        nomTxt = findViewById(R.id.txtNom);
        prenomTxt = findViewById(R.id.txtPrenom);
        telephoneTxt = findViewById(R.id.txtTelephone);
        inserer = findViewById(R.id.btnInsererData);

        inserer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                values.put("nom", nomTxt.getText().toString());
                values.put("prenom", prenomTxt.getText().toString());
                values.put("telephone", telephoneTxt.getText().toString());
                db.insert("contacts", null,values);

            }
        });
}

    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

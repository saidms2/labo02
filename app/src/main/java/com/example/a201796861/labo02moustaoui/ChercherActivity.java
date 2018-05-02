package com.example.a201796861.labo02moustaoui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by 201796861 on 18-03-20.
 */

public class ChercherActivity extends AppCompatActivity{


    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected SimpleCursorAdapter adapter;

    protected int contactId;
    ListView resultat;
    EditText textid;
    Button chercher;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //employeeId = getIntent().getIntExtra("EMPLOYEE_ID", 0);

        resultat = findViewById(R.id.listChercher);
        chercher = findViewById(R.id.searchButton);
        textid = findViewById(R.id.searchText);


        //employeeId = Integer.parseInt(textid.toString());
        chercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                contactId = Integer.parseInt(textid.getText().toString());
                search(contactId);

            }
        });

    }


    public void search(int id) {


        db = (new DatabaseHelper(this)).getWritableDatabase();

        cursor = db.rawQuery("SELECT * FROM contacts as cont WHERE cont._id = ?",
        new String[]{""+id});

        String[] from = {"nom", "prenom", "telephone"};
        int[] to = {R.id.textNom, R.id.textPrenom, R.id.textTelephone};

        adapter = new SimpleCursorAdapter(this, R.layout.affichage_object, cursor, from, to, 0);

        resultat.setAdapter(adapter);

    }

    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}

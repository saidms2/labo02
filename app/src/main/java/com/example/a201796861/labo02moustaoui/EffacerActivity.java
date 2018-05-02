package com.example.a201796861.labo02moustaoui;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by 201796861 on 18-03-21.
 */

public class EffacerActivity extends AppCompatActivity {


    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected SimpleCursorAdapter adapter;

    protected int contactId;
    ListView resultat;
    EditText textid;
    Button effacer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effacer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //resultat = findViewById(R.id.listChercher);
        effacer = findViewById(R.id.effacerButton);
        textid = findViewById(R.id.searchTextEffacer);


        //employeeId = Integer.parseInt(textid.toString());
        effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                contactId = Integer.parseInt(textid.getText().toString().trim());


                db = (new DatabaseHelper(EffacerActivity.this)).getWritableDatabase();

                //Cursor mCursor = db.rawQuery("select * from contacts WHERE id=" + contactId + ";", null);


                cursor = db.rawQuery("SELECT * FROM contacts as cont WHERE cont._id = ?", new String[]{""+contactId});

                String nom = "";
                String prenom= "";


                if (cursor != null) {

                    if (cursor.moveToFirst()) {

                         nom = cursor.getString(cursor.getColumnIndex("nom"));
                         prenom = cursor.getString(cursor.getColumnIndex("prenom"));
                    }
                }


                AlertDialog.Builder builder = new AlertDialog.Builder(EffacerActivity.this);
                builder.setTitle("AlertDialog");
                builder.setMessage("Voulez vous supprimer le contacte "+nom+" "+prenom);

                // add the buttons
                builder.setPositiveButton("Effacer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EffacerActivity.this,
                                "confirmer effacer", Toast.LENGTH_LONG).show();

                        deleteData(contactId);

                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EffacerActivity.this,
                                "annuler effacer", Toast.LENGTH_LONG).show();

                    }
                });

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

    }


    public void deleteData(int id){

        db.delete("contacts", "_id=?", new String[]{Integer.toString(id)});

    }

    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

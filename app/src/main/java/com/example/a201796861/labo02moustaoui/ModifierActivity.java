package com.example.a201796861.labo02moustaoui;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.widget.Toast;

/**
 * Created by admin on 2018-03-22.
 */

public class ModifierActivity extends AppCompatActivity {

    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected SimpleCursorAdapter adapter;

    protected int contactId;
    ListView resultat;
    EditText textid;
    Button modifier;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //employeeId = getIntent().getIntExtra("EMPLOYEE_ID", 0);

        //resultat = findViewById(R.id.listChercher);
        modifier = findViewById(R.id.modifierButton);
        textid = findViewById(R.id.searchTextModifier);

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                contactId = Integer.parseInt(textid.getText().toString().trim());

                db = (new DatabaseHelper(ModifierActivity.this)).getWritableDatabase();

                cursor = db.rawQuery("SELECT * FROM contacts as cont WHERE cont._id = ?", new String[]{""+contactId});

                String nom = "";
                String prenom= "";
                String telephone= "";


                if (cursor != null) {

                    if (cursor.moveToFirst()) {

                        nom = cursor.getString(cursor.getColumnIndex("nom"));
                        prenom = cursor.getString(cursor.getColumnIndex("prenom"));
                        telephone = cursor.getString(cursor.getColumnIndex("telephone"));
                    }
                }


                AlertDialog.Builder alert = new AlertDialog.Builder(ModifierActivity.this);
                alert.setTitle("Modifier Le téléphone");
                alert.setMessage("Nom :" + nom+"\nPrenom : "+prenom+"\nTéléphone : "+telephone+"\nEntrer le nouveau numéro");

                final EditText input = new EditText(ModifierActivity.this);
                alert.setView(input);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String nvTelephone = input.getEditableText().toString();
                        //changer le numero

                        ContentValues values = new ContentValues();
                        values.put("telephone", nvTelephone);
                        db.update("contacts", values, "contacts._id" + "= ?", new String[] {String.valueOf(contactId)});
                        Toast.makeText(ModifierActivity.this,nvTelephone, Toast.LENGTH_LONG).show();

                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                }); //End of alert.setNegativeButton
                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });



    }

    public void mofidier(int id, String valeur) {


        db = (new DatabaseHelper(this)).getWritableDatabase();

        //cursor = db.rawQuery("SELECT * from contacts", null);

        //cursor = db.rawQuery("UPDATE contacts SET telephone = "+valeur+" WHERE columnId = "+ id);



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



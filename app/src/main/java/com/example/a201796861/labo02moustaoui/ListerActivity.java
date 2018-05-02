package com.example.a201796861.labo02moustaoui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 201796861 on 18-03-16.
 */

public class ListerActivity extends AppCompatActivity {

    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected SimpleCursorAdapter adapter;
    protected ListView listResultat;


    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> listItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister);

        listResultat = findViewById(R.id.listerRsltListe);

        //listItem = new ArrayList<HashMap<String, String>>();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        search();

        listResultat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView imageTel = (ImageView)view.findViewById(R.id.brtnTeleLister);
                final String nom = cursor.getString(cursor.getColumnIndex("nom"));
                final String prenom = cursor.getString(cursor.getColumnIndex("prenom"));
                final String telephone = cursor.getString(cursor.getColumnIndex("telephone"));

                imageTel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(ListerActivity.this,
                                nom+prenom+telephone, Toast.LENGTH_LONG).show();


                        Context context = ListerActivity.this;
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+telephone));

                        //Intent intent = new Intent(Intent.ACTION_CALL,  Uri.parse("tel:" + "1324567890"));
                        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(context, "permission not granted", Toast.LENGTH_SHORT).show();
                            ActivityCompat.requestPermissions(ListerActivity.this, new String[]{Manifest.permission.CALL_PHONE},143);
                        }else{
                            context.startActivity(callIntent);
                        }

                    }
                });

            }
        });

    }


public void search() {

        //cursor = db.rawQuery("select * from contacts", null);
        //cursor = recuperer();
        db = (new DatabaseHelper(this)).getWritableDatabase();

        cursor = db.rawQuery("SELECT * from contacts", null);

        String[] from = {"nom", "prenom", "telephone"};
        int[] to = {R.id.textNom, R.id.textPrenom, R.id.textTelephone};

        adapter = new SimpleCursorAdapter(this, R.layout.affichage_object2, cursor, from, to, 0);

        //SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.affichage_object, from, to);

        listResultat.setAdapter(adapter);
        //cursor.close();

    }

    public Cursor recuperer() {

        cursor = db.rawQuery("SELECT * from contacts", null);

        return cursor;

    }


    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
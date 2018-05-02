package com.example.a201796861.labo02moustaoui;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 201796861 on 18-03-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GestionContacts";
    private static int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DatabaseHelper(Context context)
    { super(context,DATABASE_NAME,null,DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Création des tables
        String sql = "CREATE TABLE IF NOT EXISTS contacts ("
                +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, " +
                "prenom TEXT, " +
                "telephone TEXT)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("nom", "John");
        values.put("prenom", "Smith");
        values.put("telephone", "617-219-2001");
        db.insert("contacts", null,values);

        values.put("nom", "bob");
        values.put("prenom", "pika");
        values.put("telephone", "517-219-2001");
        db.insert("contacts", null,values);

        values.put("nom", "jean");
        values.put("prenom", "talon");
        values.put("telephone", "515-219-2001");
        db.insert("contacts", null,values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);

    }
}

package com.example.a201796861.labo02moustaoui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 201796861 on 18-03-16.
 */

public class Contacte implements Parcelable {

    private int id;
    private String nom, prenom;
    private int telephone;

    public Contacte() {
    }

    public Contacte(int id, String nom, String prenom, int telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    protected Contacte(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        prenom = in.readString();
        telephone = in.readInt();
    }

    public static final Creator<Contacte> CREATOR = new Creator<Contacte>() {
        @Override
        public Contacte createFromParcel(Parcel in) {
            return new Contacte(in);
        }

        @Override
        public Contacte[] newArray(int size) {
            return new Contacte[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeInt(telephone);
    }
}

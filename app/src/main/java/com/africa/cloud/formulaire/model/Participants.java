package com.africa.cloud.formulaire.model;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


public class Participants extends RealmObject {

    @PrimaryKey
    int id;

    public Participants() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Participants(int id, String mNom, String mEmail, String mNumeroTelephone, String mDateNaissance) {
        this.id = id;
        this.mNom = mNom;
        this.mEmail = mEmail;
        this.mNumeroTelephone = mNumeroTelephone;
        this.mDateNaissance = mDateNaissance;
    }

    private
    String mNom;

    private
    String mEmail;

    private
    String mNumeroTelephone;


    private
    String mDateNaissance;


    public String getmNom() {
        return mNom;
    }


    public void setmNom(String mNom) {
        this.mNom = mNom;
    }


    public String getmEmail() {
        return mEmail;
    }


    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }


    public String getmNumeroTelephone() {
        return mNumeroTelephone;
    }


    public void setmNumeroTelephone(String mNumeroTelephone) {
        this.mNumeroTelephone = mNumeroTelephone;
    }


    public String getmDateNaissance() {
        return mDateNaissance;
    }


    public void setmDateNaissance(String mDateNaissance) {
        this.mDateNaissance = mDateNaissance;
    }




}

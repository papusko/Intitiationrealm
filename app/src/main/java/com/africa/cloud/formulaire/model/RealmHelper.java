package com.africa.cloud.formulaire.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmHelper {

    Participants participants = new Participants();
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

//reccperation de la liste
    public List<Participants> reccuperation() {
        Realm realm;
        //configuration du realm dans ma nouvelle activity
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(config);

        List<Participants> participantsList = new ArrayList<>();
        RealmResults<Participants> mParticipants = realm.where(Participants.class).findAll();
        for (Participants x : participantsList) {
            participantsList.add(x);

            System.out.println(participantsList);/*
            x.getmEmail();
            x.getmNumeroTelephone();
            x.getmDateNaissance();*/

        }

        return  mParticipants;
    }


    //Lecture

    public ArrayList<String> retrieve()
    {
        ArrayList<String> participants = new ArrayList<>();
        RealmResults<Participants> participantsRealmResults = realm.where(Participants.class).findAll();

        for (Participants p : participantsRealmResults) {

            participants.add(p.getmNom());
            participants.add(p.getmEmail());
            participants.add(p.getmNumeroTelephone());
            participants.add(p.getmDateNaissance());
            /*
            x.getmEmail();
            x.getmNumeroTelephone();
            x.getmDateNaissance();*/

        }
        return  participants;
    }

public  void update(int id, String mNom, String mEmail, String mNumeroTelephone, String mDateNaissance)
{
    realm.executeTransactionAsync(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {


            RealmConfiguration config = new RealmConfiguration.Builder().build();
            realm = Realm.getInstance(config);

          List<Participants> participantsList = new ArrayList<>();

            RealmResults<Participants> result = realm.where(Participants.class).equalTo("id", participants.id).findAll();

            participants.setId(id);
            participants.setmNom(mNom);
            participants.setmEmail(mEmail);
            participants.setmNumeroTelephone(mNumeroTelephone);
            participants.setmDateNaissance(mDateNaissance);

            realm.copyToRealmOrUpdate(participants);


        }
    }, new Realm.Transaction.OnSuccess() {
        @Override
        public void onSuccess() {
            Log.e("ok", "Modifier avec succès");
        }
    }, new Realm.Transaction.OnError() {
        @Override
        public void onError(Throwable error) {
            error.printStackTrace();
        }
    });
}



}

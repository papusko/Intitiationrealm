package com.africa.cloud.formulaire;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.formulaire.model.Participants;
import com.africa.cloud.formulaire.model.ParticipantsAdapter;
import com.africa.cloud.formulaire.model.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class ModifierActivity extends AppCompatActivity implements View.OnClickListener {


    ParticipantsAdapter participantsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private TextView mEntete;
    private EditText edit_nom;
    private EditText edit_mail;
    private EditText edit_numero_telephone;
    private EditText edit_date_naissance;
    private Button mSave;
    ListView lv;
    int id;
    String mNom;
    String mEmail;
    String mNumeroTelephone;
    String mDateNaissance;

    private TextView log;

    private Realm realm;
    private Participants mParticipants;
    ArrayList<String> participants;
    ArrayAdapter adapter; //une interface commune permettant le passage de données à une interface de sélection
    RealmHelper realmHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier);
        realm.init(this); //initialisation du realm

        RealmConfiguration config =
                new RealmConfiguration.Builder()
                        .name("test.db") //affectation de nom creer par Realm pour stocké les données
                        .schemaVersion(1) //creer une version
                        .deleteRealmIfMigrationNeeded()
                        .build();
        realm = Realm.getInstance(config);

        mEntete = (TextView) findViewById(R.id.activity_main_greeting_txt);
        edit_nom = (EditText) findViewById(R.id.activity_main_nom_input);
        edit_mail = (EditText) findViewById(R.id.activity_main_email_input);
        edit_numero_telephone = (EditText) findViewById(R.id.activity_main_telephone_input);
        edit_date_naissance = (EditText) findViewById(R.id.activity_main_datenaissance_input);
        mSave = (Button) findViewById(R.id.activity_main_play_btn);


    id = Integer.parseInt(getIntent().getStringExtra("id"));
    mNom = getIntent().getStringExtra("mNom");
    mEmail = getIntent().getStringExtra("mEmail");
    mNumeroTelephone = getIntent().getStringExtra("mNumeroTelephone");
    mDateNaissance = getIntent().getStringExtra("mDateNaissance");


        edit_nom.setText(mNom);
        edit_mail.setText(mEmail);
        edit_numero_telephone.setText(mNumeroTelephone);
        edit_date_naissance.setText(mDateNaissance);

        Realm realm;

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmHelper = new RealmHelper(realm);

                realmHelper.update(id, edit_nom.getText().toString(),edit_mail.getText().toString(),edit_numero_telephone.getText().toString(),edit_date_naissance.getText().toString());
                edit_nom.setText(mNom);
                edit_mail.setText(mEmail);
                edit_numero_telephone.setText(mNumeroTelephone);
                edit_date_naissance.setText(mDateNaissance);


                Intent intent = new Intent(ModifierActivity.this, AffichageActivity.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    public void onRestart()
    {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }
}
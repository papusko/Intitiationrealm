package com.africa.cloud.formulaire;


import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {




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

    String mNom;
    String mEmail;
    String mNumeroTelephone;
    String mDateNaissance;

    private TextView log;

    private  Realm realm;
    private Participants mParticipants;
    ArrayList<String> participants;
    ArrayList<Participants> participantsList;
    ArrayAdapter adapter; //une interface commune permettant le passage de données à une interface de sélection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm.init(this); //initialisation du realm



        //configuration du realm qui donne comme nom  de fichier par defaut ("default.realm") nous allons donner un nom et une version a notre realm
        RealmConfiguration config =
                new RealmConfiguration.Builder()
                         .name("test.db") //affectation de nom creer par Realm pour stocké les données
                        .schemaVersion(1) //creer une version
                        .deleteRealmIfMigrationNeeded()
                        .build();
        realm = Realm.getInstance(config);

//Instanciation de la classe helper
        //RealmHelper helper = new RealmHelper(realm);
        //appel de la methode retrieve (la reccuperation
        //Participants = helper.retrieve();

        //Ajout  un tableau ou une instance de java.util.List
      //  adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,Participants);
        //lv.setAdapter(adapter);








        mEntete = (TextView) findViewById(R.id.activity_main_greeting_txt);
        edit_nom = (EditText) findViewById(R.id.activity_main_nom_input);
        edit_mail = (EditText) findViewById(R.id.activity_main_email_input);
        edit_numero_telephone = (EditText) findViewById(R.id.activity_main_telephone_input);
        edit_date_naissance = (EditText) findViewById(R.id.activity_main_datenaissance_input);
        mSave = (Button) findViewById(R.id.activity_main_play_btn);
    //    lv = (ListView) findViewById(R.id.lv);

/*
        //Appelle de la methode retrieve pour la reccuperation
        RealmHelper helper = new RealmHelper(realm);
        participants = helper.retrieve();

        //liasons a un adapter

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,participants);
        lv.setAdapter(adapter);

        //click sur la liste
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,participants.get(position),Toast.LENGTH_SHORT).show();
            }
        });

*/

/*

        recyclerView = findViewById(R.id.myRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(layoutManager);


        List<Participants> participantsList = new ArrayList<>();

       participantsList.add(new Participants(1,"Ibrahima Koné","papuskone43@gmail.com", "77-968-20-02","22/05/2019"));
        participantsList.add(new Participants( 2,"Moustapha Touré","moustapha@gmail.com", "77-968-20-02","22/05/2019"));
        participantsList.add(new Participants( 3,"untel","untel@gmail.com", "77-968-20-02","22/05/2019"));
        participantsList.add(new Participants( 4,"Moussa Coulibaly","moussa43@gmail.com", "77-968-20-02","22/05/2019"));
        participantsList.add(new Participants( 5,"Ibrahima Koné","papuskone43@gmail.com", "77-968-20-02","22/05/2019"));
        participantsList.add(new Participants( 6,"Ibrahima Koné","papuskone43@gmail.com", "77-968-20-02","22/05/2019"));
        participantsList.add(new Participants( 7,"Ibrahima Koné","papuskone43@gmail.com", "77-968-20-02","22/05/2019"));






        ParticipantsAdapter adapter = new ParticipantsAdapter(participantsList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

*/




        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNom = edit_nom.getText().toString();
                mNumeroTelephone = edit_numero_telephone.getText().toString();
                mDateNaissance = edit_date_naissance.getText().toString();
                mEmail = edit_mail.getText().toString();
                 if (mNom.isEmpty() | mDateNaissance.isEmpty() | mEmail.isEmpty() | mNumeroTelephone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }

                else {


                    realm = Realm.getDefaultInstance();


                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {


                            Participants p = new Participants();

                            if (realm != null) {

                                Log.e("ppppppp", "Base de données créer avec succes");
                                //auto incrementation de l'id
                                Number currentIdNum = realm.where(Participants.class).max("id");
                                int nextId;

                                if (currentIdNum==null)
                                {
                                    nextId =1;
                                }else {
                                    nextId = currentIdNum.intValue()+1;
                                }
                                p.setId(nextId);



                                p.setmNom(edit_nom.getText().toString());
                                p.setmEmail(edit_mail.getText().toString());
                                p.setmNumeroTelephone(edit_numero_telephone.getText().toString());
                                p.setmDateNaissance(edit_date_naissance.getText().toString());

                                realm.copyToRealm(p);
                                Intent intent = new Intent(MainActivity.this, AffichageActivity.class);
                                startActivity(intent);


                                //reccuperation

                            } else {
                                Log.e("ppppppp", "Base de données inexistante");
                            }


                        }

                        ;


                    });
                }

            }


        });

    }



  public ArrayList<String> allParticipants()
  {
      ArrayList<String> participants = new ArrayList<>();
      RealmResults<Participants> mParticipants = realm.where(Participants.class).findAll();

      for(Participants p: mParticipants)
      {
          participants.add(p.getmNom());
          participants.add(p.getmEmail());
          participants.add(p.getmNumeroTelephone());
          participants.add(p.getmDateNaissance());

      }
      return  participants;
  }




/*    private void rechercheParticipantsParNom() {


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmResults<Participants> results = realm.where(Participants.class).findAll();
                edit_nom.setText("");
                for (Participants mParticipants : results) {
                    edit_nom.append(mParticipants.getmNom() + " e-mail: " + mParticipants.getmEmail() + " Numero de telephone: " + mParticipants.getmNumeroTelephone()+ "Date de naissance "+ mParticipants.getmDateNaissance());
                }
            }
        });


    }*/





    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
                /*        mNom = edit_nom.getText().toString();
                mEmail = edit_mail.getText().toString();
                mNumeroTelephone=edit_numero_telephone.getText().toString();
                mDateNaissance=edit_date_naissance.getText().toString();

                if(!mNom.isEmpty() && !mEmail.isEmpty() &&!mNumeroTelephone.isEmpty()&& !mDateNaissance.isEmpty())
                {
                   Participants mParticipants = new Participants();

                    mParticipants.setmNom(mNom);
                    mParticipants.setmEmail(mEmail);
                    mParticipants.setmNumeroTelephone(mNumeroTelephone);
                    mParticipants.setmDateNaissance(mDateNaissance);


                    realmHelper = new RealmHelper(realm);
                    realmHelper.insertionDansBd(Participants);

                    edit_nom.setText("");
                    edit_mail.setText("");
                    edit_numero_telephone.setText("");
                    edit_date_naissance.setText("");

                }
            */



// Transactions give you easy thread-safety


        //mSave.setOnClickListener(this);
  /*     mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nom = mNom.getText().toString();
                mParticipants.setmNom(nom);

                String email = mEmail.getText().toString();
                mParticipants.setmEmail(email);

                String valideEmail  = ("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                                + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                                + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
                String mail = mEmail.getText().toString();
                Matcher matcher = Pattern.compile(valideEmail).matcher(mail);

                if(matcher.matches()){
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(getApplicationContext(), "Entrez un mail valide svp", Toast.LENGTH_SHORT).show();
                }

                String numero = mNumeroTelephone.getText().toString();
                mParticipants.setmNumeroTelephone(numero);

                String date = mDateNaissance.getText().toString();
                mParticipants.setmDateNaissance(date);

                if (mNom.getText().toString().isEmpty() || mEmail.getText().toString().isEmpty() || mNumeroTelephone.getText().toString().isEmpty() || mDateNaissance.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "nom -  " + mNom.getText().toString() + " \n" + "Numero -  " + mEmail.getText().toString()
                            + " \n" + "Numéro de téléphone -  " + mNumeroTelephone.getText().toString() + " \n" + "Date de naissance -  " + mDateNaissance.getText().toString()
                            + " \n" , Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(getApplicationContext(),mParticipants.getmNom()+" cool",Toast.LENGTH_LONG ).show();


            }
        });
*/

package com.africa.cloud.formulaire;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.africa.cloud.formulaire.model.Participants;
import com.africa.cloud.formulaire.model.ParticipantsAdapter;
import com.africa.cloud.formulaire.model.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class AffichageActivity extends AppCompatActivity {


    private ListView lv;
    Participants mParticipants;
    Realm realm;
    ArrayList<String> participants;
    ArrayAdapter adapter;
    ParticipantsAdapter participantsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Participants> participantsList = new ArrayList<>();

    private RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fab;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        realm.init(this);




 //       lv = (ListView) findViewById(R.id.show);



        FloatingActionButton fab = findViewById(R.id.fab );
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AffichageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //reccuperation

        RealmHelper helper = new RealmHelper(realm); //instanciation du realheper
        helper.reccuperation(); //appelle de la methode de reccuperation situé dans le realm helper (La liste Objet participant creer)
        participantsList = helper.reccuperation(); // affectation de la liste
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ParticipantsAdapter(participantsList,this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext()); // pour avoir une seule ligne et une liste de defilement RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); //dividerItemDecoration ajoute un sparateur genre ici un trait vertical
        recyclerView.setAdapter(mAdapter);








        //prepareMovieData();
    }





    private void prepareMovieData(){
            Participants participants = new Participants(1,"Ibrahima Koné", "papuskone43@gmail.com", "779682002", "");
            participantsList.add(participants);

            participants = new Participants(2,"Moustapha Touré", "moustapha@bakeli.sn", "77 652 63 58", "");
            participantsList.add(participants);

            participants = new Participants(3,"Mamadou Samaké", "msam@samprod.ml", "(00223) 74-58-60-66", "" );
            participantsList.add(participants);

            participants = new Participants(4,"Fatoumata Guissé", "guissef@gmail.com", "77 813-13-13", "");
            participantsList.add(participants);

            participants = new Participants(1,"Ibrahima Kébé", "kebem64@gmail.com", "779682002", "");
            participantsList.add(participants);

            participants = new Participants(2,"Assetou Coulibaly", "asstou@bakeli.sn", "77 652 63 58", "");
            participantsList.add(participants);

            participants = new Participants(3,"Yoro Diarra", "djiney@samprod.ml", "(00223) 74-58-60-66", "" );
            participantsList.add(participants);

            participants = new Participants(4,"Kadiatou Guissé", "guissekadi@gmail.com", "77 414-13-13", "");
            participantsList.add(participants);

            mAdapter.notifyDataSetChanged();
        }










}

    //  FloatingActionButton fab = findViewById(R.id.fab );


/*
        //liasons a un adapter

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,participants);
        lv.setAdapter(adapter);


          //   FloatingActionButton fab = findViewById(R.id.fab );
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AffichageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



  /*






    }


        //lv= (ListView)findViewById(R.id.show);



      //  declaration du bouton flottant
      /*FloatingActionButton fab = findViewById (R.id.fab );
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AffichageActivity.this,"ok pour la redirection",Toast.LENGTH_SHORT).show();
            }
        });*/

        //click sur la liste
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AffichageActivity.this,participants.get(position),Toast.LENGTH_SHORT).show();
            }
        });*/














package com.africa.cloud.formulaire.model;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.renderscript.RenderScript;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.formulaire.AffichageActivity;
import com.africa.cloud.formulaire.MainActivity;
import com.africa.cloud.formulaire.ModifierActivity;
import com.africa.cloud.formulaire.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import io.realm.RealmResults;

import static android.support.v4.content.ContextCompat.createDeviceProtectedStorageContext;
import static android.support.v4.content.ContextCompat.startActivity;
import static io.realm.RealmObject.deleteFromRealm;
import static io.realm.RealmObject.getRealm;


public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.MyViewHolder> implements View.OnClickListener {

    private List<Participants> participantsList;
    ItemClickListener itemClickListener;
    Participants participants;
    Context mContext;

    public ParticipantsAdapter(List<Participants> participantsList, Context mContext) {
        this.participantsList = participantsList;
        this.mContext = mContext;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nom, mail, numero, date;
        public ImageButton mSupression, mEditer;



        public MyViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nom);
            mail = (TextView) itemView.findViewById(R.id.mail);
            numero = (TextView) itemView.findViewById(R.id.numero);
           date = (TextView) itemView.findViewById(R.id.date);
            mSupression = (ImageButton) itemView.findViewById(R.id.delete);
            mEditer = (ImageButton) itemView.findViewById(R.id.edit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,ModifierActivity.class);
                    intent.putExtra("id",participantsList.get(getAdapterPosition()).getId()+"");
                    intent.putExtra("mNom",participantsList.get(getAdapterPosition()).getmNom());
                    intent.putExtra("mNumeroTelephone",participantsList.get(getAdapterPosition()).getmNumeroTelephone());
                    intent.putExtra("mDateNaissance",participantsList.get(getAdapterPosition()).getmNumeroTelephone());
                    mContext.startActivity(intent);
                }
            });
        }
    }


    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {
       // this.itemClickListener.onItemClick(getItemId());
       // this.itemClickListener.onItemClick(getItemId());

    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.participants_list_row, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        //Positionnement des données dans le recyclerView (selon la declaration du recyclerView
        Participants participants = participantsList.get(position);
        holder.nom.setText(participants.getmNom());
        holder.mail.setText(participants.getmEmail());
        holder.numero.setText(participants.getmNumeroTelephone());

        holder.mSupression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm;
                //configuration du realm dans ma nouvelle activity
                RealmConfiguration config = new RealmConfiguration.Builder().build();
                realm = Realm.getInstance(config);


                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        List<Participants> participantsList = new ArrayList<>();

                        RealmResults<Participants> result = realm.where(Participants.class).equalTo("id", participants.id).findAll();

                            participants.setmNom(participants.getmNom());
                            participants.setmEmail(participants.getmEmail());
                            participants.setmNumeroTelephone(participants.getmNumeroTelephone());
                            participants.setmDateNaissance(participants.getmDateNaissance());

                        //Fin


                        participants.deleteFromRealm();

                        notifyDataSetChanged();

                    }

                });


               // RealmResults<Participants> result = realm.where(Participants.class).equalTo("id", participants.id).findAll();

                //participants.deleteFromRealm();
            }
        });


        holder.mEditer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm;
                RealmConfiguration config = new RealmConfiguration.Builder().build();
                realm = Realm.getInstance(config);

                RealmResults<Participants> result = realm.where(Participants.class).equalTo("id", participants.id).findAll();
                Intent intent = new Intent(mContext,ModifierActivity.class);
                intent.putExtra("id",participantsList.get(position).getId()+"");
                intent.putExtra("mNom",participantsList.get(position).getmNom());
                intent.putExtra("mEmail",participantsList.get(position).getmEmail());
                intent.putExtra("mNumeroTelephone",participantsList.get(position).getmNumeroTelephone());
                intent.putExtra("mDateNaissance",participantsList.get(position).getmDateNaissance());

                mContext.startActivity(intent);






            }




        });


    }




    @Override
    public int getItemCount() {
        return participantsList.size();
    }


}



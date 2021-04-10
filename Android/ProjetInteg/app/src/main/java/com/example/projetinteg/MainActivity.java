package com.example.projetinteg;

import DataAdapter.RapportListAdapter;
import Model.DAO.RapportManager;
import Model.Entity.Rapport;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button bAddRapport;
    private Button bEnvoyerRapports;
    private ArrayList<Rapport> rapportList;
    RapportListAdapter adapter;
    private RapportManager rapportmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On crée un RapportManager pour pouvoir récupérer la liste de rapports
        rapportmanager = new RapportManager(this);

        // on se connecte à la base de données
        rapportmanager.open();

        // On récupère la liste de rapports contenus dans la table Rapport
        rapportList = rapportmanager.getRapports();

        // on ferme la connexion
        rapportmanager.close();

        //on lie les elements d'interface aux objets correspondants
                /*mbuttonAddData = (Button) findViewById(R.id.buttonAddData);

                mbuttonAddData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), addContactActivity.class);
                        startActivity(intent);
                    }
                });*/

        // On parametre notre vue Recycler en liant son id
        RecyclerView recyclerView = findViewById(R.id.MyrvData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // On lie maintenant les données à notre adapteur
        adapter = new RapportListAdapter(this, rapportList);

        // On ajoute la gestion des evenements sur le clic
        //adapter.setClickListener(this);

        // Puis on lie notre adapteur au RecyclerView
        recyclerView.setAdapter(adapter);

        // On ajoute un trait de séparation entre les éléments (pour faciliter la lecture et le clic
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),1);
        recyclerView.addItemDecoration(dividerItemDecoration);

        bAddRapport = (Button) findViewById(R.id.bAddRapport);
        bAddRapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormRapportActivity.class);
                startActivity(intent);


            }
        });

        bEnvoyerRapports = (Button) findViewById(R.id.bPostRapports);
        bEnvoyerRapports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rapportmanager.open();

                for(int i=0; i<rapportList.size(); i++){
                    AsyncPostJSONData task = new AsyncPostJSONData(rapportList.get(i).getMapRapport());
                    task.execute("http://lakartxela.iutbayonne.univ-pau.fr/~fbaldassini/ProjetIntegBaldassini/public/api/rapport");
                    rapportmanager.removeAllRapports(rapportList.get(i));
                }
                rapportList.clear();
                rapportmanager.close();
                adapter.notifyDataSetChanged();
            }
        });
    }

    protected void onRestart() {
        super.onRestart();

        //On vide la liste
        rapportList.clear();

        rapportmanager.open();

        //Puis on réajoute tout le contenu de la liste de la base
        rapportList.addAll(rapportmanager.getRapports());

        rapportmanager.close();

        // indique a l'adapter que les données ont été mise à jour,
        // et que le contenu de recyclerView doit être réaffiché.
        adapter.notifyDataSetChanged();
    }
}

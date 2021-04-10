package com.example.projetinteg;

import Model.DAO.RapportManager;
import Model.Entity.Rapport;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormRapportActivity extends AppCompatActivity {

    private Button bAdd;
    private RapportManager rapportManager;
    private EditText nomTechnicien;
    private EditText prenomTechnicien;
    private EditText nomClient;
    private EditText prenomClient;
    private EditText adresse;
    private EditText marqueChaudiere;
    private EditText modeleChaudiere;
    private EditText dateMiseService;
    private EditText numSerie;
    private EditText dateIntervention;
    private EditText description;
    private EditText temps;
    private boolean valider; // variable pour valider le formualaire

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_rapport);

        nomTechnicien = (EditText) findViewById(R.id.nomTechnicien);
        prenomTechnicien = (EditText) findViewById(R.id.prenomTechnicien);
        nomClient = (EditText) findViewById(R.id.nomClient);
        prenomClient = (EditText) findViewById(R.id.prenomClient);
        adresse = (EditText) findViewById(R.id.adresse);
        marqueChaudiere = (EditText) findViewById(R.id.marque);
        modeleChaudiere = (EditText) findViewById(R.id.modele);
        dateMiseService = (EditText) findViewById(R.id.dateMiseService);
        numSerie = (EditText) findViewById(R.id.numSerie);
        dateIntervention = (EditText) findViewById(R.id.dateIntervention);
        description = (EditText) findViewById(R.id.description);
        temps = (EditText) findViewById(R.id.temps);
        bAdd = (Button) findViewById(R.id.bValider);

        rapportManager = new RapportManager(this);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ouvre l'acces a la base de données
                rapportManager.open();

                //Crée un nouveau contact et peuple l'objet avec les valeurs des champs de l'activité
                Rapport newRapport = new Rapport(0,"", "","", "", "", "", "", "", 0, "", "", "");

                valider = true; // par défaut le formulaire est valide

                // on vérifie que chacun des champs à bien été rempli
                if(nomTechnicien.getText().toString().length() != 0){
                    newRapport.setNomTechnicien(nomTechnicien.getText().toString());
                }
                else {
                    valider = false;
                }

                if(prenomTechnicien.getText().toString().length() != 0){
                    newRapport.setPrenomTechnicien(prenomTechnicien.getText().toString());
                }else{
                    valider = false;
                }

                if(nomClient.getText().toString().length() != 0){
                    newRapport.setNomClient(nomClient.getText().toString());
                }else{
                     valider = false;
                }

                if(prenomClient.getText().toString().length() != 0){
                    newRapport.setPrenomClient(prenomClient.getText().toString());
                }else{
                    valider = false;
                }

                if(adresse.getText().toString().length() != 0){
                    newRapport.setAdresse(adresse.getText().toString());
                }else{
                    valider = false;
                }

                if(marqueChaudiere.getText().toString().length() != 0){
                    newRapport.setMarqueChaudiere(marqueChaudiere.getText().toString());
                }else{
                    valider = false;
                }

                if(modeleChaudiere.getText().toString().length() != 0){
                    newRapport.setModeleChaudiere(modeleChaudiere.getText().toString());
                }else{
                    valider = false;
                }

                if(dateMiseService.getText().toString().length() != 0){
                    newRapport.setDateMiseService(dateMiseService.getText().toString());
                }else{
                    valider = false;
                }

                if(numSerie.getText().toString().length() != 0){
                    newRapport.setNumSerie(Integer.parseInt(numSerie.getText().toString()));
                }else{
                    valider = false;
                }

                if(dateIntervention.getText().toString().length() != 0){
                    newRapport.setDateIntervention(dateIntervention.getText().toString());
                }else{
                    valider = false;
                }

                if(description.getText().toString().length() != 0){
                    newRapport.setDescription(description.getText().toString());
                }else{
                    valider = false;
                }

                if(temps.getText().toString().length() != 0){
                    newRapport.setTemps(temps.getText().toString());
                }else{
                    valider = false;
                }

                // si tous les champs sont remplis, alors on peut enregistrer l'intervention
                if(valider == true){

                    //Ajoute le contact à la base de données
                    rapportManager.addRapport(newRapport);

                    // Ferme l'acces a la base
                    rapportManager.close();

                    // Ferme l'activité une fois l'ajout terminé.
                    FormRapportActivity.this.finish();
                }
                else{ // sinon on affiche un message d'erreur
                    Toast.makeText(getApplicationContext(), "Le formulaire n'a pas été entièrement rempli", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

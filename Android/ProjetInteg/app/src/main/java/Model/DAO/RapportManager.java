package Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Model.Entity.Rapport;
import Model.TheSQLiteDB;

import java.util.ArrayList;

public class RapportManager {

    private static final String TABLE_NAME = "rapports";
    public static final String KEY_ID_RAPPORT="id";
    public static final String KEY_NOMTECHNICIEN_RAPPORT="nomTechnicien";
    public static final String KEY_PRENOMTECHNICIEN_RAPPORT="prenomTechnicien";
    public static final String KEY_NOMCLIENT_RAPPORT="nomClient";
    public static final String KEY_PRENOMCLIENT_RAPPORT="prenomClient";
    public static final String KEY_ADRESSE_RAPPORT="adresse";
    public static final String KEY_MARQUE_RAPPORT="marque";
    public static final String KEY_MODELE_RAPPORT="modele";
    public static final String KEY_DATEMISESERVICE_RAPPORT="dateMiseService";
    public static final String KEY_NUMEROSERIE_RAPPORT="numeroSerie";
    public static final String KEY_DATEINTERVENTION_RAPPORT="dateIntervention";
    public static final String KEY_DESCRIPTION_RAPPORT="description";
    public static final String KEY_TEMPS_RAPPORT="temps";
    public static final String CREATE_TABLE_RAPPORT = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_RAPPORT+" INTEGER primary key," +
            " "+KEY_NOMTECHNICIEN_RAPPORT+" TEXT," +
            " "+KEY_PRENOMTECHNICIEN_RAPPORT+" TEXT," +
            " "+KEY_NOMCLIENT_RAPPORT+" TEXT," +
            " "+KEY_PRENOMCLIENT_RAPPORT+" TEXT," +
            " "+KEY_ADRESSE_RAPPORT+" TEXT," +
            " "+KEY_MARQUE_RAPPORT+" TEXT," +
            " "+KEY_MODELE_RAPPORT+" TEXT," +
            " "+KEY_DATEMISESERVICE_RAPPORT+" TEXT," +
            " "+KEY_NUMEROSERIE_RAPPORT+" INTEGER," +
            " "+KEY_DATEINTERVENTION_RAPPORT+" TEXT," +
            " "+KEY_DESCRIPTION_RAPPORT+" TEXT," +
            " "+KEY_TEMPS_RAPPORT+" TEXT" +
            ");";
    private TheSQLiteDB maBase; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;



    // Constructeur
    public RapportManager(Context context)
    {
        maBase = TheSQLiteDB.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBase.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addRapport(Rapport rapport) {
        // Ajout d'un enregistrement dans la table



        ContentValues values = new ContentValues();
        values.put(KEY_NOMTECHNICIEN_RAPPORT, rapport.getNomTechnicien());
        values.put(KEY_PRENOMTECHNICIEN_RAPPORT, rapport.getPrenomTechnicien());
        values.put(KEY_NOMCLIENT_RAPPORT, rapport.getNomClient());
        values.put(KEY_PRENOMCLIENT_RAPPORT, rapport.getPrenomClient());
        values.put(KEY_ADRESSE_RAPPORT, rapport.getAdresse());
        values.put(KEY_MARQUE_RAPPORT, rapport.getMarqueChaudiere());
        values.put(KEY_MODELE_RAPPORT, rapport.getModeleChaudiere());
        values.put(KEY_DATEMISESERVICE_RAPPORT, rapport.getDateMiseService());
        values.put(KEY_NUMEROSERIE_RAPPORT, rapport.getNumSerie());
        values.put(KEY_DATEINTERVENTION_RAPPORT, rapport.getDateIntervention());
        values.put(KEY_DESCRIPTION_RAPPORT, rapport.getDescription());
        values.put(KEY_TEMPS_RAPPORT, rapport.getTemps());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int removeAllRapports(Rapport rapport) {
        // suppression de tous les enregistrement une fois qu'ils sont envoyés
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_RAPPORT+" = ?";
        String[] whereArgs = {rapport.getId()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Rapport getRapport(int id) {
        // Retourne le rapport

        Rapport a=new Rapport(0,"", "","", "", "", "", "", "", 0, "", "", "");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_RAPPORT+"="+id, null);

        if (c.moveToFirst()) {
            a.setId(c.getInt(c.getColumnIndex(KEY_ID_RAPPORT)));
            a.setNomTechnicien(c.getString(c.getColumnIndex(KEY_NOMTECHNICIEN_RAPPORT)));
            a.setPrenomTechnicien(c.getString(c.getColumnIndex(KEY_PRENOMTECHNICIEN_RAPPORT)));
            a.setNomClient(c.getString(c.getColumnIndex(KEY_NOMCLIENT_RAPPORT)));
            a.setPrenomClient(c.getString(c.getColumnIndex(KEY_PRENOMCLIENT_RAPPORT)));
            a.setAdresse(c.getString(c.getColumnIndex(KEY_ADRESSE_RAPPORT)));
            a.setMarqueChaudiere(c.getString(c.getColumnIndex(KEY_MARQUE_RAPPORT)));
            a.setModeleChaudiere(c.getString(c.getColumnIndex(KEY_MODELE_RAPPORT)));
            a.setDateMiseService(c.getString(c.getColumnIndex(KEY_DATEMISESERVICE_RAPPORT)));
            a.setNumSerie(c.getInt(c.getColumnIndex(KEY_NUMEROSERIE_RAPPORT)));
            a.setDateIntervention(c.getString(c.getColumnIndex(KEY_DATEINTERVENTION_RAPPORT)));
            a.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION_RAPPORT)));
            a.setTemps(c.getString(c.getColumnIndex(KEY_TEMPS_RAPPORT)));
            c.close();
        }

        return a;
    }

    public ArrayList<Rapport> getRapports() {

        ArrayList<Rapport> rapportList = new ArrayList<Rapport>();

        //récupère dans un curseur le résultat du select sur la table
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        if (c.moveToFirst()) {
            //parcourt le curseur obtenu, jusqu'a la fin, et créer pour chaque enregistrement un objet Contact
            do {
                Rapport a=new Rapport(0,"", "","", "", "", "", "", "", 0, "", "", "");

                a.setId(c.getInt(c.getColumnIndex(KEY_ID_RAPPORT)));
                a.setNomTechnicien(c.getString(c.getColumnIndex(KEY_NOMTECHNICIEN_RAPPORT)));
                a.setPrenomTechnicien(c.getString(c.getColumnIndex(KEY_PRENOMTECHNICIEN_RAPPORT)));
                a.setNomClient(c.getString(c.getColumnIndex(KEY_NOMCLIENT_RAPPORT)));
                a.setPrenomClient(c.getString(c.getColumnIndex(KEY_PRENOMCLIENT_RAPPORT)));
                a.setAdresse(c.getString(c.getColumnIndex(KEY_ADRESSE_RAPPORT)));
                a.setMarqueChaudiere(c.getString(c.getColumnIndex(KEY_MARQUE_RAPPORT)));
                a.setModeleChaudiere(c.getString(c.getColumnIndex(KEY_MODELE_RAPPORT)));
                a.setDateMiseService(c.getString(c.getColumnIndex(KEY_DATEMISESERVICE_RAPPORT)));
                a.setNumSerie(c.getInt(c.getColumnIndex(KEY_NUMEROSERIE_RAPPORT)));
                a.setDateIntervention(c.getString(c.getColumnIndex(KEY_DATEINTERVENTION_RAPPORT)));
                a.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION_RAPPORT)));
                a.setTemps(c.getString(c.getColumnIndex(KEY_TEMPS_RAPPORT)));

                // ajoute l'objet créé à la ArrayList de Rapport qui sera renvoyée.
                rapportList.add(a);
            }
            while (c.moveToNext());
        }
        c.close();

        return rapportList;
    }

} // class RapportManager

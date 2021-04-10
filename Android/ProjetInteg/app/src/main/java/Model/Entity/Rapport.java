package Model.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Rapport {

    private int id;
    private String nomTechnicien;
    private String prenomTechnicien;
    private String nomClient;
    private String prenomClient;
    private String adresse;
    private String marqueChaudiere;
    private String modeleChaudiere;
    private String dateMiseService;
    private int numSerie;
    private String dateIntervention;
    private String description;
    private String temps;
    private Map<String, String> mapRapport;
    private static SimpleDateFormat inSDF = new SimpleDateFormat("mm/dd/yyyy");
    private static SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-dd-mm");



    public Rapport(int id, String nomTechnicien, String prenomTechnicien, String nomClient, String prenomClient, String adresse, String marqueChaudiere, String modeleChaudiere, String dateMiseService, int numSerie, String dateIntervention, String description, String temps) {
        this.id = id;
        this.nomTechnicien = nomTechnicien;
        this.prenomTechnicien = prenomTechnicien;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.adresse = adresse;
        this.marqueChaudiere = marqueChaudiere;
        this.modeleChaudiere = modeleChaudiere;
        this.dateMiseService = dateMiseService;
        this.numSerie = numSerie;
        this.dateIntervention = dateIntervention;
        this.description = description;
        this.temps = temps;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomTechnicien(String nomTechnicien) {
        this.nomTechnicien = nomTechnicien;
    }

    public void setPrenomTechnicien(String prenomTechnicien) {
        this.prenomTechnicien = prenomTechnicien;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMarqueChaudiere(String marqueChaudiere) {
        this.marqueChaudiere = marqueChaudiere;
    }

    public void setModeleChaudiere(String modeleChaudiere) {
        this.modeleChaudiere = modeleChaudiere;
    }

    public void setDateMiseService(String dateMiseService) {
        this.dateMiseService = dateMiseService;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public void setDateIntervention(String dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public int getId() {
        return id;
    }

    public String getNomTechnicien() {
        return nomTechnicien;
    }

    public String getPrenomTechnicien() {
        return prenomTechnicien;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMarqueChaudiere() {
        return marqueChaudiere;
    }

    public String getModeleChaudiere() {
        return modeleChaudiere;
    }

    public String getDateMiseService() {
        return dateMiseService;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public String getDateIntervention() {
        return dateIntervention;
    }

    public String getDescription() {
        return description;
    }

    public String getTemps() {
        return temps;
    }

    public Map<String, String> getMapRapport(){

        mapRapport = new HashMap<>();

        mapRapport.put("nomTechnicien", nomTechnicien);
        mapRapport.put("prenomTechnicien", prenomTechnicien);
        mapRapport.put("nomClient", nomClient);
        mapRapport.put("prenomClient", prenomClient);
        mapRapport.put("adresse", adresse);
        mapRapport.put("marqueChaudiere", marqueChaudiere);
        mapRapport.put("modeleChaudiere", modeleChaudiere);
        mapRapport.put("dateMiseService", formatDate(dateMiseService));
        mapRapport.put("numSerie", Integer.toString(numSerie));
        mapRapport.put("dateIntervention", formatDate(dateIntervention));
        mapRapport.put("description", description);
        mapRapport.put("temps", temps);

        return mapRapport;
    }

    // conversion de la date dd/mm/yyyy en yyyy-mm-dd
    public  String formatDate(String inDate) {
        String outDate = "";
        if (inDate != null) {
            try {
                Date date = inSDF.parse(inDate);
                outDate = outSDF.format(date);
            } catch (ParseException ex){
            }
        }
        return outDate;
    }
}

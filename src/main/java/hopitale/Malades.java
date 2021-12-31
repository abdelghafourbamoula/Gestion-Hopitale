
package hopitale;

import java.sql.Date;


public class Malades {
   
//    ----------------- Attributes ----------------  
        private Integer NumDossier ;
        private String cin ;
        private String nom  ;
        private String sexe;
        private String naissance;
        private String tele;
        private String  adresse;
        private String  medecin;
        private String  status;

//    ----------------- ------- Condtructor ------ ---------------- 
   
    public Malades(Integer NumDossier, String cin, String nom, String sexe, String naissance, String tele, String adresse, String status, String medecin) {
        this.NumDossier = NumDossier;
        this.cin = cin;
        this.nom = nom;
        this.sexe = sexe;
        this.naissance = naissance;
        this.tele = tele;
        this.adresse = adresse;
        this.medecin = medecin;
        this.status = status;
    }

    // -------------------- setters -----------------------------
    public void setNumDossier(Integer NumDossier) {
        this.NumDossier = NumDossier;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

// -------------------- getters -----------------------------

    public Integer getNumDossier() {
        return NumDossier;
    }

    public String getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getNaissance() {
        return naissance;
    }

    public String getTele() {
        return tele;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMedecin() {
        return medecin;
    }

    public String getStatus() {
        return status;
    }
    
}
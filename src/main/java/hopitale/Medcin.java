
package hopitale;


public class Medcin {
    private String cin;
    private String nom;
    private String prenom;
    private String sexe;
    private String naissance;
    private String specialite;
    private String tele;
    private String email;
    private String adresse;
    private String password;

    public Medcin(String cin, String nom, String prenom, String sexe, String naissance, String specialite, String tele, String email, String adresse, String password) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.naissance = naissance;
        this.specialite = specialite;
        this.tele = tele;
        this.email = email;
        this.adresse = adresse;
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getNaissance() {
        return naissance;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getTele() {
        return tele;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPassword() {
        return password;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public void setSpcialite(String spcialite) {
        this.specialite = spcialite;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}

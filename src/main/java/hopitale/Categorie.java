
package hopitale;


public class Categorie {

    static Object getSelectionModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//    ----------------- Add Attributes ----------------  
    private int Id_Categorie ;
    private String Designation ;
    private int Nombre_Chembre;
    private String Nom_Chef ;
//    ----------------- ------- Condtructor ------ ---------------- 

    public Categorie(int Id_Categorie, String Designation, int Nombre_Chembre, String Nom_Chef) {
        this.Id_Categorie = Id_Categorie;
        this.Designation = Designation;
        this.Nombre_Chembre = Nombre_Chembre;
        this.Nom_Chef = Nom_Chef;
    }

    public void setId_Categorie(int Id_Categorie) {
        this.Id_Categorie = Id_Categorie;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public void setNombre_Chembre(int Nombre_Chembre) {
        this.Nombre_Chembre = Nombre_Chembre;
    }

//    ----------------- Add setter Methods ----------------
    public void setNom_Chef(String Nom_Chef) {
        this.Nom_Chef = Nom_Chef;
    }

    //    ----------------- Add getter Methods ----------------
    public int getId_Categorie() {
        return Id_Categorie;
    }

    public String getDesignation() {
        return Designation;
    }

    public int getNombre_Chembre() {
        return Nombre_Chembre;
    }

    public String getNom_Chef() {
        return Nom_Chef;
    }
   
}

package esmat.appro.model;

import esmat.appro.Database.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PDR {
    private String Nomenclature;
    private String Designation;
    private int qte_stock;
    private String photo_url;
    private String codebarre;
    private Double prix_unitaire;
    private String typeVehicule;
    private String position;
    private String groupe;
    private Boolean isDeleted ;



    public PDR(String nomenclature, String groupe, String position, String typeVehicule, Double prix_unitaire, int qte_stock, String photo_url, String designation) {
        Nomenclature = nomenclature;
        this.groupe = groupe;
        this.position = position;
        this.typeVehicule = typeVehicule;
        this.prix_unitaire = prix_unitaire;
        this.qte_stock = qte_stock;
        this.photo_url = photo_url;
        this.Designation = designation;
    }


//
//    public PDR(String nomenclature, String designation, String codebarre, String photoUrl, int qteStock, double prixUnitaire, String position, String typevehicule, String groupe) {
//        this.Nomenclature = nomenclature;
//        this.Designation = designation;
//        this.qte_stock = qteStock;
//        this.photo_url = photoUrl;
//        this.codebarre = codebarre; //generateBarcode();
//        this.prix_unitaire = prixUnitaire;
//        this.typeVehicule = typevehicule;
//        this.position = position;
//        this.groupe = groupe;
//        this.isDeleted = false;
//    }


    public PDR(String nomenclature, String designation, String codebarre, String photoUrl, int qteStock, double prixUnitaire, String position, String typevehicule, String groupe, boolean isDeleted) {
        this.Nomenclature = nomenclature;
        this.Designation = designation;
        this.codebarre = codebarre;
        this.photo_url = photoUrl;
        this.qte_stock = qteStock;
        this.prix_unitaire = prixUnitaire;
        this.position = position;
        this.typeVehicule = typevehicule;
        this.groupe = groupe;
        this.isDeleted = isDeleted;
    }

    public static ObservableList<PDR> getPDRlist() {
        ObservableList<PDR> PDRlist = FXCollections.observableArrayList();

        String query = "SELECT * FROM PDR  WHERE isdeleted = false ORDER BY designation";

                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
        try {
              conn = database.getConnection();
              ps = conn.prepareStatement(query);
              rs = ps.executeQuery();

            while (rs.next()) {
                PDR pdr = new PDR(
                        rs.getString("nomenclature"),
                        rs.getString("designation"),
                        rs.getString("codebarre"),
                        rs.getString("photo_url"),
                        rs.getInt("qte_stock"),
                        rs.getDouble("prix_unitaire"),
                        rs.getString("position"),
                        rs.getString("typevehicule"),
                        rs.getString("groupe"),
                        rs.getBoolean("isdeleted")
                );
                PDRlist.add(pdr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // Fermer les ressources dans l'ordre inverse de leur ouverture
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return PDRlist;
    }

    private String generateBarcode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        String uniqueId = timestamp + String.format("%04d", (int) (Math.random() * 10000));
        return uniqueId;
    }
    public String getNomenclature() {
        return Nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        Nomenclature = nomenclature;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public int getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(int qte_stock) {
        this.qte_stock = qte_stock;
    }


    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(Double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }
    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}

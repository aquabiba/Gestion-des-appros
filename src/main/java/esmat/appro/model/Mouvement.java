package esmat.appro.model;

import java.time.LocalDate;

public class Mouvement {
    private String N_folio;
    private String nomenclature;
    private String type_mouvement;
    private int qte_mouvement;
    private LocalDate date_mouvement;

    public Mouvement(String n_folio, String nomenclature, String type_mouvement, int qte_mouvement, LocalDate date) {
        N_folio = n_folio;
        this.nomenclature = nomenclature;
        this.type_mouvement = type_mouvement;
        this.qte_mouvement = qte_mouvement;
        this.date_mouvement = date;
    }

    public String getN_folio() {
        return N_folio;
    }

    public void setN_folio(String n_folio) {
        N_folio = n_folio;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getType_mouvement() {
        return type_mouvement;
    }

    public void setType_mouvement(String type_mouvement) {
        this.type_mouvement = type_mouvement;
    }

    public LocalDate getDate() {
        return date_mouvement;
    }

    public void setDate(LocalDate date) {
        this.date_mouvement = date;
    }

    public int getQte_mouvement() {
        return qte_mouvement;
    }

    public void setQte_mouvement(int qte_mouvement) {
        this.qte_mouvement = qte_mouvement;
    }
}

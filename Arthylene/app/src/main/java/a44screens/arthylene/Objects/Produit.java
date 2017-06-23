package a44screens.arthylene.Objects;

/**
 * Created by Thibault on 21/06/2017.
 */

public class Produit {

    private Long idProduit;
    private String nomProduit;
    private String varieteProduit;
    private int niveauMaturite;
    private Long idMaturite;
    private int niveauEtat;
    private Long idEtat;
    private Long idPresentation;

    public Produit() {
    }

    public Produit(Long idProduit, String nomProduit, String varieteProduit, int niveauMaturite, Long idMaturite, int niveauEtat, Long idEtat, Long idPresentation) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.varieteProduit = varieteProduit;
        this.niveauMaturite = niveauMaturite;
        this.idMaturite = idMaturite;
        this.niveauEtat = niveauEtat;
        this.idEtat = idEtat;
        this.idPresentation = idPresentation;
    }

    public String toString() {
        return "id : " + getIdProduit() + ", nom : " + getNomProduit() + ", variete : " + getVarieteProduit()
                + ", niveauMaturite : " + getNiveauMaturite() + ", idMAturite : " + getIdMaturite()
                + ", niveauEtat : " + getNiveauEtat() + ", idEtat : " + getIdEtat()
                + ", idPresentation : " + getIdPresentation();
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getVarieteProduit() {
        return varieteProduit;
    }

    public void setVarieteProduit(String varieteProduit) {
        this.varieteProduit = varieteProduit;
    }

    public int getNiveauMaturite() {
        return niveauMaturite;
    }

    public void setNiveauMaturite(int niveauMaturite) {
        this.niveauMaturite = niveauMaturite;
    }

    public Long getIdMaturite() {
        return idMaturite;
    }

    public void setIdMaturite(Long idMaturite) {
        this.idMaturite = idMaturite;
    }

    public int getNiveauEtat() {
        return niveauEtat;
    }

    public void setNiveauEtat(int niveauEtat) {
        this.niveauEtat = niveauEtat;
    }

    public Long getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(Long idEtat) {
        this.idEtat = idEtat;
    }

    public Long getIdPresentation() {
        return idPresentation;
    }

    public void setIdPresentation(Long idPresentation) {
        this.idPresentation = idPresentation;
    }
}

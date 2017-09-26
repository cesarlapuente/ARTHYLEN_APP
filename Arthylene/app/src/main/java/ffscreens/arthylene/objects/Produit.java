package ffscreens.arthylene.objects;

/**
 * Arthylene
 * Created by Thibault Nougues on 21/06/2017.
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
    private Long idBeneficeSante;
    private Long idCaracteristique;
    private Long idConseil;
    private Long idMarketing;

    public Produit() {
    }

    public Produit(Long idProduit, String nomProduit, String varieteProduit, int niveauMaturite, Long idMaturite, int niveauEtat, Long idEtat, Long idPresentation, Long idBeneficeSante, Long idCaracteristique, Long idConseil, Long idMarketing) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.varieteProduit = varieteProduit;
        this.niveauMaturite = niveauMaturite;
        this.idMaturite = idMaturite;
        this.niveauEtat = niveauEtat;
        this.idEtat = idEtat;
        this.idPresentation = idPresentation;
        this.idBeneficeSante = idBeneficeSante;
        this.idCaracteristique = idCaracteristique;
        this.idConseil = idConseil;
        this.idMarketing = idMarketing;
    }

    public String toString() {
        return "id : " + getIdProduit() + ", nom : " + getNomProduit() + ", variete : " + getVarieteProduit()
                + ", niveauMaturite : " + getNiveauMaturite() + ", idMAturite : " + getIdMaturite()
                + ", niveauEtat : " + getNiveauEtat() + ", idEtat : " + getIdEtat()
                + ", idPresentation : " + getIdPresentation() + ", idBeneficeSante : " + getIdBeneficeSante()
                + ", idCaracteristique : " + getIdCaracteristique() + ", idConseil : " + getIdConseil()
                + ", idMarketing : " + getIdMarketing();
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

    public Long getIdBeneficeSante() {
        return idBeneficeSante;
    }

    public void setIdBeneficeSante(Long idBeneficeSante) {
        this.idBeneficeSante = idBeneficeSante;
    }

    public Long getIdCaracteristique() {
        return idCaracteristique;
    }

    public void setIdCaracteristique(Long idCaracteristique) {
        this.idCaracteristique = idCaracteristique;
    }

    public Long getIdConseil() {
        return idConseil;
    }

    public void setIdConseil(Long idConseil) {
        this.idConseil = idConseil;
    }

    public Long getIdMarketing() {
        return idMarketing;
    }

    public void setIdMarketing(Long idMarketing) {
        this.idMarketing = idMarketing;
    }
}

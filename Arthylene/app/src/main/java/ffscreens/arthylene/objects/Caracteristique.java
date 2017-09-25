package ffscreens.arthylene.objects;

public class Caracteristique
{
    private Long idCaracteristique;
    private Long idProduit;
    private String famille;
    private String espece;
    private String taillePoids;
    private String couleurTexture;
    private String saveur;
    private String principauxProducteur;

    public Caracteristique()
    {

    }

    public Caracteristique(Long idCaracteristique, Long idProduit, String famille, String espece, String taillePoids, String couleurTexture, String saveur, String principauxProducteur)
    {
        this.idCaracteristique = idCaracteristique;
        this.idProduit = idProduit;
        this.famille = famille;
        this.espece = espece;
        this.taillePoids = taillePoids;
        this.couleurTexture = couleurTexture;
        this.saveur = saveur;
        this.principauxProducteur = principauxProducteur;
    }

    public String toString() {
        return "id : " + getIdCaracteristique() + ", idProduit : " + getIdProduit() + ", famille : " + getFamille()
                + ", espece : " + getEspece() + ", taillePoids : " + getTaillePoids()
                + ", couleurTexture : " + getCouleurTexture() + ", saveur : " + getSaveur()
                + ", principauxProducteur : " + getPrincipauxProducteur();
    }

    public Long getIdCaracteristique() {
        return idCaracteristique;
    }

    public void setIdCaracteristique(Long idCaracteristique) {
        this.idCaracteristique = idCaracteristique;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getTaillePoids() {
        return taillePoids;
    }

    public void setTaillePoids(String taillePoids) {
        this.taillePoids = taillePoids;
    }

    public String getCouleurTexture() {
        return couleurTexture;
    }

    public void setCouleurTexture(String couleurTexture) {
        this.couleurTexture = couleurTexture;
    }

    public String getSaveur() {
        return saveur;
    }

    public void setSaveur(String saveur) {
        this.saveur = saveur;
    }

    public String getPrincipauxProducteur() {
        return principauxProducteur;
    }

    public void setPrincipauxProducteur(String principauxProducteur) {
        this.principauxProducteur = principauxProducteur;
    }
}

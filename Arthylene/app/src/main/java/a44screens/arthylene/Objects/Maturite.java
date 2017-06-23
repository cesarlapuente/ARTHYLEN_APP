package a44screens.arthylene.Objects;

/**
 * Created by Thibault on 21/06/2017.
 */

public class Maturite {

    private Long idMaturite;
    private Long idProduit;
    private String contenu;
    private Long idPhoto;
    private boolean ideale;
    private String popup;

    public Maturite() {

    }

    public Maturite(Long idMaturite, Long idProduit, String contenu, Long idPhoto, boolean ideale, String popup) {
        this.idMaturite = idMaturite;
        this.idProduit = idProduit;
        this.contenu = contenu;
        this.idPhoto = idPhoto;
        this.ideale = ideale;
        this.popup = popup;
    }

    public String toString() {
        return "idMaturite : " + getIdMaturite() + ", idProduit : " + getIdProduit() + ", contenu : "
                + getContenu() + ", idPhoto : " + getIdPhoto() + ", maturiteIdeale : "
                + isIdeale() + ", popup : " + getPopup();
    }

    public Long getIdMaturite() {
        return idMaturite;
    }

    public void setIdMaturite(Long idMaturite) {
        this.idMaturite = idMaturite;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Long getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public boolean isIdeale() {
        return ideale;
    }

    public void setIdeale(boolean ideale) {
        this.ideale = ideale;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }
}

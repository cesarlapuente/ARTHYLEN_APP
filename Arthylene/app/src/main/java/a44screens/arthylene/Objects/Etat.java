package a44screens.arthylene.Objects;

/**
 * Created by Thibault on 21/06/2017.
 */

public class Etat {

    private Long idEtat;
    private Long idProduit;
    private String contenu;
    private Long idPhoto;
    private String popup;

    public Etat() {
    }

    public Etat(Long idEtat, Long idProduit, String contenu, Long idPhoto, String popup) {
        this.idEtat = idEtat;
        this.idProduit = idProduit;
        this.contenu = contenu;
        this.idPhoto = idPhoto;
        this.popup = popup;
    }

    public String toString() {
        return "idEtat : " + getIdEtat() + ", idProduit : " + getIdProduit() + ", contenu : "
                + getContenu() + ", idPhoto : " + getIdPhoto() + ", popup : " + getPopup();
    }

    public Long getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(Long idEtat) {
        this.idEtat = idEtat;
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

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }
}

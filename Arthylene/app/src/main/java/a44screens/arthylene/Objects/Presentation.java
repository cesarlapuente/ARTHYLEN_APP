package a44screens.arthylene.Objects;

/**
 * Created by Thibault on 21/06/2017.
 */

public class Presentation {

    private Long idPresentation;
    private Long idProduit;
    private String contenu;
    private Long idPhoto;

    public Presentation() {
    }

    public Presentation(Long idPresentation, Long idProduit, String contenu, Long idPhoto) {
        this.idPresentation = idPresentation;
        this.idProduit = idProduit;
        this.contenu = contenu;
        this.idPhoto = idPhoto;
    }

    public String toString() {
        return "idPresentation : " + getIdPresentation() + ", idProduit : " + getIdProduit()
                + ", contenu : " + getContenu() + ", id photo : " + getIdPhoto();
    }

    public Long getIdPresentation() {
        return idPresentation;
    }

    public void setIdPresentation(Long idPresentation) {
        this.idPresentation = idPresentation;
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
}

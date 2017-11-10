package ffscreens.arthylene.objects;

/**
 * Arthylene
 * Created by Thibault Nougues on 21/06/2017.
 */

public class Presentation {

    private Long idPresentation;
    private Long idProduit;
    private String contenu;
    private Long idPhoto;
    private Long idAudio;

    public Presentation() {
    }

    public Presentation(Long idPresentation, Long idProduit, String contenu, Long idPhoto, Long idAudio) {
        this.idPresentation = idPresentation;
        this.idProduit = idProduit;
        this.contenu = contenu;
        this.idPhoto = idPhoto;
        this.idAudio = idAudio;
    }

    public String toString() {
        return "idPresentation : " + getIdPresentation() + ", idProduit : " + getIdProduit()
                + ", contenu : " + getContenu() + ", id photo : " + getIdPhoto() + ", id audio : " + getIdAudio();
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

    public Long getIdAudio() {
        return idAudio;
    }

    public void setIdAudio(Long idAudio) {
        this.idAudio = idAudio;
    }
}

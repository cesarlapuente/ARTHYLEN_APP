package ffscreens.arthylene.objects;

/**
 * Arthylene
 * Created by Thibault on 12/07/2017.
 */

public class Photo {

    private Long idPhoto;
    private String photo;
    private String chemin;

    public Photo(Long idPhoto, String photo, String chemin) {
        this.idPhoto = idPhoto;
        this.photo = photo;
        this.chemin = chemin;
    }

    public Photo() {
    }

    public Long getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String toString() {
        return "idPhoto : " + getIdPhoto() + " photo : " + getPhoto() + " chemin : " + getChemin();
    }
}

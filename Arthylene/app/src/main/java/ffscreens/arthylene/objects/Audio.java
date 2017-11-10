package ffscreens.arthylene.objects;

/**
 * Created by thiba on 10/11/2017.
 */

public class Audio
{

    private Long idAudio;
    private String audio;
    private String chemin;

    public Audio(Long idAudio, String audio, String chemin) {
        this.idAudio = idAudio;
        this.audio = audio;
        this.chemin = chemin;
    }

    public Audio() {
    }

    public Long getIdAudio() {
        return idAudio;
    }

    public void setIdAudio(Long idAudio) {
        this.idAudio = idAudio;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String toString() {
        return "idAudio : " + getIdAudio() + " audio : " + getAudio() + " chemin : " + getChemin();
    }
}

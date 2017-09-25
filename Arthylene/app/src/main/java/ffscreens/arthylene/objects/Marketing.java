package ffscreens.arthylene.objects;


public class Marketing
{
    private Long idMarketing;
    private Long idProduit;
    private String marketing1;
    private String marketing2;

    public Marketing() {
    }

    public Marketing(Long idMarketing, Long idProduit, String marketing1, String marketing2) {
        this.idMarketing = idMarketing;
        this.idProduit = idProduit;
        this.marketing1 = marketing1;
        this.marketing2 = marketing2;
    }

    public String toString() {
        return "id : " + getIdMarketing() + ", idProduit : " + getIdProduit() + ", marketing1 : " + getMarketing1()
                + ", marketing2 : " + getMarketing2() + "";
    }

    public Long getIdMarketing() {
        return idMarketing;
    }

    public void setIdMarketing(Long idMarketing) {
        this.idMarketing = idMarketing;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getMarketing1() {
        return marketing1;
    }

    public void setMarketing1(String marketing1) {
        this.marketing1 = marketing1;
    }

    public String getMarketing2() {
        return marketing2;
    }

    public void setMarketing2(String marketing2) {
        this.marketing2 = marketing2;
    }
}

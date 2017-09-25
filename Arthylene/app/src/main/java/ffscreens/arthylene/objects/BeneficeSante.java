package ffscreens.arthylene.objects;

public class BeneficeSante
{
    private Long idBeneficeSante;
    private Long idProduit;
    private String benefice1;
    private String benefice2;
    private String benefice3;
    private String benefice4;
    private String benefice5;
    private String benefice6;

    public BeneficeSante()
    {

    }

    public BeneficeSante(Long idBeneficeSante, Long idProduit, String benefice1, String benefice2, String benefice3, String benefice4, String benefice5, String benefice6)
    {
        this.idBeneficeSante = idBeneficeSante;
        this.idProduit = idProduit;
        this.benefice1 = benefice1;
        this.benefice2 = benefice2;
        this.benefice3 = benefice3;
        this.benefice4 = benefice4;
        this.benefice5 = benefice5;
        this.benefice6 = benefice6;
    }

    public String toString() {
        return "id : " + getIdBeneficeSante() + ", idProduit : " + getIdProduit() + ", benefice1 : " + getBenefice1()
                + ", benefice2 : " + getBenefice2() + ", benefice3 : " + getBenefice3()
                + ", benefice4 : " + getBenefice4() + ", benefice5 : " + getBenefice5()
                + ", benefice6 : " + getBenefice6();
    }

    public Long getIdBeneficeSante() {
        return idBeneficeSante;
    }

    public void setIdBeneficeSante(Long idBeneficeSante) {
        this.idBeneficeSante = idBeneficeSante;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getBenefice1() {
        return benefice1;
    }

    public void setBenefice1(String benefice1) {
        this.benefice1 = benefice1;
    }

    public String getBenefice2() {
        return benefice2;
    }

    public void setBenefice2(String benefice2) {
        this.benefice2 = benefice2;
    }

    public String getBenefice3() {
        return benefice3;
    }

    public void setBenefice3(String benefice3) {
        this.benefice3 = benefice3;
    }

    public String getBenefice4() {
        return benefice4;
    }

    public void setBenefice4(String benefice4) {
        this.benefice4 = benefice4;
    }

    public String getBenefice5() {
        return benefice5;
    }

    public void setBenefice5(String benefice5) {
        this.benefice5 = benefice5;
    }

    public String getBenefice6() {
        return benefice6;
    }

    public void setBenefice6(String benefice6) {
        this.benefice6 = benefice6;
    }
}

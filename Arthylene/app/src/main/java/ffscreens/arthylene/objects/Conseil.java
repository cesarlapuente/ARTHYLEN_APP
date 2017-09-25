package ffscreens.arthylene.objects;

public class Conseil
{
    private Long idConseil;
    private Long idProduit;
    private String conseil1;
    private String conseil2;
    private String conseil3;
    private String conseil4;
    private String conseil5;
    private String conseil6;

    public Conseil() {
    }

    public Conseil(Long idConseil, Long idProduit, String conseil1, String conseil2, String conseil3, String conseil4, String conseil5, String conseil6) {
        this.idConseil = idConseil;
        this.idProduit = idProduit;
        this.conseil1 = conseil1;
        this.conseil2 = conseil2;
        this.conseil3 = conseil3;
        this.conseil4 = conseil4;
        this.conseil5 = conseil5;
        this.conseil6 = conseil6;
    }

    public String toString() {
        return "id : " + getIdConseil() + ", idProduit : " + getIdProduit() + ", conseil1 : " + getConseil1()
                + ", conseil2 : " + getConseil2() + ", conseil3 : " + getConseil3()
                + ", conseil4 : " + getConseil4() + ", conseil5 : " + getConseil5()
                + ", conseil6 : " + getConseil6();
    }

    public Long getIdConseil() {
        return idConseil;
    }

    public void setIdConseil(Long idConseil) {
        this.idConseil = idConseil;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getConseil1() {
        return conseil1;
    }

    public void setConseil1(String conseil1) {
        this.conseil1 = conseil1;
    }

    public String getConseil2() {
        return conseil2;
    }

    public void setConseil2(String conseil2) {
        this.conseil2 = conseil2;
    }

    public String getConseil3() {
        return conseil3;
    }

    public void setConseil3(String conseil3) {
        this.conseil3 = conseil3;
    }

    public String getConseil4() {
        return conseil4;
    }

    public void setConseil4(String conseil4) {
        this.conseil4 = conseil4;
    }

    public String getConseil5() {
        return conseil5;
    }

    public void setConseil5(String conseil5) {
        this.conseil5 = conseil5;
    }

    public String getConseil6() {
        return conseil6;
    }

    public void setConseil6(String conseil6) {
        this.conseil6 = conseil6;
    }
}

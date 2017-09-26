package ffscreens.arthylene.objects;

/**
 * Created by 44Screens on 2017-09-26.
 */

public class ScanResult
{
    private final String name;
    private final Float convidence;
    private final Integer maturity;

    public ScanResult(String name, Integer maturity, Float convidence)
    {
        this.name = name;
        this.maturity = maturity;
        this.convidence = convidence;
    }

    public String getName()
    {
        return name;
    }

    public Integer getMaturity()
    {
        return maturity;
    }

    public Float getConvidence()
    {
        return convidence;
    }
}

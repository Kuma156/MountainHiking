package model;

/**
 *
 * @author The Miracle Invoker
 */
public class Mountain {

    private String mountainPeakCode;
    private String mountain;
    private String province;
    private String description;

    public Mountain() {
    }

    public Mountain(String mountainPeakCode, String mountain, String province, String description) {
        this.mountainPeakCode = mountainPeakCode;
        this.mountain = mountain;
        this.province = province;
        this.description = description;
    }

    public String getMountainPeakCode() {
        return "MT" + mountainPeakCode;
    }

    public String getMountain() {
        return mountain;
    }

    public String getProvince() {
        return province;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("| %-12s | %-20s | %-20s | %-50s |\n", "MT" + mountainPeakCode, mountain, province, description);
    }
}

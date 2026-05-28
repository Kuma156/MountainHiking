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
        return mountainPeakCode;
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
        return "Mountain{" + "mountainPeakCode=" + mountainPeakCode + ", mountain=" + mountain + ", province=" + province + ", description=" + description + '}';
    }

}


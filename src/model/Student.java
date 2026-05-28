package model;

import controller.Acceptable;
import java.io.Serializable;

/**
 *
 * @author The Miracle Invoker
 */
public class Student implements Serializable {

    private final String id;
    private String name;
    private String phone;
    private String email;
    private String mountainPeakCode;
    private double tutionFee;

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String name, String phone, String email, String mountainPeakCode) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mountainPeakCode = mountainPeakCode;
        this.tutionFee = 6000000;
        if (Acceptable.isValid(this.phone, Acceptable.VIETTEL_VALID) || Acceptable.isValid(this.phone, Acceptable.VNPT_VALID)) {
            this.tutionFee = this.tutionFee * 0.65;
        }

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMountainCode() {
        return mountainPeakCode;
    }

    public void setMountainPeakCode(String mountainPeakCode) {
        this.mountainPeakCode = mountainPeakCode;
    }

    public double getTutionFee() {
        return tutionFee;
    }

    public void setTutionFee(double tutionFee) {
        this.tutionFee = tutionFee;
    }

    @Override
    public String toString() {
        if (this.mountainPeakCode.length() > 1) {
            return "--------------------------------------------------"
                    + "Student ID: " + id
                    + "Name=     : " + name
                    + "Phone     : " + phone
                    + "Mountain  : MT" + mountainPeakCode
                    + "Fee       : " + tutionFee
                    + "--------------------------------------------------";
        } else {
            return "--------------------------------------------------"
                    + "Student ID: " + id
                    + "Name=     : " + name
                    + "Phone     : " + phone
                    + "Mountain  : MT0" + mountainPeakCode
                    + "Fee       : " + tutionFee
                    + "--------------------------------------------------";
        }
    }

}


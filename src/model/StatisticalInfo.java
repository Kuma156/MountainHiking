package model;

/**
 *
 * @author The Miracle Invoker
 */
public class StatisticalInfo {

    private String mountainCode;
    private int numberOfStudent;
    private double totalCost;

    public StatisticalInfo() {
    }

    public StatisticalInfo(String mountainCode, int numberOfStudent, double totalCost) {
        this.mountainCode = mountainCode;
        this.numberOfStudent = numberOfStudent;
        this.totalCost = totalCost;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "StatisticalInfo{" + "mountainCode=" + mountainCode + ", numberOfStudent=" + numberOfStudent + ", totalCost=" + totalCost + '}';
    }

}

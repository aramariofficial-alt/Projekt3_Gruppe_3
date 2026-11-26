package dk.kea.projekt3_gruppe_3.Model;

public class Car {
    private int vehicleNumber;
    private String chassisNumber;
    private String brand;
    private String model;
    private String status;

    public Car(int vehicleNumber, String chassisNumber, String brand, String model, String status){
        this.vehicleNumber = vehicleNumber;
        this.chassisNumber = chassisNumber;
        this.brand = brand;
        this.model = model;
        this.status = status;
    }

    public Car() {

    }

    public int getVehicleNumber(int vehicleNumber) {
        return this.vehicleNumber;
    }

    public String getChassisNumber(String chassisNumber) {
        return this.chassisNumber;
    }

    public String getBrand(String brand) {
        return this.brand;
    }

    public String getModel(int model) {
        return this.model;
    }

    public String getStatus(String status) {
        return this.status;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setChasisNumber(String chasisNumber) {
        this.chassisNumber = chasisNumber;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

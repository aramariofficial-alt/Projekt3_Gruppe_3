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
    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
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

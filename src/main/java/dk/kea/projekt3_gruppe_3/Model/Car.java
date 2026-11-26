package dk.kea.projekt3_gruppe_3.Model;

public class Car {
    private int vehicleNumber;
    private String chassisNumber;
    private String brand;
    private String model;
    private CarStatus status;

    public Car(int vehicleNumber, String chassisNumber, String brand, String model, CarStatus status){
        this.vehicleNumber = vehicleNumber;
        this.chassisNumber = chassisNumber;
        this.brand = brand;
        this.model = model;
        this.status = status;
    }

    public Car() {

    }

    public int getVehicleNumber() {
        return this.vehicleNumber;
    }

    public String getChassisNumber() {
        return this.chassisNumber;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public CarStatus getStatus() {
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

    public boolean isAvailable() {
        return status == CarStatus.AVAILABLE;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

}

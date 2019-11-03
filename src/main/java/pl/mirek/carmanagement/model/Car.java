package pl.mirek.carmanagement.model;

public class Car {

    private long carId;
    private String carBrand;
    private String carModel;
    private long carMnfdYear;
    private java.sql.Timestamp lastUpdate = null;

    public Car(String carBrand, String carModel, long carMnfdYear) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carMnfdYear = carMnfdYear;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }


    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }


    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }


    public long getCarMnfdYear() {
        return carMnfdYear;
    }

    public void setCarMnfdYear(long carMnfdYear) {
        this.carMnfdYear = carMnfdYear;
    }


    public java.sql.Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(java.sql.Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "\nCar{" +
                "carId=" + carId +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carMnfdYear=" + carMnfdYear +
                '}';
    }
}

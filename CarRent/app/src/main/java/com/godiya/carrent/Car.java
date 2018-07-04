package com.godiya.carrent;

import java.io.Serializable;

/**
 * Created by DUTT PATEL on 11/4/2017.
 */

public class Car implements Serializable {
    String CarId;
    String CompanyName;
    String ModelName;
    String Seats;
    String Rate;
    String Mileage;
    String Gear;
    String Path;

    public  Car(){}
    public Car(String carId, String companyName, String modelName, String seats, String rate, String mileage, String gear, String path) {
        CarId = carId;
        CompanyName = companyName;
        ModelName = modelName;
        Seats = seats;
        Rate = rate;
        Mileage = mileage;
        Gear = gear;
        Path = path;
    }

    public String getCarId() {
        return CarId;
    }

    public void setCarId(String carId) {
        CarId = carId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public String getSeats() {
        return Seats;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getMileage() {
        return Mileage;
    }

    public void setMileage(String mileage) {
        Mileage = mileage;
    }

    public String getGear() {
        return Gear;
    }

    public void setGear(String gear) {
        Gear = gear;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}

package com.example.restaurantapplication;

import java.net.URL;

public class Plate {
    String plateName;
    double platePrice;
    String imageLink;
    String plateInformation;
    String code;

    public Plate() {
        this.plateName = plateName;
        this.platePrice = platePrice;
        this.imageLink = imageLink;
        this.plateInformation = plateInformation;
        this.code=code;
    }

    public String getPlateName() {
        return plateName;
    }

    public double getPlatePrice() {
        return platePrice;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getPlateInformation() {
        return plateInformation;
    }
    public String getCode(){ return code; }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public void setPlatePrice(double platePrice) {
        this.platePrice = platePrice;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setPlateInformation(String plateInformation) {
        this.plateInformation = plateInformation;
    }

    public void setCode(String code) { this.code = code; }
}

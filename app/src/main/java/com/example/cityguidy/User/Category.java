package com.example.cityguidy.User;

public class Category {
    String hotel,house,car,restaurants,hospital,bank;


    public Category(String hotel, String house, String car, String restaurants, String hospital, String bank) {
        this.hotel = hotel;
        this.house = house;
        this.car = car;
        this.restaurants = restaurants;
        this.hospital = hospital;
        this.bank = bank;
    }

    public String getHotel() {
        return hotel;
    }

    public String getHouse() {
        return house;
    }

    public String getCar() {
        return car;
    }

    public String getRestaurants() {
        return restaurants;
    }

    public String getHospital() {
        return hospital;
    }

    public String getBank() {
        return bank;
    }
}

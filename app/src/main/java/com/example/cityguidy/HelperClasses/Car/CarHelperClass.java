package com.example.cityguidy.HelperClasses.Car;

public class CarHelperClass {


    String name,city,price,image;

    public CarHelperClass(String image, String name, String city, String price) {
        this.image = image;
        this.name = name;
        this.city = city;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}

package com.gw.v3.vehicles;

public class Car extends Vehicle {
    public int quantity;
    public int yearOfManufacture;

    public Car(String name, int quantity, int yearOfManufacture) {
        super(name);
        this.quantity = quantity;
        this.yearOfManufacture = yearOfManufacture;
    }
}

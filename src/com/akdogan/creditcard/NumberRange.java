package com.akdogan.creditcard;

public class NumberRange {
    private int floor;
    private int ceiling;

    public NumberRange(int floor, int ceiling){
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public boolean isInRange(int numberToCheck){
        return (numberToCheck >= floor && numberToCheck <= ceiling);
    }

    public int getCeiling() {
        return this.ceiling;
    }
}

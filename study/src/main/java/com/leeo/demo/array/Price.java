package com.leeo.demo.array;

/**
 * Created by DELL on 2016/11/20.
 */
public class Price {
    final static Price instance = new Price(2.8d);
    static double initPrice = 20d;

    public double currentPrice;

    public Price(double discount) {
        this.currentPrice = initPrice - discount;
    }

    public static void main(String[] args) {
        System.out.println(Price.instance.currentPrice);

        Price p=new Price(2.8);
        System.out.println(p.currentPrice);
    }
}

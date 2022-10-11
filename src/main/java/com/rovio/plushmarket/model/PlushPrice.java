package com.rovio.plushmarket.model;

import lombok.Data;

@Data
public class PlushPrice extends Plush{
    private double price;

    public PlushPrice(String plushName, double price) {
        super(plushName);
        this.price = price;
    }
}

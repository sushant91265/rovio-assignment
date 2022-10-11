package com.rovio.plushmarket.model;

import lombok.Data;

@Data
public class SellAction extends Action {
    private String name = "sell";
    private String plushName;
    private double price;

    public SellAction(String actionName, String plushName, double price) {
        super(actionName);
        this.plushName = plushName;
        this.price = price;
    }
}

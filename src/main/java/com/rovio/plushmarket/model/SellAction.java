package com.rovio.plushmarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SellAction extends Action {
    private String plushName;
    private double price;

    public SellAction(final String plushName, final double price) {
        super("sell");
        this.plushName = plushName;
        this.price = price;
    }
}

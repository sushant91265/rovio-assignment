package com.rovio.plushmarket.model;

import lombok.Data;

@Data
public class TradeAction extends Action{
    private String name = "trade";
    private String give;
    private String take;

    public TradeAction(String actionName, String give, String take) {
        super(actionName);
        this.give = give;
        this.take = take;
    }
}
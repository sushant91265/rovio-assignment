package com.rovio.plushmarket.model;

import java.util.List;

import lombok.Data;

@Data
public class Market {
    List<Plush> plushes;
    List<Trade> trades;

    public void addPlush(Plush plush) {
        this.plushes.add(plush);
    }

    public void addTrade(Trade trade) {
        this.trades.add(trade);
    }
}

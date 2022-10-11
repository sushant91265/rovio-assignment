package com.rovio.plushmarket.model;

import java.util.List;

import lombok.Data;

@Data
public class Market {
    List<PlushPrice> plushesPrices;
    List<Trade> trades;

    public void addPlush(PlushPrice plushPrice) {
        this.plushesPrices.add(plushPrice);
    }

    public void addTrade(Trade trade) {
        this.trades.add(trade);
    }
}

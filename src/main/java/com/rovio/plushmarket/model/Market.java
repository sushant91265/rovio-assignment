package com.rovio.plushmarket.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Market {
    @JsonProperty("plushes")
    private List<PlushPrice> plushes;
    @JsonProperty("trades")
    private List<Trade> trades;

    public void addPlush(final PlushPrice plushPrice) {
        this.plushes.add(plushPrice);
    }

    public void addTrade(final Trade trade) {
        this.trades.add(trade);
    }
}

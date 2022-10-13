package com.rovio.plushmarket.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Market {
    @JsonProperty("plushes")
    private List<PlushPrice> plushes;
    @JsonProperty("trades")
    private List<Trade> trades;

    public void addPlush(final PlushPrice plushPrice) {
        if(Objects.isNull(this.plushes)) {
            this.plushes = new ArrayList<>();
        }
        this.plushes.add(plushPrice);
    }

    public void addTrade(final Trade trade) {
        if(Objects.isNull(this.trades)) {
            this.trades = new ArrayList<>();
        }
        this.trades.add(trade);
    }
}

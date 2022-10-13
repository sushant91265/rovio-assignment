package com.rovio.plushmarket.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.rovio.plushmarket.model.Action;
import com.rovio.plushmarket.model.ActionData;
import com.rovio.plushmarket.model.SellAction;
import com.rovio.plushmarket.model.TradeAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/*
 * Result class to formulate the result of the plush market and to decide the best strategy
 * based on either the minimum number of trades or the maximum profit.
 */
@Data
@AllArgsConstructor
@Slf4j
public class Result { 
    private final int trades;
    public final double price;
    public final List<String> paths;

    public boolean isBetterDealThan(final Result r) {
        // greater price or lesser trades
        if(!Objects.equals(price, r.price)) {
            return price > r.price;
        } else {
            return trades < r.trades;
        }
    }

    public ActionData createOutput() {
        if(Objects.isNull(this.getPaths()) || this.getPaths().isEmpty()) {
            return new ActionData(Collections.emptyList());
        }
        final int pathSize = this.getPaths().size();
        log.info("Resultant path: " + this.paths + " with total trades: " + pathSize);
        
        final List<Action> actions = new ArrayList<>(pathSize);
        for(int i=pathSize-2; i>=0; i--) {
            actions.add(new TradeAction(this.paths.get(i),this.paths.get(i+1)));
        }
        log.debug("Trade actions: " + actions);
        Collections.reverse(actions);
        actions.add(new SellAction(this.paths.get(pathSize-1), this.price));
        log.debug("Final actions: " + actions);

        return new ActionData(actions);
    }

    @Override
    public String toString() {
        return String.format("Result [trades=%s, price=%s, path=%s]", trades, price, paths);
    }
}

package com.rovio.plushmarket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rovio.plushmarket.model.ActionData;
import com.rovio.plushmarket.model.Market;
import com.rovio.plushmarket.model.Offer;
import com.rovio.plushmarket.model.Plush;
import com.rovio.plushmarket.model.PlushPrice;
import com.rovio.plushmarket.model.Trade;

public class PlushMarketImpl implements PlushMarket {
    private final Offer offer;
    private final Market market;

    private final Map<String, Set<String>> graph;
    private final Map<String, Double> prices;

    PlushMarketImpl(Offer offerJSON, Market marketJSON) {
        this.offer = offerJSON;
        this.market = marketJSON;
        
        this.graph = new HashMap<>();
        for(Trade trade : market.getTrades()) {
            graph.putIfAbsent(trade.getGive(), new HashSet<>());
            graph.get(trade.getGive()).add(trade.getTake());
            graph.putIfAbsent(trade.getTake(), new HashSet<>());
            graph.get(trade.getTake()).add(trade.getGive());
        }

        this.prices = new HashMap<>();
        for(PlushPrice plushPrice : market.getPlushesPrices()) {
            prices.put(plushPrice.getPlushName(), plushPrice.getPrice());
        }
    }

    @Override
    public String calculateStrategy() {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        for(Plush plus: offer.getPlushes()) {
            getOptimizedTrades(plus.getPlushName(), visited, path);
        }
        ActionData actionData = getResultData(finalResult);
        return mapper.writeValueAsString(actionData);
    }

    private void getOptimizedTrades(String plushName, Set<String> visited, List<String> path) {
        if(visited.contains(plushName)) {
            return;
        }
        // TODO
        visited.add(plushName);
        Set<String> nbrs = graph.getOrDefault(plushName, Collections.emptySet());
        for(String next: nbrs){
            path.add(plushName);
            getOptimizedTrades(next, visited, path);
            path.remove(path.size()-1);
        }
        visited.remove(plushName);
    }
}

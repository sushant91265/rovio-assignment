package com.rovio.plushmarket.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rovio.plushmarket.App;
import com.rovio.plushmarket.model.Market;
import com.rovio.plushmarket.model.Plush;
import com.rovio.plushmarket.model.PlushPrice;
import com.rovio.plushmarket.model.Trade;
import com.rovio.plushmarket.util.FileUtils;
import com.rovio.plushmarket.util.Validation;

import lombok.extern.slf4j.Slf4j;

/*
 * Plush market implementation, where we calculate the best strategy for the given offer and market.
 */
@Slf4j
public class PlushMarketImpl implements PlushMarket {
    private Result finalResult = new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, new ArrayList<>());

    private Map<String, Set<String>> plushTradeMappingGraph;
    private Map<String, Double> plushPriceMap;

    @Override
    public String calculateStrategy(String offerJSON, String marketJSON) {
        try {
            Plush offer = FileUtils.parseFile(offerJSON, Plush.class);
            Market market = FileUtils.parseFile(marketJSON, Market.class);

            Validation.validateInput(offer, market);
            log.info("****** Plush Market ******");
            log.info("Plush Offer: " + offer);

            init(market);

            Set<String> visited = new HashSet<>();
            List<String> path = new ArrayList<>();
            
            getOptimizedTrades(offer.getPlush(), visited, path);
            return App.mapper.writeValueAsString(finalResult.createOutput());
        } catch (IOException e) {
            log.error("Error while calculating the strategy", e);
            throw new RuntimeException(e);
        } finally {
            this.plushTradeMappingGraph = null;
            this.plushPriceMap = null;
            finalResult = new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, new ArrayList<>());
        }
    }

    private void init(Market market) {
        // create a plush mapping graph out of the trades
        this.plushTradeMappingGraph = new HashMap<>();
        for(Trade trade : market.getTrades()) {
            plushTradeMappingGraph.putIfAbsent(trade.getGive(), new HashSet<>());
            plushTradeMappingGraph.get(trade.getGive()).add(trade.getTake());
            plushTradeMappingGraph.putIfAbsent(trade.getTake(), new HashSet<>());
            plushTradeMappingGraph.get(trade.getTake()).add(trade.getGive());
        }
        
        // create a plush price mapping
        this.plushPriceMap = new HashMap<>();
        for(PlushPrice plushPrice : market.getPlushes()) {
            plushPriceMap.put(plushPrice.getPlush(), plushPrice.getPrice());
        }
    }

    /*
     * Recursive method to find the best strategy for the given offer and market.
     * @param plush
     */
    private void getOptimizedTrades(String plush, Set<String> visited, List<String> path) {
        if(visited.contains(plush)) {
            return;
        }

        // check plushes and see if we can get a better result
        if(plushPriceMap.containsKey(plush)){
            Result intermediateResult = new Result(path.size()+1, plushPriceMap.get(plush), 
                new ArrayList<String>(path){{add(plush);}}
                );
            if(intermediateResult.isBetterDealThan(finalResult)){
                finalResult=intermediateResult;
                log.info("Final result: "+finalResult);
            }
        }

        // check trades recursively
        visited.add(plush);
        Set<String> connectedNodes = plushTradeMappingGraph.getOrDefault(plush, Collections.emptySet());
        for(String nextNode: connectedNodes) {
            path.add(plush);
            getOptimizedTrades(nextNode, visited, path);
            path.remove(path.size()-1);
        }
        visited.remove(plush);
    }
}

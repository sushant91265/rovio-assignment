package com.rovio.plushmarket;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rovio.plushmarket.service.PlushMarket;
import com.rovio.plushmarket.service.PlushMarketImpl;

/*
 * This is the main class of the application. 
 */
public class App {
    // TODO logger level
    
    private static final String DEFAULT_OFFER_JSON = "src/main/resources/offerJson.json";
    private static final String DEFAULT_MARKET_JSON = "src/main/resources/marketJson.json";
    public static ObjectMapper mapper = JsonMapper.builder()
                            .findAndAddModules()
                            .build();
    /*
     * Main method which will be executed.
     * @param args
     *  - args[0] - path to offer json file
     *  - args[1] - path to market json file
     * @throws IOException
     */
    public static void main( String[] args ) throws IOException
    {   
        mapper.findAndRegisterModules();

        String offerJson = args.length>0 ? args[0] : DEFAULT_OFFER_JSON;
        String marketJson = args.length>1 ? args[1] : DEFAULT_MARKET_JSON;

        PlushMarket plushMarket = new PlushMarketImpl();
        System.out.println(plushMarket.calculateStrategy(offerJson, marketJson));
    }
}

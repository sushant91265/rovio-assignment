package com.rovio.plushmarket;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rovio.plushmarket.model.Market;
import com.rovio.plushmarket.model.Offer;
import com.rovio.plushmarket.util.Validation;

public class App {
    
    private static final String DEFAULT_OFFER_JSON = "src/main/resources/offerJson.json";
    private static final String DEFAULT_MARKET_JSON = "src/main/resources/marketJson.json";
    private static ObjectMapper mapper = JsonMapper.builder()
                            .findAndAddModules()
                            .build();

    /*
     * Main method which will be executed.
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws IOException
    {   
        mapper.findAndRegisterModules();

        String offerJson = args.length>0 ? args[0] : DEFAULT_OFFER_JSON;
        String marketJson = args.length>1 ? args[1] : DEFAULT_MARKET_JSON;
        Offer offer = parseFile(offerJson, Offer.class);
        Market market = parseFile(marketJson, Market.class);

        Validation.validateInput(offer, market);

        PlushMarket plushMarket = new PlushMarketImpl();
        // print the output on console
        System.out.println(plushMarket.calculateStrategy(offer, market));
    }

    private static <T> T parseFile(String path, Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), cls);
    }
}

package com.rovio.plushmarket;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rovio.plushmarket.model.Market;
import com.rovio.plushmarket.model.Offer;
import com.rovio.plushmarket.model.Response;
import com.rovio.plushmarket.util.Validation;

public class App {
    
    private static final String DEFAULT_OFFER_JSON = "offer.json";
    private static final String DEFAULT_MARKET_JSON = "market.json";
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
        Offer offer = parseOfferFile(args.length>=1 ? args[0] : DEFAULT_OFFER_JSON);
        Market market = parseMarketFile(args.length>=2 ? args[1] : DEFAULT_MARKET_JSON);
        Validation.validateInput(offer, market);

        PlushMarket plushMarket = new PlushMarketImpl();
        Response response = plushMarket.calculateStrategy(offer, market);
        // print the output on console
        System.out.println(response);
    }

    /*
     * This method will parse the input file and return the request model object.
     * @param inputFileName
     * @return Offer object
     */
    static Offer parseOfferFile(String inputFile) throws IOException {
        return mapper.readValue(new File(inputFile),Offer.class);
    }

    /*
     * This method will parse the input file and return the request model object.
     * @param inputFileName
     * @return Market object
     */
    static Market parseMarketFile(String inputFile) throws IOException {
        return mapper.readValue(new File(inputFile),Market.class);
    }
}

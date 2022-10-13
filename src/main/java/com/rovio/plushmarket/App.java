package com.rovio.plushmarket;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rovio.plushmarket.service.PlushMarket;
import com.rovio.plushmarket.service.PlushMarketImpl;
import com.rovio.plushmarket.util.FileUtils;

import lombok.extern.slf4j.Slf4j;

/*
 * This is the main class of the application.
 */
@Slf4j
public class App {
    private static final String DEFAULT_OFFER_JSON = "src/main/resources/offerJson.json";
    private static final String DEFAULT_MARKET_JSON = "src/main/resources/marketJson.json";
    public static final ObjectMapper mapper = JsonMapper.builder()
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

        String offerJsonFile = args.length>0 ? args[0] : DEFAULT_OFFER_JSON;
        String marketJsonFile = args.length>1 ? args[1] : DEFAULT_MARKET_JSON;
        String offerJson = FileUtils.readFile(offerJsonFile);
        String marketJson = FileUtils.readFile(marketJsonFile);

        PlushMarket plushMarket = new PlushMarketImpl();
        String actionJson = plushMarket.calculateStrategy(offerJson, marketJson);
        log.info("Output:"+actionJson);
    }
}

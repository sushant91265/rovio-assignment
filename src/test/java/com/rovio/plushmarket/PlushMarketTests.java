package com.rovio.plushmarket;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rovio.plushmarket.model.ActionData;
import com.rovio.plushmarket.service.PlushMarket;
import com.rovio.plushmarket.service.PlushMarketImpl;

public class PlushMarketTests {

    private PlushMarket plushMarket;
    private String offerJSON;
    private String marketJSON;
    private ObjectMapper mapper;
    
    @BeforeAll
    public void setup() {
        plushMarket = new PlushMarketImpl();
        offerJSON = "src/test/java/com/rovio/plushmarket/resources/offer.json";
        marketJSON = "src/test/java/com/rovio/plushmarket/resources/market.json";
        mapper = JsonMapper.builder()
                            .findAndAddModules()
                            .build();
    }

    @Test
    void testCalculateStrategy() throws IOException {
        String result = plushMarket.calculateStrategy(offerJSON, marketJSON);
        assert result != null;
        // ActionData actionData = FileUtils.parseFile(offerJSON, ActionData.class);
    }
}

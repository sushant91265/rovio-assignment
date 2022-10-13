package com.rovio.plushmarket;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.rovio.plushmarket.service.PlushMarket;
import com.rovio.plushmarket.service.PlushMarketImpl;
import com.rovio.plushmarket.util.FileUtils;

public class PlushMarketTests {

    private static PlushMarket plushMarket;

    @BeforeAll
    public static void setup() {
        plushMarket = new PlushMarketImpl();
    }


    @Test
    public void testCalculateStrategy() throws IOException {
        for(int i = 1; i<12; i++) {
            String offerJSON = "src/test/java/com/rovio/plushmarket/resources/case"+i+"/offer.json";
            String marketJSON = "src/test/java/com/rovio/plushmarket/resources/case"+i+"/market.json";
            String actionJSON = "src/test/java/com/rovio/plushmarket/resources/case"+i+"/actions.json";

            String result = plushMarket.calculateStrategy(offerJSON, marketJSON);
            assert result != null;
            String expected = FileUtils.readFile(actionJSON);
            assert result.equals(expected);
        }
    }
}

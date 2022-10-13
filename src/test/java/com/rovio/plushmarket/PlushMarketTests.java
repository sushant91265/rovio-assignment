package com.rovio.plushmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.rovio.plushmarket.service.PlushMarket;
import com.rovio.plushmarket.service.PlushMarketImpl;
import com.rovio.plushmarket.util.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlushMarketTests {

    private static PlushMarket plushMarket;

    @BeforeAll
    public static void setup() {
        plushMarket = new PlushMarketImpl();
    }

    @Test
    public void testCalculateStrategy() throws IOException {
        for(int i = 1; i<12; i++) {
            String offerJSON = FileUtils.readFile("src/test/java/com/rovio/plushmarket/resources/case"+i+"/offer.json");
            String marketJSON = FileUtils.readFile("src/test/java/com/rovio/plushmarket/resources/case"+i+"/market.json");
            String expectedActionJSON = FileUtils.readFile("src/test/java/com/rovio/plushmarket/resources/case"+i+"/actions.json");

            log.info("####### RUNNING TEST CASE "+i+" ########");

            String actualActionJSON = plushMarket.calculateStrategy(offerJSON, marketJSON);
            assertEquals(expectedActionJSON, actualActionJSON);
        }
    }
}

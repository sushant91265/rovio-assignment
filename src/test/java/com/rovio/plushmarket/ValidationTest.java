package com.rovio.plushmarket;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rovio.plushmarket.model.Market;
import com.rovio.plushmarket.model.Plush;
import com.rovio.plushmarket.model.PlushPrice;
import com.rovio.plushmarket.util.Validation;

/*
 * This class is used to unit test the validation of the input data
 * as per the rules defined in the Validation.java class. 
 */
public class ValidationTest {
    @Test
    public void shouldThrowExceptionWhenInputIsNull() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validation.validateInput(null,null);
        });
        String expectedMessage = "Offer cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWhenPlushIsEmpty() {
        Plush offer = new Plush();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validation.validateInput(offer,new Market());
        });
        String expectedMessage = "Plush cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWhenMarketIsNull() {
        Plush offer = new Plush();
        offer.setPlush("plush");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validation.validateInput(offer,null);
        });
        String expectedMessage = "Market cannot be null";
        String actualMessage = exception.getMessage();
        System.out.println(expectedMessage+" "+actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWhenMarketPlushesAreNull() {
        Plush offer = new Plush();
        offer.setPlush("plush");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validation.validateInput(offer,new Market());
        });
        String expectedMessage = "Plushes cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWhenMarketTradesAreNull() {
        Plush offer = new Plush();
        offer.setPlush("plush");
        Market market = new Market();
        market.addPlush(new PlushPrice());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validation.validateInput(offer,market);
        });
        String expectedMessage = "Trades cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

package com.rovio.plushmarket.util;

import java.util.Objects;

import com.rovio.plushmarket.model.Market;
import com.rovio.plushmarket.model.Plush;

/*
 * This class will validate the input request model based on the following criteria:
 * 1. The request model should not be null.
 * 2. The request model should have at least one value.
 */
public class Validation {
    public static void validateInput(final Plush offer, final Market market) {
        Objects.requireNonNull(offer, "Offer cannot be null");
        Objects.requireNonNull(market, "Market cannot be null");
        Objects.requireNonNull(market.getPlushes(), "Plushes cannot be null");
        Objects.requireNonNull(market.getTrades(), "Trades cannot be null");
        Objects.requireNonNull(market.getPlushes().get(0), "Plushes cannot be empty");
        Objects.requireNonNull(market.getTrades().get(0), "Trades cannot be empty");
        Objects.requireNonNull(offer.getPlush(), "Plush cannot be null");
    }
}

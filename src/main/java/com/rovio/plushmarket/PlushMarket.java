package com.rovio.plushmarket;

import com.rovio.plushmarket.model.Market;
import com.rovio.plushmarket.model.Offer;
import com.rovio.plushmarket.model.Response;

/**
 * PlushMarket interface.
 */
public interface PlushMarket {

    /**
     * Returns sales strategy in JSON format.
     *
     * @param offerJSON for example: <CODE>
     * {"plush": "RedBird"}
     * </CODE>
     * @param marketJSON for example: <CODE>
     * {"plushes": [
     *   {"plush": "RedBird", "price": 80},
     *   {"plush": "Stella", "price": 90},
     *   {"plush": "Pig", "price": 75}
     * ],
     * "trades": [
     *   {"take": "RedBird", "give": "Pig"},
     *   {"take": "Pig", "give": "Stella"}
     * ]}
     * </CODE>
     * @return strategyJSON containing the list of actions to take to get the best value. For the example inputs it would be:<CODE>
     * {"actions": [
     *    {"action": "trade", "give": "RedBird", "take": "Pig"},
     *    {"action": "trade", "give": "Pig", "take": "Stella"},
     *    {"action": "sell", "plush": "Stella", "price": 90}
     * ]}
     * </CODE>
     */
    Response calculateStrategy(Offer offerJSON, Market marketJSON);
}

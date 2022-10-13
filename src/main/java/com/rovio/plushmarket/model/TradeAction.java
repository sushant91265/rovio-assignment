package com.rovio.plushmarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TradeAction extends Action{
    private String give;
    private String take;

    public TradeAction(final String give, final String take) {
        super("trade");
        this.give = give;
        this.take = take;
    }
}

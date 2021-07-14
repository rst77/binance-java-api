package com.binance.api.client.domain.event;

import java.io.Serializable;

public class TickerEventKey implements Serializable {

    private long eventTime;
    private String symbol;

    public TickerEventKey(long eventTime, String symbol) {
        this.eventTime = eventTime;
        this.symbol = symbol;
    }

    public long getEventTime() {
        return eventTime;
    }
    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

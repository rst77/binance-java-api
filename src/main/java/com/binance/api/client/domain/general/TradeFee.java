package com.binance.api.client.domain.general;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * Dust transfer result information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeFee implements Serializable {

    private String symbol;
    private String makerCommission;
    private String takerCommission;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMakerCommission() {
        return makerCommission;
    }

    public void setMakerCommission(String makerCommission) {
        this.makerCommission = makerCommission;
    }

    public String getTakerCommission() {
        return takerCommission;
    }

    public void setTakerCommission(String takerCommission) {
        this.takerCommission = takerCommission;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("makerCommission", makerCommission)
                .append("takerCommission", takerCommission)
                .toString();
    }

}
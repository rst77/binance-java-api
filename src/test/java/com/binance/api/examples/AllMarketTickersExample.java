package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.TickerEvent;
import com.binance.api.client.domain.event.TickerEventKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * All market tickers channel examples.
 *
 * It illustrates how to create a stream to obtain all market tickers.
 */
public class AllMarketTickersExample {

  public static void main(String[] args) {

    NamedCache<TickerEventKey, TickerEvent> map = CacheFactory.getCache("welcomes");
    
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiWebSocketClient client = factory.newWebSocketClient();

    client.onAllMarketTickersEvent(event -> {
      for(TickerEvent te : event) {
      /*  
        if (te.getSymbol().equals("ETHBTC")) {
          System.out.println( te );
        }
      */
        map.put( new TickerEventKey( te.getEventTime(), te.getSymbol() ), te );
      }
      //System.out.print(te.getSymbol() + ", ");

      System.out.print("\n#");
    });
  }
}

package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.TickerEvent;
import com.binance.api.client.domain.event.TickerEventKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.net.Session;
import com.tangosol.net.topic.NamedTopic;
import com.tangosol.net.ValueTypeAssertion;
import com.tangosol.net.topic.Publisher;

/**
 * All market tickers channel examples.
 *
 * It illustrates how to create a stream to obtain all market tickers.
 */
public class AllMarketTickersTopic {

  public static long biggerts = 0;
  public static long currentts = 0;

  public static void main(String[] args) {

    NamedCache<TickerEventKey, TickerEvent> map = CacheFactory.getCache("welcomes");
    
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiWebSocketClient client = factory.newWebSocketClient();

    client.onAllMarketTickersEvent(event -> {
      for(TickerEvent te : event) {
        map.put( new TickerEventKey( te.getEventTime(), te.getSymbol() ), te );
      
        if (currentts < te.getEventTime() ) {
          currentts = te.getEventTime();
        }

      }
      System.out.print( "\n# " + event.size() );
      if ( biggerts == 0 || biggerts < currentts)
      {
        System.out.print(" - biggerts lower = " + currentts );
        System.out.print(" - diff = " + (biggerts - currentts) );
        biggerts = currentts;
      }
    });
  }
}

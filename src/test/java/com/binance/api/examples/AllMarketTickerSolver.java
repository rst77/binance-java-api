package com.binance.api.examples;

import com.binance.api.client.domain.event.TickerEvent;
import com.binance.api.client.domain.event.TickerEventKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.ValueExtractor;

public class AllMarketTickerSolver {

    public static void main(String[] args) {

        NamedCache<TickerEventKey, TickerEvent> map = CacheFactory.getCache("welcomes");
        while( true ) {
            ValueExtractor<TickerEvent, Long> ageExtractor = TickerEvent::getEventTime;
            long max = map.stream().mapToLong(entry -> entry.extract(ageExtractor)).max().getAsLong();
            System.out.println(" - " + max);

            try {
                Thread.sleep(1000);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

}

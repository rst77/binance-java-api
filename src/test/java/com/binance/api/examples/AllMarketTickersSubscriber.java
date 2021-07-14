package com.binance.api.examples;

import com.binance.api.client.domain.event.TickerEvent;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.net.Session;
import com.tangosol.net.topic.NamedTopic;
import com.tangosol.net.topic.Subscriber;
import com.tangosol.net.ValueTypeAssertion;

import com.tangosol.net.topic.Subscriber.Name;
import com.tangosol.util.Base;

public class AllMarketTickersSubscriber {

    public static void main(String[] args) {
        
        System.out.println("###########################################################");

        Session session = Session.create();
        NamedTopic<TickerEvent>  tickerEventTopic = session.getTopic("tickerEventTopic", ValueTypeAssertion.withType(TickerEvent.class));

        //Subscriber.Option subscriberGroup = Name.of("JobQueue");
        Subscriber<TickerEvent> subscriber = tickerEventTopic.createSubscriber();

        subscriber.onClose(() -> System.out.println(System.lineSeparator() + "Closing subscriber for group subscription JobQueue"));

        System.out.println(System.lineSeparator() + "Started group subscriber for topic " + tickerEventTopic.getName() + " group subscription JobQueue");

        while (subscriber.isActive())
        {
            subscriber.receive().handle((Subscriber.Element<TickerEvent> v, Throwable t) ->
            {
                TickerEvent te = v.getValue();

                System.out.println( te.getSymbol() );

                return null;
            });
        }


    }
}

package com.spring.elasticSearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by tboss on 2017/10/28.
 */
@Component
public class ElasticSearchClient{

    public TransportClient getClient() {
        Settings settings = Settings.builder().put("cluster.name","tyw").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        try {
            InetSocketTransportAddress master = new InetSocketTransportAddress(
                    InetAddress.getByName("127.0.0.1"), 9300);
            InetSocketTransportAddress slave1 = new InetSocketTransportAddress(
                    InetAddress.getByName("127.0.0.1"), 8100);
            InetSocketTransportAddress slave2 = new InetSocketTransportAddress(
                    InetAddress.getByName("127.0.0.1"), 8200);
            client.addTransportAddress(master);
            client.addTransportAddress(slave1);
            client.addTransportAddress(slave2);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }
}

package com.spring.elasticSearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by tboss on 2017/10/28.
 */
@Service
public class ElasticSearchClient{

    public TransportClient getClient() {
        Settings settings = Settings.builder().put("cluster.name","tyw").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        try {
            InetSocketTransportAddress node = new InetSocketTransportAddress(
                    InetAddress.getByName("127.0.0.1"), 9300);
            client.addTransportAddress(node);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }
}

package com.weatherapp;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestHandler {
    public CloseableHttpResponse executeGet(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        return httpClient.execute(httpGet);
    }
}
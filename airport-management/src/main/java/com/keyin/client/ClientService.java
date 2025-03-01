package com.keyin.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private static final String BASE_URL = "http://localhost:8080/api"; // Ensure this matches your server URL
    private final CloseableHttpClient httpClient;

    public ClientService() {
        this.httpClient = HttpClients.createDefault();
    }

    public String getAirportsInCities() throws Exception {
        return executeRequest("/airports-in-cities");
    }

    public String getAircraftPassengersTravelledOn() throws Exception {
        return executeRequest("/aircraft-passengers-travelled-on");
    }

    public String getAirportsForAircraft() throws Exception {
        return executeRequest("/airports-for-aircraft");
    }

    public String getAirportsUsedByPassengers() throws Exception {
        return executeRequest("/airports-used-by-passengers");
    }

    private String executeRequest(String endpoint) throws Exception {
        HttpGet request = new HttpGet(BASE_URL + endpoint);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return EntityUtils.toString(response.getEntity());
            } else {
                String errorMessage = EntityUtils.toString(response.getEntity());
                logger.error("Error fetching data from {}: {} - {}", endpoint, statusCode, errorMessage);
                throw new IOException("Error fetching data from " + endpoint + ": " + statusCode + " - " + errorMessage);
            }
        }
    }
}
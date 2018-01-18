package edu.nc.servicebus.model.sender;

import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.response.HttpResponse;
import edu.nc.servicebus.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpSender implements Sender{

    private Response resp;

    @Override
    public Response send(Request request) {
        try {
            URL url = new URL(request.getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            resp =  new HttpResponse(response.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
        return resp;
    }
}

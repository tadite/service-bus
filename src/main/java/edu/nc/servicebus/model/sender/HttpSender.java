package edu.nc.servicebus.model.sender;

import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.response.HttpResponse;
import edu.nc.servicebus.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpSender implements Sender {
    @Override
    public Response send(Request request) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                request.getUrl(),
                String.class);

        if (response.getStatusCode()== HttpStatus.OK)
            return new HttpResponse(response.getBody());
        else
            return new HttpResponse("");
    }
}

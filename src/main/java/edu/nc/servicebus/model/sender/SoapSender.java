package edu.nc.servicebus.model.sender;


import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.response.Response;
import edu.nc.servicebus.model.response.SoapResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SoapSender implements Sender{

    private String postBody;

    public SoapSender(String postBody){
        this.postBody = postBody;
    }

    @Override
    public Response send(Request request) {
        Response response = new SoapResponse(null);
        try {
            URL url = new URL(request.getUrl());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            OutputStream out = conn.getOutputStream();
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
            outWriter.write(postBody);

            outWriter.flush();
            outWriter.close();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();

            while ((inputLine = reader.readLine()) != null){
                responseBuffer.append(inputLine);
            }

            response.setRawData(responseBuffer.toString());
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
}

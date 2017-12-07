package edu.nc.servicebus;

import edu.nc.servicebus.model.request.AppendRequestFilter;
import edu.nc.servicebus.model.request.HttpRequest;
import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.source.RestSource;
import edu.nc.servicebus.model.source.Source;
import org.junit.Assert;
import org.junit.Test;

public class AppendRequestFilterTests {

    @Test
    public void canAppendStringToUrl(){
        //Array
        String stringToAppend = "/users";
        String url = "https://stackoverflow.com";
        String expectedUrl = "https://stackoverflow.com/users";
        AppendRequestFilter appendRequestFilter = new AppendRequestFilter(stringToAppend);
        Source source = new RestSource(url);
        Request request = new HttpRequest(source);

        //Act
        request = appendRequestFilter.filter(request);

        //Assert
        Assert.assertEquals(expectedUrl,request.getUrl());
    }
}

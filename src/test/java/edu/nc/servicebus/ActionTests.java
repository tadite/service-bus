package edu.nc.servicebus;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.HttpAction;
import edu.nc.servicebus.model.request.AppendRequestFilter;
import edu.nc.servicebus.model.request.HttpRequest;
import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.request.RequestFilter;
import edu.nc.servicebus.model.response.JsonPathResponseFilter;
import edu.nc.servicebus.model.response.Response;
import edu.nc.servicebus.model.response.ResponseFilter;
import edu.nc.servicebus.model.sender.HttpSender;
import edu.nc.servicebus.model.sender.Sender;
import edu.nc.servicebus.model.source.RestSource;
import edu.nc.servicebus.model.source.Source;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class ActionTests {


    @Test
    public void canExecuteHttpActionFromRestApi(){
        //Array
        Source source = new RestSource("https://jsonplaceholder.typicode.com");
        Request request = new HttpRequest(source);

        Sender sender = new HttpSender();

        List<RequestFilter> requestFilterList = new LinkedList<>();
        requestFilterList.add(new AppendRequestFilter("/users"));

        List<ResponseFilter> responseFilters = new LinkedList<>();
        responseFilters.add(new JsonPathResponseFilter("$..email"));

        Action action = new HttpAction(request, sender, requestFilterList, responseFilters, (double) 5);

        String expectedRawData = "[" +
                "\"Sincere@april.biz\"," +
                "\"Shanna@melissa.tv\"," +
                "\"Nathan@yesenia.net\"," +
                "\"Julianne.OConner@kory.org\"," +
                "\"Lucio_Hettinger@annie.ca\"," +
                "\"Karley_Dach@jasper.info\"," +
                "\"Telly.Hoeger@billy.biz\"," +
                "\"Sherwood@rosamond.me\"," +
                "\"Chaim_McDermott@dana.io\"," +
                "\"Rey.Padberg@karina.biz\"" +
                "]";
        //Act
        Response response = action.execute();
        //Assert
        Assert.assertEquals(expectedRawData,response.getRawData());
    }
}

package edu.nc.servicebus;

import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.parser.JsonActionFactory;
import edu.nc.servicebus.model.parser.JsonReader;
import edu.nc.servicebus.model.request.HttpRequest;
import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.response.Response;
import edu.nc.servicebus.model.sender.HttpSender;
import edu.nc.servicebus.model.sender.Sender;
import edu.nc.servicebus.model.source.RestSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class JsonActionReaderTest {

    @Test
    public void executeActionFromJson() throws Exception{
        JsonActionFactory jsonActionFactory = new JsonActionFactory(new JsonReader());

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
        Action httpAction = jsonActionFactory.getAction("json-get-users");
        Response httpResponse = httpAction.execute();

        Assert.assertEquals(expectedRawData, httpResponse.getRawData());
    }
}

package edu.nc.servicebus;

import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.ActionFactory;
import edu.nc.servicebus.model.parser.JsonActionFactory;
import edu.nc.servicebus.model.parser.JsonReader;
import edu.nc.servicebus.model.request.HttpRequest;
import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.response.Response;
import edu.nc.servicebus.model.sender.HttpSender;
import edu.nc.servicebus.model.sender.Sender;
import edu.nc.servicebus.model.source.RestSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonActionReaderTest {

    @Autowired
    ActionFactory jsonActionFactory;

    @Test
    public void executeActionFromJson() throws Exception{

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

package edu.nc.servicebus;

import edu.nc.servicebus.controller.EndpointController;
import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.ActionReader;
import edu.nc.servicebus.model.response.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.mockito.BDDMockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Import(ServiceBusApplication.class)
public class RateLimiterManagerTests {

    @MockBean
    ActionReader actionReader;

    @Autowired
    EndpointController endpointController;

    //@Test
    public void canLimitRates() throws InterruptedException {
        //Array
        Action mockAction = mock(Action.class);
        when(mockAction.getRate()).thenReturn(Double.valueOf(1));
        when(mockAction.execute()).thenAnswer(invocationOnMock -> {
            TimeUnit.MILLISECONDS.sleep(10);
            return new HttpResponse("t");
        });
                
        String actionName = "test";
        given(actionReader.createByName(actionName)).willReturn(mockAction);

        //Act
        long startTime = ZonedDateTime.now().getSecond();
        IntStream.range(0, 6).forEach(i -> endpointController.endpoint(actionName));
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;

        //Assert
        Assert.assertTrue(elapsedTimeSeconds >= 5);
    }
}

package edu.nc.servicebus.model.response;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathResponseFilter implements ResponseFilter {

    private String expression;

    public JsonPathResponseFilter(String jsonPathExpression) {
        this.expression = jsonPathExpression;
    }

    @Override
    public Response filter(Response sourceResult) {
        DocumentContext document = JsonPath.parse(sourceResult.getRawData());
        sourceResult.setRawData(document.read(expression).toString());
        return sourceResult;
    }
}

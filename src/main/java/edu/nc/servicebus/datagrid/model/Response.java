package edu.nc.servicebus.datagrid.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.Date;

public class Response {

    /** Primary key. */
    @QuerySqlField(index = true)
    private int responseId;

    @QuerySqlField
    private String content;

    @QuerySqlField
    private Date time;

    public Response(int id, String  content, Date time){
        this.responseId = id;
        this.content = content;
        this.time = time;
    }

    public int getResponseId() {
        return responseId;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    @Override public String toString() {
        return "Response [responseId=" + responseId +
                ", time=" + time +
                ", content=" + content + ']';
    }
}

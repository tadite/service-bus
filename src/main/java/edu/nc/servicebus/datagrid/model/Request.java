package edu.nc.servicebus.datagrid.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.Date;

public class Request {

    /** Primary key. */
    @QuerySqlField(index = true)
    private int requestId;

    @QuerySqlField
    private String content;

    @QuerySqlField
    private Date time;

    public Request(int id, String  content, Date time){
        this.requestId = id;
        this.content = content;
        this.time = time;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    @Override public String toString() {
        return "Request [requestId=" + requestId +
                ", time=" + time +
                ", content=" + content + ']';
    }
}

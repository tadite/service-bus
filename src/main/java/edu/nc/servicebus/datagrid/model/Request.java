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

    @QuerySqlField
    private Date endTime;

    public Request(int id, String  content, Date time, Date emdTime){
        this.requestId = id;
        this.content = content;
        this.time = time;
        this.endTime = emdTime;
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

    public Date getEndTime() {
        return endTime;
    }

    @Override public String toString() {
        return "Request [requestId=" + requestId +
                ", time=" + time +
                ", endTime=" + endTime +
                ", content=" + content + ']';
    }
}

package edu.nc.servicebus.datagrid.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Response implements Comparable<Response>{

    /** Primary key. */
    @QuerySqlField(index = true)
    private int responseId;

    @QuerySqlField
    private String content;

    @QuerySqlField
    private Date time;

    @QuerySqlField
    private Date endTime;

    public Response(int id, String  content, Date time, Date endTime){
        this.responseId = id;
        this.content = content;
        this.time = time;
        this.endTime = endTime;
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

    public Date getEndTime() {
        return endTime;
    }

    @Override public String toString() {
        return "Response [responseId=" + responseId +
                ", time=" + time +
                ", endTime=" + endTime +
                ", content=" + content + ']';
    }

    @Override
    public int compareTo(@NotNull Response o) {
        return getTime().compareTo(o.getTime());
    }
}

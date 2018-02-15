package edu.nc.servicebus.datagrid.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class Log {
    /** Primary key. */
    @QuerySqlField(index = true)
    private int logId;

    /** Foreign key. */
    @QuerySqlField
    private int requestId;

    /** Foreign key. */
    @QuerySqlField
    private int responseId;

    /** Foreign key. */
    @QuerySqlField
    private int errorId;

    public Log(int id, int requestId, int responseId, int errorId){
        this.logId = id;
        this.errorId = errorId;
        this.requestId = requestId;
        this.responseId = responseId;
    }

    public int getLogId() {
        return logId;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getResponseId() {
        return responseId;
    }

    public int getErrorId() {
        return errorId;
    }

    @Override public String toString() {
        return "Log [logId=" + logId +
                ", requestId=" + requestId +
                ", responseId=" + responseId +
                ", errorId=" + errorId + ']';
    }
}

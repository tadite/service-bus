package edu.nc.servicebus.datagrid.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Error implements Comparable<Error>{
    /** Primary key. */
    @QuerySqlField(index = true)
    private int errorId;

    @QuerySqlField
    private String reason;

    @QuerySqlField
    private Date time;

    public Error(int id, String  reason, Date time){
        this.errorId = id;
        this.reason = reason;
        this.time = time;
    }


    public Date getTime() {
        return time;
    }

    public int getErrorId() {
        return errorId;
    }

    public String getReason() {
        return reason;
    }


    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override public String toString() {
        return "Error [errorId=" + errorId +
                ", time=" + time +
                ", reason=" + reason + ']';
    }

    @Override
    public int compareTo(@NotNull Error o) {
        return getTime().compareTo(o.getTime());
    }
}

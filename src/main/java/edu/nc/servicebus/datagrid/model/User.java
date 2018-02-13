package edu.nc.servicebus.datagrid.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.Date;

public class User {
    /** Primary key. */
    @QuerySqlField(index = true)
    private int userId;

    @QuerySqlField
    private String login;

    @QuerySqlField
    private String password;

    @QuerySqlField
    private Date dateOfRegistration;

    public User(int id,String login, String password, Date dateOfRegistration){
        this.userId = id;
        this.login = login;
        this.password = password;
        this.dateOfRegistration = dateOfRegistration;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    @Override public String toString() {
        return "User [userId=" + userId +
                ", login=" + login +
                ", password=" + password +
                ",dateOfRegistration=" + dateOfRegistration + ']';
    }
}

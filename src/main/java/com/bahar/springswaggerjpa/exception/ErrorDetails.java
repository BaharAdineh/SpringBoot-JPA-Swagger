package com.bahar.springswaggerjpa.exception;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: B_Adineh
 * Date: 12/14/2018
 * Time: 1:54 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}


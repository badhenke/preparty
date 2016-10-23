package com.forfesten.Models;

import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ErrorJson that is used to send to client if something has gone wrong.
 */
public class ErrorJson {

    private String timestamp;
    private String message;
    private String error;
    private int status;
    private String path;

    public ErrorJson(String message, String error, HttpStatus status, String path) {
        this.error = error;
        this.status = status.value();
        this.path = path;
        this.message = message;

        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse("20110327032913"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.timestamp = c1.getTimeInMillis() + "";

    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }
}

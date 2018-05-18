package com.xq.websocketchatdemo.po;

public class Response {

    private String message;

    private String fromUser;

    public Response(String message) {
        this.message = message;
    }


    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public Response(String message, String fromUser) {
        this.message = message;
        this.fromUser = fromUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

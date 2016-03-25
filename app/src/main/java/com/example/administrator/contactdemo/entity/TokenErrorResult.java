package com.example.administrator.contactdemo.entity;

public class TokenErrorResult {

    /**
     * description : PermissionError:this api need auth
     * message : 400: Bad Request
     */

    private String description;
    private String message;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}

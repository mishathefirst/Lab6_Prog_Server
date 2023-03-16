package com.lab6.server.entities;

public class ResponseObject {

    private String command;
    private String parameters;
    private int responseCode;

    public void setCommand(String command) {
        this.command = command;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getCommand() {
        return command;
    }

    public String getParameters() {
        return parameters;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public ResponseObject() {}

    public ResponseObject(String command, String parameters, int responseCode) {
        this.command = command;
        this.parameters = parameters;
        this.responseCode = responseCode;
    }
}

package com.burakkocak.eventdrivenms.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEntityResource {
    private String message;
    private ResultsListResource details = new ResultsListResource();

    public ResultsListResource getDetails() {
        return details;
    }

    public void setDetails(ResultsListResource details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

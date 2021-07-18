package com.burakkocak.eventdrivenms.resource.response;

import java.util.ArrayList;

public class ResultsListResource {
    private ArrayList<String> success = new ArrayList<>();
    private ArrayList<String> fail = new ArrayList<>();

    public ArrayList<String> getSuccess() {
        return success;
    }

    public void setSuccess(ArrayList<String> success) {
        this.success = success;
    }

    public ArrayList<String> getFail() {
        return fail;
    }

    public void setFail(ArrayList<String> fail) {
        this.fail = fail;
    }
}

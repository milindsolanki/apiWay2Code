package com.waytojob.bins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPassResponse {


    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }



}
		
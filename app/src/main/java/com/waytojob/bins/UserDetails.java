
package com.waytojob.bins;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseData")
    @Expose
    private List<ResponseDatum> responseData = null;

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

    public List<ResponseDatum> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<ResponseDatum> responseData) {
        this.responseData = responseData;
    }

}

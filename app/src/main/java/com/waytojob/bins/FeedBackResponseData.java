
package com.waytojob.bins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedBackResponseData {

    @SerializedName("vUserEmail")
    @Expose
    private String vUserEmail;
    @SerializedName("vUserFeedBack")
    @Expose
    private String vUserFeedBack;

    public String getVUserEmail() {
        return vUserEmail;
    }

    public void setVUserEmail(String vUserEmail) {
        this.vUserEmail = vUserEmail;
    }

    public String getVUserFeedBack() {
        return vUserFeedBack;
    }

    public void setVUserFeedBack(String vUserFeedBack) {
        this.vUserFeedBack = vUserFeedBack;
    }

}


package com.waytojob.bins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDatum {

    @SerializedName("I_User_id")
    @Expose
    private String iUserId;
    @SerializedName("V_Name")
    @Expose
    private String vName;
    @SerializedName("V_Email")
    @Expose
    private String vEmail;
    @SerializedName("V_Pass")
    @Expose
    private String vPass;
    @SerializedName("I_Mobile_Number")
    @Expose
    private String iMobileNumber;
    @SerializedName("V_Qualification")
    @Expose
    private String vQualification;
    @SerializedName("V_University_School")
    @Expose
    private String vUniversitySchool;
    @SerializedName("I_Pass_Year")
    @Expose
    private String iPassYear;
    @SerializedName("I_Worck_Exp")
    @Expose
    private String iWorckExp;
    @SerializedName("V_Address")
    @Expose
    private String vAddress;
    @SerializedName("V_City")
    @Expose
    private String vCity;
    @SerializedName("V_State")
    @Expose
        private String vState;
    @SerializedName("I_Pincode")
    @Expose
    private String iPincode;
    @SerializedName("V_CV")
    @Expose
    private String vCV;
    @SerializedName("I_Create_At")
    @Expose
    private String iCreateAt;
    @SerializedName("I_Update_At")
    @Expose
    private String iUpdateAt;

    public String getIUserId() {
        return iUserId;
    }

    public void setIUserId(String iUserId) {
        this.iUserId = iUserId;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName = vName;
    }

    public String getVEmail() {
        return vEmail;
    }

    public void setVEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String getVPass() {
        return vPass;
    }

    public void setVPass(String vPass) {
        this.vPass = vPass;
    }

    public String getIMobileNumber() {
        return iMobileNumber;
    }

    public void setIMobileNumber(String iMobileNumber) {
        this.iMobileNumber = iMobileNumber;
    }

    public String getVQualification() {
        return vQualification;
    }

    public void setVQualification(String vQualification) {
        this.vQualification = vQualification;
    }

    public String getVUniversitySchool() {
        return vUniversitySchool;
    }

    public void setVUniversitySchool(String vUniversitySchool) {
        this.vUniversitySchool = vUniversitySchool;
    }

    public String getIPassYear() {
        return iPassYear;
    }

    public void setIPassYear(String iPassYear) {
        this.iPassYear = iPassYear;
    }

    public String getIWorckExp() {
        return iWorckExp;
    }

    public void setIWorckExp(String iWorckExp) {
        this.iWorckExp = iWorckExp;
    }

    public String getVAddress() {
        return vAddress;
    }

    public void setVAddress(String vAddress) {
        this.vAddress = vAddress;
    }

    public String getVCity() {
        return vCity;
    }

    public void setVCity(String vCity) {
        this.vCity = vCity;
    }

    public String getVState() {
        return vState;
    }

    public void setVState(String vState) {
        this.vState = vState;
    }

    public String getIPincode() {
        return iPincode;
    }

    public void setIPincode(String iPincode) {
        this.iPincode = iPincode;
    }

    public String getVCV() {
        return vCV;
    }

    public void setVCV(String vCV) {
        this.vCV = vCV;
    }

    public String getICreateAt() {
        return iCreateAt;
    }

    public void setICreateAt(String iCreateAt) {
        this.iCreateAt = iCreateAt;
    }

    public String getIUpdateAt() {
        return iUpdateAt;
    }

    public void setIUpdateAt(String iUpdateAt) {
        this.iUpdateAt = iUpdateAt;
    }

}

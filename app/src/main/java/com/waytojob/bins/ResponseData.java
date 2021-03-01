
package com.waytojob.bins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("V_Name")
    @Expose
    private String vName;
    @SerializedName("V_Email")
    @Expose
    private String vEmail;
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
    @SerializedName("V_CV")
    @Expose
    private String vCV;

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

    public String getVCV() {
        return vCV;
    }

    public void setVCV(String vCV) {
        this.vCV = vCV;
    }

}

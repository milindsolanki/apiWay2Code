package com.waytojob.communicator

interface AdapterClicked {
    fun itemClick( vName: String, vEmail: String ,
                   iMobileNumber: String, vQualification: String , vUniversitySchool: String,
                   iPassYear: String, iWorckExp: String , vAddress: String,
                   vCity: String, vState: String , iPincode: String, vCV: String)
}
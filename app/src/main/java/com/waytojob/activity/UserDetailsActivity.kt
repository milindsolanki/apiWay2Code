package com.waytojob.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.waytojob.BaseActivity
import com.waytojob.R
import com.waytojob.helper.*
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.toolbar.*


class UserDetailsActivity : BaseActivity(), View.OnClickListener {
    private val STORAGE_PERMISSION_REQUEST_CODE = 1

    var permissionUtils: PermissionUtils? = null
    var vName: String=""
    var vEmail: String=""
    var iMobileNumber: String=""
    var vQualification: String=""
    var vUniversitySchool: String=""
    var iWorckExp: String=""
    var iPassYear: String=""
    var vAddress: String=""
    var vCity: String=""
    var vState: String=""
    var iPincode: String=""
    var vCV: String=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        permissionUtils = PermissionUtils()
        initListeners()
    }

    private fun initListeners() {

        vName=intent.getStringExtra(FULL_NAME)
        vEmail=intent.getStringExtra(USER_EMAIL)
        iMobileNumber=intent.getStringExtra(USER_MOBILE_NUMBER)
        vQualification=intent.getStringExtra(EDUCTION)
        vUniversitySchool=intent.getStringExtra(UNIVERCITY)
        iWorckExp =intent.getStringExtra(WORK_EXP)
        iPassYear=intent.getStringExtra(PASS_YEAR)
        vAddress=intent.getStringExtra(ADDRESS)
        vCity=intent.getStringExtra(CITY)
        vState=intent.getStringExtra(STATE)
        iPincode=intent.getStringExtra(PIN_CODE)
        vCV=intent.getStringExtra(USER_CV)


        txtName.text="Full Name : "+vName
        txtEmail.text="Email ID : "+vEmail
        txtMobileNumber.text="Mobile Number : "+iMobileNumber
        txtExprience.text="Work Exprience : "+iWorckExp
        txtEduction.text="Eduction : "+vQualification
        txtPassingYear.text="Passing Year : "+iPassYear
        txtUnivercity.text="University : "+vUniversitySchool
        txtAddress.text="Address : "+vAddress
        txtCity.text="City : "+vCity
        txtPinCode.text="Pin Code : "+iPincode
        txtState.text="Full Name : "+vState



        txtLeft.setOnClickListener(this)
        btnCVDownload.setOnClickListener(this)
        btnCVShare.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.txtLeft -> finish()

            R.id.btnCVDownload -> downloadCV(v)


            R.id.btnCVShare -> shareCV()
        }
    }

    private fun downloadCV(v: View) {
     //   DownloadTask(this@UserDetailsActivity, vCV)
        if (permissionUtils!!.checkPermission(this@UserDetailsActivity, STORAGE_PERMISSION_REQUEST_CODE,v)) {
            try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(vCV)))
                } catch (e: Exception) {
                    e.stackTrace
                }

        }
    }

    private fun shareCV() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
       val shareData="Full Name : "+vName +"/n" + "Email : " + vEmail +"/n" +
        "Mobile Number : "+ iMobileNumber+"/n"+ "Exprience : "+ iWorckExp
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareData)
        sendIntent.type = "text/plain"
        Intent.createChooser(sendIntent, "Share via")
        startActivity(sendIntent)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) { //Snackbar.make(urlTextInputLayout, "Permission Granted, Now you can write storage.", Snackbar.LENGTH_LONG).show();
            } else { //Snackbar.make(urlTextInputLayout, "Permission Denied, You cannot access storage.", Snackbar.LENGTH_LONG).show();
            }
        }
    }


}

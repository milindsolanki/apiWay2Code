package com.waytojob.authantication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ithub.api.APIService
import com.waytojob.BaseActivity
import com.waytojob.R
import com.waytojob.bins.ForgetPassResponse
import com.waytojob.helper.CommanUtils
import com.waytojob.helper.CommanUtils.isValidEmail
import com.waytojob.helper.RESPONSE_CODE
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgetPasswordActivity : BaseActivity(), View.OnClickListener {
    var apiService : APIService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        initControls()
    }

    private fun initControls() {
        txtLeft.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            txtLeft -> {
                if (isValid()) {
                    apiCalling()
                }
            }
        }
    }

    private fun apiCalling() {
        if (!CommanUtils.isInternetConnected(this, true))
            return

        showProgressDialog(this)
        val call = apiService!!.getUserFogotPassword(textInputEditTextEmail.text.toString())
        call.enqueue(object : Callback<ForgetPassResponse> {
            override fun onResponse(call: Call<ForgetPassResponse>, response: Response<ForgetPassResponse>) {
                Log.e("TAG", "Got error : " + response.body())
                hideProgressDialog()
                if (response.body()!!.responseCode==200){
                    finish()
                }else{
                    Toast.makeText(this@ForgetPasswordActivity,response.body()!!.responseMessage, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ForgetPassResponse>, t: Throwable) {
                hideProgressDialog()
                Log.e("TAG", "Got 0error : " + t.localizedMessage)
            }
        })
    }

    private fun isValid(): Boolean {
        if (textInputEditTextEmail.text.toString().trim().isEmpty()) {
            textInputEditTextEmail.requestFocus()
            textInputEditTextEmail.error = resources.getString(R.string.msg_blank_email)
        } else if (!isValidEmail(textInputEditTextEmail)) {
            textInputEditTextEmail.requestFocus()
            textInputEditTextEmail.error = resources.getString(R.string.msg_valid_email)
        } else {
            return true
        }
        return false
    }
}
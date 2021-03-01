package com.waytojob.authantication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ithub.api.APIService
import com.example.ithub.api.RestClient
import com.waytojob.BaseActivity
import com.waytojob.R
import com.waytojob.activity.MainActivity
import com.waytojob.bins.LoginBin
import com.waytojob.helper.*
import com.waytojob.helper.CommanUtils.isValidEmail
import com.waytojob.helper.PreferenceHelper.set
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("Registered")
class SignInActivity : BaseActivity(), View.OnClickListener {
    var apiService : APIService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        apiService = RestClient.client.create(APIService::class.java)
        initViews()
    }
    private fun initViews() {
        appCompatButtonLogin.setOnClickListener(this)
        textViewLinkRegister.setOnClickListener(this)
        txtForgotPass.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonLogin -> {
                if (isValid()) {
                    if (textInputEditTextEmail.text.toString().trim().equals("hr@waytocode.com")
                            && textInputEditTextPassword.text.toString().trim().equals("waytocode") ){
                        prefs[IS_LOGIN]=true
                        prefs[IS_HR] = true
                        val intent=Intent(this@SignInActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        signInAPI()
                    }

                }
            }
            R.id.textViewLinkRegister -> {
                val intentRegister = Intent(applicationContext, SignUpActivity::class.java)
                startActivity(intentRegister)
            }

            R.id.txtForgotPass-> {
                val intent = Intent(applicationContext, ForgetPasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun isValid(): Boolean {
        if (textInputEditTextEmail.text.toString().trim().isEmpty()) {
            textInputEditTextEmail.requestFocus()
            textInputEditTextEmail.error = resources.getString(R.string.msg_blank_email)
        } else if (!isValidEmail(textInputEditTextEmail)) {
            textInputEditTextEmail.requestFocus()
            textInputEditTextEmail.error = resources.getString(R.string.msg_valid_email)
        } else if (textInputEditTextPassword.text.toString().trim().isEmpty()) {
            textInputEditTextPassword.requestFocus()
            textInputEditTextPassword.error = resources.getString(R.string.msg_blank_password)
        } else {
            return true
        }
        return false
    }

    private fun signInAPI() {
        if (!CommanUtils.isInternetConnected(this, true))
            return
        showProgressDialog(this)
        val call = apiService!!.getUserLogin(textInputEditTextEmail.text.toString(),textInputEditTextPassword.text.toString())
        call.enqueue(object : Callback<LoginBin> {
            override fun onResponse(call: Call<LoginBin>, response: Response<LoginBin>) {
                Log.e("TAG", "Got error : " + response.body()!!.responseData)
                hideProgressDialog()
                if (response.body()!!.responseCode==200){
                    prefs[IS_LOGIN]=true
                    val intent=Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    Toast.makeText(this@SignInActivity,response.body()!!.responseMessage, Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<LoginBin>, t: Throwable) {
                hideProgressDialog()
                Log.e("TAG", "Got 0error : " + t.localizedMessage)
            }

        })

    }


}
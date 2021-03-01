package com.waytojob.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ithub.api.APIService
import com.example.ithub.api.RestClient
import com.waytojob.BaseFragment
import com.waytojob.R
import com.waytojob.bins.FeedBackBin
import com.waytojob.helper.CommanUtils
import com.waytojob.helper.CommanUtils.isValidEmail
import kotlinx.android.synthetic.main.fragment_feed_back.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class FeedBackFragment : BaseFragment() {
    var apiService : APIService? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_back, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = RestClient.client.create(APIService::class.java)

        btnSubmit.setOnClickListener {
            if (isValid()) {
                FeedBackApi()
            }
        }
    }

    private fun FeedBackApi() {
        if (!activity?.let { CommanUtils.isInternetConnected(it, true) }!!)
            return
        showProgressDialog(activity!!)
        val call = apiService!!.getFeedBack(edtEmail.text.toString(), edtMessage.text.toString())

        call.enqueue(object : Callback<FeedBackBin> {

            override fun onResponse(call: Call<FeedBackBin>, response: Response<FeedBackBin>) {
                    Log.e("TAG", "Got error : " + response.body()!!.responseData)
                    hideProgressDialog()
                    if (response.body()!!.responseCode == 200) {
                        Toast.makeText(activity, response.body()!!.responseMessage, Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(activity, response.body()!!.responseMessage, Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<FeedBackBin>, t: Throwable) {
                    hideProgressDialog()
                    Log.e("TAG", "Got 0error : " + t.localizedMessage)
                }
            })




    }
    private fun isValid(): Boolean {
        if (edtName.text.toString().trim().isEmpty()) {
            edtName.requestFocus()
            edtName.error = resources.getString(R.string.msg_blank_user_name)
        } else if (edtEmail.text.toString().trim().isEmpty()) {
            edtEmail.requestFocus()
            edtEmail.error = resources.getString(R.string.msg_blank_email)
        } else if (!isValidEmail(edtEmail)) {
            edtEmail.requestFocus()
            edtEmail.error = resources.getString(R.string.msg_valid_email)
        } else if (edtMessage.text.toString().trim().isEmpty()) {
            edtMessage.requestFocus()
            edtMessage.error = resources.getString(R.string.msg_blank_message)
        } else {
            return true
        }
        return false
    }
    }

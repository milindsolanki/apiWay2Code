package com.waytojob.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ithub.api.APIService
import com.example.ithub.api.RestClient
import com.waytojob.BaseFragment
import com.waytojob.R
import com.waytojob.activity.UserDetailsActivity
import com.waytojob.adapter.UsetAdapter
import com.waytojob.bins.ResponseDatum
import com.waytojob.bins.UserDetails
import com.waytojob.communicator.AdapterClicked
import com.waytojob.helper.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment : BaseFragment() , SearchView.OnQueryTextListener{
    val userList=ArrayList<ResponseDatum>()
    var apiService : APIService? = null
    var  usetAdapter: UsetAdapter?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = RestClient.client.create(APIService::class.java)

        dataSearch.setOnQueryTextListener(this)
        userRecyclerView.layoutManager= LinearLayoutManager(activity)
        usetAdapter= UsetAdapter(activity!!,userList,object : AdapterClicked{
            override fun itemClick(vName: String,  vEmail: String, iMobileNumber: String, vQualification: String, vUniversitySchool: String, iPassYear: String, iWorckExp: String, vAddress: String, vCity: String, vState: String, iPincode: String, vCV: String) {
                val intent = Intent(activity, UserDetailsActivity::class.java)
                intent.putExtra(FULL_NAME, vName)
                intent.putExtra(USER_EMAIL, vEmail)
                intent.putExtra(USER_MOBILE_NUMBER, iMobileNumber)
                intent.putExtra(EDUCTION, vQualification)
                intent.putExtra(PASS_YEAR, iPassYear)
                intent.putExtra(WORK_EXP, iWorckExp)
                intent.putExtra(ADDRESS, vAddress)
                intent.putExtra(CITY, vCity)
                intent.putExtra(STATE, vState)
                intent.putExtra(PIN_CODE, iPincode)
                intent.putExtra(UNIVERCITY, vUniversitySchool)
                intent.putExtra(USER_CV, vCV)
                startActivity(intent)
            }

        })
        userRecyclerView.adapter=usetAdapter
        userDataApi()
    }

    private fun userDataApi() {
        if (!activity?.let { CommanUtils.isInternetConnected(it, true) }!!)
            return

        showProgressDialog(activity!!)

        val call=apiService!!.getUserDetails("10")

        call.enqueue(object : Callback<UserDetails> {
            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                hideProgressDialog()
                Log.d("TAG",response.message().toString())
                userList.clear()
                if (response.body()!!.responseData.size<0) {
                    userRecyclerView.visibility=View.GONE
                    txtDataNotFound.visibility=View.VISIBLE
                }else {
                    userRecyclerView.visibility=View.VISIBLE
                    txtDataNotFound.visibility=View.GONE
                    userList.addAll(response.body()!!.responseData)
                    usetAdapter!!.notifyDataSetChanged()
                }

            }
            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                hideProgressDialog()
                Log.e("TAG", "Got error : " + t.localizedMessage)
            }


        })


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val userInput= newText!!.toLowerCase()
        val list=ArrayList<ResponseDatum>()


        for (data in userList) {
            if (data.iWorckExp.toLowerCase().contains(userInput) || data.vQualification.toLowerCase().contains(userInput)
                    ||  data.iPassYear.toLowerCase().contains(userInput))
                list.add(data)
        }



        usetAdapter!!.updateList(list)
        return true
    }
}



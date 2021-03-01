package com.waytojob.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.waytojob.BaseActivity
import com.waytojob.BaseFragment

import com.waytojob.R
import com.waytojob.helper.*
import com.waytojob.helper.PreferenceHelper.set
import com.waytojob.helper.PreferenceHelper.get
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * A simple [Fragment] subclass.
 */
class UserFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtName.text="Full Name : "+ prefs[FULL_NAME, ""]!!
        txtEmail.text="Email ID : "+prefs[USER_EMAIL, ""]!!
        txtMobileNumber.text="Mobile Number : "+prefs[USER_MOBILE_NUMBER, ""]!!
        txtExprience.text="Work Exprience : "+prefs[WORK_EXP, ""]!!
        txtEduction.text="Eduction : "+prefs[EDUCTION, ""]!!
        txtPassingYear.text="Passing Year : "+prefs[PASS_YEAR, ""]!!
        txtUnivercity.text="University : "+prefs[UNIVERCITY, ""]!!
        txtAddress.text="Address : "+prefs[ADDRESS, ""]!!
        txtCity.text="City : "+prefs[CITY, ""]!!
        txtPinCode.text="Pin Code : "+prefs[PIN_CODE, ""]!!
        txtState.text="Full Name : "+prefs[STATE, ""]!!

    }
}

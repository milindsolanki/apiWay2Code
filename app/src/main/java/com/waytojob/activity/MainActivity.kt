package com.waytojob.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.waytojob.BaseActivity
import com.waytojob.R
import com.waytojob.authantication.SignInActivity
import com.waytojob.fragment.AboutUsFragment
import com.waytojob.fragment.FeedBackFragment
import com.waytojob.fragment.HomeFragment
import com.waytojob.fragment.UserFragment
import com.waytojob.helper.IS_HR
import com.waytojob.helper.IS_LOGIN
import com.waytojob.helper.PreferenceHelper.get
import com.waytojob.helper.PreferenceHelper.set
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()
        if (prefs[IS_HR]!!){
            replaceFragment(HomeFragment())
        }else{
            replaceFragment(UserFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
    }

    private fun setToolBar() {
        txtLeft.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_menu, 0, 0, 0)
        txtLeft.setOnClickListener(this)
        nav_view.setNavigationItemSelectedListener(this)
    }
    /*private fun loginParams(): JsonObject? {
        var gsonObject = JsonObject()
        try {
            val jsonObject = JSONObject()
            jsonObject.put(API_Params.firstName, "")
            jsonObject.put(API_Params.lastName, "")
            jsonObject.put(API_Params.email, binding.etLoginEmail.getText().toString().trim())
            jsonObject.put(API_Params.password, binding.etLoginPassword.getText().toString().trim())
            jsonObject.put(API_Params.isSocial, "0")
            jsonObject.put(API_Params.isFacebook, "0")
            jsonObject.put(API_Params.deviceAccess, API_Params.DEVICE_ACCESS)
            jsonObject.put(API_Params.deviceToken, "fgdhfhfhdf")
            jsonObject.put(API_Params.fbId, "")
            jsonObject.put(API_Params.gmailId, "")
            jsonObject.put(API_Params.fbImageUrl, "")
            jsonObject.put(API_Params.gmailImageUrl, "")
            val parser = JsonParser()
            gsonObject = parser.parse(jsonObject.toString()) as JsonObject
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return gsonObject
    }*/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                if (prefs[IS_HR]!!) {
                    replaceFragment(HomeFragment())
                } else {
                    replaceFragment(UserFragment())
                }
            }
            R.id.nav_feedback -> {
                replaceFragment(FeedBackFragment())
            }
            R.id.nav_aboutapp -> {
                replaceFragment(AboutUsFragment())
            }
            R.id.nav_logout -> {
                askForLogout()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    @SuppressLint("NewApi")
    private fun askForLogout() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(resources.getString(R.string.lable_logout))
        builder.setPositiveButton(resources.getString(R.string.title_yes)) { dialog, _ ->
            prefs.edit().clear().commit()
            prefs[IS_LOGIN]=false
            prefs[IS_HR] = false
            startActivity(Intent(this, SignInActivity::class.java))
            finishAffinity()
            dialog.cancel()
        }
        builder.setNegativeButton(resources.getString(R.string.title_no)) { dialog, _ ->
            dialog.cancel()
        }
        val dialog: AlertDialog = builder.create()
        dialog.setOnShowListener(object : DialogInterface.OnShowListener {
            override fun onShow(arg0: DialogInterface?) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorBlack))
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorBlack))
            }
        })
        dialog.show()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            finishAffinity()
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.txtLeft -> {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawers()
                } else {
                    drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        }
    }
}

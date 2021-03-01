package com.waytojob.authantication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.waytojob.BaseActivity
import com.waytojob.R
import com.waytojob.activity.MainActivity
import com.waytojob.helper.IS_LOGIN

import com.waytojob.helper.PreferenceHelper.get

class SplashActivity : BaseActivity() {
    private val SPLASH_TIME_OUT:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({

            if (prefs[IS_LOGIN]!!) {
                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            } else {
                val mainIntent = Intent(this@SplashActivity, SignInActivity::class.java)
                startActivity(mainIntent)
                finish()
            }

        }, SPLASH_TIME_OUT)



    }

}

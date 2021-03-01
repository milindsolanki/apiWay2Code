package com.waytojob
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.waytojob.helper.PreferenceHelper


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    lateinit var prefs: SharedPreferences
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = PreferenceHelper.customPrefs(this)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor =0
                ContextCompat.getColor(this, R.color.colorBlack) //resources.getColor(R.color.colorBlack)
        }

    }

    private var mProgressDialog: ProgressDialog? = null

    fun showProgressDialog(mContext: Context, title: String = "", message: String = getString(R.string.default_loading_message)) {
        if (mProgressDialog != null && mProgressDialog!!.isShowing)
            return
        mProgressDialog = ProgressDialog(mContext)
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        mProgressDialog!!.setCancelable(false)
        if (title.length > 0)
            mProgressDialog!!.setTitle(title)
        mProgressDialog!!.setMessage(message)
        mProgressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null)
            if (mProgressDialog!!.isShowing) {
                mProgressDialog!!.dismiss()
                mProgressDialog = null
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
    }

}

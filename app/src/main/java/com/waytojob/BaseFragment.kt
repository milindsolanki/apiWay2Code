package com.waytojob

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.waytojob.helper.PreferenceHelper


open class BaseFragment : Fragment() {

    internal lateinit var context: Context

    var baseActivity: BaseActivity? = null

    lateinit var prefs: SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.baseActivity = activity
            prefs = PreferenceHelper.customPrefs(context)

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
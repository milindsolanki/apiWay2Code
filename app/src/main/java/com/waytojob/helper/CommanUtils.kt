package com.waytojob.helper

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.waytojob.R
import java.util.regex.Pattern

object CommanUtils {

    fun showOkDialog(
        context: Context,
        title: String = context.getString(R.string.app_name),
        message: String?,
        isFinish: Boolean = false) {
        if (null == message)
            return
        val adb = AlertDialog.Builder(context)
        adb.setTitle(title)
        adb.setMessage(message)
        adb.setPositiveButton("Ok") { dialog, _ ->
            if (isFinish) {
                val activity = context as Activity
                activity.finish()
            } else {
                dialog.dismiss()
            }

        }
        adb.show()
    }




    fun isInternetConnected(mContext: Context, isShowDialog: Boolean = false): Boolean {
        var isConnected = false
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm.activeNetworkInfo != null) {
            isConnected = cm.activeNetworkInfo.isConnected
        }
        if (!isConnected && isShowDialog) {
            showOkDialog(
                mContext, mContext.getString(R.string.app_name),
                mContext.getString(R.string.default_internet_message)
            )

        }
        return isConnected
    }



    fun showToast(mContext: Context, message: String) = Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

    fun showLongToast(mContext: Context, message: String) = Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()


    fun showSnakbar(rootView: View, mMessage: String) = Snackbar.make(rootView, mMessage, Snackbar.LENGTH_SHORT).show()

    fun showLongSnakbar(rootView: View, mMessage: String) =
        Snackbar.make(rootView, mMessage, Snackbar.LENGTH_LONG).show()

    fun isValidEmail(editText: EditText): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")
        val matcher = pattern.matcher(editText.text.toString())
        return matcher.matches()
    }

    @VisibleForTesting
    fun isValidEmail(editText: String): Boolean {
        //val EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
        val pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")
        val matcher = pattern.matcher(editText)
        return matcher.matches()
    }

    @VisibleForTesting
    fun isValidPhoneNumber(editText: String): Boolean {
        val PHONE_REGEX = "^\\+(?:[0-9] ?){6,14}[0-9]\$"
        val pattern = Pattern.compile(PHONE_REGEX)
        val matcher = pattern.matcher(editText)
        return matcher.matches()
    }

    fun inflateLayout(parent: ViewGroup, id: Int) = LayoutInflater.from(parent.context).inflate(id, parent, false)

    fun getLayoutManager(context: Context, type: Int): RecyclerView.LayoutManager {
        if (type == TYPE_HORIZENTAL)
            return LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        else
            return LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    fun getGridManager(context: Context, numberOfColumns: Int): RecyclerView.LayoutManager {
        return GridLayoutManager(context, numberOfColumns)
    }

    fun addDivider(recyclerView: RecyclerView) = DividerItemDecoration(recyclerView.getContext(), 1);

/*
    fun showDialog(context: Context, title: String = "", description: String = "", nameButton: String = "", dialogClickListner: DialogClickListner) {
        val builder = android.app.AlertDialog.Builder(context)
        // builder.setTitle(getString(R.string.title_update_mobile_number))
        val view = (context as Activity).layoutInflater.inflate(R.layout.dialog_box, null)


        val btnOk = view.findViewById(R.id.btnOk) as AppCompatButton
        val txtTitle = view.findViewById(R.id.txtTitle) as AppCompatTextView
        txtTitle.text = title

        val txtDescription = view.findViewById(R.id.txtDescription) as AppCompatTextView
        txtDescription.text = description

        builder.setView(view)
        val alertDialog = builder.show()

        alertDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnOk.setOnClickListener {
            dialogClickListner.dialogClick()
            alertDialog.dismiss()
        }
    }
*/

    interface DialogClickListner {
        fun dialogClick()
    }

/*
    fun showDialogReport(context: Context, reportDialogClickListner: ReportDialogClickListner) {
        val builder = android.app.AlertDialog.Builder(context)
        // builder.setTitle(getString(R.string.title_update_mobile_number))
        val view = (context as Activity).layoutInflater.inflate(R.layout.report_dialog_box, null)


        val btnSubmit = view.findViewById(R.id.btnSubmit) as AppCompatButton
        val txtCancel = view.findViewById(R.id.txtCancel) as AppCompatTextView

        builder.setView(view)
        val alertDialog = builder.show()

        alertDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnSubmit.setOnClickListener {
            alertDialog.dismiss()
        }

        txtCancel.setOnClickListener {
            alertDialog.dismiss()
        }
    }
*/

    interface ReportDialogClickListner {
        fun submitClick()
        fun cancelCkcik()
    }


}

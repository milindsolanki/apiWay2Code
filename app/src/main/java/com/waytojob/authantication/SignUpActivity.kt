package com.waytojob.authantication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ithub.api.APIService
import com.example.ithub.api.RestClient
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.waytojob.BaseActivity
import com.waytojob.R
import com.waytojob.bins.RegisterBin
import com.waytojob.helper.*
import com.waytojob.helper.CommanUtils.isValidEmail
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import com.waytojob.helper.PreferenceHelper.set
import com.waytojob.helper.PreferenceHelper.get


@SuppressLint("Registered")
class SignUpActivity : BaseActivity(), View.OnClickListener {
    var apiService : APIService? = null
    private var PDF_PATH = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        requestMultiplePermissions()
        apiService = RestClient.client.create(APIService::class.java)
        initListeners()
    }

    private fun initListeners() {
        appCompatButtonRegister!!.setOnClickListener(this)
        appCompatTextViewLoginLink!!.setOnClickListener(this)
        textInputEditTextUploadYourCV.setOnClickListener(this)
        txtLeft.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonRegister -> {
                if (isValid()) {
                    signUpAPI()
                }
            }
            R.id.appCompatTextViewLoginLink -> finish()
            R.id.textInputEditTextUploadYourCV -> {
                val intent = Intent()
                intent.action = Intent.ACTION_GET_CONTENT
                intent.type = "application/pdf"
                startActivityForResult(intent, 1)
            }
            R.id.txtLeft ->  finish()
        }
    }

    private fun isValid(): Boolean {
        if (textInputEditTextName.text.toString().trim().isEmpty()) {
            textInputEditTextName.requestFocus()
            textInputEditTextName.error = resources.getString(R.string.msg_blank_fullname)
        } else if (textInputEditTextEmail.text.toString().trim().isEmpty()) {
            textInputEditTextEmail.requestFocus()
            textInputEditTextEmail.error = resources.getString(R.string.msg_blank_email)
        } else if (!isValidEmail(textInputEditTextEmail)) {
            textInputEditTextEmail.requestFocus()
            textInputEditTextEmail.error = resources.getString(R.string.msg_valid_email)
        } else if (textInputEditTextPassword.text.toString().trim().isEmpty()) {
            textInputEditTextPassword.requestFocus()
            textInputEditTextPassword.error = resources.getString(R.string.msg_blank_password)
        } else if (textInputEditTextPassword.getText().toString().length < 6 || textInputEditTextPassword.getText().toString().length > 10) {
            textInputEditTextPassword.requestFocus()
            textInputEditTextPassword.error = resources.getString(R.string.msg_pass_set)
        } else if (textInputEditTextMobileNumber.text.toString().trim().isEmpty()) {
            textInputEditTextMobileNumber.requestFocus()
            textInputEditTextMobileNumber.error = resources.getString(R.string.msg_blank_mobile_number)
        } else if (textInputEditTextEduction.text.toString().trim().isEmpty()) {
            textInputEditTextEduction.requestFocus()
            textInputEditTextEduction.error = resources.getString(R.string.msg_blank_eduction)
        }else if (textInputEditPasseingYear.text.toString().trim().isEmpty()) {
            textInputEditPasseingYear.requestFocus()
            textInputEditPasseingYear.error = resources.getString(R.string.msg_blank_year_of_pass)
        }else if (textInputEditTextUniversityName.text.toString().trim().isEmpty()) {
            textInputEditTextUniversityName.requestFocus()
            textInputEditTextUniversityName.error = resources.getString(R.string.msg_blank_univercity)
        }else if (textInputEditTextWorkExperience.text.toString().trim().isEmpty()) {
            textInputEditTextWorkExperience.requestFocus()
            textInputEditTextWorkExperience.error = resources.getString(R.string.msg_blank_exprience)
        }else if (textInputEditTextUploadYourCV.text.toString().trim().isEmpty()) {
            textInputEditTextUploadYourCV.requestFocus()
            textInputEditTextUploadYourCV.error = resources.getString(R.string.msg_blank_cv)
        }else if (textInputEditTextAddress.text.toString().trim().isEmpty()) {
            textInputEditTextAddress.requestFocus()
            textInputEditTextAddress.error = resources.getString(R.string.msg_blank_address)
        }else if (textInputEditTextCity.text.toString().trim().isEmpty()) {
            textInputEditTextCity.requestFocus()
            textInputEditTextCity.error = resources.getString(R.string.msg_blank_city)
        }else if (textInputEditTextPinCode.text.toString().trim().isEmpty()) {
            textInputEditTextPinCode.requestFocus()
            textInputEditTextPinCode.error = resources.getString(R.string.msg_blank_pincode)
        }else if (textInputEditTextState.text.toString().trim().isEmpty()) {
            textInputEditTextState.requestFocus()
            textInputEditTextState.error = resources.getString(R.string.msg_blank_state)
        } else {
            return true
        }
        return false
    }


    @SuppressLint("NewApi")
    private fun signUpAPI() {
        val pdfname: String = java.lang.String.valueOf(Calendar.getInstance().getTimeInMillis())

        //Create a file object using file path

        //Create a file object using file path
        val file = File(PDF_PATH)
        // Parsing any Media type file
        // Parsing any Media type file
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("*/*"), file)
        val fileToUpload = MultipartBody.Part.createFormData("filename", file.name, requestBody)
        val filename = RequestBody.create(MediaType.parse("text/plain"), pdfname)
        if (!CommanUtils.isInternetConnected(this, true))
            return

        showProgressDialog(this)
        val mobno: Int = textInputEditTextMobileNumber.text.toString().toInt()
        val year: Int = textInputEditPasseingYear.text.toString().toInt()
        val work: Int = textInputEditTextWorkExperience.text.toString().toInt()
        val pincode: Int = textInputEditTextPinCode.text.toString().toInt()
        val emailPart = RequestBody.create(MultipartBody.FORM, textInputEditTextEmail.text.toString())
        val namePart=RequestBody.create(MultipartBody.FORM, textInputEditTextName.text.toString())
        val passwordPart=RequestBody.create(MultipartBody.FORM, textInputEditTextPassword.text.toString())
        val educationPart=RequestBody.create(MultipartBody.FORM,textInputEditTextEduction.text.toString())
        val addressPart=RequestBody.create(MultipartBody.FORM,  textInputEditTextAddress.text.toString())
        val cityPart=RequestBody.create(MultipartBody.FORM,textInputEditTextCity.text.toString())
        val statePart=RequestBody.create(MultipartBody.FORM, textInputEditTextState.text.toString())
        val uniPart=RequestBody.create(MultipartBody.FORM, textInputEditTextUniversityName.text.toString())

        val call = apiService!!.getUserRegister(fileToUpload,filename,
                emailPart, namePart, passwordPart, mobno, educationPart,
                year, work, addressPart, cityPart, statePart, pincode, uniPart)

        call.enqueue(object : Callback<RegisterBin> {
            override fun onResponse(call: Call<RegisterBin>, response: Response<RegisterBin>) {
                Log.e("TAG", "Got error : " + response.body()!!.responseData)
                hideProgressDialog()
                if (response.body()!!.responseCode==200){

                    prefs[FULL_NAME] = response.body()!!.responseData.vName
                    prefs[USER_EMAIL] = response.body()!!.responseData.vEmail
                    prefs[USER_MOBILE_NUMBER] = response.body()!!.responseData.iMobileNumber
                    prefs[EDUCTION] = response.body()!!.responseData.vQualification
                    prefs[PASS_YEAR] = response.body()!!.responseData.iPassYear
                    prefs[WORK_EXP] = response.body()!!.responseData.iWorckExp
                    prefs[ADDRESS] = response.body()!!.responseData.vAddress
                    prefs[CITY] = response.body()!!.responseData.vCity
                    prefs[STATE] = statePart
                    prefs[PIN_CODE] = pincode
                    prefs[UNIVERCITY] = response.body()!!.responseData.vUniversitySchool

                    Toast.makeText(this@SignUpActivity,response.body()!!.responseCode, Toast.LENGTH_LONG).show()
                    val intent=Intent(this@SignUpActivity,SignInActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    Toast.makeText(this@SignUpActivity,response.body()!!.responseMessage, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<RegisterBin>, t: Throwable) {
                hideProgressDialog()
                Log.e("TAG", "Got error : " + t.message)
            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            // Get the Uri of the selected file
            val uri = data!!.data
            val uriString = uri.toString()
            val myFile = File(uriString)
            val path = SignUpActivity.getFilePathFromURI(this, uri)
            Log.d("tag", path)
            PDF_PATH= path!!
            textInputEditTextUploadYourCV.setText(path)

           // uploadPDF(path)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show()
                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied) {
                            // show alert dialog navigating to Settings
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                        token.continuePermissionRequest()
                    }
                }).withErrorListener { Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show() }
                .onSameThread()
                .check()
    }
    companion object {
        private const val BUFFER_SIZE = 1024 * 2
        private const val IMAGE_DIRECTORY = "/demonuts_upload_gallery"
        fun getFilePathFromURI(context: Context, contentUri: Uri?): String? {
            //copy file and send new file path
            val fileName = getFileName(contentUri)
            val wallpaperDirectory = File(Environment.getExternalStorageDirectory().toString() + IMAGE_DIRECTORY)
            // have the object build the directory structure, if needed.
            if (!wallpaperDirectory.exists()) {
                wallpaperDirectory.mkdirs()
            }
            if (!TextUtils.isEmpty(fileName)) {
                val copyFile = File(wallpaperDirectory.toString() + File.separator + fileName)
                // create folder if not exists
                copy(context, contentUri, copyFile)
                return copyFile.absolutePath
            }
            return null
        }

        fun getFileName(uri: Uri?): String? {
            if (uri == null) return null
            var fileName: String? = null
            val path = uri.path
            val cut = path!!.lastIndexOf('/')
            if (cut != -1) {
                fileName = path.substring(cut + 1)
            }
            return fileName
        }

        fun copy(context: Context, srcUri: Uri?, dstFile: File?) {
            try {
                val inputStream = context.contentResolver.openInputStream(srcUri!!) ?: return
                val outputStream: OutputStream = FileOutputStream(dstFile)
                copystream(inputStream, outputStream)
                inputStream.close()
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @Throws(Exception::class, IOException::class)
        fun copystream(input: InputStream?, output: OutputStream?): Int {
            val buffer = ByteArray(BUFFER_SIZE)
            val `in` = BufferedInputStream(input, BUFFER_SIZE)
            val out = BufferedOutputStream(output, BUFFER_SIZE)
            var count = 0
            var n = 0
            try {
                while (`in`.read(buffer, 0, BUFFER_SIZE).also { n = it } != -1) {
                    out.write(buffer, 0, n)
                    count += n
                }
                out.flush()
            } finally {
                try {
                    out.close()
                } catch (e: IOException) {
                    Log.e(e.message, e.toString())
                }
                try {
                    `in`.close()
                } catch (e: IOException) {
                    Log.e(e.message, e.toString())
                }
            }
            return count
        }
    }

}







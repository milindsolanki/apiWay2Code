package com.example.ithub.api

import com.waytojob.bins.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface APIService {

    /*  @FormUrlEncoded
     @POST("registration.php")
    fun getUserRegistration(@Field("vEmail") vEmail: String,
                             @Field("vUserName") vUserName: String,
                             @Field("vPassword") vPassword: String,
                             @Field("vISDCode") vISDCode: String,
                             @Field("vPhoneNumber") vPhoneNumber: String,
                             @Field("vUserStatus")  vUserStatus: String,
                             @Field("tUserSkills") tUserSkills: String,
                             @Field("vQualification") vQualification: String): Call<RegistrationData>
  */
  /* @FormUrlEncoded
   @POST("changepassword.php")
   fun getUserChangePassword(@Field("vEmail") uname: String, @Field("vPassword") password: String): Call<ChangePassword>
*/
    @FormUrlEncoded
    @POST("login.php")
    fun getUserLogin(@Field("V_Email") uname: String,
                     @Field("V_Pass") password: String): retrofit2.Call<LoginBin>


    @Multipart
    @POST("uploadfile.php")
    fun uploadImage(
            @Part file: MultipartBody.Part?, @Part("filename") name: RequestBody?
    ): Call<String?>?

    @Multipart
    @POST("Register.php")
    fun getUserRegister(@Part file: MultipartBody.Part, @Part("filename") name: RequestBody?,
                        @Part("V_Email") uemail: RequestBody,
                        @Part("V_Name") uname: RequestBody, @Part("V_Pass") upass: RequestBody,
                        @Part("I_Mobile_Number") umo: Int,
                        @Part("V_Qualification") uqlif: RequestBody,
                        @Part("I_Pass_Year") Pass: Int,
                        @Part("I_Worck_Exp") Exp:Int,
                        @Part("V_Address") Add:RequestBody,
                        @Part("V_City") Cit:RequestBody,
                        @Part("V_State") State:RequestBody,
                        @Part("I_Pincode") Pin:Int,
                        @Part("V_University_School") uni:RequestBody): retrofit2.Call<RegisterBin>





    @FormUrlEncoded
    @POST("ForggotPassword.php")
    fun getUserFogotPassword(@Field("V_Email") uemail: String): retrofit2.Call<ForgetPassResponse>


    /*  @FormUrlEncoded
      @POST("interview_question.php")
      fun getInterviewQuestion(@Field("iCategoryCourseId") id: Int): Call<InterviewQuestion>


      @FormUrlEncoded
      @POST("category_course.php")
      fun getWebDevelopmentData(@Field("iCategoryId") id: Int): Call<WebDevelopment>


      @FormUrlEncoded
      @POST("course_detail.php")
      fun getCourceData(@Field("iCategoryCourseId") id: Int): Call<CourseDetails>

      @FormUrlEncoded
      @POST("test_question.php")
      fun getQuestion(@Field("iQuestionid") id: Int): Call<Test>

      @FormUrlEncoded
      @POST("test_demo.php")
      fun getQuestionAnswer(@Field("iCategoryCourseId") id: Int): Call<TestDemo>


      @FormUrlEncoded
      @POST("forrgatepassword.php")
      fun getEmail(@Field("vEmail") vEmail: String): Call<ForrgetPassword>
  */
    @FormUrlEncoded
    @POST("feedback.php")
    fun getFeedBack(@Field("vUserEmail") vUserEmail: String,@Field("vUserFeedBack") vUserFeedBack: String): Call<FeedBackBin>


    @FormUrlEncoded
    @POST("User_Details.php")
    fun getUserDetails(@Field("id") vId: String) :Call<UserDetails>
}
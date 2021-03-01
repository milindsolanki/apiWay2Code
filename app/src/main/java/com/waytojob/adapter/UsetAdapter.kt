package com.waytojob.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waytojob.R
import com.waytojob.bins.ResponseDatum
import com.waytojob.communicator.AdapterClicked
import com.waytojob.helper.CommanUtils
import com.waytojob.helper.ViewHolder
import kotlinx.android.synthetic.main.item_user.view.*

class UsetAdapter  (val context: Context, var userList:ArrayList<ResponseDatum>,val callback: AdapterClicked): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(CommanUtils.inflateLayout(parent, R.layout.item_user))

    override fun getItemCount(): Int =userList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.txtUserName.text=userList[position].vName
        if (userList[position].iWorckExp=="0" ){
            holder.itemView.txtExprience.visibility=View.GONE
        }else{
            holder.itemView.txtExprience.visibility=View.VISIBLE
        }
        holder.itemView.txtExprience.text="Work Exprience : "+userList[position].iWorckExp
        holder.itemView.txtEmail.text="Email ID : "+userList[position].vEmail
        holder.itemView.txtMobNumber.text="Mobile Number : "+userList[position].iMobileNumber
        holder.itemView.txtEduction.text="Eduction : "+userList[position].vQualification
        holder.itemView.txtPassingYear.text="Passing Year : "+userList[position].iPassYear


        holder.itemView.linearLayout.setOnClickListener {
            callback.itemClick(userList[position].vName,
                    userList[position].vEmail,
                    userList[position].iMobileNumber,
                    userList[position].vQualification,
                    userList[position].vUniversitySchool,
                    userList[position].iPassYear,
                    userList[position].iWorckExp,
                    userList[position].vAddress,
                    userList[position].vCity,
                    userList[position].vState,
                    userList[position].iPincode,
                    userList[position].vcv)
        }


    }

     fun updateList(list:List<ResponseDatum>){
        userList=ArrayList()
        userList.addAll(list)
        notifyDataSetChanged()

    }
}
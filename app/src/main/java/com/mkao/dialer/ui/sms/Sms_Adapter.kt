package com.mkao.dialer.ui.sms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mkao.dialer.MainActivity
import com.mkao.dialer.R

class Sms_Adapter (private val activity: MainActivity):RecyclerView.Adapter<Sms_Adapter.SMSViewHolder>() {

    inner class SMSViewHolder (itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
        internal var mSender = itemView.findViewById<View>(R.id.sender) as TextView
        internal var mBody = itemView.findViewById<View>(R.id.body) as TextView

        init {
            itemView.isClickable = true
            itemView.setOnClickListener(this)
            itemView.setOnClickListener {
                //open SMS dialog

                return@setOnClickListener
            true }
        }

        override fun onClick(view: View?) = activity.openDialog(ViewSMS(texts[adapterPosition]))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Sms_Adapter.SMSViewHolder {

       return SMSViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sms_entry,parent,false))
    }

    override fun onBindViewHolder(holder: SMSViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
package com.mkao.dialer.ui.callLog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mkao.dialer.MainActivity
import com.mkao.dialer.R

class CallAdapter(private  val activity: MainActivity): RecyclerView.Adapter<CallAdapter.CallViewHolder>() {
    inner class CallViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        internal  var mDirection = itemView.findViewById<View>(R.id.callDirection) as ImageView
        internal  var mPhoneNumber = itemView.findViewById<View>(R.id.number)
        internal  var mCallDate = itemView.findViewById<View>(R.id.date) as TextView
        internal var mCallBack = itemView.findViewById<View>(R.id.callBack) as ImageButton
        init {
            itemView.setOnLongClickListener {

                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        return  CallViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.call_log_entry, parent, false))
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}
package com.mkao.dialer.ui.callLog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mkao.dialer.CallLogEvent
import com.mkao.dialer.MainActivity
import com.mkao.dialer.R

class CallAdapter(private  val activity: MainActivity): RecyclerView.Adapter<CallAdapter.CallViewHolder>() {
    var callLog = listOf<CallLogEvent>()

    inner class CallViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        internal  var mDirection = itemView.findViewById<View>(R.id.callDirection) as ImageView
        internal  var mPhoneNumber = itemView.findViewById<View>(R.id.number) as TextView
        internal  var mCallDate = itemView.findViewById<View>(R.id.date) as TextView
        internal var mCallBack = itemView.findViewById<View>(R.id.callBack) as ImageButton
        init {
            itemView.setOnLongClickListener {
                val phoneNumber = callLog[adapterPosition].number
                if (phoneNumber.isNotBlank())
                    activity.showCallLogPopup(it,phoneNumber)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        return  CallViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.call_log_entry, parent, false))
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
       val current = callLog[position]
        val  callDirection = holder.mDirection
        callDirection.setColorFilter(ContextCompat.getColor(activity, android.R.color.holo_green_dark))
        when(current.direction){
            "OUTGOING" -> callDirection.setImageResource(R.drawable.ic_outgoing)
            "INCOMING" -> callDirection.setImageResource(R.drawable.ic_incoming)
            "MISSED" -> {
                callDirection.setImageResource(R.drawable.ic_missed)
                callDirection.setColorFilter(ContextCompat.getColor(activity,android.R.color.holo_red_dark))
            }
            null -> callDirection.visibility = View.INVISIBLE
        }
        holder.mPhoneNumber.text = current.number
        holder.mCallDate.text = current.date
        holder.mCallBack.setOnClickListener { 
            if (current.number.isNotBlank()) activity.callNumber(current.number)
        }
    }

    override fun getItemCount() = callLog.size

}
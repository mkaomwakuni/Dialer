package com.mkao.dialer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mkao.dialer.ui.sms.SMS

class CommunicationViewModel:ViewModel(){
    var callLog = MutableLiveData<List<CallLogEvent>>()
    val texts = MutableLiveData<List<SMS>>()
}
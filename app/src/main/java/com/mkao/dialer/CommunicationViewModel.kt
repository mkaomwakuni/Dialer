package com.mkao.dialer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunicationViewModel:ViewModel(){
    var callLog = MutableLiveData<List<CallLogEvent>>()
}
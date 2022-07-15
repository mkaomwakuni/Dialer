package com.mkao.dialer.ui.sms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mkao.dialer.CommunicationViewModel
import com.mkao.dialer.MainActivity
import com.mkao.dialer.databinding.FragmentSmsBinding

class SMS_Fragment :Fragment() {

    private var _binding:FragmentSmsBinding? = null
    private val communicationViewModel:CommunicationViewModel by activityViewModels()
    private lateinit var callingActivity: MainActivity
    private lateinit var smsAdapter: Sms_Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSmsBinding.inflate(inflater,container,false)
        callingActivity = activity as MainActivity
        //adapter
        smsAdapter = Sms_Adapter(callingActivity)
        return binding.root
    }
}
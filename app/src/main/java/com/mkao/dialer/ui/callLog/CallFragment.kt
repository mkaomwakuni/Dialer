package com.mkao.dialer.ui.callLog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mkao.dialer.CommunicationViewModel
import com.mkao.dialer.MainActivity
import androidx.fragment.app.activityViewModels
import com.mkao.dialer.databinding.FragmentCallLogBinding






class callFragment:Fragment() {
    private val communicationViewModel: CommunicationViewModel by activityViewModels()

    private var _binding : FragmentCallLogBinding? = null
    private  lateinit var callingActivity: MainActivity
    private lateinit var callAdapter: CallAdapter
    //binding will store instance of the fragment_call log layout providing access to componets of the layout
    //calling activity will provide access to mainActivity and its methods,variables
    //CallAdapter variable store instance of recyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallLogBinding.inflate(inflater,container,false)
        callingActivity = activity as MainActivity
        //call Adapter initialisation
        callAdapter = CallAdapter((callingActivity))

        return  binding.root
    }
}
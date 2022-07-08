package com.mkao.dialer.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.AbsSavedState
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.mkao.dialer.MainActivity
import com.mkao.dialer.R
import com.mkao.dialer.databinding.FragmentPhoneBinding

class PhoneFragment : Fragment() {
    private var _binding: FragmentPhoneBinding? = null
    private lateinit var callingActivity: MainActivity

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneBinding.inflate(inflater, container, false)
        callingActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backspace.setOnClickListener {
            removeNumber()
        }
        binding.one.setOnClickListener {
            addNumeric("1")
        }
        binding.two.setOnClickListener {
            addNumeric("2")
        }
        binding.three.setOnClickListener {
            addNumeric("3")
        }
        binding.four.setOnClickListener {
            addNumeric("4")
        }
        binding.five.setOnClickListener {
            addNumeric("5")
        }
        binding.six.setOnClickListener {
            addNumeric("6")
        }
        binding.seven.setOnClickListener {
            addNumeric("7")
        }
        binding.eight.setOnClickListener {
            addNumeric("8")
        }
        binding.nine.setOnClickListener {
            addNumeric("9")
        }
        binding.zero.setOnClickListener {
            addNumeric("0")
        }
        binding.hash.setOnClickListener {
            addNumeric("#")
        }
        binding.star.setOnClickListener {
            addNumeric("*")
        }
        binding.call.setOnClickListener {
            val number = binding.phoneNumber.text
            if (number.isNotBlank())
                callingActivity.callNumber(number.toString())
            else
                Toast.makeText(callingActivity,getString(R.string.no_number),Toast.LENGTH_SHORT).show()
        }
    }
    private fun removeNumber() {
        val reductNumber = binding.phoneNumber.text.toString()
        if (reductNumber.isEmpty())
            return
        val reducedNumber = reductNumber.take(reductNumber.length-1)
        binding.phoneNumber.text = reducedNumber
    }

    private fun addNumeric(s: String) {
        val previousNumber = binding.phoneNumber.text.toString()
        val  neoNumber = (previousNumber + s)
        binding.phoneNumber.text = neoNumber
         }
}

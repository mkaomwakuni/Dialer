package com.mkao.dialer

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mkao.dialer.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val communicationViewModel: CommunicationViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    companion object{
        //Reference code allows the MainActivity keep track and respond to permission requests
        const val READ_STORAGE_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            READ_STORAGE_REQUEST_CODE-> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) getCallLogs()
        }
    }

    fun callNumber(number: String) {
        // if the permissions not granted prompt
        //packages the data and converts phone number in URI Intent
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED)
        {
            val  intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }else ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),0)
    }

    //retrieve the call log
    fun getCallLogs(){
        val readStoragePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val readCallLogsPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALL_LOG)
        if (readStoragePermission!=PackageManager.PERMISSION_GRANTED || readCallLogsPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.READ_CALL_LOG),READ_STORAGE_REQUEST_CODE)
            return
        }
        val cursor = application.contentResolver.query(CallLog.Calls.CONTENT_URI,null,null,null,CallLog.Calls.DATE + "DESC")
        val callLog = mutableListOf<CallLogEvent>()
        cursor?.use {
            val number = it.getColumnIndexOrThrow(CallLog.Calls.NUMBER)
            val type = it.getColumnIndexOrThrow(CallLog.Calls.TYPE)
            val date = it.getColumnIndexOrThrow(CallLog.Calls.DATE)
            while (it.moveToNext()){
                val phoneNumber = cursor.getString(number)
                val callType =cursor.getString(type)
                val callDate = cursor.getLong(date)
                val callDateString = SimpleDateFormat("dd/MM/yy HH:mm",Locale.getDefault()).format(Date(callDate))
                val direction = when(callType.toInt()){
                    CallLog.Calls.OUTGOING_TYPE -> "OUTGOING"
                    CallLog.Calls.INCOMING_TYPE -> "INCOMING"
                    CallLog.Calls.MISSED_TYPE -> "MISSED"

                    else -> null }
                val entry = CallLogEvent(direction,phoneNumber,callDateString)
                callLog.add(entry)}
                }
                   cursor?.close()
        communicationViewModel.callLog.value = callLog
            }
    fun showCallLogPopup (view: View, phoneNumber: String){
        PopupMenu(this,view).apply {
            inflate(R.menu.call_log)
            setOnMenuItemClickListener {
                when (it.itemId){
                    R.id.make_call-> {
                        callNumber(phoneNumber)
                        true }
                    R.id.send_sms -> {
                        openDialog(SendSMS(phoneNumber))
                        true } else -> super.onOptionsItemSelected(it)
                }
            }
            show()
        }
    }

    fun openDialog(dialog: Dialog) = dialog.show(supportFragmentManager, "")

}
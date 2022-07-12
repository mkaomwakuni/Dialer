package com.mkao.dialer

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.activity.viewModels
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private val communicationViewModel:CommunicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }

}
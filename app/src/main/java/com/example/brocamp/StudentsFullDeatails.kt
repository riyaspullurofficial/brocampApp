package com.example.brocamp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.brocamp.databinding.ActivityStudentsFullDeatailsBinding

class StudentsFullDeatails : AppCompatActivity() {
    private lateinit var binding:ActivityStudentsFullDeatailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStudentsFullDeatailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.acceptBtnStdDtls.setOnClickListener {
            val contact_number = "+919072990008"
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$contact_number")
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 100)
            } else {
                try {
                    startActivity(callIntent)
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }
            }
        }
        binding.rejectBtnStd.setOnClickListener {
            Toast.makeText(this,"Rejected Specified Student",Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
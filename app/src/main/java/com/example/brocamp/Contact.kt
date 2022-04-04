package com.example.brocamp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.brocamp.databinding.ActivityContactBinding


class Contact : AppCompatActivity() {
    lateinit var contactBinding:ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactBinding= ActivityContactBinding.inflate(layoutInflater)
        setContentView(contactBinding.root)
        supportActionBar?.hide()

        contactBinding.locationBtn.setOnClickListener {
            startActivity(Intent(this@Contact,MapsActivity::class.java))

        }
        contactBinding.callBtn.setOnClickListener {
            val numTxt= "123456789"
                val contact_number = "+917902606117"
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
        contactBinding.emailBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val emails_in_to = arrayOf("sps@packapeer.com")
            intent.putExtra(Intent.EXTRA_EMAIL, emails_in_to)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Your Email's subject")
            intent.putExtra(Intent.EXTRA_TEXT, "Your Email's predefined Body")
            intent.putExtra(Intent.EXTRA_CC, "mailcc@gmail.com")
            intent.type = "text/html"
            intent.setPackage("com.google.android.gm")
            startActivity(intent)
        }
        contactBinding.whatsappBtn.setOnClickListener {
           // val phoneNumberWithCountryCode = "+917902606117"
            val phoneNumberWithCountryCode = "+919072990008"
            val message = "Welcome To Brototype \n May I help You?"

            startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message))))
        }
        contactBinding.facebookBtn.setOnClickListener {
            val uri = Uri.parse("https://www.facebook.com/brototypemalayalam") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        contactBinding.instagrambtn.setOnClickListener {
            val uri = Uri.parse("https://www.instagram.com/brototype.malayalam/") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}
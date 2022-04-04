package com.example.brocamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brocamp.databinding.ActivityAdminLoginBinding

class AdminLogin : AppCompatActivity() {
    lateinit var admnBinding:ActivityAdminLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        admnBinding= ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(admnBinding.root)
        supportActionBar?.hide()

        admnBinding.backBtnAdmin.setOnClickListener {
            finish()
        }
        admnBinding.LoginBtnAdmin.setOnClickListener {
            startActivity(Intent(this,StudentsListView::class.java))
            finish()
        }


    }
}
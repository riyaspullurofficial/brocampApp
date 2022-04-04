package com.example.brocamp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.brocamp.databinding.ActivityUserOtpverificationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.*
import java.util.concurrent.TimeUnit

class UserOTPVerification : AppCompatActivity() {
    lateinit var binding:ActivityUserOtpverificationBinding
    //if code sending failed will used to resend
    var forceResendingToken: PhoneAuthProvider.ForceResendingToken?=null

    var mCallBacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks?=null
    var mVerificationId:String?=null
    lateinit var firebaseAuth: FirebaseAuth

    private val TAG="MAIN_TAG"

    //progrss Dialog
    lateinit var progressDialog: ProgressDialog


    lateinit var useNamesss:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.otpView.visibility= View.GONE
        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setCanceledOnTouchOutside(false)

        mCallBacks = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                progressDialog.dismiss()
                Toast.makeText(this@UserOTPVerification,"${e.message}",Toast.LENGTH_SHORT).show()

            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(TAG,"On code sent : $verificationId")
                mVerificationId=verificationId
                forceResendingToken=token
                progressDialog.dismiss()

                binding.otpView.visibility=View.VISIBLE
                Toast.makeText(this@UserOTPVerification,"Verification COde sent...",Toast.LENGTH_SHORT).show()

                binding.otpSendedShowTv.text="Please type verification code we sent to ${binding.mobileOtpET.text.toString().trim()}"

            }

        }
        binding.sendOtpTV.setOnClickListener {
            val phone= binding.mobileOtpET.text.toString().trim()
            //validate phone number
            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this,"Please enter mobile Number",Toast.LENGTH_SHORT).show()
            }
            else{
                startPhoneNumberVerification(phone)
            }
        }
        binding.resendOtpTv.setOnClickListener {
            val phone= binding.mobileOtpET.text.toString().trim()
            //validate phone number
            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this,"Please enter mobile Number",Toast.LENGTH_SHORT).show()
            }
            else{
                resendVerificationCode(phone,forceResendingToken)
            }
        }
        binding.loginBtnOtp.setOnClickListener {
            useNamesss=binding.nameOtpET.text.toString()
           if (useNamesss.isNotEmpty()){

               val code=binding.otpET.text.toString().trim()
               if (TextUtils.isEmpty(code)){
                   Toast.makeText(this,"Please enter verification Code",Toast.LENGTH_SHORT).show()
               }else{
                   verifyPhoneNUmberwithCode(mVerificationId!!,code)
               }
           }
            else{
               Toast.makeText(this,"Please Complete Data...",Toast.LENGTH_LONG).show()
           }
        }

    }
    private fun startPhoneNumberVerification(phone:String){
        progressDialog.setMessage("Verifying number...")
        progressDialog.show()

        val options= PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBacks!!)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private fun resendVerificationCode(phone: String,token:PhoneAuthProvider.ForceResendingToken?){

        progressDialog.setMessage("Verifying number...")
        progressDialog.show()

        val options= PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBacks!!)
            .build()

    }
    private fun verifyPhoneNUmberwithCode(verificationId:String,code:String){
        progressDialog.setMessage("Verifying code...")
        progressDialog.show()

        val credential=PhoneAuthProvider.getCredential(verificationId,code)
        signInWithPhoneAuthCredential(credential)

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        progressDialog.setMessage("Logging In...")
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val phone=firebaseAuth.currentUser?.phoneNumber
                Toast.makeText(this,"Logged in as $useNamesss number : $phone  ",Toast.LENGTH_SHORT).show()
               // startActivity(Intent(this,UserRegistration::class.java))
                var name=binding.nameOtpET.text.toString()
                var mob=binding.mobileOtpET.text.toString()


                var intent:Intent= Intent(this,UserRegistration::class.java)
                intent.putExtra("name",name)
                intent.putExtra("mobile",mob)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {e->
                progressDialog.dismiss()
                Toast.makeText(this,"${e.message}",Toast.LENGTH_SHORT).show()
            }
    }
}
package com.example.brocamp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.brocamp.databinding.ActivityUserRegistrationBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class UserRegistration : AppCompatActivity() {

    private val studentCollectionRef=Firebase.firestore.collection("studentDetails")

    private lateinit var binding: ActivityUserRegistrationBinding
    private var fullName: String? =null
    private var place:String?=null
    private var contactNumber:String?=null
    private var gender:String="male"
    private var email: String? =null
    private var employeeStatus:String?=null
    private var educationalQualification: String? =null
    private var collegeName:String?=null
    private var graduationYear: Int=0
    private var tellMeAboutYou:String?=null
    private var whyDoYouJoinSPS: String? =null
    private var whatMotivates:String?=null
    private var whereHearSPS: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        var username= intent.getStringExtra("name")
        var userMob= intent.getStringExtra("mobile")


        binding.fullNameET.setText(username)
        binding.mobileNoET.setText(userMob)
        val genderlist = resources.getStringArray(R.array.gender_arrays)
        val spinner = binding.genderDropdownET
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, genderlist)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(this@UserRegistration,
//                        getString(R.string.Gender_prompt) + " " +
//                                "" + genderlist[position], Toast.LENGTH_SHORT).show()
//
                    gender=genderlist[position]
                    Toast.makeText(this@UserRegistration,"Gender in gender is $gender",Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        binding.RegisterBtnET.setOnClickListener {
            var registration="notspecified"
            try {

                fullName=binding.fullNameET.text.toString()
                place=binding.placeET.text.toString()
                contactNumber=binding.mobileNoET.text.toString()
                email=binding.emailET.text.toString()
                employeeStatus=binding.employeeStatusET.text.toString()
                educationalQualification=binding.qualificationET.text.toString()
                collegeName=binding.collegeNameET.text.toString()
                graduationYear=binding.graduationYearET.text.toString().toInt()
                tellMeAboutYou=binding.tellmeMoreET.text.toString()
                whyDoYouJoinSPS=binding.whyDoYouJoinSPSET.text.toString()
                whatMotivates=binding.whatMotivationET.text.toString()
                whereHearSPS=binding.wherefromhearaboutSPSSET.text.toString()

                var month:String=Calendar.getInstance().get(Calendar.MONTH).toString()
                val year: Int = Calendar.getInstance().get(Calendar.YEAR)
                var monthName:String?=null

                var monthInt=month.toInt()+1
                Log.d("Current month is==== ",monthInt.toString())
                Log.d("Current year is==== ",year.toString())

                if (monthInt==1){
                    monthName="January"
                }else if (monthInt==2){
                    monthName="February"
                } else if (monthInt==3){
                    monthName="March"
                }else if (monthInt==4){
                    monthName="April"
                }
                else if (monthInt==5){
                    monthName="May"
                }
                else if (monthInt==6){
                    monthName="June"
                }
                else if (monthInt==7){
                    monthName="July"
                }
                else if (monthInt==8){
                    monthName="August"
                }
                else if (monthInt==9){
                    monthName="September"
                }else if (monthInt==10){
                    monthName="October"
                }else if (monthInt==11){
                    monthName="November"
                }
                else if (monthInt==12){
                    monthName="December"
                }


                Log.d("Current month is==== ",monthName.toString())


               if (fullName?.isEmpty() == true || place?.isEmpty() == true || email?.isEmpty() == true ||employeeStatus?.isEmpty() == true ||
                    educationalQualification?.isEmpty() == true || collegeName?.isEmpty() == true ||  tellMeAboutYou?.isEmpty() == true||
                    whyDoYouJoinSPS?.isEmpty() == true || whatMotivates?.isEmpty() == true || whereHearSPS?.isEmpty() == true || monthName?.isEmpty()==true ){

                        Toast.makeText(this,"full  fill Datas is empty ",Toast.LENGTH_LONG).show()

                }else{
                   val student=Student(fullName!!,place!!,contactNumber!!,gender!!,email!!, employeeStatus!!,educationalQualification!!,collegeName!!,
                                        graduationYear,tellMeAboutYou!!,whyDoYouJoinSPS!!,whatMotivates!!,whereHearSPS!!,registration,monthName!!,year)

                  if ( binding.checkBox.isChecked){
                      uploadStudentDetails(student)
                  }
                  else{
                       Toast.makeText(this,"Please agree FAQs ",Toast.LENGTH_SHORT).show()
                  }


                   // Toast.makeText(this,"Succefully Uploaded ",Toast.LENGTH_LONG).show()
                }

            }catch (e:Exception){
                Toast.makeText(this,"Enter Data Correctly...!!${e.toString()}",Toast.LENGTH_LONG).show()
               // Toast.makeText(this,"fill all data",Toast.LENGTH_LONG).show()

            }
        }

    }

    private fun uploadStudentDetails(student: Student) = CoroutineScope(Dispatchers.IO).launch {
        try {
            studentCollectionRef.add(student).await()
            withContext(Dispatchers.Main){
                Toast.makeText(applicationContext,"Data Succesfully saved...!!",Toast.LENGTH_LONG).show()
                startActivity(Intent(this@UserRegistration,MainActivity::class.java))
                finish()
            }

        }catch (e:java.lang.Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(applicationContext,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }
}
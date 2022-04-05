package com.example.brocamp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.Person
import androidx.core.content.ContextCompat
import com.example.brocamp.databinding.ActivityStudentsFullDeatailsBinding
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_students_full_deatails.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class StudentsFullDeatails : AppCompatActivity() {
    private val studentCollectionRef= Firebase.firestore.collection("studentDetails")

    lateinit var regis:String

    private lateinit var binding:ActivityStudentsFullDeatailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStudentsFullDeatailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        val bundle = intent.extras
        if (bundle != null){


            binding.fullNameTVfull.text="${bundle.getString("fullname")}"
            binding.placeTVfull.text="${bundle.getString("place")}"
            binding.genderTVfull.text="${bundle.getString("gender")}"
            binding.registrationSelectionTVfull.text="${bundle.getString("registration")}"
            binding.emailTVfull.text="${bundle.getString("email")}"
            binding.contactTVfull.text="${bundle.getString("mobile" )}"
            binding.employeeStatusTVfull.text="${bundle.getString("employeestatus")}"
            binding.qualificationTVfull.text="${bundle.getString("education")}"
            binding.collegeNameTVfull.text="${bundle.getString("college")}"
            binding.graduationYearTVfull.text="${bundle.getString("graduationyear")}"
            binding.tellmeMoreTVfull.text="${bundle.getString("tellmeabout")}"
            binding.whyDoYouJoinSPSTVfull.text="${bundle.getString("whydoyoujoinsps")}"
            binding.whatMotivationTVfull.text="${bundle.getString("motivation")}"
            binding.wherefromhearaboutSPSSTVfull.text="${bundle.getString("whereheresps")}"

            regis=bundle.getString("registration").toString()


        }




        binding.acceptBtnStdDtls.setOnClickListener {
           /* val contact_number = "+919072990008"
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$contact_number")
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 100)
            } else {
                try {
                    val oldStudent=getoldStudentReg("Accepted")
                    val newStudentMap=getNewStudentMap()
                    updateRegister(oldStudent,newStudentMap)
                    Toast.makeText(this,newStudentMap.toString(),Toast.LENGTH_LONG).show()
                    startActivity(callIntent)

                } catch (e: SecurityException) {
                    e.printStackTrace()
                }
            }*/
            regis="accepted"
            val oldStudent=getoldStudentReg(regis)

            val newStudentMap=getNewStudentMap()
            updateRegister(oldStudent,newStudentMap)
           // Toast.makeText(this,newStudentMap.toString(),Toast.LENGTH_LONG).show()
        }
        binding.rejectBtnStd.setOnClickListener {
            regis="Rejected"
            val oldStudent=getoldStudentReg(regis)
            val newStudentMap=getNewStudentMap()
            Toast.makeText(this,newStudentMap.toString(),Toast.LENGTH_LONG).show()
            updateRegister(oldStudent,newStudentMap)
           // Toast.makeText(this,"Rejected Specified Student",Toast.LENGTH_LONG).show()
            //finish()
        }
    }
    private fun getoldStudentReg(rg:String):Student{
        val registdata=rg
        return Student(registdata)
    }
    private fun getNewStudentMap():Map<String,Any>{
        val reg=regis
        val map= mutableMapOf<String,Any>()
        if(reg.isNotEmpty()){
            map["registration"]=reg
            //Toast.makeText(this,"REgist newmap",Toast.LENGTH_LONG).show()
        }
        return map
    }
    private fun updateRegister(student: Student,newStudentMap:Map<String,Any>)= CoroutineScope(Dispatchers.IO).launch {
        try {
            val studentQuery=studentCollectionRef
                .whereEqualTo("registration",student.registration)
                .get()
                .await()
            if (studentQuery.documents.isNotEmpty()){
                for (documents in studentQuery){
                    try {
                        studentCollectionRef.document(documents.id).set(
                            newStudentMap,
                            SetOptions.merge()
                        )
                    }catch (e:Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@StudentsFullDeatails,e.message,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }else{
                withContext(Dispatchers.Main){
                    Toast.makeText(this@StudentsFullDeatails,"student Query empty...",Toast.LENGTH_LONG).show()

                }

            }
        }catch (e:Exception){
            Log.d("errroooossssssss",e.message.toString())
        }
    }

}
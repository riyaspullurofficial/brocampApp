package com.example.brocamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brocamp.databinding.ActivityStudentsListViewBinding
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.lang.StringBuilder

class StudentsListView : AppCompatActivity() {

    private val studentCollectionRef= Firebase.firestore.collection("studentDetails")
    lateinit var stdLstBnding:ActivityStudentsListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stdLstBnding= ActivityStudentsListViewBinding.inflate(layoutInflater)
        setContentView(stdLstBnding.root)
        supportActionBar?.hide()
        retrieveStudentDetailsOnebyOne()
        stdLstBnding.backBtnStudentList.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        stdLstBnding.logoutBtn.setOnClickListener {
            startActivity(Intent(this,AdminLogin::class.java))
            finish()
        }
        stdLstBnding.recyclerviewStudentlist.setOnClickListener {
            startActivity(Intent(this,StudentsFullDeatails::class.java))
        }

/*
        var xyx= arrayListOf<StudentListViewDataClass>()
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))
        xyx.add(StudentListViewDataClass("Rahul","tirur","Btech",907222888,"not specified"))

        stdLstBnding.recyclerviewStudentlist.layoutManager=LinearLayoutManager(this)
        val aa=StudentListAdapter(applicationContext,xyx)
        stdLstBnding.recyclerviewStudentlist.adapter=aa
*/
    }
    private fun retrieveStudentDetails()= CoroutineScope(Dispatchers.IO).launch {
        try {
            val querySnapshot=studentCollectionRef.get().await()
            val sb=StringBuilder()
            for (document in querySnapshot.documents){
                //val persons=document.toObject(Student::class.java)
                val student=document.toObject<Student>()
                sb.append("$student\n")
            }
            withContext(Dispatchers.Main){
                stdLstBnding.headingRegisterTV.text=sb.toString()
            }
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@StudentsListView,e.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun retrieveStudentDetailsOnebyOne()= CoroutineScope(Dispatchers.IO).launch {
        try {
            var xyx= arrayListOf<StudentListViewDataClass>()
            var nameFull:String=""
            var qualif:String=""
            var regiss:String=""
            var plac:String=""
            var mob:String=""
            val querySnapshot=studentCollectionRef.get().await()
            val sb=StringBuilder()
            for (document in querySnapshot.documents){
                //val persons=document.toObject(Student::class.java)
                val student=document.toObject<Student>()
                nameFull= student!!.fullName
                Log.e("dataaa", "$nameFull...")
                qualif= student!!.educationalQualification
                regiss=student!!.registration.toString()
                plac=student!!.place.toString()
                mob=student!!.contactMob.toString()
                xyx.add(StudentListViewDataClass(nameFull,plac,qualif,mob,regiss))
                Log.e("Error", student.fullName)

             //   sb.append("$student\n")
            }
            withContext(Dispatchers.Main){


                stdLstBnding.recyclerviewStudentlist.layoutManager=LinearLayoutManager(this@StudentsListView)
                val aa=StudentListAdapter(applicationContext,xyx)
                stdLstBnding.recyclerviewStudentlist.adapter=aa
            }
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@StudentsListView,e.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }

}
package com.example.brocamp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brocamp.databinding.ActivityStudentsListViewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

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
            var placeFull:String=""
            var contactMobFull:String=""
            var genderFull:String=""
            var emailFull:String=""
            var employeeStatusFull:String=""
            var educationalQualificationFull: String=""
            var collegeNameFull:String=""
            var graduationYearFull: Int=-1
            var tellMeAboutYouFull:String=""
            var whyDoYouJoinSPSFull: String=""
            var whatMotivatesFull:String=""
            var whereHearSPSFull: String=""
            var registrationFull:String=""
            var monthFull:String=""
            var yearFull:Int=-1

            val querySnapshot=studentCollectionRef.get().await()
            val sb=StringBuilder()
            for (document in querySnapshot.documents){
                //val persons=document.toObject(Student::class.java)
                val student=document.toObject<Student>()
                nameFull= student!!.fullName
                Log.e("dataaa", "$nameFull...")
                placeFull= student!!.place
                contactMobFull=student!!.contactMob
                genderFull=student!!.gender
                emailFull=student!!.email
                employeeStatusFull=student!!.employeeStatus
                educationalQualificationFull=student!!.educationalQualification
                collegeNameFull=student!!.collegeName
                graduationYearFull=student!!.graduationYear
                tellMeAboutYouFull=student!!.tellMeAboutYou
                whyDoYouJoinSPSFull=student!!.whyDoYouJoinSPS
                whatMotivatesFull=student!!.whatMotivates
                whereHearSPSFull=student!!.whereHearSPS
                registrationFull=student!!.registration
                monthFull=student!!.month
                yearFull=student!!.year





                xyx.add(StudentListViewDataClass(nameFull,placeFull,contactMobFull,genderFull,emailFull,employeeStatusFull,educationalQualificationFull,
                                                    collegeNameFull,graduationYearFull,tellMeAboutYouFull,whyDoYouJoinSPSFull,whatMotivatesFull,whereHearSPSFull,
                                                    registrationFull,monthFull,yearFull
                                                ))
                Log.e("Error", student.fullName)

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


    inner class StudentListAdapter(cnx: Context, xyx: ArrayList<StudentListViewDataClass>) : RecyclerView.Adapter<StudentListAdapter.MyViewHolder>() {
        val bundle = Bundle()
         var xyz = xyx
         var ctx = cnx

         inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
             var names = v.findViewById<TextView>(R.id.nameCardTV)
             var place = v.findViewById<TextView>(R.id.placeCardTV)
             var qualificationDet: TextView = v.findViewById(R.id.qualificationCardTV)
             var mobileno = v.findViewById(R.id.mobileCardTV) as TextView
             var registSel = v.findViewById<TextView>(R.id.registrationSelectionCardTV)
             val root = v


         }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
             val itemView =
                 LayoutInflater.from(parent.context).inflate(R.layout.studentdatacard, parent, false)
             return MyViewHolder(itemView)
         }

         override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
             var u = xyz[position]

             /*
             holder.names.text = u.name
             holder.place.text = u.place
             holder.qualificationDet.text = u.qualification
             holder.mobileno.text = u.mobileNum.toString()
             holder.registSel.text = u.register

             holdername = u.name
             holderplace = u.place
             holderQualification = u.qualification
             holderMob = u.mobileNum
             */
             holder.names.text = u.fullName
             holder.place.text = u.place
             holder.qualificationDet.text = u.educationalQualification
             holder.mobileno.text = u.contactMob.toString()
             holder.registSel.text = u.registration


             val holderfullName:String=u.fullName
             val holderplace:String=u.place
             val holdercontactMob:String=u.contactMob
             val holdergender:String=u.gender
             val holderemail:String=u.email
             val holderemployeeStatus:String=u.employeeStatus
             val holdereducationalQualification: String=u.educationalQualification
             val holdercollegeName:String=u.collegeName
             val holdergraduationYear: Int=u.graduationYear
             val holdertellMeAboutYou:String=u.tellMeAboutYou
             val holderwhyDoYouJoinSPS: String=u.whyDoYouJoinSPS
             val holderwhatMotivates:String=u.whatMotivates
             val holderwhereHearSPS: String=u.whereHearSPS
             val holderregistration:String=u.registration
             val holdermonth:String=u.month
             val holderyear:Int=u.year


             holder.root.setOnClickListener {
             /*   startActivity(Intent(this@StudentsListView,StudentsFullDeatails::class.java))

                 val intent = Intent(applicationContext, StudentsFullDeatails::class.java)
                 intent.putExtra("fullname", holderfullName)
                 intent.putExtra("place", holderplace)
                 intent.putExtra("mobile",holdercontactMob)
                 intent.putExtra("gender", holdergender)
                 intent.putExtra("email", holderemail)
                 intent.putExtra("employeestatus", holderemployeeStatus)
                 intent.putExtra("education", holdereducationalQualification)
                 intent.putExtra("college", holdercollegeName)
                 intent.putExtra("graduationyear", holdergraduationYear)
                 intent.putExtra("tellmeabout", holdertellMeAboutYou)
                 intent.putExtra("whydoyoujoinsps", holderwhyDoYouJoinSPS)
                 intent.putExtra("motivation", holderwhatMotivates)
                 intent.putExtra("whereheresps", holderwhereHearSPS)
                 intent.putExtra("registration", holderregistration)
                 intent.putExtra("month", holdermonth)
                 intent.putExtra("year", holderyear)
                 startActivity(intent);

                */
                 Log.d("Employee status=====",holderemployeeStatus)
                 Toast.makeText(this@StudentsListView,"error${holderemployeeStatus}",Toast.LENGTH_LONG).show()


                 bundle.putString("fullname", holderfullName)
                 bundle.putString("place", holderplace)
                 bundle.putString("mobile",holdercontactMob)
                 bundle.putString("gender", holdergender)
                 bundle.putString("email", holderemail)
                 bundle.putString("employeestatus", holderemployeeStatus)
                 bundle.putString("education", holdereducationalQualification)
                 bundle.putString("college", holdercollegeName)
                 bundle.putInt("graduationyear", holdergraduationYear)
                 bundle.putString("tellmeabout", holdertellMeAboutYou)
                 bundle.putString("whydoyoujoinsps", holderwhyDoYouJoinSPS)
                 bundle.putString("motivation", holderwhatMotivates)
                 bundle.putString("whereheresps", holderwhereHearSPS)
                 bundle.putString("registration", holderregistration)
                 bundle.putString("month", holdermonth)
                 bundle.putInt("year", holderyear)

                 val intent = Intent(applicationContext, StudentsFullDeatails::class.java)
                 intent.putExtras(bundle)
                 startActivity(intent)


                 Log.d("hey",holderfullName)
             }

     }

     override fun getItemCount(): Int {
         return xyz.size
     }
 }
}
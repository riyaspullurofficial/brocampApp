//package com.example.brocamp
//
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import android.widget.Toast
//import androidx.core.content.ContextCompat.startActivity
//import androidx.recyclerview.widget.RecyclerView
//import java.lang.Exception
//
//
//class StudentListAdapter(cnx: Context, xyx: ArrayList<StudentListViewDataClass>) :RecyclerView.Adapter<StudentListAdapter.MyViewHolder>(){
//    var xyz=xyx
//    var ctx=cnx
//
//    var holdername:String=""
//    var holderplace:String=""
//    var holderQualification:String=""
//    var holderMob:String=""
//    lateinit var stdlstviw:StudentsListView
//
//    var intent: Intent = Intent(ctx.getApplicationContext(), StudentsFullDeatails::class.java)
//
//
//    class MyViewHolder(v:View):RecyclerView.ViewHolder(v){
//        var names=v.findViewById<TextView>(R.id.nameCardTV)
//        var place=v.findViewById<TextView>(R.id.placeCardTV)
//        var qualificationDet:TextView=v.findViewById(R.id.qualificationCardTV)
//        var mobileno=v.findViewById(R.id.mobileCardTV)as TextView
//        var registSel=v.findViewById<TextView>(R.id.registrationSelectionCardTV)
//        val root=v
//
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.studentdatacard,parent,false)
//        return MyViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        var u=xyz[position]
//        holder.names.text=u.name
//        holder.place.text=u.place
//        holder.qualificationDet.text=u.qualification
//        holder.mobileno.text=u.mobileNum.toString()
//        holder.registSel.text=u.register
//
//        holdername=u.name
//        holderplace=u.place
//        holderQualification=u.qualification
//        holderMob=u.mobileNum
//
//        holder.root.setOnClickListener {
//
//
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return xyz.size
//    }
//}
//
//

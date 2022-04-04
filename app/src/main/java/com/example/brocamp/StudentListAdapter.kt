package com.example.brocamp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentListAdapter(cnx: Context, xyx: ArrayList<StudentListViewDataClass>) :RecyclerView.Adapter<StudentListAdapter.MyViewHolder>(){
    var xyz=xyx
    var ctx=cnx
    class MyViewHolder(v:View):RecyclerView.ViewHolder(v){
        var names=v.findViewById<TextView>(R.id.nameCardTV)
        var place=v.findViewById<TextView>(R.id.placeCardTV)
        var qualificationDet:TextView=v.findViewById(R.id.qualificationCardTV)
        var mobileno=v.findViewById(R.id.mobileCardTV)as TextView
        var registSel=v.findViewById<TextView>(R.id.registrationSelectionCardTV)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.studentdatacard,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var u=xyz[position]
        holder.names.text=u.name
        holder.place.text=u.place
        holder.qualificationDet.text=u.qualification
        holder.mobileno.text=u.mobileNum.toString()
        holder.registSel.text=u.register

        holder.names.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return xyz.size
    }
}



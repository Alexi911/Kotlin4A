package com.example.kotlin4a.presentation.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin4a.R
import com.example.kotlin4a.domain.Warriors
import kotlinx.android.synthetic.main.row_display.view.*

class WarriorsAdapter(val arrayList: ArrayList<Warriors>, val context: Context) :
    RecyclerView.Adapter<WarriorsAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindItems(model: Warriors)
        {
            itemView.firstLine.text = model.warriors_firstName
            itemView.secondLine.text = model.warriors_lastName

            Glide.with(itemView.imageView3).load(model.warriors_image)
                .into(itemView.imageView3)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.row_display, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int
    {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bindItems(arrayList[position])
        holder.itemView.setOnClickListener{

            val model = arrayList[position]
            val image = model.warriors_image
            val firstName = model.warriors_firstName
            val lastName = model.warriors_lastName
            val jersey = model.warriors_jersey
            val height= model.warriors_heightMeters
            val weight= model.warriors_weightKilograms
            val age = model.warriors_age
            val position = model.warriors_pos

            val intent = Intent(context, WarriorsDetails::class.java)
            intent.putExtra("Image", image)
            intent.putExtra("FirstName", firstName)
            intent.putExtra("LastName", lastName)
            intent.putExtra("Jersey", jersey)
            intent.putExtra("Height", height)
            intent.putExtra("Weight", weight)
            intent.putExtra("Age", age)
            intent.putExtra("Position", position)

            context.startActivity(intent)
        }
    }
}
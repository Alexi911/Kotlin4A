package com.example.kotlin4a.presentation.view

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlin4a.R
import kotlinx.android.synthetic.main.user_info_display.*

class WarriorsDetails : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.user_info_display)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        val intentUser = intent
        val displayImage = intentUser.getStringExtra("Image")
        val displayFirstName = intentUser.getStringExtra("FirstName")
        val displayLastName = intentUser.getStringExtra("LastName")
        val displayJersey = intentUser.getStringExtra("Jersey")
        val displayHeight = intentUser.getStringExtra("Height")
        val displayWeight = intentUser.getStringExtra("Weight")
        val displayAge = intentUser.getStringExtra("Age")
        val displayPosition = intentUser.getStringExtra("Position")

        FirstName_details.text = displayFirstName
        LastName_details.text = displayLastName
        Jersey_details.text = displayJersey
        HeightMeters_details.text = displayHeight
        WeightKilograms_details.text = displayWeight
        Age_details.text = displayAge
        Pos_details.text = displayPosition

        Glide.with(this).load(displayImage).into(Image_details)
    }
}
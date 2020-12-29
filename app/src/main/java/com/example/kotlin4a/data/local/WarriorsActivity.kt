package com.example.kotlin4a.data.local

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin4a.R
import com.example.kotlin4a.presentation.view.WarriorsAdapter
import com.example.kotlin4a.domain.Warriors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_warriors.*
import java.lang.reflect.Type

class WarriorsActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_warriors)
        val queue = Volley.newRequestQueue(this)
        parseJson(queue)
        swipeRefresh.setOnRefreshListener()
        {
            parseJson(queue)
            swipeRefresh.isRefreshing = false
        }
    }

    private fun parseJson(requestQueue: RequestQueue)
    {
        val url = "https://raw.githubusercontent.com/Alexi911/NBA/master/WarriorsPlayers.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener
            {
                success ->
                val type: Type = object : TypeToken<ArrayList<Warriors?>?>() {}.type
                val githubUser: ArrayList<Warriors> = Gson().fromJson(success, type)
                setDataToRecyclerView(githubUser)
            },

            Response.ErrorListener
            {
                error_ -> Toast.makeText(this, "API Error", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(stringRequest)
    }

    private fun setDataToRecyclerView(githubUser: ArrayList<Warriors>)
    {

        val customAdapter = WarriorsAdapter(githubUser, this)
        teams_recycler_view.layoutManager = LinearLayoutManager(this)
        teams_recycler_view.adapter = customAdapter

    }
}
package com.example.kotlin4a.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import com.example.kotlin4a.R
import com.example.kotlin4a.data.local.WarriorsActivity
import com.example.kotlin4a.domain.InputValidation
import com.example.kotlin4a.data.repository.UserRepository
import com.example.kotlin4a.presentation.RegisterActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), View.OnClickListener
{

    private val activity = this@MainActivity

    private lateinit var nestedScrollView: NestedScrollView

    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout

    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText

    private lateinit var appCompatButtonLogin: AppCompatButton

    private lateinit var textViewLinkRegister: AppCompatTextView

    private lateinit var inputValidation: InputValidation
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        initViews()
        initListeners()
        initObjects()
    }

    @SuppressLint("WrongViewCast")
    private fun initViews()
    {

        nestedScrollView = findViewById<View>(R.id.nestedScrollView) as NestedScrollView

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail1) as TextInputLayout
        textInputLayoutPassword = findViewById<View>(R.id.textInputLayoutPassword1) as TextInputLayout

        textInputEditTextEmail = findViewById<View>(R.id.textInputEditTextEmail1) as TextInputEditText
        textInputEditTextPassword = findViewById<View>(R.id.textInputEditTextPassword1) as TextInputEditText

        appCompatButtonLogin = findViewById<View>(R.id.appCompatButtonLogin) as AppCompatButton

        textViewLinkRegister = findViewById<View>(R.id.textViewLinkRegister) as AppCompatTextView

    }

    private fun initListeners()
    {

        appCompatButtonLogin!!.setOnClickListener(this)
        textViewLinkRegister!!.setOnClickListener(this)
    }

    private fun initObjects()
    {

        userRepository = UserRepository(activity)
        inputValidation = InputValidation(activity)

    }

    override fun onClick(v: View)
    {
        when (v.id)
        {
            R.id.appCompatButtonLogin -> verifyFromSQLite()
            R.id.textViewLinkRegister ->
            {
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }

    private fun verifyFromSQLite()
    {

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail!!, textInputLayoutEmail!!,"Enter Valid Email"))
        {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail!!, textInputLayoutEmail!!,"Enter Valid Email"))
        {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword!!, textInputLayoutPassword!!,"Enter Valid Email"))
        {
            return
        }

        if (userRepository!!.checkUser(textInputEditTextEmail!!.text.toString().trim { it <= ' ' }, textInputEditTextPassword!!.text.toString().trim { it <= ' ' }))
        {


            val accountsIntent = Intent(activity, WarriorsActivity::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)
        } else
        {
            Snackbar.make(nestedScrollView!!, "Wrong Email or Password", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun emptyInputEditText() {
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
    }
}

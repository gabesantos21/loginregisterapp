package com.example.login_register_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class FrontActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front)
        register()
        login()
    }

    private fun login() {
        var loginBtnFront = findViewById(R.id.loginBtnFront) as TextView
        loginBtnFront.setOnClickListener{
            startActivity(Intent(this@FrontActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun register() {
        var registerBtnFront = findViewById(R.id.registerBtnFront) as Button

        registerBtnFront.setOnClickListener{
            startActivity(Intent(this@FrontActivity, RegistrationActivity::class.java))
            finish()
        }
    }
}
package com.example.login_register_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_activity)

        forgotPassword()
    }

    private fun forgotPassword() {
        var backLink = findViewById(R.id.backBtn) as TextView

        backLink.setOnClickListener{
            startActivity(Intent(this@ForgotPasswordActivity, LoginActivity::class.java))
            finish()
        }
    }
}
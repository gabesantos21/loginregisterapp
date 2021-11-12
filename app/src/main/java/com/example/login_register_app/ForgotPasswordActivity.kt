package com.example.login_register_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_activity)

        forgotPassword()
    }

    private fun forgotPassword() {
        var backLink = findViewById(R.id.backBtn) as TextView
        var emailEditText = findViewById(R.id.emailEditText) as EditText
        var resetBtn = findViewById(R.id.resetBtn) as Button

        resetBtn.setOnClickListener{
            if (TextUtils.isEmpty(emailEditText.text.toString())) {
                Toast.makeText(this, "Please enter an Email", Toast.LENGTH_LONG).show()
            }
            else {
                auth.sendPasswordResetEmail(emailEditText.text.toString())
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "Unable to send reset mail", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
            }
        }

        backLink.setOnClickListener{
            startActivity(Intent(this@ForgotPasswordActivity, LoginActivity::class.java))
            finish()
        }
    }
}
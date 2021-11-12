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

    private lateinit var backLink: TextView
    private lateinit var emailEditText: EditText
    private lateinit var resetBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_activity)

        backLink = findViewById(com.example.login_register_app.R.id.backBtn)
        emailEditText =  findViewById(com.example.login_register_app.R.id.emailEditText)
        resetBtn =  findViewById(R.id.resetBtn)
        auth = FirebaseAuth.getInstance()

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
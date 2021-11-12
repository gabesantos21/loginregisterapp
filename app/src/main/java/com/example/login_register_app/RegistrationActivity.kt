package com.example.login_register_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class RegistrationActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    private fun register(){
        var registerButton = findViewById(R.id.registerBtn) as Button
        var email = findViewById(R.id.email) as EditText
        var password = findViewById(R.id.passwordInput) as EditText
        var loginLink = findViewById(R.id.hasaccountText) as TextView

        registerButton.setOnClickListener{
            if(TextUtils.isEmpty(email.text.toString())){
                email.setError("Please enter an email")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(password.text.toString())){
                password.setError("Please enter a password")
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child(currentUser?.uid!!)
                        Toast.makeText(this, "Registration successful!",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@RegistrationActivity, ProfileActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Registration failed, please try again!",Toast.LENGTH_LONG).show()
                    }
                }
        }
        loginLink.setOnClickListener{
            startActivity(Intent(this@RegistrationActivity, FrontActivity::class.java))
            finish()
        }
    }
}
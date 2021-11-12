package com.example.login_register_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val currentuser = auth.currentUser

        if (currentuser != null){
            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
            finish()
        }

        login()

    }

    private fun login() {
        var loginButton = findViewById(R.id.loginBtn) as Button
        var email = findViewById(R.id.emailLogin) as EditText
        var password = findViewById(R.id.passwordInputLogin) as EditText
        var registerLink = findViewById(R.id.hasNoAccount) as TextView

        loginButton.setOnClickListener {
            if (TextUtils.isEmpty(email.text.toString())) {
                email.setError("Please enter first name")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(password.text.toString())) {
                password.setError("Please enter last name")
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, "Login failed, please try again!",
                            Toast.LENGTH_LONG).show()
                    }
                }
        }

        registerLink.setOnClickListener{
            startActivity(Intent(this@LoginActivity, FrontActivity::class.java))
            finish()
        }
    }
}
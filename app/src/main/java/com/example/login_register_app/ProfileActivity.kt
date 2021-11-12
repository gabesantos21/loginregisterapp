package com.example.login_register_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ProfileActivity() : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database : FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        loadProfile()
    }

    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

//      var firstName = findViewById(R.id.firstName) as TextView
//      var lastName = findViewById(R.id.lastName) as TextView
//        var email = findViewById(R.id.EmailProfile) as TextView
        var logoutButton = findViewById(R.id.logoutBtn) as Button
        var deleteButton = findViewById(R.id.deleteBtn) as Button
        var editButton = findViewById(R.id.editBtn) as Button
        var addButton = findViewById(R.id.addBtn) as Button
        var welcomeText = findViewById(R.id.welcomeText) as TextView
        var nameEditText = findViewById(R.id.nameEditText) as EditText
        var eventEditText = findViewById(R.id.eventEditText) as EditText
        var numberEditText = findViewById(R.id.numberEditText) as EditText


//        email.text = "Email: " + user?.email

        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                firstName.text = "Firstname: " + snapshot.child("firstname").value.toString()
//                lastName.text = "Lastname: " + snapshot.child("lastname").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        logoutButton.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }

        addButton.setOnClickListener{
            Toast.makeText(this@ProfileActivity,"Add Attendee",Toast.LENGTH_SHORT).show()
        }

        editButton.setOnClickListener{
            Toast.makeText(this@ProfileActivity,"Edit Attendee",Toast.LENGTH_SHORT).show()
//            firstName.text = "Firstname: " + firstNameEditText.getText().toString()
//            lastName.text = "Lastname: " + lastNameEditText.getText().toString()

//           val user = mapOf<String, String>(
//               "firstname" to firstNameEditText.getText().toString(),
//               "lastname" to lastNameEditText.getText().toString(),
//           )

//            val currentUser = auth.currentUser
//            databaseReference?.child(currentUser?.uid!!)?.updateChildren(user)?.addOnSuccessListener{
//                nameEditText.getText().clear()
//                eventEditText.getText().clear()
//                numberEditText.getText().clear()
//
//                Toast.makeText(this@ProfileActivity,"Successfully Updated Account",Toast.LENGTH_SHORT).show()
//            }?.addOnFailureListener{
//                Toast.makeText(this@ProfileActivity,"Failed to Update Account",Toast.LENGTH_SHORT).show()
//            }
        }

        deleteButton.setOnClickListener{
            Toast.makeText(this@ProfileActivity,"Delete Attendee",Toast.LENGTH_SHORT).show()
//            val currentUser = auth.currentUser
//            databaseReference?.child(currentUser?.uid!!)?.removeValue()?.addOnCompleteListener{
//                Toast.makeText(this@ProfileActivity,"Successfully Deleted Account",Toast.LENGTH_SHORT).show()
//            }?.addOnFailureListener{
//                Toast.makeText(this@ProfileActivity,"Failed to Delete Account",Toast.LENGTH_SHORT).show()
//            }
//            auth.signOut()
//            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
//            finish()
        }
    }
}
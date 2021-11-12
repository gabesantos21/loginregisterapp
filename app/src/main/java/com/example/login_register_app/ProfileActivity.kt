package com.example.login_register_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileActivity() : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        databaseReference = Firebase.database.getReference("Attendees")

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
            if(TextUtils.isEmpty(nameEditText.text.toString()) || TextUtils.isEmpty(eventEditText.text.toString())
                || TextUtils.isEmpty(numberEditText.text.toString())){
                Toast.makeText(this@ProfileActivity,"Please fill out all the fields!",Toast.LENGTH_SHORT).show()
            }else{
                var name: String = nameEditText.text.toString()
                var event: String = eventEditText.text.toString()
                var number: String = numberEditText.text.toString()

                val attendee: Attendee = Attendee(name, event, number)

                userreference.push().setValue(attendee)
                Toast.makeText(this@ProfileActivity,"Successfully added Attendee",Toast.LENGTH_SHORT).show()
                nameEditText.text.clear()
                eventEditText.text.clear()
                numberEditText.text.clear()
            }

        }

        editButton.setOnClickListener{

            if(TextUtils.isEmpty(nameEditText.text.toString()) || TextUtils.isEmpty(eventEditText.text.toString())
                || TextUtils.isEmpty(numberEditText.text.toString())){
                Toast.makeText(this@ProfileActivity,"Please fill out all the fields!",Toast.LENGTH_SHORT).show()
            }else{
                var value: String = nameEditText.text.toString()
                var insertQuery = userreference.orderByChild("name").equalTo(value)

                insertQuery.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach{
                            var name: String = nameEditText.text.toString()
                            var event: String =eventEditText.text.toString()
                            var number: String =numberEditText.text.toString()

                            var attendee:Attendee = Attendee(name, event, number)
                            it.ref.setValue(attendee)
                        }
                        nameEditText.text.clear()
                        eventEditText.text.clear()
                        numberEditText.text.clear()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                Toast.makeText(this@ProfileActivity,"Successfully edited an attendee",Toast.LENGTH_SHORT).show()
            }

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
            if(TextUtils.isEmpty(nameEditText.text.toString())){
                Toast.makeText(this@ProfileActivity,"Please fill out name in order to delete!",Toast.LENGTH_SHORT).show()
            }else{
                var value: String = nameEditText.text.toString()
                var deleteQuery = userreference.orderByChild("name").equalTo(value)

                deleteQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach{
                            it.ref.removeValue()
                        }
                        nameEditText.text.clear()
                        eventEditText.text.clear()
                        numberEditText.text.clear()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
                Toast.makeText(this@ProfileActivity,"Successfully removed an attendee",Toast.LENGTH_SHORT).show()
            }

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
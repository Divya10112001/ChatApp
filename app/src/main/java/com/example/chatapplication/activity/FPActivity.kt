package com.example.chatapplication.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.chatapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FPActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fpactivity)
        supportActionBar?.hide()
        val backbtn = findViewById<ImageView>(R.id.backfp)
        val loginbtn = findViewById<TextView>(R.id.logggg)
        val emailtext = findViewById<EditText>(R.id.EMAILfp)
        val register = findViewById<TextView>(R.id.registerfp)
        loginbtn.setOnClickListener {
            if (emailtext.text.toString().isEmpty())
                Toast.makeText(this, "Please fill all the credentials.", Toast.LENGTH_SHORT).show()
            else {
                val email = emailtext.text.toString()
                mAuth = FirebaseAuth.getInstance()
                val reference: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("users")
                sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                val checkUser: Query = reference.orderByChild("email").equalTo(email)
                val postListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            val emailfromDB =
                                dataSnapshot.child(email).child("email")
                                    .getValue(true)
                            if (emailfromDB != null && emailfromDB == email) {
                                val emailDB =
                                    dataSnapshot.child(email).child("email")
                                        .getValue(true)
                                editor.putString("email", emailDB.toString())
                                editor.apply()
                                mAuth!!.sendPasswordResetEmail(email)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                this@FPActivity,
                                                "Password link Sent!!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            startActivity(
                                                Intent(
                                                    this@FPActivity,
                                                    LoginActivity::class.java
                                                )
                                            )
                                        } else {
                                            Toast.makeText(
                                                this@FPActivity,
                                                "Failed!!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            } else {
                                Toast.makeText(
                                    this@FPActivity,
                                    "No such User exist",
                                    Toast.LENGTH_SHORT
                                ).show()
                                emailtext.requestFocus()
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                }

                checkUser.addValueEventListener(postListener)

            }
        }
        register.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
        backbtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
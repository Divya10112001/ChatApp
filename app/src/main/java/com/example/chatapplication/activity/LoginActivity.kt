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

class LoginActivity : AppCompatActivity() {
    lateinit var mAuth :FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val backbtn = findViewById<ImageView>(R.id.back)
        val loginbtn = findViewById<TextView>(R.id.log)
        val emailtext = findViewById<EditText>(R.id.EMAIL)
        val passwordtext = findViewById<EditText>(R.id.password)
        val register = findViewById<TextView>(R.id.register)
        val fp = findViewById<TextView>(R.id.fp)
        mAuth = FirebaseAuth.getInstance()
        fp.setOnClickListener {
            startActivity(Intent(this, FPActivity::class.java))
        }
        register.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
        backbtn.setOnClickListener {
            startActivity(Intent(this, ShowActivity::class.java))
        }
        loginbtn.setOnClickListener {
            if (emailtext.text.toString().isEmpty() || passwordtext.text.toString().isEmpty()) {
                Toast.makeText(
                    this, "Please fill all credentials",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val email = emailtext.text.toString()
                val password = passwordtext.text.toString()
                val reference: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("users")
                sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                val checkUser: Query = reference.orderByChild("email").equalTo(email)
                val postListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            val passwordfromDB =
                                dataSnapshot.child(email).child("password")
                                    .getValue(true)
                            if (passwordfromDB != null && passwordfromDB == password) {

                                val namefromDB =
                                    dataSnapshot.child(email).child("name")
                                        .getValue(true)
                                editor.putString("username", namefromDB.toString())
                                val emailfromDB =
                                    dataSnapshot.child(email).child("email")
                                        .getValue(true)
                                editor.putString("email", emailfromDB.toString())
                                editor.apply()
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login Successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                )
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Wrong Password",
                                    Toast.LENGTH_SHORT
                                ).show()

                                passwordtext.requestFocus()
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "No such User exist",
                                Toast.LENGTH_SHORT
                            ).show()
                            emailtext.requestFocus()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                }

                checkUser.addValueEventListener(postListener)


            }
        }
    }
}
package com.example.chatapplication.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.chatapplication.R
import com.example.chatapplication.modal.userdatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()
        var rootNode: FirebaseDatabase
        var references: DatabaseReference
        mAuth = FirebaseAuth.getInstance()
        val backbtn = findViewById<ImageView>(R.id.regback)
        val loginbtn = findViewById<TextView>(R.id.loginpage)
        val emailtext = findViewById<EditText>(R.id.regEMAIL)
        val passwordtext = findViewById<EditText>(R.id.regPASS)
        val nametext = findViewById<EditText>(R.id.NAME)
        val cpasswordtext = findViewById<EditText>(R.id.regCPASS)
        val register = findViewById<TextView>(R.id.regbtn)
         backbtn.setOnClickListener {
             startActivity(Intent(this, LoginActivity::class.java))
         }
        loginbtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        register.setOnClickListener {
            if(emailtext.text.toString().isEmpty() || passwordtext.text.toString().isEmpty()
                || cpasswordtext.text.toString().isEmpty() || nametext.text.toString().isEmpty())
                Toast.makeText(this, "Fill all the credentials", Toast.LENGTH_SHORT).show()
            else {
                val username = nametext.text.toString()
                val useremail = emailtext.text.toString()
                val userpassword = passwordtext.text.toString()
                val usercpassword = cpasswordtext.text.toString()
                if (usercpassword == userpassword) {
                    rootNode = FirebaseDatabase.getInstance()
                    references = rootNode.getReference("users")
                    val helper =
                        userdatabase(username, useremail, userpassword)
                    references.child(useremail).setValue(helper)
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Your Account has been created successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    /*sharedPreferences = getSharedPreferences("registration", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("name", username)
                    editor.putString("email", useremail)
                    editor.apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()*/
                }
                else
                    Toast.makeText(this, "Passwords do not match!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.chatto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatto.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.sql.DatabaseMetaData

class signUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var mDbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView5.setOnClickListener{
            val intent = Intent(this, signInActivity::class.java)
            startActivity(intent)
        }

        binding.idSignUpBTN.setOnClickListener{

            val name = binding.idTIName.text.toString()
            val email = binding.email.text.toString()
            val pass= binding.passwordTI.text.toString()
            val confirmpass = binding.idRetypePasswordTI.text.toString()

            if(name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()){
                if(pass == confirmpass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){

                            //Add user to the database
                            addUserToDataBase(name, email, firebaseAuth.currentUser!!.uid)

                            Toast.makeText(this, "User ${email.toString()} registered successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,signInActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are prohibited", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addUserToDataBase(name: String, email: String, uid: String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }
}
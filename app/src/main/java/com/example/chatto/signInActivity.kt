package com.example.chatto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatto.databinding.ActivitySignInBinding
import com.example.chatto.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class signInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView5.setOnClickListener{
            val intent = Intent(this, signUpActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{

            val email = binding.email.text.toString()
            val pass= binding.passwordTI.text.toString()

            if(email.isNotEmpty() or pass.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            finish()
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "Incorrect username or password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Empty fields are prohibited", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
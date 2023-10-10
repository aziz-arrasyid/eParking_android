package com.example.eparking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.util.Log

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)

        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener()
        {
            Log.d("button:", "$username, $password")
        }

    }
}
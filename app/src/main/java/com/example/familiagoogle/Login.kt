package com.example.familiagoogle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class Login : AppCompatActivity() {
    private lateinit var btnAuth: Button
    private lateinit var authStatusTv: TextView

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // init biometric
        init()
        //set properties like title and description on auth dialog
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometricauth))
            .setSubtitle(getString(R.string.biometricauthsubtitle))
            .setNegativeButtonText(getString(R.string.useapp))
            .build()

        // handle click button, start auth dialog
        btnAuth.setOnClickListener {
            // show auth dialog
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun init() {
        btnAuth = findViewById(R.id.btn_auth)
        authStatusTv = findViewById(R.id.authStatusTv)

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this@Login, executor, object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // auth error, stop tasks that requires auth
                authStatusTv.text = StringBuilder(getString(R.string.autherror)).append(errString)
                toast("Authentification Error: $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                // auth succeed, do tasks that requires auth
                authStatusTv.text = getString(R.string.authsucceed)
                toast(getString(R.string.authsucceed))
                val intent = Intent(applicationContext, Menu::class.java)
                startActivity(intent)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                //auth failed, stop tasks that requires auth
                authStatusTv.text = getString(R.string.authfailed)
                toast(getString(R.string.authfailed))
            }
        })

    }

    private fun toast(message: String) {
        Toast.makeText(this@Login, message, Toast.LENGTH_SHORT).show()
    }

}
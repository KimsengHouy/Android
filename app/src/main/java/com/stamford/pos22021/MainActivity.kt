package com.stamford.pos22021


import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.stamford.pos22021.databinding.ActivityLoginBinding
import java.nio.charset.Charset
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), MODE_PRIVATE
        )

        val isAnyCredentialsSaved =
            sharedPref.getBoolean(getString(R.string.credential_stored_key), false)

        if (isAnyCredentialsSaved) {
            val username = sharedPref.getString(getString(R.string.username_key), "")

            val passwordBase64 = sharedPref.getString(getString(R.string.password_key), "")

            val keyBytesBase64 = sharedPref.getString(getString(R.string.keyBytes_key), "")

            val ivBase64 = sharedPref.getString(getString(R.string.iv_key), "")

            val password = Base64.decode(passwordBase64, Base64.NO_WRAP)
            val keyBytes = Base64.decode(keyBytesBase64, Base64.NO_WRAP)
            val iv = Base64.decode(ivBase64, Base64.NO_WRAP)

            val ivSpec = IvParameterSpec(iv)

            val keySpec = SecretKeySpec(keyBytes, "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
            val decryptedPassword: ByteArray = cipher.doFinal(password)
            val passwordStr = String(decryptedPassword, Charset.forName("UTF-8"))

            Log.i(TAG, "Decrypted password is = $passwordStr")

            binding.UserInputTextView?.setText(username)
            binding.PwInputTextView?.setText(passwordStr)
            binding.rememberMeCheckBox?.isChecked = true
        }

//        setContentView(R.layout.activity_login)
//        Log.i(TAG, "onCreate is called")


        // get reference to TextView
        val titleTextView = findViewById<TextView>(R.id.LoginTextView)
        titleTextView.setOnLongClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            Log.v(TAG, "Setting Activity Detect");

            startActivity(intent)

            return@setOnLongClickListener true
        }
    }

    fun onclick_login_btn(view: View) {
        Log.i(TAG, "Login Button click. Command Center UI started.")

        if (binding.rememberMeCheckBox?.isChecked == true) {
            val username = binding.UserInputTextView?.text.toString()
            val password = binding.PwInputTextView?.text.toString()

            if (username.isNotEmpty()) {
                val sharePref = getSharedPreferences(
                    getString(R.string.preference_file_key), MODE_PRIVATE
                )

                val random = SecureRandom()

                val salt = ByteArray(256)
                val passkey = ByteArray(8)
                random.nextBytes(salt)
                random.nextBytes(passkey)

                val pbKeySpec = PBEKeySpec(passkey.toString().toCharArray(), salt, 1324, 256)
                val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
                val keyBytes = secretKeyFactory.generateSecret(pbKeySpec).encoded
                val keySpec = SecretKeySpec(keyBytes, "AES")
                val ivRandom = SecureRandom()
                val iv = ByteArray(16)
                ivRandom.nextBytes(iv)
                val ivSpec = IvParameterSpec(iv)
                val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
                val encrypted = cipher.doFinal(password.toByteArray(Charset.forName("UTF-8")))

                Log.i(TAG, "Remember me is checked, username is = $username, password = $password")
                Log.i(TAG, "Encoded ciphered password is = $encrypted")

                val passwordBase64 = Base64.encodeToString(encrypted, Base64.NO_WRAP)
                val keyByteBase64 = Base64.encodeToString(keyBytes, Base64.NO_WRAP)
                val passkeyBase64 = Base64.encodeToString(passkey, Base64.NO_WRAP)
                val saltBase64 = Base64.encodeToString(salt, Base64.NO_WRAP)
                val ivBase64 = Base64.encodeToString(iv, Base64.NO_WRAP)

                with(sharePref.edit()) {
                    putString(getString(R.string.username_key), username)
                    putString(getString(R.string.password_key), passwordBase64)
                    putString(getString(R.string.iv_key), ivBase64)
                    putString(getString(R.string.keyBytes_key), keyByteBase64)
                    putBoolean(getString(R.string.credential_stored_key), true)

                    apply()
                }
            }
        } else {
            val sharePref = getSharedPreferences(
                getString(R.string.preference_file_key), MODE_PRIVATE
            )

            with(sharePref.edit()) {
                putBoolean(
                    getString(
                        R.string.credential_stored_key
                    ), false
                )
                apply()
            }
        }
        val intent = Intent(this, CommandCenterActivity::class.java)
        Log.d(TAG, "Login process successful; going to start the CommandActivity")
        startActivity(intent)
    }
}


package com.example.penjualan.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.penjualan.R
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var Username:String
    private lateinit var Password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Username = edtUsername.text.toString()
        Password = edtPassword.text.toString()

       if (Username.isEmpty()){
           edtUsername.error
       }else if(Password.isEmpty()){
           edtPassword.error
       }else if(Username.isEmpty() && Password.isEmpty()){
           edtUsername.error
           edtPassword.error
       }else{
           checkLogin()
       }
    }

    private fun checkLogin() {
        if (Username == "admin" && Password == "admin"){
            startActivity(Intent(this@Login, MainActivity::class.java))
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Androidly Alert")
            builder.setMessage("We have a message")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton("yes") { _, _ ->
                Toast.makeText(applicationContext,
                    "Yes", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(applicationContext,
                    "No", Toast.LENGTH_SHORT).show()
            }

            builder.setNeutralButton("Maybe") { _, _ ->
                Toast.makeText(applicationContext,
                    "Maybe", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}
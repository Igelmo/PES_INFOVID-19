package edu.upc.fib.pes_infovid19.ui.main.activity.user

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        setSupportActionBar(toolbarChangePassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        confirmPasswordButton.setOnClickListener {
            savePasswordChanges()
        }
    }

    private fun savePasswordChanges() {
        var et = findViewById<EditText>(R.id.newPasswordEditText)
        var password = et.text.toString()
        et = findViewById<EditText>(R.id.confirmNewPasswordEditText)
        var confirmPassword = et.text.toString()

        val database = FirebaseDatabase.getInstance().getReference()
        val id = FirebaseAuth.getInstance().currentUser?.uid
        if (id != null && !password.isNullOrEmpty() && !confirmPassword.isNullOrEmpty()) {
            if (password == confirmPassword) {
                database.child("User").child(id).child("password").setValue(password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        user?.let {
                            // Name, email address, and profile photo Url
                            user.updatePassword(password)
                        }
                        onSupportNavigateUp()
                    }
                }
            } else {
                showAlertPassword()
            }
        } else {
            showAlert()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Algun dels camps està buit")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlertPassword() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Les contrasenyes no coincideixen")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
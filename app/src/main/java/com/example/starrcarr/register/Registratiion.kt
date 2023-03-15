package com.example.starrcarr.register

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starrcarr.databinding.ActivityRegistratiionBinding
import com.example.starrcarr.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Registratiion : AppCompatActivity() {
    lateinit var binding: ActivityRegistratiionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityRegistratiionBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root)
        binding.buttonAddd.setOnClickListener {

            binding.apply {
                var firstname = firstnameUser.text.toString()
                var lastname = secondnameUser.text.toString()
                var email = email.text.toString()
                var phone = phone.text.toString()
                var password = password.text.toString()
                var generationcode = genertecode.text.toString()
                val userdata = UserData(
                    "",
                    firstName = firstname,
                    lastName = lastname,
                    email = email,
                    phone = phone,
                    password = password,
                    invitationCode = "123223",

                    profitpercentage = 2,
                    numberOfTrips = 2,
                    numberOfsubscribers = 0

                )
                createEmailAndSetData(email, password, userdata)
            }

        }

    }

    private fun createEmailAndSetData(email: String, password: String, userData: UserData) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                userData.id = FirebaseAuth.getInstance().currentUser!!.uid

                setData(userData)
                searchValueFromDB("123")
            }.addOnFailureListener {
                Toast.makeText(
                    applicationContext,
                    "Error${it.message.toString()}",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d("error", it.message.toString())
            }
    }

    private fun setData(userData: UserData) {
        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid.toString())
            .setValue(userData).addOnSuccessListener {
            }.addOnFailureListener {

            }

    }

    private fun increaseProfitPercentageUsingSubscription(invitaionCode: String) {

    }

    private fun searchValueFromDB(ccode: String) {
        val mDatabase = FirebaseDatabase.getInstance().getReference("Users")
        mDatabase.orderByChild("invitationCode").equalTo(ccode)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Toast.makeText(applicationContext, "Search was soo gooood", Toast.LENGTH_SHORT)
                        .show()

                    for (data in snapshot.children) {
                        val models: UserData? = data.getValue(UserData::class.java);
                        Log.d("name", models!!.firstName)
                        val subscrip = models.numberOfsubscribers + 1
                        FirebaseDatabase.getInstance().getReference("Users").child(models!!.id)
                            .child("numberOfsubscribers").setValue(subscrip)


                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

    }
}
package com.example.antharjalawatch

import android.os.Bundle
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.graphics.Color

import androidx.core.app.ActivityCompat

import com.example.antharjalawatch.ui.screens.MainNavigation

import com.example.antharjalawatch.utils.FirebaseAuthHelper

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // 📍 Location Permission
        ActivityCompat.requestPermissions(

            this,

            arrayOf(

                android.Manifest.permission.ACCESS_FINE_LOCATION,

                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),

            100
        )

        // 🔥 Firebase Anonymous Login
        FirebaseAuthHelper.signInAnonymously(

            onSuccess = {

                setContent {

                    MaterialTheme {

                        Surface(

                            color =
                                Color(0xFFF4F8F4)
                        ) {

                            MainNavigation()
                        }
                    }
                }
            },

            onFailure = {

                Toast.makeText(

                    this,

                    it,

                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }
}
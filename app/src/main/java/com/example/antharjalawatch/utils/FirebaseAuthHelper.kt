package com.example.antharjalawatch.utils

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthHelper {

    fun signInAnonymously(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        FirebaseAuth.getInstance()
            .signInAnonymously()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Unknown Error")
            }
    }
}
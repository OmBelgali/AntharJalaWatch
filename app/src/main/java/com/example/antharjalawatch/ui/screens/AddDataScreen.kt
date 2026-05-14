package com.example.antharjalawatch.ui.screens

import android.widget.Toast

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.example.antharjalawatch.data.model.BorewellEntry

import com.example.antharjalawatch.utils.LocationHelper

import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AddDataScreen() {

    val firestore = FirebaseFirestore.getInstance()

    val context = LocalContext.current

    var area by remember {
        mutableStateOf("")
    }

    var depth by remember {
        mutableStateOf("")
    }

    var yieldValue by remember {
        mutableStateOf("")
    }

    var year by remember {
        mutableStateOf("")
    }

    var isSaving by remember {
        mutableStateOf(false)
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        Text(

            text = "➕ Add Borewell Data",

            style =
                MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // 🌍 AREA FIELD
        OutlinedTextField(

            value = area,

            onValueChange = {

                area = it
            },

            label = {

                Text("Area / Village")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // 📏 DEPTH
        OutlinedTextField(

            value = depth,

            onValueChange = {

                depth = it
            },

            label = {

                Text("Depth (ft)")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // 💧 YIELD
        OutlinedTextField(

            value = yieldValue,

            onValueChange = {

                yieldValue = it
            },

            label = {

                Text("Yield")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // 📅 YEAR
        OutlinedTextField(

            value = year,

            onValueChange = {

                year = it
            },

            label = {

                Text("Year")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // 💾 SAVE BUTTON
        Button(

            onClick = {

                isSaving = true

                // 📍 GET LIVE LOCATION
                LocationHelper.getCurrentLocation(

                    context

                ) { lat, lng ->

                    val entry = BorewellEntry(

                        area = area,

                        depth = depth,

                        yield = yieldValue,

                        year = year,

                        latitude = lat,

                        longitude = lng
                    )

                    firestore.collection("borewell_logs")
                        .add(entry)

                        .addOnSuccessListener {

                            isSaving = false

                            Toast.makeText(

                                context,

                                "Borewell Data Saved With Live Location",

                                Toast.LENGTH_SHORT
                            ).show()

                            area = ""
                            depth = ""
                            yieldValue = ""
                            year = ""
                        }

                        .addOnFailureListener {

                            isSaving = false

                            Toast.makeText(

                                context,

                                it.message,

                                Toast.LENGTH_LONG
                            ).show()
                        }
                }
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            if (isSaving) {

                CircularProgressIndicator(

                    color = MaterialTheme.colorScheme.onPrimary,

                    modifier = Modifier.size(20.dp)
                )

            } else {

                Text("Save Borewell Data")
            }
        }
    }
}
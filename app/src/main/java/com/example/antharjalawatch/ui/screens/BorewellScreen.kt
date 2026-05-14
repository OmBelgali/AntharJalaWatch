package com.example.antharjalawatch.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BorewellScreen(
    onSave: (String, String, String) -> Unit
) {

    var depth by remember { mutableStateOf("") }
    var yield by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = depth,
            onValueChange = { depth = it },
            label = { Text("Depth") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = yield,
            onValueChange = { yield = it },
            label = { Text("Yield") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Year Dug") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                onSave(depth, yield, year)
            }
        ) {
            Text("Save Borewell Data")
        }
    }
}
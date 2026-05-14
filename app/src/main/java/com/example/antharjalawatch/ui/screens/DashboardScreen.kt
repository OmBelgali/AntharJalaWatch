package com.example.antharjalawatch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.antharjalawatch.data.model.BorewellEntry

import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {

    val firestore = FirebaseFirestore.getInstance()

    var borewellList by remember {

        mutableStateOf<List<BorewellEntry>>(emptyList())
    }

    var selectedArea by remember {

        mutableStateOf("All Areas")
    }

    var expanded by remember {

        mutableStateOf(false)
    }

    // ⚡ REAL-TIME FIRESTORE LISTENER
    LaunchedEffect(Unit) {

        firestore.collection("borewell_logs")

            .addSnapshotListener { snapshot, _ ->

                if (snapshot != null) {

                    val list = snapshot.documents.mapNotNull {

                        it.toObject(BorewellEntry::class.java)
                    }

                    borewellList = list
                }
            }
    }

    // 🌍 AVAILABLE AREAS
    val areaList = listOf("All Areas") +

            borewellList.map {

                it.area
            }.filter {

                it.isNotBlank()
            }.distinct()

    // 🌿 FILTERED AREA DATA
    val filteredList = if (

        selectedArea == "All Areas"

    ) {

        borewellList

    } else {

        borewellList.filter {

            it.area == selectedArea
        }
    }

    // 🌿 AVERAGE DEPTH
    val averageDepth =

        if (filteredList.isNotEmpty()) {

            filteredList.map {

                it.depth.toDoubleOrNull() ?: 0.0

            }.average()

        } else 0.0

    // ⚠ RISK LEVEL
    val riskLevel = when {

        averageDepth < 100 -> "LOW"

        averageDepth in 100.0..300.0 -> "MEDIUM"

        else -> "HIGH"
    }

    // 📈 TREND LOGIC
    val trendMessage =

        if (filteredList.size >= 2) {

            val sortedList =

                filteredList.sortedBy {

                    it.timestamp
                }

            val firstDepth =

                sortedList.first()
                    .depth
                    .toDoubleOrNull() ?: 0.0

            val latestDepth =

                sortedList.last()
                    .depth
                    .toDoubleOrNull() ?: 0.0

            val difference =
                latestDepth - firstDepth

            when {

                difference > 100 ->

                    "Rapid groundwater depletion"

                difference > 30 ->

                    "Groundwater stress increasing"

                else ->

                    "Groundwater stable"
            }

        } else {

            "Not enough historical data"
        }

    // 💡 RECOMMENDATION
    val recommendation = when {

        averageDepth < 100 ->

            "Maintain conservation"

        averageDepth in 100.0..300.0 ->

            "Start recharge activities"

        else ->

            "Emergency recharge required"
    }

    // 🔮 AI PREDICTION
    val prediction = when {

        averageDepth < 100 ->

            "Water table likely stable"

        averageDepth in 100.0..300.0 ->

            "Moderate stress expected"

        else ->

            "Severe depletion risk"
    }

    // 🌿 MAIN UI
    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8F4))
            .padding(16.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        // 🌿 TITLE
        Text(

            text = "Anthar-Jala Dashboard",

            fontSize = 28.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF1B5E20)
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(

            text =
                "Community Groundwater Analytics",

            color = Color.Gray
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // 🌍 AREA DROPDOWN
        ExposedDropdownMenuBox(

            expanded = expanded,

            onExpandedChange = {

                expanded = !expanded
            }
        ) {

            OutlinedTextField(

                value = selectedArea,

                onValueChange = {},

                readOnly = true,

                label = {

                    Text("Select Area")
                },

                trailingIcon = {

                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },

                shape = RoundedCornerShape(16.dp),

                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(

                expanded = expanded,

                onDismissRequest = {

                    expanded = false
                }
            ) {

                areaList.forEach { area ->

                    DropdownMenuItem(

                        text = {

                            Text(area)
                        },

                        onClick = {

                            selectedArea = area

                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // 📍 TOTAL BOREWELLS
        PremiumDashboardCard(

            emoji = "📍",

            title = "Total Borewells",

            value = "${filteredList.size}",

            subtitle =
                "Community monitored borewells",

            color = Color(0xFFE3F2FD)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // 🌿 AVERAGE DEPTH
        PremiumDashboardCard(

            emoji = "🌿",

            title = "Average Depth",

            value = "${averageDepth.toInt()} ft",

            subtitle =
                "Average groundwater depth",

            color = Color(0xFFE8F5E9)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // ⚠ RISK LEVEL
        PremiumDashboardCard(

            emoji = "⚠",

            title = "Risk Level",

            value = riskLevel,

            subtitle =
                "Current groundwater condition",

            color = when {

                riskLevel == "LOW" ->
                    Color(0xFFE8F5E9)

                riskLevel == "MEDIUM" ->
                    Color(0xFFFFF8E1)

                else ->
                    Color(0xFFFFEBEE)
            }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // 📈 TREND
        PremiumDashboardCard(

            emoji = "📈",

            title = "Groundwater Trend",

            value = trendMessage,

            subtitle =
                "Historical groundwater behavior",

            color = Color(0xFFF3E5F5)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // 💡 RECOMMENDATION
        PremiumDashboardCard(

            emoji = "💡",

            title = "Recommendation",

            value = recommendation,

            subtitle =
                "Suggested sustainability action",

            color = Color(0xFFFFF3E0)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // 🔮 AI PREDICTION
        PremiumDashboardCard(

            emoji = "🔮",

            title = "AI Prediction",

            value = prediction,

            subtitle =
                "Future groundwater forecast",

            color = Color(0xFFE1F5FE)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}

@Composable
fun PremiumDashboardCard(

    emoji: String,

    title: String,

    value: String,

    subtitle: String,

    color: Color
) {

    Card(

        shape = RoundedCornerShape(22.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = color
        ),

        modifier = Modifier.fillMaxWidth()
    ) {

        Column(

            modifier = Modifier
                .padding(20.dp)
        ) {

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text = emoji,

                    fontSize = 28.sp
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(

                    text = title,

                    fontWeight = FontWeight.Bold,

                    fontSize = 18.sp
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Text(

                text = value,

                fontSize = 24.sp,

                fontWeight = FontWeight.Bold,

                color = Color.Black
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = subtitle,

                color = Color.DarkGray
            )
        }
    }
}
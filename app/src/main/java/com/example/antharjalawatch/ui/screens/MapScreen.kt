package com.example.antharjalawatch.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.example.antharjalawatch.data.model.BorewellEntry

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.firebase.firestore.FirebaseFirestore

import com.google.maps.android.compose.*

@Composable
fun MapScreen() {

    val firestore = FirebaseFirestore.getInstance()

    var borewellList by remember {

        mutableStateOf<List<BorewellEntry>>(emptyList())
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

    // 📍 DEFAULT LOCATION
    val defaultLocation = LatLng(

        15.4250,
        75.6350
    )

    val cameraPositionState =
        rememberCameraPositionState {

            position = CameraPosition.fromLatLngZoom(

                defaultLocation,

                5.8f
            )
        }

    // 🌿 MAIN UI
    Box(

        modifier = Modifier.fillMaxSize()
    ) {

        GoogleMap(

            modifier = Modifier.fillMaxSize(),

            cameraPositionState =
                cameraPositionState
        ) {

            // 🌍 GROUP NEARBY BOREWELLS
            val groupedBorewells =

                borewellList.groupBy {

                    Pair(

                        String.format("%.1f", it.latitude),

                        String.format("%.1f", it.longitude)
                    )
                }

            // 🌿 PREMIUM ECO HEATMAP
            groupedBorewells.forEach { (_, group) ->

                val localAverageDepth =

                    group.map {

                        it.depth.toDoubleOrNull() ?: 0.0

                    }.average()

                val heatColor = when {

                    localAverageDepth < 100 ->

                        Color(0xFF00C853)

                    localAverageDepth in 100.0..300.0 ->

                        Color(0xFFFFD600)

                    else ->

                        Color(0xFFD50000)
                }

                val center = LatLng(

                    group.first().latitude,

                    group.first().longitude
                )

                // 🔥 OUTER SOFT GLOW
                Circle(

                    center = center,

                    radius = 2000.0,

                    fillColor =
                        heatColor.copy(alpha = 0.08f),

                    strokeColor =
                        heatColor.copy(alpha = 0.01f),

                    strokeWidth = 1f
                )

                // 🔥 SECOND LAYER
                Circle(

                    center = center,

                    radius = 1500.0,

                    fillColor =
                        heatColor.copy(alpha = 0.14f),

                    strokeColor =
                        heatColor.copy(alpha = 0.02f),

                    strokeWidth = 1f
                )

                // 🔥 THIRD LAYER
                Circle(

                    center = center,

                    radius = 1000.0,

                    fillColor =
                        heatColor.copy(alpha = 0.20f),

                    strokeColor =
                        heatColor.copy(alpha = 0.03f),

                    strokeWidth = 1f
                )

                // 🔥 CORE HEAT
                Circle(

                    center = center,

                    radius = 500.0,

                    fillColor =
                        heatColor.copy(alpha = 0.32f),

                    strokeColor =
                        heatColor.copy(alpha = 0.05f),

                    strokeWidth = 2f
                )
            }

            // 📍 LIVE MARKERS
            borewellList.forEach { borewell ->

                Marker(

                    state = MarkerState(

                        position = LatLng(

                            borewell.latitude,

                            borewell.longitude
                        )
                    ),

                    title =
                        "${borewell.area} • ${borewell.depth} ft",

                    snippet =
                        "Yield: ${borewell.yield}"
                )
            }
        }
    }
}
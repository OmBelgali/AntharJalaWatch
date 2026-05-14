package com.example.antharjalawatch.ui.screens

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.padding

@Composable
fun MainNavigation() {

    var selectedScreen by remember {

        mutableStateOf(0)
    }

    Scaffold(

        containerColor =
            Color(0xFFF4F8F4),

        bottomBar = {

            NavigationBar(

                containerColor =
                    Color(0xFFEAF4EA),

                tonalElevation = 8.dp
            ) {

                // 🗺 MAP
                NavigationBarItem(

                    selected =
                        selectedScreen == 0,

                    onClick = {
                        selectedScreen = 0
                    },

                    colors =
                        NavigationBarItemDefaults.colors(

                            selectedIconColor =
                                Color(0xFF1B5E20),

                            selectedTextColor =
                                Color(0xFF1B5E20),

                            indicatorColor =
                                Color(0xFFC8E6C9),

                            unselectedIconColor =
                                Color.Gray,

                            unselectedTextColor =
                                Color.Gray
                        ),

                    icon = {

                        Icon(

                            Icons.Default.Home,

                            contentDescription = "Map"
                        )
                    },

                    label = {

                        Text("Map")
                    }
                )

                // 📊 DASHBOARD
                NavigationBarItem(

                    selected =
                        selectedScreen == 1,

                    onClick = {
                        selectedScreen = 1
                    },

                    colors =
                        NavigationBarItemDefaults.colors(

                            selectedIconColor =
                                Color(0xFF1565C0),

                            selectedTextColor =
                                Color(0xFF1565C0),

                            indicatorColor =
                                Color(0xFFBBDEFB),

                            unselectedIconColor =
                                Color.Gray,

                            unselectedTextColor =
                                Color.Gray
                        ),

                    icon = {

                        Icon(

                            Icons.Default.Info,

                            contentDescription = "Dashboard"
                        )
                    },

                    label = {

                        Text("Dashboard")
                    }
                )

                // ➕ ADD DATA
                NavigationBarItem(

                    selected =
                        selectedScreen == 2,

                    onClick = {
                        selectedScreen = 2
                    },

                    colors =
                        NavigationBarItemDefaults.colors(

                            selectedIconColor =
                                Color(0xFFEF6C00),

                            selectedTextColor =
                                Color(0xFFEF6C00),

                            indicatorColor =
                                Color(0xFFFFE0B2),

                            unselectedIconColor =
                                Color.Gray,

                            unselectedTextColor =
                                Color.Gray
                        ),

                    icon = {

                        Icon(

                            Icons.Default.Add,

                            contentDescription = "Add Data"
                        )
                    },

                    label = {

                        Text("Add Data")
                    }
                )
            }
        }

    ) { padding ->

        Surface(

            modifier = androidx.compose.ui.Modifier
                .padding(padding),

            color = Color(0xFFF4F8F4)
        ) {

            when (selectedScreen) {

                0 -> MapScreen()

                1 -> DashboardScreen()

                2 -> AddDataScreen()
            }
        }
    }
}
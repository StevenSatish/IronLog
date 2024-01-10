package com.example.ironlog

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val name: String,
    val icon: ImageVector,

)
data class TopNavItem(
    val route: String,
    val name: String,

)


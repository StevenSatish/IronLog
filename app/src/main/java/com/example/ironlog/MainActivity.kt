package com.example.ironlog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem(
                                route = "workout",
                                name = "Workout",
                                icon = Icons.Default.Add
                            ),
                            BottomNavItem(
                                route = "calendar",
                                name = "Calendar",
                                icon = Icons.Default.DateRange
                            ),
                            BottomNavItem(
                                route = "home",
                                name = "Home",
                                icon = Icons.Default.Home
                            ),
                            BottomNavItem(
                                route = "templates",
                                name = "Templates",
                                icon = Icons.Default.Edit
                            ),
                            BottomNavItem(
                                route = "settings",
                                name = "Settings",
                                icon = Icons.Default.Settings
                            )
                        ),
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        }
                    )
                }
            ) {
                Navigation(navController = navController, )
            }
        }
    }
}
@Composable
fun Navigation(navController: NavHostController,) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("history") {
            TopNavigationScreenCalendar(navController = navController, "history")
        }
        composable("settings") {
            SettingsScreen()
        }
        composable("workout") {
            WorkoutScreen()
        }
        composable("templates") {
            TopNavigationScreenTemplates(navController = navController, "templates")
        }
        composable("database"){
            TopNavigationScreenTemplates(navController = navController, "database")
        }
        composable("calendar"){
            TopNavigationScreenCalendar(navController = navController, "calendar")
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        containerColor = Color.DarkGray,
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xff5b026b),
                    unselectedIconColor = Color.Gray
                ),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )

                        if (selected) {
                            Text(
                                text = item.name,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            )
        }
    }
}
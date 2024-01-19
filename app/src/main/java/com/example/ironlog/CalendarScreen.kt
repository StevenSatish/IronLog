package com.example.ironlog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun CalendarScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center,


        ){
        Text(text = "Calendar Screen",
            fontSize = 20.sp,
            color = Color.White)
    }
}
@Composable
fun TopNavigationBarCalendar(
    items: List<TopNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (TopNavItem) -> Unit
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
                        Text(
                            text = item.name,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                        )

                    }
                }
            )
        }
    }
}
@Composable
fun TopNavigationScreenCalendar(navController: NavController, startDestination: String) {

    // NavHost for top navigation
    val topNavController = rememberNavController()
    Scaffold(topBar = {
        TopNavigationBarCalendar(
            items = listOf(
                TopNavItem(
                    route = "calendar",
                    name = "Calendar"
                ),
                TopNavItem(
                    route = "history",
                    name = "History",
                )
            ),
            navController = navController,
            onItemClick = {
                navController.navigate(it.route)
            }
        )
    }) { innerPadding ->
        NavHost(
            navController = topNavController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("calendar") {
                CalendarScreen()
            }
            composable("history") {
                HistoryScreen()
            }
        }
    }
}
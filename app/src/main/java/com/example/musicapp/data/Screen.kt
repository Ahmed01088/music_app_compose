package com.example.musicapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route:String,val title :String,val icon:ImageVector){
    object Home:Screen(route = "home", title = "Home",icon= Icons.Filled.Home)
    object Library:Screen(route = "library", title = "Library",icon= Icons.Filled.Home)
    object Search:Screen(route = "search", title = "Search",icon= Icons.Filled.Home)

}


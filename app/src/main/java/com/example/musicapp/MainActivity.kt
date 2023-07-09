package com.example.musicapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.data.Screen
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Navigation()
                    WindowCompat.setDecorFitsSystemWindows(window, true)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val view = LocalView.current
    val navController= rememberNavController()
    val containerColor = listOf(
        Color(0xFF8677F2),
        Color(0xFF313346),
        Color(0xFF3D4058),
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    val icons= listOf(painterResource(id = R.drawable.home), painterResource(id = R.drawable.search), painterResource(id = R.drawable.music_library))
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = containerColor[selectedItem],
                    scrolledContainerColor = Color.Magenta,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = { /* "Open nav drawer" */ }
                    ) {
                        Icon(painterResource(id = R.drawable.menu), contentDescription = "Localized description")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Notifications,
                            modifier = Modifier
                                .size(36.dp),
                            contentDescription = "Notifications", tint = Color.White)
                    }
                    Image(painter = painterResource(id = R.drawable.img), contentDescription ="circle profile image",
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .size(48.dp)
                            .clip(CircleShape),
                    )
                },
            )
        },
        content = { innerPadding ->
            Surface( modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(top = 56.dp)
                .background(Color(0xFF8677F2))) {
                when(selectedItem){
                    0-> navController.navigate(Screen.Home.route)
                    1-> navController.navigate(Screen.Search.route)
                    2-> navController.navigate(Screen.Library.route)
                }
                SideEffect {
                    val window = (view.context as Activity).window
                    window.statusBarColor = containerColor[selectedItem].toArgb()
                    window.navigationBarColor = containerColor[selectedItem].toArgb()
                }
         }
  },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White.copy(alpha = 0.3f),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 38.dp, vertical = 6.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    ),
            ) {
                icons.forEachIndexed { index, painter ->
                    NavigationBarItem(
                        modifier = Modifier.weight(1f),
                        icon = {
                            Image(
                                painter = painter, contentDescription = "icon", modifier = Modifier
                                    .weight(1f)
                                    .size(48.dp)
                            )
                        },
                        onClick = { selectedItem = index},
                        selected = selectedItem == index,
                        colors = NavigationBarItemColors(
                            selectedIconColor = Color(0xFF8677F2),
                            unselectedIconColor = Color.White.copy(alpha = 0.5f),
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White.copy(alpha = 0.5f),
                            disabledIconColor = Color.Transparent,
                            disabledTextColor = Color.Transparent,
                            selectedIndicatorColor = Color(0xFF313346),
                        )
                    )
                }

            }
        }

    )

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MusicAppTheme {
        MainScreen()
    }
}

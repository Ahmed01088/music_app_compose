package com.example.musicapp.ui.theme

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.PlaySongActivity
import com.example.musicapp.R
import com.example.musicapp.Song
import com.example.musicapp.data.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController= rememberNavController()) {
    val images = listOf(
        R.drawable.pop,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img,
        R.drawable.img_4,
        R.drawable.img_6,
    )
    val context= LocalContext.current
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    Column( modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF313346))) {
        Text(text = "Find the best music for\n your playlist", fontSize = 24.sp, color = Color.White, modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            content={
            },
            query = text,
            onQueryChange = { text = it } ,
            onSearch = {  },
            active = active,
            onActiveChange = { active = it },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search", tint = Color.White)
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = { text = "" }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close", tint = Color.White)
                    }
                }
            },
            colors = SearchBarDefaults.colors(
                containerColor = Color(0xFFC4C4C4).copy(alpha = 0.2f),
            ),
            placeholder = {
                Text(text = "Search for music...", color = Color.White.copy(alpha = 0.7f))
            },

            )
        Text(text = "Recent searches", fontSize = 16.sp, color = Color.White, modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth())
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
            content = {
                items(20) { index ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            //animation start activity from bottom to top code
                            val intent = Intent(context, PlaySongActivity::class.java)
                            val options =
                                ActivityOptions.makeSceneTransitionAnimation(context as Activity)
                            context.startActivity(intent, options.toBundle())
                        }
                        .padding(vertical = 4.dp, horizontal = 16.dp), shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF4C4E62)
                        ),
                        elevation = CardDefaults.cardElevation(6.dp)) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                            .background(
                                Color(0xFF4C4E62),
                                shape = RoundedCornerShape(16.dp)
                            ), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Image(painter = painterResource(id = images[index%images.size]), contentDescription = "image", modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(16.dp)))
                            Column(modifier = Modifier.weight(1f)){
                                Text(text = "Song name", fontSize = 16.sp, color = Color.White, modifier = Modifier
                                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                                    .fillMaxWidth())
                                Text(text = "Artist name", fontSize = 14.sp, color = Color.White.copy(alpha = 0.7f), modifier = Modifier
                                    .padding(start = 16.dp, top = 4.dp, end = 16.dp)
                                    .fillMaxWidth())
                            }
                            IconButton(onClick = {
                                //delete item from list



                            }) {
                                Icon(imageVector = Icons.Outlined.Close, contentDescription = "More", tint = Color.White.copy(alpha = 0.12f))

                            }

                        }
                    }
                }
            })

    }
}
@Preview(showBackground = true,
    name = "Light Mode",
    showSystemUi = true,

    )
@Composable
fun PreviewSearchScreen() {
    MusicAppTheme {
        SearchScreen()
    }
}
@Composable
fun LibraryScreen(navController: NavController = rememberNavController()){
    val context = LocalContext.current

    val songNames = listOf<Song>(
        Song(
            title = "Song name 1",
            artist = "Artist name 1",
            album = "Album name 1",
            duration = 3.45f,
            image = R.drawable.img_2
        ),
        Song(
            title = "Song name 2",
            artist = "Artist name 2",
            album = "Album name 2",
            duration = 3.45f,
            image = R.drawable.img_3
        ),
        Song(
            title = "Song name 3",
            artist = "Artist name 3",
            album = "Album name 3",
            duration = 3.45f,
            image = R.drawable.img_4
        ),
        Song(
            title = "Song name 4",
            artist = "Artist name 4",
            album = "Album name 4",
            duration = 3.45f,
            image = R.drawable.img_5
        ),
        Song(
            title = "Song name 5",
            artist = "Artist name 5",
            album = "Album name 5",
            duration = 3.45f,
            image = R.drawable.img_6
        ),


        )
    val playListsName=listOf(
        "Playlist",
        "Artist",
        "Albums",
        "Podcasts",
        "Genres",
        "Downloads",
        "Liked Songs",
        "Recently Played",
        "Recently Added",

        )
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF3D4058))) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Your library",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth())
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "More", modifier = Modifier.size(32.dp))

            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "More", modifier = Modifier.size(32.dp))

            }
        }
        LazyRow(contentPadding= PaddingValues(8.dp)) {
            items(playListsName.size) { index ->
                OutlinedButton(onClick = { /*TODO*/ }, border = BorderStroke(2.dp, Color.White), shape = RoundedCornerShape(32.dp), modifier = Modifier.padding(8.dp)) {
                    Text(text = playListsName[index], fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(8.dp))
                }
            }


        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.sort_down), contentDescription ="sort down", tint = Color.White, modifier = Modifier.size(32.dp))
            }
            Text(text = "Recently played",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription ="menu", tint = Color.White, modifier = Modifier.size(32.dp))
            }
        }
        LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
            items(songNames.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            //animation start activity from bottom to top code
                            val intent = Intent(context, PlaySongActivity::class.java)
                            val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity)
                            context.startActivity(intent, options.toBundle())
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF5B5F76)
                    ),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(Color(0xFF5B5F76)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "image",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                        Text(
                            text = songNames[index].title,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = playListsName[if(index==0) 0 else index%playListsName.size],
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LibraryScreenPreview() {
    MusicAppTheme {
        LibraryScreen()
    }
}

@Preview(showBackground = true,
    name = "Light Mode",
    showSystemUi = true,

    )
@Composable
fun PreviewHomeScreen() {
    MusicAppTheme {
        HomeScreen()
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(navController: NavController= rememberNavController()) {
    val colors = listOf(
        Color(0xFF7B5EEE),
        Color(0xFFE34A4A),
        Color(0xFFEE5E5E),
        Color(0xFF609ED8),
        Color(0xFF868686),
        Color(0xFFFF2B2B),
    )
    val colors2= listOf(
        Color(0xFF2D5B6C),
        Color(0xFFB5A19B),
        Color(0xFF3B3943),
        Color(0xFFA07D7C),
        Color(0xFFF9703A),
    )
    val images = listOf(
        R.drawable.pop,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img,
        R.drawable.img_4,
        R.drawable.img_6,
    )
    val background by remember { mutableStateOf(
        Modifier
            .size(80.dp)
            .background(Color(0xFFC2BCDA), shape = RoundedCornerShape(16.dp))) }
    var itemSelected by remember { mutableStateOf(listOf(1,0,0)) }
    val itemSelectedTint by remember { mutableStateOf(Color(0xFF8677F2)) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color(0xFF8677F2),
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
                        Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "Notifications", tint = Color.White)

                    }
                    Image(painter = painterResource(id = R.drawable.img), contentDescription ="circle profile image",
                        modifier = Modifier
                            .size(26.dp)
                            .padding(start = 8.dp, end = 8.dp)
                            .clip(CircleShape),
                    )

                },
            )
        },content = { innerPadding ->
            Column( modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(top = 56.dp)
                .background(Color(0xFF8677F2))) {
                Text(text = "Hello Ahmed \uD83D\uDC4B", fontSize = 24.sp, color = Color.White, modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth(), fontWeight = FontWeight.Bold)
                Text(text = "What’s your mood today?", fontSize = 18.sp, color = Color.White, modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .fillMaxWidth())
                Text(text="Genres", fontSize = 20.sp, color = Color.White, modifier = Modifier
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp)
                    .fillMaxWidth())
                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)){
                    items(10) { index ->
                        Card(modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .size(150.dp, 150.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .background(colors[index % colors.size])
                                .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Pop", fontSize = 18.sp, color = Color.Black)
                                Image(painter = painterResource(images[index % images.size]), contentDescription = "pop image", modifier = Modifier
                                    .padding(top = 8.dp)
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .size(80.dp),
                                    contentScale = ContentScale.Crop)

                            }
                        }
                    }
                }
                Text(text="You may like it", fontSize = 20.sp, color = Color.White, modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .fillMaxWidth())
                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)){
                    items(10) { index ->
                        Card(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                                .size(150.dp, 150.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(colors2[index % colors2.size])
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Pop",
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                                Image(
                                    painter = painterResource(images[index % images.size]),
                                    contentDescription = "pop image",
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .weight(1f)
                                        .clip(RoundedCornerShape(12.dp))
                                        .size(80.dp),
                                    contentScale = ContentScale.Crop
                                )

                            }
                        }
                    }
                }

            }


        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent,
                contentColor = Color.White,
                modifier = Modifier
                    .height(110.dp)
                    .padding(start = 48.dp, end = 48.dp, bottom = 8.dp, top = 16.dp)
                    .fillMaxWidth()
            ) {
                FlowRow(modifier = Modifier
                    .background(
                        color = Color.White.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalArrangement = Arrangement.Center){
                    IconButton(onClick = {
                        itemSelected = listOf(1,0,0)

                    }, modifier = if (itemSelected[0] == 1) background else Modifier.size(60.dp)) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", tint = if(itemSelected[0]==1) itemSelectedTint else Color.White, modifier = Modifier.size(60.dp))
                    }
                    IconButton(onClick = {
                        itemSelected = listOf(0,1,0)
                        navController.navigate(Screen.Search.route)
                    },modifier = if (itemSelected[1] == 1) background else Modifier.size(60.dp)) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search", tint = if(itemSelected[1]==1) itemSelectedTint else Color.White, modifier = Modifier.size(60.dp))
                    }
                    IconButton(onClick = {
                        itemSelected = listOf(0,0,1)
                        navController.navigate(Screen.Library.route)
                    },modifier = if (itemSelected[2] == 1) background else Modifier.size(60.dp)) {
                        Icon(painter = painterResource(id = R.drawable.music_library), contentDescription = "Library", tint = if(itemSelected[2]==1) itemSelectedTint else Color.White, modifier = Modifier.size(60.dp))
                    }
                }
            }

        }

    )

}


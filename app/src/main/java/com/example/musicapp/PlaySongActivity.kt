package com.example.musicapp

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.ui.theme.MusicAppTheme

class PlaySongActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayMusicSong()
                    SideEffect {
                        val window = (this as Activity).window
                        window.statusBarColor = Color(0xFFBF6336).toArgb()
                        window.navigationBarColor = Color(0xFF292A32).toArgb()

                    }

                }
            }
        }
    }
}

@Composable
fun PlayMusicSong() {
    val lightColorTheme = Color(0xFFBF6336)
    val darkColorTheme = Color(0xFF5069B1)
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var offset by remember { mutableFloatStateOf(0f) }
    var tintColor by remember { mutableStateOf(Color.White) }
    var iconPlay by remember { mutableIntStateOf(R.drawable.play) }
    var lightColor by remember { mutableStateOf(lightColorTheme) }
    var tintColorMoon by remember { mutableStateOf(lightColorTheme) }
    var image by remember { mutableIntStateOf(R.drawable.img) }
    val intent = Intent(Intent.ACTION_SEND)
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    Surface(modifier = Modifier
        .scrollable(
            state = rememberScrollableState { delta ->
                offset += delta
                delta
            },
            orientation = Orientation.Horizontal,
        )){
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(lightColor, Color(0xFF292A32)),
                )
            )
            .padding(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    val activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        context as Activity
                    )
context.startActivity(intent, activityOptions.toBundle())
                }, modifier = Modifier.background(Color.Transparent)) {
                    Icon(painter = painterResource(id = R.drawable.back),
                        contentDescription = "back",
                        tint = Color.White)


                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(8.dp)
                    .weight(1f),
                    verticalArrangement = Arrangement.Center) {
                    Text(text ="playing from playlist",color = Color.White, fontSize = 18.sp, textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally))
                    Text(text ="Discovered weakly",color = Color.White, fontSize = 18.sp, textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally))

                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.background(Color.Transparent)) {
                    Icon(painter = painterResource(id = R.drawable.dots),
                        contentDescription = "back",
                        tint = Color.White)


                }
            }
            Image(painter = painterResource(image),
                contentDescription ="image play",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .size(320.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(24.dp)),
                )
            Text(text = "Watercolor Eyes - From Euphoria", color = Color.White, fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start=16.dp,end=16.dp),
                textAlign = TextAlign.Center)
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Lana Del Rey", color = Color.LightGray, fontSize = 18.sp,modifier= Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .weight(1f),
                    textAlign = TextAlign.Start)
                IconButton(onClick = {
                    tintColor = if (tintColor == Color.White) Color.Red else Color.White

                }, modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                    Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "favorite", tint = tintColor)
                }

            }
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = lightColor,
                    activeTrackColor = lightColor,
                    inactiveTrackColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "0:00", color = Color.LightGray, fontSize = 18.sp)
                Text(text = "3:00", color = Color.LightGray, fontSize = 18.sp)

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.background(Color.Transparent)) {
                    Icon(
                        painter = painterResource(id = R.drawable.shuffle),
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .background(Color.Transparent)
                    .size(56.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.previous),
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = Modifier.size(56.dp)
                    )
                }
                IconButton(onClick = {
                    iconPlay = if (iconPlay == R.drawable.play) R.drawable.pause else R.drawable.play
                }, modifier = Modifier
                    .background(lightColor, CircleShape)
                    .size(72.dp)) {
                    Icon(
                        painter = painterResource(iconPlay),
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = Modifier.size(56.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .background(Color.Transparent)
                    .size(56.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.next),
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = Modifier.size(56.dp)
                    )
                }
                IconButton(onClick = {
                    if (tintColorMoon == Color.White) {
                        tintColorMoon= lightColorTheme
                        lightColor= lightColorTheme
                        image = R.drawable.img
                    } else {
                        tintColorMoon= Color.White
                        lightColor= darkColorTheme
                        image = R.drawable.img_1

                    }
                }, modifier = Modifier.background(Color.Transparent)) {
                    Icon(
                        painter = painterResource(id = R.drawable.moon),
                        contentDescription = "back",
                        tint = tintColorMoon
                    )
                }
            }
            Row(modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.background(Color.Transparent)) {
                    Icon(
                        painter = painterResource(id = R.drawable.laptop_computer),
                        contentDescription = "laptop computer",
                        tint = lightColor
                    )
                }
                Text(text = "This Phone", color = lightColor, fontSize = 18.sp, modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically))
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.background(Color.Transparent)) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "share",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /*TODO*/ },modifier = Modifier.background(Color.Transparent)) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "menu",
                        tint = Color.White
                    )
                }

            }
            IconButton(onClick = { expanded = !expanded  }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = "arrow down",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(36.dp)
                        .align(Alignment.CenterHorizontally)
                )

            }
            Text(text = "Lyrics", color = Color.White, fontSize = 18.sp, modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally))
            if (expanded) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .padding(8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(lightColor)
                        .padding(16.dp)) {
                        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Text(text = "Lyrics", color = Color.White, fontSize = 18.sp, modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, end = 16.dp)
                            )
                            IconButton(onClick = {

                            }, modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .background(lightColor, CircleShape)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.expand),
                                    contentDescription = "expand",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(36.dp)
                                        .align(Alignment.CenterVertically)
                                )

                            }
                        }
                        text1="Young love don’t always last forever"
                        text2="Wild horses can’t keep us together\n" +
                                "So what if you taste just like heaven?"
                        Text(text = text1, fontSize = 22.sp, color = Color.White)
                        Text(text =text2 , fontSize = 22.sp, color = Color.Black)
                        OutlinedButton(onClick = {
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_TEXT, "$text1 $text2")
                            context.startActivity(Intent.createChooser(intent, "Share"))
                        }, border = BorderStroke(1.dp, Color.White), modifier = Modifier.align(
                            Alignment.End)) {
                            Icon(imageVector = Icons.Filled.Share, contentDescription ="share" , tint = Color.White)
                            Text(text = "Share", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, name = "Light Mode", showSystemUi = true)
@Composable
fun PreviewPlayMusicSong() {
    MusicAppTheme {
        PlayMusicSong()
    }

}
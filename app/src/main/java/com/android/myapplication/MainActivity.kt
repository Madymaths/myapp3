package com.android.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myapplication.ui.theme.MyApplicationTheme
import com.android.myapplication.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Composable
fun CreateScaffold() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { TopAppBarCompose() },
        scaffoldState = scaffoldState,
    )
    {
        Log.d("paddingValues", it.toString())
        MainUi()
    }
}

@Composable
fun MainUi() {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val offset = Offset(1.0f, 10.0f)
        Text(
            text = "TopAppBar Jetpack Compose",
            style = TextStyle(
                color = Purple500,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, shadow = Shadow(
                    color = Color.LightGray, offset = offset, blurRadius = 2f
                )
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

fun makeToast(ctx: Context, msg: String) {
    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            CreateScaffold()
        }
    }
}

@Composable
fun TopAppBarCompose() {
    val context = LocalContext.current
    val showMenu = remember {
        mutableStateOf(false)
    }
    TopAppBar(contentColor = Color.White,
        title = { Text(text = "Top app bar") }, navigationIcon = {
        IconButton(onClick = {
            makeToast(context, "TopAppBar menu clicked")
        }) {
            Icon(Icons.Default.Menu, contentDescription = null)
        }
    }, actions = {
        IconButton(onClick = { makeToast(context, "Favourites") }) {
            Icon(Icons.Default.Favorite, contentDescription = null)
        }
        IconButton(onClick = { makeToast(context, "Search") }) {
            Icon(Icons.Default.Search, contentDescription = null)
        }
        IconButton(onClick = { showMenu.value = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = null)
        }
        DropdownMenu(expanded = showMenu.value, onDismissRequest = { showMenu.value = false }) {
            DropdownMenuItem(onClick = { makeToast(context, "Settings clicked") }) {
                Text(text = "Settings")
            }
            DropdownMenuItem(onClick = { makeToast(context, "Logout clicked") }) {
                Text(text = "Logout")
            }
        }
    })
}
package br.com.euvickson.maratastarwars

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import br.com.euvickson.maratastarwars.api.StarWarsRetrofit
import br.com.euvickson.maratastarwars.ui.theme.MarataStarWarsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarataStarWarsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val starWarsTestApi = StarWarsRetrofit.listStarWarsPerson()
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    TelaPrincipal(drawerState, scope)
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun TelaPrincipal(
        drawerState: DrawerState,
        scope: CoroutineScope
    ) {
        ModalNavigationDrawer(
            drawerContent = {
                Text(text = "Item 1")
                Text(text = "Item 2")
                Text(text = "Item 3")
                Text(text = "Item 4")
            },
            drawerState = drawerState
        ) {
            TopAppBar(scope, drawerState)
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun TopAppBar(
        scope: CoroutineScope,
        drawerState: DrawerState
    ) {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Complete List") },
                navigationIcon = {
                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu Icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "Ícone de Lista de personagens Favoritos")
                    }
                }
            )
        },
            content = { innerPadding ->
                //The main screen itens will be placed right here
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(1) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(
                                        2.dp,
                                        SolidColor(Color(red = 78, green = 0, blue = 0))
                                    ),
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .width(300.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "Name: ")
                            Text(text = "Heigth: ")
                            Text(text = "Mass: ")
                            Text(text = "Hair Color: ")
                            Text(text = "Skin Color: ")
                            Text(text = "Eye Color: ")
                            Text(text = "Birth Year: ")
                            Text(text = "Gender: ")
                            Text(text = "Homeworld: ")
                            Text(text = "Species: ")
                            Row(modifier = Modifier.fillParentMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                                var buttonState by remember { mutableStateOf(false) }
                                Button(onClick = { buttonState = !buttonState}) {
                                    Text(text = if (buttonState) "Ver mais" else "Ver menos")
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(imageVector = Icons.Default.Star, contentDescription = "Favoritar")
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}
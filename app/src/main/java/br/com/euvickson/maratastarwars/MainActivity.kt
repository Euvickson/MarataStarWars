package br.com.euvickson.maratastarwars

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "Lista de personagens Favoritos")
                    }
                }
            )
        },
            content = { innerPadding ->
                //The main screen itens will be placed right here
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(1) {
                        Text(text = "Teste 1")
                        Text(text = "Teste 2")
                        Text(text = "Teste 3")
                        Text(text = "Teste 4")
                    }
                }
            }
        )
    }
}
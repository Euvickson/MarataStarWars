package br.com.euvickson.maratastarwars

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import br.com.euvickson.maratastarwars.ui.elements.ListItem
import br.com.euvickson.maratastarwars.ui.theme.MarataStarWarsTheme
import br.com.euvickson.maratastarwars.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()
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
                    val lista = mainViewModel.starWarsPersonListResponse
                    mainViewModel.getPeopleList()
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()


                    TelaPrincipal(drawerState, scope, lista)
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun TelaPrincipal(
        drawerState: DrawerState,
        scope: CoroutineScope,
        lista: List<StarWarsPerson>
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
            TopAppBar(scope, drawerState, lista)
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun TopAppBar(
        scope: CoroutineScope,
        drawerState: DrawerState,
        lista: List<StarWarsPerson>
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
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(lista.size) {
                        ListItem(lista[it])
                    }
                }
            }
        )
    }
}
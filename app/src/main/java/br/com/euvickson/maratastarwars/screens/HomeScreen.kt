package br.com.euvickson.maratastarwars.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.euvickson.maratastarwars.model.StarWarsPerson
import br.com.euvickson.maratastarwars.ui.elements.ListItem
import br.com.euvickson.maratastarwars.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val mainViewModel = viewModel(modelClass = MainViewModel::class.java)
    val list by mainViewModel.state.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    TelaPrincipal(drawerState = drawerState, scope = scope, list = list)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TelaPrincipal(
    drawerState: DrawerState,
    scope: CoroutineScope,
    list: List<StarWarsPerson>,
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
        TopAppBar(scope, drawerState, list)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    list: List<StarWarsPerson>
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
                if (list.isEmpty()) {
                    item {
                        CircularProgressIndicator(modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }
                items(list) {
                    ListItem(starWarsPerson = it)
                }
            }
        }
    )
}
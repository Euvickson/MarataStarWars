package br.com.euvickson.maratastarwars.ui.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import br.com.euvickson.maratastarwars.model.StarWarsPerson

@Composable
fun ListItem(starWarsPerson: StarWarsPerson) {
    var isExpanded by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(starWarsPerson.favorite) }
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
            .width(300.dp)
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Nome: ${starWarsPerson.name}")
        Text(text = "Altura: ${starWarsPerson.height}")
        Text(text = "Peso: ${starWarsPerson.mass}")
        Text(text = "Cor do Cabelo: ${starWarsPerson.hair_color}")
        Text(text = "Cor de Pele: ${starWarsPerson.skin_color}")
        AnimatedVisibility(visible = isExpanded) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Cor dos olhos: ${starWarsPerson.eye_color}")
                Text(text = "Ano de Nascimento: ${starWarsPerson.birth_year}")
                Text(text = "Gênero: ${starWarsPerson.gender}")
                Text(text = "Planeta Natal: ${starWarsPerson.homeworld}")
                Text(text = "Spécie: ${starWarsPerson.species}")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { isExpanded = !isExpanded }) {
                Text(text = if (isExpanded) "Ver menos" else "Ver mais")
            }

            IconButton(onClick = {
                isFavorite = !isFavorite
                starWarsPerson.favorite = isFavorite
            }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favoritar"
                )
            }
        }
    }
}
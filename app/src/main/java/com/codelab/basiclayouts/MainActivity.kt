/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelab.basiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.MySootheTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi ::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MySootheApp(windowSizeClass)
        }
    }
}

// Step: Search bar - Modifiers
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    TextField( // TextField espera un texto y se usa para la barra de busqueda
        value = "Search",
        onValueChange = {},
        leadingIcon = { // Icono lider, lo que quiere decir que se pondrá en la cabeza a la izquierda
            Icon(imageVector = Icons.Default.Search
                , contentDescription = null )
                      },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
    placeholder = { // texto que se va a remplazar.
       Text(stringResource(R.string.placeholder_search))
    },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
   @DrawableRes drawable: Int, //parametros para crear que sea dinámico INVESTIGAR
   @StringRes text: Int, //pasamos objetos de tipo imagen y otra de tipo texto.
    modifier: Modifier = Modifier //parametro opcional, no tenemos porque pasarlo. Por eso no lleva @
) {
    // Implement composable here
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, //Alinea los componentes de la columna al centro de ella
    ){
        Image(painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop, // .Fit o FillBounds(mete toda la foto en el espacio que le des) modifica el cacho del recorte
            modifier = Modifier
                .size(88.dp) // tamaño de la imagen
                .clip(CircleShape) // circulo de la imagen
        )
        Text(
            text = stringResource(text), // texto dado en un xml con el archivo
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp), //separación entre la imagen y el texto (top) y del texto con el espacio de abajo
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    // Implement composable here
    Surface (
        shape = MaterialTheme.shapes.medium, //redondea las esquinas
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ){
    Row(
        verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            .width (255.dp)
    ){

        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop, //Lo mismo que en el ejemplo del yoga
            modifier = Modifier
                .size (80.dp)
            )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
            )
    }
    }
}


// Step: Align your body row - Arrangements
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    LazyRow( // Lo mismo del chat
        horizontalArrangement = Arrangement.spacedBy(8.dp), //crea espacio entre los elementos esto es spaceBy. Separa a los hijos
        contentPadding = PaddingValues(horizontal = 16.dp), // mantienen que la imagen no se corte al recorrerlo en la app. Si no está sale 4 imagenes y un cachito de la 5 en el inicio
        modifier = Modifier
            //.padding(horizontal = 8.dp) //este pading no se mantiene dinamico con el movimiento. En este caso funciona igual pero no es la manera
    ){
        items (alignYourBodyData){// Llama a objetos por cada vez que ejecuta la funcióm
            item -> AlignYourBodyElement(item.drawable, item.text )
        }
    }
}

// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(   //asignacion más atractiva pero igual que el LazyRow grid se lo toma celda
        rows = GridCells.Fixed(2), //número de celdas
        contentPadding = PaddingValues(horizontal = 16.dp), // no me termina de quedar claro
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .height(168.dp)  // Altura del conjuto de 2 conjunto en vertical
    ){
        items(favoriteCollectionsData){
            item -> FavoriteCollectionCard(item.drawable, item.text, modifier = modifier. height(80.dp))
        }
    }
    // Implement composable here
}

// Step: Home section - Slot APIs
@Composable
fun HomeSection(  //funcion genérica donde se pasa cada una de las variables del column del HomeScreen. esquema para las otras 2 partes
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
    ) {
    // Implement composable here
    Column {
    Text (stringResource(title),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
            .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
            .padding(horizontal = 16.dp)
        )
    content()
}

}
// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Implement composable here
        Column (modifier
            .verticalScroll(rememberScrollState())) //recuerda la posicion del scroll para abajo
    {
            Spacer(modifier = Modifier.height(16.dp)) // espacio
            SearchBar(Modifier.padding(horizontal = 16.dp)) // la barra de busqueda
            HomeSection(title = R.string.align_your_body) { // las pelotas de cuerpos
                AlignYourBodyRow()
            }
            HomeSection(title = R.string.favorite_collections) { // la naturaleza en cuadritos
                FavoriteCollectionsGrid()
            }
            Spacer(modifier.height(16.dp)) //espacio abajo
        }


}

// Step: Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    // Implement composable here
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant //Le damos el marroncito del fondo
        ){

        NavigationBarItem( //el simbolo de Home es el primero y aparece como seleccionado
            selected = true,
            onClick = {  }, //uso de estados
            icon = { Icon(imageVector = Icons.Default.Spa, contentDescription = null)}, //icono en imagen vertorial de la librería llamado Spa
            label = { Text (text = stringResource(R.string.bottom_navigation_home))}
        )

        NavigationBarItem( //icono perfil
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)},
            label = { Text (text = stringResource(R.string.bottom_navigation_profile))}

        )
    }

}

// Step: MySoothe App - Scaffold
@Composable
fun MySootheAppPortrait() {
    // Implement composable here
    MySootheTheme { //Nuestro tema de diseño
        Scaffold ( // implenta el material basico de diseño de una extructura. Usa varios materiales conjuntamente en la pantalla
            bottomBar = { SootheBottomNavigation()}
        ){
            padding -> HomeScreen(Modifier.padding(padding))
        }
    }
}

// Step: Bottom navigation - Material
@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) { // Rail de navegacion para la posición horizontal
    // Implement composable here
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),  //pading para separar de la derecha y la izquierda
        contentColor = MaterialTheme.colorScheme.background   //cambio de color como antes y el tipo de letras
    ){
        Column (
            modifier = modifier.fillMaxHeight(), //rellenar la altura completa de la pantalla
            verticalArrangement = Arrangement.Center, //Centra en medio de la barra
            horizontalAlignment = Alignment.CenterHorizontally // Centro horizontal para dejar espacio a ambos lados
        ) {
            NavigationRailItem (
                selected = true, //seleccionado
                onClick = { /*TODO*/ },
                icon = { Icon(imageVector = Icons.Default.Spa, contentDescription = null)},
                label = { Text (text = stringResource(R.string.bottom_navigation_home))}
            )
            Spacer(modifier = modifier.height(8.dp)) //espacio en medio de los item con una medida en altura

            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)},
                label = { Text (text = stringResource(R.string.bottom_navigation_profile))}
            )



        }

    }
}

// Step: Landscape Mode
@Composable
fun MySootheAppLandscape(){
    // Implement composable here
    MySootheTheme{
        Surface(color = MaterialTheme.colorScheme.background){
            Row{
                SootheNavigationRail()
                HomeScreen()
            }
        }
    }

}


// Step: MySoothe App
@Composable
fun MySootheApp(windowSize : WindowSizeClass) {
    // Implement composable here
when (windowSize.widthSizeClass){ //un swich
    WindowWidthSizeClass.Compact -> {
        MySootheAppPortrait()

        }
    WindowWidthSizeClass.Expanded ->{
        MySootheAppLandscape()
        
    }

}

}

private val alignYourBodyData = listOf( // lista del yoga
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    MySootheTheme { SearchBar(Modifier.padding(8.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    MySootheTheme {
        AlignYourBodyElement(
            modifier = Modifier.padding(8.dp) ,
            drawable = R.drawable.ab1_inversions,
            text = R.string.ab1_inversions
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    MySootheTheme {
        FavoriteCollectionCard(
            modifier = Modifier.padding(8.dp) ,
            drawable = R.drawable.fc2_nature_meditations,
            text = R.string.fc2_nature_meditations,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview() {
    MySootheTheme { FavoriteCollectionsGrid() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    MySootheTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    MySootheTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE,) //heightDp = 180)
@Composable
fun ScreenContentPreview() {
    MySootheTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    MySootheTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview() {
    MySootheTheme { SootheNavigationRail() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePortraitPreview() {
    MySootheAppPortrait()
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MySootheLandscapePreview() {
    MySootheAppLandscape()
}

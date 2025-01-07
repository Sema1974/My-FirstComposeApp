package com.example.myfirstcomposeapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstcomposeapp.model.Superhero
import kotlinx.coroutines.launch

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(getSuperHeroes()) {
            ItemHero(it) { Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show() }
        }
    }
}

fun getSuperHeroes(): List<Superhero> {
    return listOf(
        Superhero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        Superhero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        Superhero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        Superhero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
        Superhero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        Superhero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        Superhero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman)
    )
}

@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier.width(250.dp) .clickable { onItemSelected(superhero) }) {
        Column() {
            Image(
                painter = painterResource(id = superhero.picture),
                contentDescription = "SuperHero Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superHeroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                modifier = Modifier.align(Alignment.End).padding(6.dp),
                fontSize = 10.sp
            )
        }
    }
}
@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier.padding(8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
        content = {
            items(getSuperHeroes()) {
                ItemHero(it) {
                    Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                }
            }
        })
}
@Composable
fun SuperHeroView2() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutine = rememberCoroutineScope();
    val showButton by remember{
        derivedStateOf {
            rvState.firstVisibleItemIndex > 0
        }
    }
    Column {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperHeroes()) {
                ItemHero(it) {
                    Toast.makeText(context, it.superHeroName, Toast.LENGTH_SHORT).show()
                }
            }
        }
        if(showButton) {
            Button(
                onClick = { coroutine.launch { rvState.animateScrollToItem(0)} },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(text = "Haz cosas")
            }
        }
    }
}
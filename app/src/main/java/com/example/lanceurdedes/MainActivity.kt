package com.example.lanceurdedes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                App(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
@Composable
fun App(modifier: Modifier = Modifier) {
    var selecteurDeDe2Save by rememberSaveable { mutableStateOf(1) }
    var selecteurDeFace2Save by rememberSaveable { mutableStateOf(6) }
    var selecteurDeTrisave by rememberSaveable { mutableStateOf("Aucun") }
    var total by remember { mutableStateOf(listOf<Int>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lanceur de des", style = MaterialTheme.typography.headlineSmall)


        Spacer(Modifier.height(16.dp))



        Text("Choix du nombre de des")
        var selecteurDeDe1 by remember { mutableStateOf(false) }

        Box {
            Button(onClick = { selecteurDeDe1 = true }) {
                Text("Selection du nombre de des")
            }
            DropdownMenu(expanded = selecteurDeDe1, onDismissRequest = { selecteurDeDe1 = false }) {
                (1..6).forEach { nbreDeDe ->
                    DropdownMenuItem(
                        text = { Text("$nbreDeDe") },
                        onClick = {
                            selecteurDeDe2Save = nbreDeDe
                            selecteurDeDe1 = false
                            Log.d("Nombre", "Nombre de dés choisi $nbreDeDe")
                        }
                    )
                }
            }
        }


        Spacer(Modifier.height(16.dp))


        Text("Choix du nombre de faces")
        var selecteurDeFace1 by remember { mutableStateOf(false) }
        val differenteFace = listOf(4, 6, 8, 10, 12, 20)

        Box {
            Button(onClick = { selecteurDeFace1 = true }) {
                Text("selectionner le nombre de face")
            }
            DropdownMenu(expanded = selecteurDeFace1, onDismissRequest = { selecteurDeFace1 = false }) {
                differenteFace.forEach { choixFace ->
                    DropdownMenuItem(
                        text = { Text("D$choixFace") },
                        onClick = {
                            selecteurDeFace2Save = choixFace
                            selecteurDeFace1 = false
                            Log.d("Face", "Nombre de faces choisi $choixFace")
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))


        Text("Choix du type de tri ")
        var selecteurDeTri by remember { mutableStateOf(false) }
        val typedeTri = listOf("Aucun", "Croissant", "Décroissant")

        Box {
            Button(onClick = { selecteurDeTri = true }) {
                Text(selecteurDeTrisave)
            }

            DropdownMenu(expanded = selecteurDeTri, onDismissRequest = { selecteurDeTri = false }) {
                typedeTri.forEach { choixTri ->
                    DropdownMenuItem(
                        text = { Text(choixTri) },
                        onClick = {
                            selecteurDeTrisave = choixTri
                            selecteurDeTri = false
                            Log.d("Tri", "Tri choisi $choixTri")
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            var element = (1..selecteurDeDe2Save).map {
                Random.nextInt(1, selecteurDeFace2Save + 1)
            }
            element = when (selecteurDeTrisave) {
                "Croissant" -> element.sorted()
                "Décroissant" -> element.sortedDescending()
                else -> element
            }
            total = element
            Log.i("Rfinal", "$total")
        }) {
            Text("Lancer les dés")
        }
        Spacer(Modifier.height(16.dp))
        if (total.isNotEmpty()) {
            Text(
                "Total :",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(total.size) { index ->
                    DesTextEtImage(valeur = total[index])
                }
            }


            Spacer(Modifier.height(12.dp))

            Text("Somme : ${total.sum()}")
        } else {
            Text("pas encore de total")
        }
    }
}

@Composable
fun DesTextEtImage(valeur: Int) {
    Box(
        modifier = Modifier
            .size(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.dice_1),
            contentDescription = "Dé",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = valeur.toString(),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}

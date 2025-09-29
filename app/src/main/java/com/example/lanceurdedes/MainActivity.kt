package com.example.lanceurdedes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()){innerPadding ->
                App(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lanceur de des")

        Spacer(Modifier.height(16.dp))

        Text("Selection du nombre de des")
        var selecteurDeDe1 by remember { mutableStateOf(false) }
        var selecteurDeDe2Save by rememberSaveable { mutableStateOf("") }

        Box {

            Button(onClick = { selecteurDeDe1 = true }) {
                Text(selecteurDeDe2Save)
            }
            DropdownMenu(
                expanded = selecteurDeDe1,
                onDismissRequest = { selecteurDeDe1 = false }
            )
            {
                (1..6).forEach { nbreDeDe ->
                    DropdownMenuItem(
                        text = { Text(nbreDeDe.toString()) },
                        onClick = {
                            selecteurDeDe2Save = nbreDeDe.toString()
                            selecteurDeDe1 = false
                            Log.d("Nombre", "Nombre de dés choisi $nbreDeDe")
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Selection du nombre de faces :")
        var selecteurDeFace1 by remember { mutableStateOf(false) }
        var selecteurDeFace2Save by rememberSaveable { mutableStateOf("") }

        Box {
            Button(onClick = { selecteurDeFace1 = true }) {
                Text(selecteurDeFace2Save)
            }
            DropdownMenu(
                expanded = selecteurDeFace1,
                onDismissRequest = { selecteurDeFace1 = false }
            ) {
                listOf("d4", "d6", "d8", "d10", "d12", "d20").forEach { choix ->
                    DropdownMenuItem(
                        text = { Text(choix) },
                        onClick = {
                            selecteurDeFace2Save = choix
                            selecteurDeFace1 = false
                            Log.d("Face", "nombre de Faces choisies $choix")
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Selection du type de tri :")
        var selecteurDeTri by remember { mutableStateOf(false) }
        var selecteurDeFruit2 by rememberSaveable { mutableStateOf("") }

        Box {
            Button(onClick = { selecteurDeTri = true }) {
                Text(selecteurDeFruit2)
            }
            DropdownMenu(
                expanded = selecteurDeTri,
                onDismissRequest = { selecteurDeTri = false }
            ) {
                listOf("Aucun", "Croissant", "Décroissant").forEach { choix ->
                    DropdownMenuItem(
                        text = { Text(choix) },
                        onClick = {
                            selecteurDeFruit2 = choix
                            selecteurDeTri = false
                            Log.d("Tri", "Le tri choisie est : $choix")
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))
    }}


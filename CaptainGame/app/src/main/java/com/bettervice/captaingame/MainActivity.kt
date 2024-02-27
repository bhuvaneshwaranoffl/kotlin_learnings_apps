package com.bettervice.captaingame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bettervice.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptainGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                CaptainGame()
                }
            }
        }
    }

    @Composable
    fun CaptainGame(){
        //after assign like the by we have to import the state.set value
        //val treasuresFound =remember {mutableStateOf(0)}
        // when we use by we don't need to call the variable value the variable itself will act as a value
        var treasuresFound by remember {
            mutableStateOf(0)
        }
        val direction =remember { mutableStateOf("north")}
        val stromOrTreasure = remember {
            mutableStateOf("")
        }
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
            ) {
            Text(text = "Treasure Found: ${treasuresFound}")
            Text(text = "Direction: ${direction.value}")
            Text(text = "StromOrTreasure: ${stromOrTreasure.value}")

            Button(onClick = {
                direction.value="East"
                if(Random.nextBoolean()){
                    treasuresFound +=1
                    stromOrTreasure.value = "Found a treasure"
                }else{
                    stromOrTreasure.value = "Found a storm"
                }
            }) {
             Text(text = "Sail East")
            }

            Button(onClick = {
                direction.value="West"
                if(Random.nextBoolean()){
                    treasuresFound +=1
                    stromOrTreasure.value = "Found a treasure"
                }else{
                    stromOrTreasure.value = "Found a storm"
                }
            }) {
                Text(text = "Sail West")
            }

            Button(onClick = {
                direction.value="North"
                if(Random.nextBoolean()){
                    treasuresFound +=1
                    stromOrTreasure.value = "Found a treasure"
                }else{
                    stromOrTreasure.value = "Found a storm"
                }
            }) {
                Text(text = "Sail North")
            }

            Button(onClick = {
                direction.value="South"
                if(Random.nextBoolean()){
                    treasuresFound +=1
                    stromOrTreasure.value = "Found a treasure"
                }else{
                    stromOrTreasure.value = "Found a storm"
                }
            }) {
                Text(text = "Sail South")
            }
        }
    }
}




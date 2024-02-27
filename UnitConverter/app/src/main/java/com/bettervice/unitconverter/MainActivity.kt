package com.bettervice.unitconverter

import android.os.Bundle
import android.service.credentials.CreateEntry
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bettervice.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                 UniConverter()
                }
            }
        }
    }
}



@Composable
fun UniConverter(){

    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }

    var outputUnit by remember {
        mutableStateOf("Meters")
    }

    var iExpanded by remember {
        mutableStateOf(false)
    }

    var oExpanded by remember {
        mutableStateOf(false)
    }

    val conversionFactor = remember {
        mutableStateOf(1.0)
    }

    val oconversionFactor = remember {
        mutableStateOf(1.0)
    }

     fun conversion(){
         val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
         val results =( inputValueDouble * conversionFactor.value*100/ oconversionFactor.value).roundToInt() / 100.0
         outputValue = results.toString()
     }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter",
            style=MaterialTheme.typography.bodyLarge
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange ={inputValue=it
                           conversion()
                           },
            label = { Text(text = "Enter the value")},

           // placeholder = {Text(text="Enter the value")}
        )
     Spacer(modifier = Modifier.height(16.dp))
        Row{
            //input valie
             Box {
                 //inputunit
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null)
                }
                     DropdownMenu(expanded = iExpanded, onDismissRequest =  {
                         iExpanded = false
                     }) {
                         DropdownMenuItem(
                             text = {Text(text = "Centimeter")},
                             onClick = {
                                 iExpanded = false
                                 inputUnit = "Centimeter"
                                 conversionFactor.value = 0.01
                                 conversion()
                             }
                         )
                         DropdownMenuItem(
                             text = {Text(text = "Meters")},
                             onClick = {
                                 iExpanded = false
                                 inputUnit = "Meters"
                                 conversionFactor.value = 1.0
                                 conversion()
                             }
                         )
                         DropdownMenuItem(
                             text = {Text(text = "Feet")},
                             onClick = {
                                 iExpanded = false
                                 inputUnit = "Feet"
                                 conversionFactor.value = 0.348
                                 conversion()
                             }
                         )
                         DropdownMenuItem(
                             text = {Text(text = "MilliMeter")},
                             onClick = {
                                 iExpanded = false
                                 inputUnit = "MilliMeter"
                                 conversionFactor.value = 0.001
                                 conversion()
                             }
                         )
                     }
             }
            Spacer(modifier = Modifier.width(10.dp))
         //output value
            Box {
                //output button
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null)
                }
                DropdownMenu(expanded =oExpanded
                    , onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = {Text(text = "Centimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeter"
                            conversionFactor.value = 0.01
                            conversion()


                        }
                    )
                    DropdownMenuItem(
                        text = {Text(text = "Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            conversionFactor.value = 1.00
                            conversion()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text(text = "Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            conversion()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text(text = "MilliMeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "MilliMeter"
                            conversionFactor.value = 0.001
                            conversion()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "the result is : $outputValue $outputUnit",
            style=MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UniConverter()
}
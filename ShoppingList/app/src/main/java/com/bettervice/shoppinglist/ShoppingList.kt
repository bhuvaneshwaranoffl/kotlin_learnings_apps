package com.bettervice.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sign

data class ShoppingItems(
    val id:Int,
    var name:String,
    var quantity:Int,
    var isEditing :Boolean = false
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ShoppingListApp() {
    var sItems by remember {
        mutableStateOf(listOf<ShoppingItems>())
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var itemName by remember {
        mutableStateOf("")
    }


    var itemQuantity by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        {
            Text(
                text = "Add Item", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) { item ->
                if (item.isEditing) {
                    shoppingItemEditor(item = item, onEdit = { editedName, editedQuantity ->
                        sItems = sItems.map { it.copy(isEditing = false) }
                        val editedItem = sItems.find { it.id == item.id }
                        editedItem?.let {
                            it.name = editedName
                            it.quantity = editedQuantity
                        }
                    })
                } else {
              shoppingListItems(item = item, onEdit = {
                  sItems =sItems.map { it.copy(isEditing = it.id==item.id) }
              },
                  onDelete ={
                    sItems = sItems-item
                  }
                  )
                }
            }
        }

        if (showDialog) {
            AlertDialog(onDismissRequest = {
                showDialog = false
            }, confirmButton = {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Button(onClick = {
                        if (itemName.isNotBlank()) {
                            val newItems = ShoppingItems(
                                id = sItems.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toInt(),
                            )
                            sItems = sItems + newItems
                            showDialog = false
                            itemName = ""
                            itemQuantity = ""
                        }
                    }) {
                        Text(text = "Add")
                    }
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            },
                title = { Text(text = "Add shopping item") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = itemName,
                            onValueChange = { itemName = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = { Text(text = "Item") }

                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = itemQuantity,
                            onValueChange = { itemQuantity = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            label = { Text(text = "quantity") }
                        )


                    }


                }
            )

        }
    }
}





@Composable
fun  shoppingListItems(
    item:ShoppingItems,
    onEdit:()-> Unit,
    onDelete:()-> Unit,

){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, Color.Blue),
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
     Column {
         Text(text = "Item: ${item.name}", modifier = Modifier.padding(8.dp),style = TextStyle(
             fontSize = 18.sp,
             color = Color.Black
         ))
         Spacer(modifier = Modifier.height(10.dp))

         Text(text = "Quantity: ${item.quantity}", modifier = Modifier.padding(8.dp),style = TextStyle(
             fontSize = 18.sp,
             color = Color.Black
         ))
     }


           Row {
               IconButton(onClick = {
                  onEdit
               }) {
                   Icon(imageVector =  Icons.Default.Edit, contentDescription = "")
               }
               Spacer(modifier = Modifier.width(10.dp))

               IconButton(onClick = {
                    onDelete
               }) {
                   Icon( imageVector = Icons.Default.Delete, contentDescription = "")
               }
           }


    }
}

@Composable
fun shoppingItemEditor(
    item:ShoppingItems,
    onEdit: (String,  Int) -> Unit
){
     var editedName by remember {
         mutableStateOf(item.name)
     }
    var editedQuantity by remember {
        mutableStateOf(item.quantity.toString())
    }
    var isEditing by remember {
        mutableStateOf(item.isEditing)
    }


    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Blue)
        .padding(8.dp)){

        Column {
            OutlinedTextField(
                value = editedName,
                onValueChange ={editedName=it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
                )

            OutlinedTextField(
                value = editedQuantity,
                onValueChange ={editedQuantity=it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp))
        }


        Button(onClick = {
            isEditing = false
            onEdit(editedName,editedQuantity.toIntOrNull()?: 0)

        }) {
            Text(text = "Save" , style = TextStyle(
                fontSize = 18.sp,
                color = Color.White
            ))

        }

    }
}

@Preview
@Composable
fun ShoppingItemPreview() {
    ShoppingListApp()
}

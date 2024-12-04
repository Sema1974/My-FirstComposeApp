package com.example.myfirstcomposeapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SimpleFilledTextFieldSample(){
    var text by remember{ mutableStateOf("Hello") }
    var text2 = "Pepe"

    TextField(
        value = text2,
        onValueChange = {valor-> text2 = valor},
        label = {Text("label")}
    )
}
@Composable
fun MyCheckbox(){
    var state by rememberSaveable { mutableStateOf(true) }
    Row(Modifier.padding(8.dp)) {
        Checkbox(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Yellow,
                checkmarkColor = Color.Blue
            )
        )
    }
}



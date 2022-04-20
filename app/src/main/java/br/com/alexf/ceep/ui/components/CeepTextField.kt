package br.com.alexf.ceep.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CeepTextField(text: String = "", onClick: () -> Unit) {
    var text by remember {
        mutableStateOf(text)
    }
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF673AB7))
            .defaultMinSize(minHeight = 60.dp)
            .wrapContentSize()
    )

    Button(onClick = onClick) {

    }
}

@Preview(showBackground = true)
@Composable
fun CeepTextFieldPreview() {
    CeepTextField("basic text field preview") { /*TODO*/ }
}
package com.example.kitchenbook.presentation.recipe

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text("Search", color = Color.Black) }, textStyle = TextStyle(fontSize = 16.sp),
        singleLine = true,
        modifier = Modifier
            .padding(5.dp)
            .height(50.dp)
            .shadow(4.dp, shape = RoundedCornerShape(20.dp)),

        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
    )
}

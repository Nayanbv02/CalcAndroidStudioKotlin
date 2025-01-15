package com.example.calckotlinnbv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraApp()
        }
    }
}

@Composable
fun CalculadoraApp() {
    var numero1 by remember { mutableStateOf(TextFieldValue("")) }
    var numero2 by remember { mutableStateOf(TextFieldValue("")) }
    var resultado by remember { mutableStateOf<String?>(null) }
    var errorMensaje by remember { mutableStateOf<String?>(null) }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                TextField(
                    value = numero1,
                    onValueChange = { numero1 = it; errorMensaje = null },
                    label = { Text("Número 1") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))


                TextField(
                    value = numero2,
                    onValueChange = { numero2 = it; errorMensaje = null },
                    label = { Text("Número 2") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))


                Button(
                    onClick = {
                        try {
                            val num1 = numero1.text.toDouble()
                            val num2 = numero2.text.toDouble()
                            resultado = (num1 + num2).toString()
                            errorMensaje = null
                        } catch (e: NumberFormatException) {
                            errorMensaje = "Ingresa un numero valido."
                            resultado = null
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sumar")
                }

                Spacer(modifier = Modifier.height(16.dp))


                if (errorMensaje != null) {
                    Text(
                        text = errorMensaje!!,
                        color = MaterialTheme.colors.error
                    )
                } else if (resultado != null) {
                    Text(
                        text = "Resultado: $resultado",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}

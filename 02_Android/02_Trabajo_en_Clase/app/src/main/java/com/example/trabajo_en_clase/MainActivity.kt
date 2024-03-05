package com.example.trabajo_en_clase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trabajo_en_clase.ui.theme.Trabajo_en_ClaseTheme

/*
Realizar una interfaz con qualifiers donde se modifique el texto, color de fondo y color de texto
dependiendo de las siguientes condiciones:

LANDS EN -> condicion 1
PORT EN -> Condicion 2
LANDS ES -> Condicion 3
PORT ES -> Condicion 4

cada condicion tendra un texto diferente, color de fondo diferente y color de texto diferente
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

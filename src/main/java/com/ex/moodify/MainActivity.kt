package com.ex.moodify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ex.moodify.navigation.AppNavigation
import com.ex.moodify.ui.theme.MoodifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Aqui você instancia o banco e o repositório (por enquanto sem Hilt)

        // Passa isso para sua ViewModel manualmente ou via factory

        setContent {

            // Aplica o tema Material 3

            MoodifyTheme {

                // Chama a função que define as rotas da navegação
                AppNavigation()
            }
        }
    }
}
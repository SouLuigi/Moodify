package com.ex.moodify.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ex.moodify.domain.MoodType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MoodScreen() {

    var description: String by remember { mutableStateOf("") }
    var selectedEmoji: MoodType? by remember { mutableStateOf(null) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val isFormValid: Boolean = selectedEmoji != null && description.isNotBlank()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Bem Vindo!",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "Como você está se sentindo hoje?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier.fillMaxWidth(),
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    listOf(
                        MoodType.HAPPY,
                        MoodType.SAD,
                        MoodType.ANGRY,
                        MoodType.IN_LOVE,
                        MoodType.NEUTRAL,
                        MoodType.EXCITED,
                        MoodType.TIRED,
                        MoodType.CONFUSED
                    )
                ) { emoji ->
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .clickable { selectedEmoji = emoji }
                            .background(
                                if (emoji == selectedEmoji) Color.LightGray else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = emoji.symbol,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

            TextField(
                value = description,
                onValueChange = {
                    if (it.length <= 500) {
                        description = it
                    }
                },
                label = {
                    Text(
                        "Quer escrever algo sobre seu dia?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
            )

            Text(
                fontSize = 16.sp,
                text = "${description.length}/500",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    /*TODO*/
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Registro salvo!")
                    }
                    Log.d("MoodApp", "Salvando: ${selectedEmoji?.name} - $description")
                },
                enabled = isFormValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) { Text("Salvar") }
        }
    }
}

@Preview(showBackground = true, name = "Tela Inicial")
@Composable
fun MoodScreemPreview() {
    MoodScreen()
}

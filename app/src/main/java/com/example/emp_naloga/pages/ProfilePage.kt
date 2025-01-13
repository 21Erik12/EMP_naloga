package com.example.emp_naloga.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isLoggedIn = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Prijava",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        BasicTextField(
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            decorationBox = { innerTextField ->
                if (username.value.isEmpty()) {
                    Text(
                        text = "Uporabniško ime",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        fontSize = 18.sp
                    )
                }
                innerTextField()
            }
        )

        BasicTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default, // Dodano za tipkovnico za gesla
            decorationBox = { innerTextField ->
                if (password.value.isEmpty()) {
                    Text(
                        text = "Geslo",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        fontSize = 18.sp
                    )
                }
                innerTextField()
            }
        )

        Button(
            onClick = { isLoggedIn.value = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Prijava", fontSize = 20.sp)
        }

        if (isLoggedIn.value) {
            Text(
                text = "Pozdravljeni ${username.value}!",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}



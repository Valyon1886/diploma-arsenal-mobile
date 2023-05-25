package com.example.arsenalmobile.Auth.Screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.arsenalmobile.Auth.RoutesAuth
import com.example.arsenalmobile.AuthActivity
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.MegaColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenRegistration(authActivity: AuthActivity, navController: NavController){
    Scaffold() {
        var emailState by remember { mutableStateOf(TextFieldValue()) }
        var passwordState by remember { mutableStateOf(TextFieldValue()) }
        var showPassword by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.background(BackColor).padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    Text(
                        text = "Регистрация",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 10.dp),
                        textAlign = TextAlign.Left
                    )
                    OutlinedTextField(
                        value = emailState,
                        onValueChange = { emailState = it },
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            trailingIconColor = MegaColor,
                            focusedIndicatorColor = MegaColor,
                            cursorColor = MegaColor,
                            focusedLabelColor = MegaColor
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email Icon"
                            )
                        },
                        shape = RoundedCornerShape(25.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        label = { Text("Пароль") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            trailingIconColor = MegaColor,
                            focusedIndicatorColor = MegaColor,
                            cursorColor = MegaColor,
                            focusedLabelColor = MegaColor),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(25.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = "Lock Icon"
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(
                                    painter = if (showPassword) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(
                                        id = R.drawable.baseline_visibility_off_24
                                    ),
                                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                                )
                            }
                        },
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val email = emailState.text
                            val password = passwordState.text
                            authActivity.register(email, password)
                            navController.navigate(RoutesAuth.Authorization.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(MegaColor)
                    ) {
                        Text(
                            text = "Зарегистрироваться",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3.5f)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
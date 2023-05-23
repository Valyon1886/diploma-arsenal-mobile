package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.ImageView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.Entity.GameInput
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ViewModels.AddGame.chooseSeries
import com.example.arsenalmobile.ViewModels.AddGame.dropListMode
import com.example.arsenalmobile.ui.theme.BoxColor
import com.example.arsenalmobile.ui.theme.NavBarColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateGame(
    gameController: GameController,
    userController: UserController,
    auth: FirebaseAuth,
    user: User,
    mainActivity: MainActivity
) {

    val name = remember { mutableStateOf(TextFieldValue("")) }
    var mode by remember { mutableStateOf("") }
    val amount = remember { mutableStateOf("") }
    val destination = remember { mutableStateOf(TextFieldValue("")) }
    val description = remember { mutableStateOf(TextFieldValue("")) }
    var series = remember { mutableStateOf<List<String>>(emptyList()) }
    var users = listOf(user.id)

    val startDate = remember { mutableStateOf("") }

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            startDate.value = "${if(mDayOfMonth<10) "0$mDayOfMonth" else mDayOfMonth}/${if(mMonth+1<10) "0"+(mMonth+1) else mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    var gameImgSrc by remember { mutableStateOf("") }

    val imageUriState = remember { mutableStateOf<Uri?>(null) }
    var uploadUri: Uri? = null
    val storage = Firebase.storage
    var storageRef = storage.reference
    var imDB: ImageView? = null

    fun saveUser(uploadUri: Uri?){
        CoroutineScope(Dispatchers.IO).launch {
            val gameInput = GameInput(name.value.text, amount.value.toInt(), uploadUri.toString(), mode, description.value.text, destination.value.text, series.value, startDate.toString(), users, true)
            Log.d("gameInput = ", " ${gameInput}")
            user.id?.let { gameController.addGame(gameInput, it) }
        }
    }

    fun uploadImage() {
        storageRef = Firebase.storage.reference.child("images_games/${auth.currentUser?.uid}/${imageUriState.value?.lastPathSegment}")
        val uploadTask = imageUriState.value?.let { storageRef.putFile(it) }

        uploadTask?.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let { throw it }
            }
            storageRef.downloadUrl
        }?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                uploadUri = task.result
                saveUser(uploadUri)
            }
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                imageUriState.value = uri
            }
        }
    }

    fun getImage(){
        val intentChooser = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        launcher.launch(intentChooser)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Добавление нового события",
                        color = Color.Black
                    )
                },
                backgroundColor = NavBarColor
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .background(color = BoxColor)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Название события", color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        unfocusedBorderColor = NavBarColor,
                        focusedBorderColor = Color.Yellow,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                mode = dropListMode()
                Log.d("mode ", mode)
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = amount.value,
                    onValueChange = { amount.value = it },
                    label = { Text("Количество участников", color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        unfocusedBorderColor = NavBarColor,
                        focusedBorderColor = Color.Yellow,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                series = chooseSeries()
                Log.d("seriesInput ", "${series.value}")
                OutlinedTextField(
                    value = destination.value,
                    onValueChange = { destination.value = it },
                    label = { Text("Введите место проведения события", color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        unfocusedBorderColor = NavBarColor,
                        focusedBorderColor = Color.Yellow,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Box() {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                mDatePickerDialog.show()
                            }
                        ) {
                            Icon(painterResource(id = R.drawable.baseline_calendar_month_24), contentDescription = "Календарь", tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Конечная дата: ${startDate.value}", fontSize = 20.sp, textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Выберите изображение",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable {
                            getImage()
                        }
                    )
                    Text(
                        text = if (imageUriState.value?.lastPathSegment == null) "" else imageUriState.value?.lastPathSegment.toString(),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Divider(modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 10.dp), color = Color.White)
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Введите описание", color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        unfocusedBorderColor = NavBarColor,
                        focusedBorderColor = Color.Yellow,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(modifier = Modifier.fillMaxWidth()
                    .padding(start = 80.dp, end = 80.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green, contentColor = Color.Black), onClick = { uploadImage() }) {
                    Text(text = "Создать")
                }
            }
        }
    )
}
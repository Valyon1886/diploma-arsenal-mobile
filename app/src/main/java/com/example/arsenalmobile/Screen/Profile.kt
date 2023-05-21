package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.R
import com.example.arsenalmobile.Models.UserImg
import com.example.arsenalmobile.Navigation.Routes
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.BoxColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Profile(
    userController: UserController,
    auth: FirebaseAuth,
    mainActivity: MainActivity,
    user: User,
    navController: NavController
) {
    var userImgSrc by remember { mutableStateOf<User?>(null) }

    val imageUriState = remember { mutableStateOf<Uri?>(null) }
    var uploadUri: Uri? = null
    val storage = Firebase.storage
    var storageRef = storage.reference
    var imDB: ImageView? = null

//    fun saveUser(uploadUri: Uri?){
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d("imgSrc = ", " ${uploadUri.toString()}")
//            val userImg = UserImg(uploadUri.toString())
//            userApi.updateUserImg(user.id, userImg)
//        }
//    }
//
//    fun uploadImage() {
//        storageRef = Firebase.storage.reference.child("images/${auth.currentUser?.uid}/${imageUriState.value?.lastPathSegment}")
//        val uploadTask = imageUriState.value?.let { storageRef.putFile(it) }
//
//        uploadTask?.continueWithTask { task ->
//            if (!task.isSuccessful) {
//                task.exception?.let { throw it }
//            }
//            storageRef.downloadUrl
//        }?.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                uploadUri = task.result
//                saveUser(uploadUri)
//            }
//        }
//    }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            result.data?.data?.let { uri ->
//                imageUriState.value = uri
//            }
//            uploadImage()
//        }
//    }
//
//    fun getImage(){
//        val intentChooser = Intent(Intent.ACTION_GET_CONTENT).apply {
//            type = "image/*"
//        }
//        launcher.launch(intentChooser)
//    }

    val painter = imageUriState.value?.let { uri ->
        rememberAsyncImagePainter(model = uri)
    } ?: painterResource(id = R.drawable.image)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ПРОФИЛЬ",
                        color = Color.Black,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {

                },
                actions = {
                    IconButton(onClick = {
                        mainActivity.signOut()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_logout_24),
                            contentDescription = "Выход из аккаунта",
                            tint = Color.Black
                        )
                    }
                },
                backgroundColor = Color.Yellow
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BackColor)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.5f),
                    contentAlignment = Alignment.Center
                ) {

                    Box() {
                        Image(
                            painter = painter,
                            contentDescription = "profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(125.dp)
                                .clip(CircleShape)
                                .border(2.dp, color = Color.Black, CircleShape)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_edit_24),
                            contentDescription = "pencil",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(30.dp)
                                .aspectRatio(1f)
                                .padding(end = 5.dp, bottom = 5.dp)
                                .align(Alignment.BottomEnd)
                                .clip(CircleShape)
                                .background(Color.White)
                                .clickable {
//                                    getImage()
                                }
                                .border(2.dp, color = Color.Black, CircleShape)
                                .scale(0.75f)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.userName,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Card(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f),
                                contentAlignment = Alignment.Center
                            ) {
                                Column() {
                                    Text(
                                        text = "12",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        text = "Заказы",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f),
                                contentAlignment = Alignment.Center
                            ) {
                                Column() {
                                    Text(
                                        text = "2,467",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        text = "КПД",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f),
                                contentAlignment = Alignment.Center
                            ) {
                                Column() {
                                    Text(
                                        text = "4.8",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        text = "Рейтинг",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(5f),
                    contentAlignment = Alignment.TopCenter
                ){
                    Card(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        backgroundColor = BoxColor,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                border = BorderStroke(3.dp, Color.Black),
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(15.dp),
                                onClick = {
                                    navController.navigate(Routes.UserGames.route)
                                }
                            ){
                                Text(
                                    text = "Мои игры",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                border = BorderStroke(3.dp, Color.Black),
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(15.dp),
                                onClick = {
                                    navController.navigate(Routes.UserBlasters.route)
                                }
                            ){
                                Text(
                                    text = "Мой арсенал",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                border = BorderStroke(3.dp, Color.Black),
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(15.dp),
                                onClick = {
                                    navController.navigate(Routes.UserTest.route)
                                }
                            ){
                                Text(
                                    text = "Пройти тестирование",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
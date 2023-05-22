package com.example.arsenalmobile.Navigation

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.arsenalmobile.Controllers.AmmoController
import com.example.arsenalmobile.Controllers.BlasterController
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.Screen.*
//import com.example.arsenalmobile.Screen.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import java.util.concurrent.atomic.AtomicBoolean

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
    userController: UserController,
    ammoController: AmmoController,
    gameController: GameController,
    blasterController: BlasterController,
    auth: FirebaseAuth,
    mainActivity: MainActivity
) {

    var user by remember { mutableStateOf<User?>(null) }
//
//    CoroutineScope(Dispatchers.IO).launch {
//        user = userApi.getUserByIdToken(auth.currentUser?.uid.toString())
//    }

    var checkIdToken by remember { mutableStateOf(false) }
    val isLoading = remember { AtomicBoolean(true) }


//    LaunchedEffect(Unit) {
////        user = userController.getUserByIdToken(auth.currentUser?.uid.toString())
//        user = userController.findUser(1)
//        Log.d("User ", "${user}")
//    }

    NavHost(
        navController = navHostController,
        startDestination = "loading"
    ) {
        composable("loading") {
            Loading()
        }
        composable("user_registration") {
            UserRegistration(userController, navHostController, auth, mainActivity)
        }
        composable("profile") {
            Profile(userController, auth, mainActivity, user!!, navHostController)
        }
        composable(Routes.UserGames.route) {
            UserGames()
        }
        composable(Routes.UserBlasters.route) {
            UserBlasters()
        }
        composable(Routes.UserTest.route) {
            UserTest()
        }
        composable("games") {
            ActiveGames(userController, gameController, auth, mainActivity, user!!, navHostController)
        }
        composable("${Routes.InfoGame.route}/{id}") { navBackStack ->
            val gamerId = navBackStack.arguments?.getString("id")?.toLong() ?: 0
            InfoGame(gameId = gamerId, gameController, navHostController, userController, user!!, mainActivity)
        }
        composable(Routes.CreateGame.route) {
            CreateGame()
        }
        composable("blasters") {
            CatalogBlasters(userController, blasterController, auth, mainActivity, user!!, navHostController)
        }
        composable("${Routes.InfoBlaster.route}/{id}") { navBackStack ->
            val blasterId = navBackStack.arguments?.getString("id")?.toLong() ?: 0
            InfoBlaster(blasterId = blasterId, blasterController, navHostController, userController, user!!, mainActivity)
        }
//        composable("${Routes.Material.route}/{id}") { navBackStack ->
//            val jobId = navBackStack.arguments?.getString("id")?.toInt() ?: 0
//            ScreenMaterial(jobId = jobId, userApi, materialApi, navHostController, mainActivity)
//        }
//        composable(Routes.CreateJob.route) {
//            ScreenCreateJob(jobApi, navHostController)
//        }


    }

    LaunchedEffect(Unit) {
        delay(5000)
        isLoading.set(true)
        checkIdToken = userController.checkIdTokenUser(auth.currentUser?.uid.toString()) == true
        if (checkIdToken) user = userController.getUserByIdToken(auth.currentUser?.uid.toString())
        Log.d("User ", "${user}")
        isLoading.set(false)
        if (user == null) {
            navHostController.navigate("user_registration")
        } else {
            navHostController.navigate("profile")
        }
    }

}



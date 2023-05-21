package com.example.arsenalmobile.Navigation.Screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.arsenalmobile.Controllers.AmmoController
import com.example.arsenalmobile.Controllers.BlasterController
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Navigation.BottomNavigation
import com.example.arsenalmobile.Navigation.NavGraph
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    auth: FirebaseAuth,
    mainActivity: MainActivity
) {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8081").client(client)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userController = retrofit.create(UserController::class.java)
    val ammoController = retrofit.create(AmmoController::class.java)
    val gameController = retrofit.create(GameController::class.java)
    val blasterController = retrofit.create(BlasterController::class.java)

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController)
        }
    ) {
        NavGraph(navHostController = navController, userController, ammoController, gameController, blasterController, auth, mainActivity)
    }
}
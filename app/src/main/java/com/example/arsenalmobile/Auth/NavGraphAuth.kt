package com.example.arsenalmobile.Auth

import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.arsenalmobile.Auth.Screen.ScreenAuthorization
import com.example.arsenalmobile.Auth.Screen.ScreenPhone
import com.example.arsenalmobile.Auth.Screen.ScreenRegistration
import com.example.arsenalmobile.AuthActivity
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraphAuth(
    navHostController: NavHostController,
    authActivity: AuthActivity
) {
    NavHost(navController = navHostController, startDestination = "authorization"){
        composable("authorization"){
            ScreenAuthorization(authActivity = authActivity, navHostController)
        }
        composable("phone"){
            ScreenPhone(authActivity = authActivity, navHostController)
        }
        composable("registration"){
            ScreenRegistration(authActivity = authActivity, navHostController)
        }

//        composable("${Routes.Material.route}/{id}"){ navBackStack ->
//            val jobId = navBackStack.arguments?.getString("id")?.toInt() ?: 0
//            ScreenMaterial(jobId = jobId, userApi, materialApi, navHostController)
//        }
//        composable(Routes.CreateJob.route){
//            ScreenCreateJob(jobApi, navHostController)
//        }
//        composable("${Routes.SubTask.route}/{id}"){ navBackStack ->
//            val jobId = navBackStack.arguments?.getString("id")?.toInt() ?: 0
//            ScreenSubTask(jobId = jobId, jobApi, navHostController, userApi)
//        }
    }
}
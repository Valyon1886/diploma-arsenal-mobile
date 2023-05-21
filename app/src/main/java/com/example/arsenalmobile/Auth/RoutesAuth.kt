package com.example.arsenalmobile.Auth

sealed class RoutesAuth(val route: String) {
    object Authorization: RoutesAuth("authorization")
    object Phone: RoutesAuth("phone")
    object Registration: RoutesAuth("registration")
}
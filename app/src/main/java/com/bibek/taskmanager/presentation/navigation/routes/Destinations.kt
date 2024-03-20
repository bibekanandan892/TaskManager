package com.bibek.taskmanager.presentation.navigation.routes
/**
Author: Bibekananda Nayak
Date: 21-02-2024
Description: This Kotlin code defines a sealed class Destinations representing different routes in an application.
Each route is represented by an object inheriting from the Destinations sealed class. The class also defines a
typealias DestinationRoute for better readability.
*/

sealed class Destinations(val route: String) {
    object Login : Destinations("Login") // Represents the Login route
    object Task : Destinations("Task") // Represents the Dashboard route
    object Edit : Destinations("Edit/{task}") {
        fun passTask(task : String): String{
            return "Edit/$task"
        }
    }// Represents the PhoneVerificationScreen route
    object OtpScreen : Destinations("OtpScreen") // Represents the OtpScreen route
    object WalletReportScreen : Destinations("WalletReportScreen") // Represents the WalletReportScreen route
    object UpiReportScreen : Destinations("UpiReportScreen") // Represents the UpiReportScreen route
    object SignUpScreen : Destinations("SignUpScreen") // Represents the ForgotPassword route
    object ChangePasswordScreen : Destinations("ChangePasswordScreen") // Represents the ForgotPassword route
}

typealias DestinationRoute = String // Alias for DestinationRoute for better readability

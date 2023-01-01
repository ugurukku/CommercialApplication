package com.ugurukku.secondhand.dto

data class CreateUserDetailsRequest(
    val phoneNumber: String,
    val address: String,
    val city: String,
    val country: String,
    val userId:Long
)

data class UpdateUserDetailsRequest(
    val phoneNumber: String,
    val address: String,
    val city: String,
    val country: String
)


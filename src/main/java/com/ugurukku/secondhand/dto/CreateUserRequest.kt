package com.ugurukku.secondhand.dto

data class CreateUserRequest(
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val postCode: String?,
)

package com.ugurukku.secondhand.dto

data class CreateUserRequest(
    val email: String?,
    val firstName: String?,
    val lastname: String?,
    val postCode: String?
)

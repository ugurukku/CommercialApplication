package com.ugurukku.secondhand.dto


data class UserDto(
    val email: String?,
    val firstName: String?,
    val lastname: String?,
    val postCode: String?,
    val isActive:Boolean?,
    val userDetails:List<UserDetailsDto>
)

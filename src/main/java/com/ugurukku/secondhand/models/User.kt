package com.ugurukku.secondhand.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val email: String?,
    val firstName: String?,
    val lastname: String?,
    val postCode: String?
)

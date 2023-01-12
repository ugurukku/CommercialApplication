package com.ugurukku.secondhand.user.models

import java.time.LocalDateTime

data class BaseEntity(
    val createdDate: LocalDateTime? = null,
    val updatedDate: LocalDateTime? = null
)
package com.ugurukku.secondhand.models

import java.time.LocalDateTime

data class BaseEntity(
    val createdDate: LocalDateTime? = null,
    val updatedDate: LocalDateTime? = null
)
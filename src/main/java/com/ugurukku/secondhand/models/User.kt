package com.ugurukku.secondhand.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User constructor(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val postCode: String?
) {
    constructor() : this(null,null,null,null,null)
}

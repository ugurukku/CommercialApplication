package com.ugurukku.secondhand.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User constructor(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "email")
    val email: String?,

    @Column(name = "first_name")
    val firstName: String?,

    @Column(name = "last_name")
    val lastName: String?,

    @Column(name = "post_code")
    val postCode: String?
) {
    constructor() : this(null,null,null,null,null)
}

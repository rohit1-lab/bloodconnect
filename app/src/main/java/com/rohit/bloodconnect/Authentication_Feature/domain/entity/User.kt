package com.rohit.bloodconnect.Authentication_Feature.domain.entity

data class User(
   val id: Long = 0,
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val role: String = "",
      val firebaseUid: String?=null,
)

package com.rohit.bloodconnect.Authentication_Feature.util

import com.auth0.android.jwt.JWT
import java.util.Date


object JwtUtils {
    fun isTokenExpired(token: String?): Boolean {
        if (token.isNullOrBlank()) return true
        val jwt = JWT(token)
        val expiration = jwt.expiresAt ?: return true
        return expiration.before(Date())
    }
}

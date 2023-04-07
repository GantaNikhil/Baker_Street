package com.example.baker_street.models

data class UserModel(

    var name: String? = null,

    var email: String? = null,

    var admNo: String? = null,

    var password: String? = null,

    var jwtToken: String? = null,

    var status: String ? =null
)

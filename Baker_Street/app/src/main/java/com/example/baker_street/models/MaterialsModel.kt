package com.example.baker_street.models

//POST
//data class MaterialsModel(
//    val courseid: Any? = null,
//    val file :
//            val materials : ArrayList<String>
//    )

data class MaterialModel(
    val url: String? = null,
)

data class MaterialsModel(
    val materials: ArrayList<MaterialModel>? = null
)
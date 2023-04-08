package com.example.baker_street.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AnnouncementModel(
    @SerializedName("courseid")
    val courseid : String?=null,

    @SerializedName("text")
    val description :String?=null,

    @SerializedName("createdAt")
    val createdAt :Date?=null,
)
data class AnnouncementsModel(
    @SerializedName("announcements")
    val announcementsModel: ArrayList<AnnouncementModel>?= null
)
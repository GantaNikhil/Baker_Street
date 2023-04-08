package com.example.baker_street.models

data class CommentModel(
    val sourceid: Any? = null,
    val sourcetype: String? = null
)
data class CommentsModel(
    val comments :ArrayList<CommentModel>
)

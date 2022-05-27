package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

data class Response(
    val id: Int,
    val name: String,
    var checked: Boolean = false,
    val color: String = "",
    val description: String = "",
    val hint: String = "",
    val icon: String = "",
    val modalUrl: String = "",
    val referenceId: Int = 0,
    val title: String = ""
)

fun Response.toAnswer(): Answer {
    return Answer(
        id = id,
        name = name,
        referenceId = referenceId,
        labels = emptyList(),
        bodyLocation = "",
        symptoms = emptyList(),
        triggers = emptyList(),
        types = emptyList()
    )
}
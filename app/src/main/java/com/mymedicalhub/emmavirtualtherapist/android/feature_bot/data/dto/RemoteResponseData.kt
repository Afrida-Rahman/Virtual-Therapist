package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseData
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseType


data class RemoteResponseData(
    @SerializedName("audioUrl") val audioUrl: String,
    @SerializedName("bodyLocation") val bodyLocation: String,
    @SerializedName("bodyParts") val bodyParts: List<RemoteBodyPart>,
    @SerializedName("botName") val botName: String,
    @SerializedName("buttonText") val buttonText: String,
    @SerializedName("chatEnded") val chatEnded: Boolean,
    @SerializedName("dialogue") val dialogue: String,
    @SerializedName("disableBodyPartIds") val disableBodyPartIds: List<String>,
    @SerializedName("editable") val editable: Boolean,
    @SerializedName("exercise") val exercise: Any?,
    @SerializedName("header1") val header1: String,
    @SerializedName("header2") val header2: String,
    @SerializedName("hint") val hint: String,
    @SerializedName("id") val id: Int,
    @SerializedName("intent") val intent: String,
    @SerializedName("lastQuestionInGroup") val lastQuestionInGroup: Boolean,
    @SerializedName("pageCaption") val pageCaption: String,
    @SerializedName("questionId") val questionId: Int,
    @SerializedName("responseType") val responseType: String,
    @SerializedName("responses") val responses: List<RemoteResponse>,
    @SerializedName("saveAnswer") val saveAnswer: Boolean,
    @SerializedName("selectedBodyRegions") val selectedBodyRegions: List<String>,
    @SerializedName("sessionId") val sessionId: String,
    @SerializedName("skipped") val skipped: Boolean,
    @SerializedName("tenant") val tenant: String,
    @SerializedName("text") val text: String,
    @SerializedName("title") val title: String,
    @SerializedName("typeId") val typeId: Int,
    @SerializedName("typeName") val typeName: String,
    @SerializedName("vasQuestion") val vasQuestion: Boolean
)

fun RemoteResponseData.toResponseData(): ResponseData {
    return ResponseData(
        audioUrl = audioUrl,
        bodyLocation = bodyLocation,
        bodyParts = bodyParts.map { it.toBodyPart() },
        botName = botName,
        buttonText = buttonText,
        chatEnded = chatEnded,
        dialogue = dialogue,
        disableBodyPartIds = disableBodyPartIds,
        editable = editable,
        exercise = exercise,
        header1 = header1,
        header2 = header2,
        hint = hint,
        id = id,
        intent = intent,
        lastQuestionInGroup = lastQuestionInGroup,
        pageCaption = pageCaption,
        questionId = questionId,
        responseType = when (responseType) {
            "BUTTON" -> ResponseType.BUTTON
            "CHECKBOX" -> ResponseType.CHECKBOX
            "DATE_PICKER" -> ResponseType.DATE
            "DATETIME_PICKER" -> ResponseType.DATETIME
            else -> ResponseType.TEXT
        },
        responses = responses.map { it.toResponse() },
        saveAnswer = saveAnswer,
        selectedBodyRegions = selectedBodyRegions,
        sessionId = sessionId,
        skipped = skipped,
        tenant = tenant,
        text = text,
        title = title,
        typeId = typeId,
        typeName = typeName,
        vasQuestion = vasQuestion
    )
}
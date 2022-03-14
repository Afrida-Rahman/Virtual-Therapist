package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Constraint
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.LineType
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.constraint.AngleConstraint
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.constraint.LineConstraint

data class RemoteConstraint(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("ExerciseId")
    val exerciseId: Int,
    @SerializedName("Phase")
    val phaseId: Int,
    @SerializedName("Scale")
    val scale: String,
    @SerializedName("NoOfKeyPoints")
    val noOfKeyPoints: Int,
    @SerializedName("StartKeyPosition")
    val startKeyPosition: String,
    @SerializedName("MiddleKeyPosition")
    val middleKeyPosition: String,
    @SerializedName("EndKeyPosition")
    val endKeyPosition: String,
    @SerializedName("AngleArea")
    val angleArea: String,
    @SerializedName("LineType")
    val lineType: String,
    @SerializedName("MaxValidationValue")
    val maxValidationValue: Int,
    @SerializedName("MinValidationValue")
    val minValidationValue: Int,
    @SerializedName("Direction")
    val direction: String,
    @SerializedName("CapturedImage")
    val capturedImage: String?,
)

fun RemoteConstraint.toConstraint(): Constraint {
    return when (scale) {
        "Angle" -> AngleConstraint(
            startPointIndex = 0,
            middlePointIndex = 0,
            endPointIndex = 0,
            lineType = if (lineType == "solid") {
                LineType.SOLID
            } else {
                LineType.DASHED
            },
            minValidationValue = minValidationValue,
            maxValidationValue = maxValidationValue,
            isClockwise = angleArea == "clockwise"
        )
        else -> LineConstraint(
            startPointIndex = 0,
            endPointIndex = 0,
            lineType = if (lineType == "solid") {
                LineType.SOLID
            } else {
                LineType.DASHED
            },
            minValidationValue = minValidationValue,
            maxValidationValue = maxValidationValue
        )
    }
}
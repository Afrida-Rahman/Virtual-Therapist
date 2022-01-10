package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.constraint

import android.graphics.Paint
import com.mymedicalhub.emmavirtualtherapist.android.core.model.Point
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Draw
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.Constraint
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.LineType

data class LineConstraint(
    val startPoint: Point,
    val endPoint: Point,
    val lineType: LineType = LineType.SOLID,
    val minValidationValue: String,
    val maxValidationValue: String
) : Constraint {
    override fun draw(draw: Draw) {
        val lineStyle = if (lineType == LineType.SOLID) {
            Paint.Style.FILL
        } else {
            Paint.Style.FILL_AND_STROKE
        }
        draw.line(startPoint = startPoint, endPoint = endPoint, lineType = lineStyle)
        draw.circle(startPoint, 4f, startPoint, 360f)
        draw.circle(endPoint, 4f, endPoint, 360f)
    }
}
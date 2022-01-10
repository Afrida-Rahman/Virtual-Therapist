package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.constraint


import android.graphics.Paint
import com.mymedicalhub.emmavirtualtherapist.android.core.model.Point
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Draw
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.Constraint
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.LineType

data class AngleConstraint(
    val startPoint: Point,
    val middlePoint: Point,
    val endPoint: Point,
    val lineType: LineType = LineType.SOLID,
    val ninValidationValue: Int,
    val maxValidationValue: Int,
    val isClockwise: Boolean
) : Constraint {
    override fun draw(draw: Draw) {
        val lineStyle = if (lineType == LineType.SOLID) {
            Paint.Style.FILL
        } else {
            Paint.Style.FILL_AND_STROKE
        }
        draw.angle(
            startPoint = startPoint,
            middlePoint = middlePoint,
            endPoint = endPoint,
            lineType = lineStyle,
            clockwise = isClockwise
        )
        draw.circle(startPoint, 4f, startPoint, 360f)
        draw.circle(middlePoint, 4f, middlePoint, 360f)
        draw.circle(endPoint, 4f, endPoint, 360f)
    }
}
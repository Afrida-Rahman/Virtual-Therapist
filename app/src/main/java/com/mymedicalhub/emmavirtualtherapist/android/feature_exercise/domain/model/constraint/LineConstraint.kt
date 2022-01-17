package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.constraint

import android.graphics.Paint
import com.mymedicalhub.emmavirtualtherapist.android.core.model.Point
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Draw
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Constraint
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.LineType

data class LineConstraint(
    val startPointIndex: Int,
    val endPointIndex: Int,
    val lineType: LineType = LineType.SOLID,
    val minValidationValue: Int,
    val maxValidationValue: Int
) : Constraint {
    override fun draw(draw: Draw) {
        val lineStyle = if (lineType == LineType.SOLID) {
            Paint.Style.FILL
        } else {
            Paint.Style.FILL_AND_STROKE
        }
        val startPoint = Point(0f, 0f)
        val endPoint = Point(0f, 0f)
        draw.line(startPoint = startPoint, endPoint = endPoint, lineType = lineStyle)
        draw.circle(startPoint, 4f, startPoint, 360f)
        draw.circle(endPoint, 4f, endPoint, 360f)
    }
}
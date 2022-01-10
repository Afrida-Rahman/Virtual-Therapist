package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.constraint

import android.graphics.Color
import com.mymedicalhub.emmavirtualtherapist.android.core.model.Point
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Draw
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.Constraint

data class TextConstraint(
    val text: String,
    val position: Point,
    val textColor: Int = Color.WHITE,
    val fontSize: Float = 30f,
    val showBackground: Boolean = false,
    val backgroundColor: Int = Color.rgb(0, 0, 0)
) : Constraint {
    override fun draw(draw: Draw) {
        draw.writeText(
            text = text,
            position = position,
            textColor = textColor,
            fontSize = fontSize,
            showBackground = showBackground,
            backgroundColor = backgroundColor
        )
    }
}

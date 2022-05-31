package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.posedetector.ml_kit

import android.graphics.Bitmap
import android.graphics.Canvas

/** Draw camera image to background.  */
class CameraImageGraphic(overlay: GraphicOverlay, private val bitmap: Bitmap) :
    GraphicOverlay.Graphic(overlay) {

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, getTransformationMatrix(), null)
    }
}
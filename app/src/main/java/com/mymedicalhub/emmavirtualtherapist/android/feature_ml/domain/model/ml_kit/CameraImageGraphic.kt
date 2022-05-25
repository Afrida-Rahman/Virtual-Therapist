package com.mymedicalhub.emmavirtualtherapist.android.feature_ml.domain.model.ml_kit

import android.graphics.Bitmap
import android.graphics.Canvas
import com.mymedicalhub.emmavirtualtherapist.android.feature_ml.domain.model.ml_kit.GraphicOverlay.Graphic

/** Draw camera image to background.  */
class CameraImageGraphic(overlay: GraphicOverlay, private val bitmap: Bitmap) : Graphic(overlay) {

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, getTransformationMatrix(), null)
    }
}
package com.mymedicalhub.emmavirtualtherapist.android.feature_ml.domain.model.posedetector

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.common.MlKitException
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseViewModel

class PoseEstimation(
    private val viewModel: ExerciseViewModel
) : ImageAnalysis.Analyzer {
    override fun analyze(imageProxy: ImageProxy) {
        viewModel.needUpdateGraphicOverlayImageSourceInfo = true
        if (viewModel.needUpdateGraphicOverlayImageSourceInfo) {
            val isImageFlipped = true
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            if (rotationDegrees == 0 || rotationDegrees == 180) {
                viewModel.graphicOverlay!!.setImageSourceInfo(
                    imageProxy.width,
                    imageProxy.height,
                    isImageFlipped
                )
            } else {
                viewModel.graphicOverlay!!.setImageSourceInfo(
                    imageProxy.height,
                    imageProxy.width,
                    isImageFlipped
                )
            }
            viewModel.needUpdateGraphicOverlayImageSourceInfo = false
        }
        try {
            viewModel.imageProcessor!!.processImageProxy(
                imageProxy,
                viewModel.graphicOverlay
            )
        } catch (e: MlKitException) {
            Log.d("exceptionImage", "${e.localizedMessage}")

        }
    }
}
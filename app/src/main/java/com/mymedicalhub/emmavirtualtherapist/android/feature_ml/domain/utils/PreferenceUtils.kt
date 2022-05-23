package com.mymedicalhub.emmavirtualtherapist.android.feature_ml.domain.utils

import android.content.Context
import android.preference.PreferenceManager
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions

object PreferenceUtils {

    fun getPoseDetectorOptionsForLivePreview(context: Context): PoseDetectorOptionsBase {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val preferGPU = sharedPreferences.getBoolean("pdpg", true)
        val builder = AccuratePoseDetectorOptions.Builder()
            .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
        if (preferGPU) {
            builder.setPreferredHardwareConfigs(AccuratePoseDetectorOptions.CPU_GPU)
        }
        return builder.build()
    }
}
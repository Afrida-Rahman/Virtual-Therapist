package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlipCameraAndroid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_ml.domain.model.posedetector.PoseDetectorProcessor
import com.mymedicalhub.emmavirtualtherapist.android.feature_ml.domain.utils.PreferenceUtils

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun ExerciseScreen(
    tenant: String,
    testId: String,
    exerciseId: Int,
    navController: NavController,
    viewModel: ExerciseViewModel
) {
    viewModel.loadExerciseConstraints(tenant = tenant, testId = testId, exerciseId = exerciseId)
    val exercise = viewModel.getExercise(testId = testId, exerciseId = exerciseId)
    val localContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(localContext)
    }
    val cameraProvider = cameraProviderFuture.get()
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                localContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }
    Scaffold(
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.ic_arrow_back,
                onClickLeadingIcon = { navController.popBackStack() }
            ) {
                Text(text = exercise?.name ?: "Exercise Screen")
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (hasCamPermission) {
                if (viewModel.showFrontCamera.value) {
                    AndroidView(
                        factory = { context ->
                            val previewView = PreviewView(context)
                            val executor = ContextCompat.getMainExecutor(context)
                            previewView.removeAllViews()
                            val preview = Preview.Builder().build().also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }
                            val cameraSelector = CameraSelector.Builder()
                                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                                .build()
                            val imageAnalyzer = ImageAnalysis.Builder()
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build()
                                .apply {
                                    viewModel.analyzer.value?.let { imageAnalyzer ->
                                        setAnalyzer(executor, imageAnalyzer)
                                    }
                                }
                            
                            viewModel.imageProcessor = try {
                                val poseDetectorOptions =
                                    PreferenceUtils.getPoseDetectorOptionsForLivePreview(context = context)
                                PoseDetectorProcessor(
                                    context = context,
                                    poseDetectorOptions,
                                    showInFrameLikelihood = true,
                                    visualizeZ = false,
                                    rescaleZForVisualization = false
                                )
                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    "Can not create image processor for pose model: " + e.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                val poseDetectorOptions =
                                    PreferenceUtils.getPoseDetectorOptionsForLivePreview(context = context)
                                PoseDetectorProcessor(
                                    context = context,
                                    poseDetectorOptions,
                                    showInFrameLikelihood = true,
                                    visualizeZ = false,
                                    rescaleZForVisualization = false
                                )
                            }
                            try {
                                cameraProvider.unbindAll()
                                cameraProvider.bindToLifecycle(
                                    lifecycleOwner,
                                    cameraSelector,
                                    imageAnalyzer,
                                    preview
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            previewView
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    AndroidView(
                        factory = { context ->
                            val previewView = PreviewView(context)
                            previewView.removeAllViews()
                            val preview = Preview.Builder().build().also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }
                            try {
                                cameraProvider.unbindAll()
                                cameraProvider.bindToLifecycle(
                                    lifecycleOwner,
                                    CameraSelector.Builder()
                                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                                        .build(),
                                    preview
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            previewView
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                if (viewModel.isExerciseLoading.value) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (viewModel.showTryAgain.value) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Button(onClick = {
                            viewModel.onEvent(
                                ExerciseEvent.FetchExerciseConstraints(
                                    testId = testId,
                                    tenant = tenant,
                                    exerciseId = exerciseId
                                )
                            )
                        }) {
                            Text(text = "Try Again")
                        }
                    }
                }
                IconButton(onClick = { viewModel.onEvent(ExerciseEvent.FlipCamera) }) {
                    Icon(
                        imageVector = Icons.Default.FlipCameraAndroid,
                        contentDescription = "Camera",
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(bottom = 12.dp)
                    )
                }
            }
        }
    }
}


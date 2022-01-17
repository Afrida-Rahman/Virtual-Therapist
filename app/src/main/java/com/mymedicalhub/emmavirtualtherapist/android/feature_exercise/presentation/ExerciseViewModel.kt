package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.usecase.ExerciseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases
) : ViewModel() {
    private val _assessments = mutableStateOf<List<Assessment>>(emptyList())
    val assessments: State<List<Assessment>> = _assessments

    private val _isAssessmentLoading = mutableStateOf(false)
    val isAssessmentLoading: State<Boolean> = _isAssessmentLoading

    private val _showTryAgainButton = mutableStateOf(false)
    val showTryAgain: State<Boolean> = _showTryAgainButton

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        fetchAssessments()
    }

    fun onEvent(event: ExerciseEvent) {
        when (event) {
            is ExerciseEvent.FetchAssessments -> fetchAssessments()
        }
    }

    private fun fetchAssessments() {
        viewModelScope.launch {
            exerciseUseCases.fetchAssessments("emma", "0fc2e143-5abd-eb11-8236-000d3a33d0fd")
                .onEach {
                    when (it) {
                        is Resource.Error -> {
                            _showTryAgainButton.value = true
                            _isAssessmentLoading.value = false
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
                                    it.message ?: "Failed to load assessments! Please try again."
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _isAssessmentLoading.value = true
                            _showTryAgainButton.value = false
                            _eventFlow.emit(UIEvent.ShowSnackBar("Loading data from API"))
                        }
                        is Resource.Success -> {
                            _showTryAgainButton.value = false
                            _isAssessmentLoading.value = false
                            _assessments.value = it.data ?: emptyList()
                            _eventFlow.emit(UIEvent.ShowSnackBar("Successfully loaded data from API"))
                        }
                    }
                }.launchIn(this)
        }
    }
}
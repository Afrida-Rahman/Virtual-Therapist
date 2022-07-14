package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ChatPayload
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ChatResponse
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.toAnswer
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.usecase.ChatUseCases
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    preferences: SharedPreferences,
    private val chatUseCases: ChatUseCases
) : ViewModel() {
    private val _chatResponses = mutableStateOf<List<ChatResponse>>(emptyList())
    val chatResponses: State<List<ChatResponse>> = _chatResponses

    private val _bodyRegions = mutableStateOf("")
    val bodyRegions: State<String> = _bodyRegions

    private val _testId = mutableStateOf("")
    val testId: State<String> = _testId

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val chatPayload = mutableStateOf<ChatPayload?>(null)

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val patientData = Utilities.getPatient(preferences = preferences)
        val currentTime = Utilities.currentTime()
        chatPayload.value = ChatPayload(
            botName = "MSK_BOT",
            tenant = patientData.tenant,
            email = patientData.email,
            intent = "initial",
            patientId = "411a6950-b0b3-eb11-8236-000d3a5a872e",
            demographicsCompleted = true,
            providerId = "00000000-0000-0000-0000-000000000000",
            loggedTime = currentTime.subSequence(
                startIndex = 0, endIndex = currentTime.length - 2
            ).toString()
        )
    }

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.InitializeBotInformation -> {
                chatPayload.value = chatPayload.value?.copy(
                    botName = event.botCodeName
                )
            }
            is ChatEvent.MultiselectQuestionSubmitted -> {
                if (event.responses.isEmpty()) {
                    viewModelScope.launch {
                        _eventFlow.emit(UIEvent.ShowSnackBar("You need to select at least one option"))
                    }
                } else {
                    handleReply(
                        questionId = event.questionId,
                        responses = event.responses
                    )
                }
            }
            is ChatEvent.ResponseButtonClicked -> {
                handleReply(
                    questionId = event.questionId,
                    responses = listOf(event.response)
                )
            }
            is ChatEvent.TextMessageEntered -> {
                handleReply(
                    questionId = event.questionId,
                    responses = listOf(
                        Response(
                            id = 0,
                            name = event.message
                        )
                    )
                )
            }
        }
    }

    private fun getBodyRegions(): List<String> {
        val chatResponse = chatResponses.value.find {
            it.responseData.questionId == Questions.WELCOME.id
        }
        return chatResponse?.responseData?.answers?.map {
            it.name
        } ?: emptyList()
    }

    private fun handleReply(questionId: Int, responses: List<Response>) {
        val chatResponse = _chatResponses.value.find {
            it.responseData.questionId == questionId
        }
        val alreadyExists = chatResponse != null && chatResponse.responseData.answers.isNotEmpty()

        if (alreadyExists) {
            updateResponse(questionId = questionId, answers = responses)
        } else {
            sendReply(questionId = questionId, responses = responses)
        }
    }

    private fun sendReply(questionId: Int, responses: List<Response>) {
        if (!isLoading.value) {
            _isLoading.value = true
            viewModelScope.launch {
                chatPayload.value?.let { payload ->
                    chatUseCases.sendChatReply(
                        chatPayload = payload.copy(
                            questionId = questionId,
                            answers = responses.map { it.toAnswer() }
                        )
                    ).onEach {
                        when (it) {
                            is Resource.Error -> {
                                _isLoading.value = false
                                _eventFlow.emit(
                                    UIEvent.ShowToastMessage(
                                        it.message ?: "Unknown error occurred"
                                    )
                                )
                            }
                            is Resource.Loading -> {
                                _isLoading.value = true
                            }
                            is Resource.Success -> {
                                _isLoading.value = false
                                addResponse(
                                    previousResponses = responses,
                                    chatResponse = it.data
                                )
                                if (questionId == Questions.WELCOME.id) {
                                    val bodyRegions = buildString {
                                        getBodyRegions().forEach { bodyRegion ->
                                            append("$bodyRegion, ")
                                        }
                                    }
                                    _bodyRegions.value = bodyRegions.substring(
                                        startIndex = 0,
                                        endIndex = bodyRegions.length - 2
                                    )
                                }
                            }
                        }
                    }.launchIn(this)
                }
            }
        }
    }

    private fun addResponse(previousResponses: List<Response>, chatResponse: ChatResponse?) {
        chatResponse?.let { response ->
            if (chatResponses.value.isNotEmpty()) {
                val lastIndex = _chatResponses.value.size - 1
                _chatResponses.value[lastIndex].responseData.answers = previousResponses
            }
            _chatResponses.value = chatResponses.value + response
            chatPayload.value = chatPayload.value?.copy(
                intent = response.responseData.intent,
                sessionId = response.responseData.sessionId
            )
        }
    }

    private fun updateResponse(questionId: Int, answers: List<Response>) {
        for (index in 0 until _chatResponses.value.size) {
            val responseData = _chatResponses.value[index].responseData
            if (responseData.questionId == questionId) {
                _chatResponses.value[index].responseData.answers = answers
                Log.d("ResponseUpdate", "Updating: $questionId\tResponses: $answers")
                break
            }
        }
    }
}
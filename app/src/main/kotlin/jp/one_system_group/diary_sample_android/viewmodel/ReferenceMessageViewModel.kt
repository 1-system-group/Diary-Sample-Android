package jp.one_system_group.diary_sample_android.viewmodel

import androidx.compose.material.contentColorFor
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.model.ReferenceMessage
import jp.one_system_group.diary_sample_android.repository.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReferenceMessageViewModel @Inject constructor(
    private val repository: ReferenceMessageRepository,
    private val module: GetReferenceMessageUseCase,
    private val useCase: GetReferenceMessageUseCase
): ViewModel() {
    val uiState: MutableState<UiState> = mutableStateOf(UiState.Loading)
    var referenceMessage : ReferenceMessage = ReferenceMessage("","")

    init {
        getReferenceMessage()
    }

    private fun getReferenceMessage() {
        viewModelScope.launch {
            useCase.invoke().collect {
                uiState.value = UiState.Success(it)
                referenceMessage = it
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val msg: ReferenceMessage) : UiState()
        data class Failure(val error: String) : UiState()
    }
}
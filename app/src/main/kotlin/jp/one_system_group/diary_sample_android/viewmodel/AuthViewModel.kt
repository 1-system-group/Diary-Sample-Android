package jp.one_system_group.diary_sample_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.one_system_group.diary_sample_android.infra.TokenManager
import jp.one_system_group.diary_sample_android.repository.AuthRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private var deviceId: String = UUID.randomUUID().toString()
    private val _resultFlow = MutableStateFlow<String?>(null)
    val resultFlow: StateFlow<String?> = _resultFlow

    fun login(email: String, password: String): Job {
        return viewModelScope.launch {
            val token = repository.login(email, password, deviceId)
            if (token != null) {
                // SharedPreferencesに保存する
                tokenManager.saveToken(token)
                _resultFlow.value = token
            }
        }
    }
}
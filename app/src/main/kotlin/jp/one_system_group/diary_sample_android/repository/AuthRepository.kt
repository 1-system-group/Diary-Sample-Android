package jp.one_system_group.diary_sample_android.repository

import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.model.Auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AuthRepository {
    suspend fun login(email: String, password: String, deviceId: String): String?
}

class AuthRepositoryImpl @Inject constructor(
    private val webService: WebService,
) : AuthRepository {
    override suspend fun login(email: String, password: String, deviceId: String): String? {
        val response =
            withContext(Dispatchers.IO) {
                webService.login(Auth(email, password, deviceId))
            }
        return if (response.isSuccessful) {
            response.body()
        } else {
            // TODO: ログイン失敗時の処理
            null
        }
    }
}
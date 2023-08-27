package jp.one_system_group.diary_sample_android.api

import jp.one_system_group.diary_sample_android.infra.TokenManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class AuthorizationInterceptor(private val tokenManager: TokenManager) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            // トークンを取得
            val token = tokenManager.getToken()
            val request: Request = chain.request()

            // トークンがなければ、何もしない
            if (token.isNullOrEmpty()) return chain.proceed(request)

            // ヘッダにHEADER_PLACEHOLDERがなければ、何もしない
            if (request.header(HEADER_NAME) == null) return chain.proceed(request)
            if (HEADER_VALUE != request.header(HEADER_NAME)) return chain.proceed(request)

            // "Authorization"ヘッダをtokenに設定
            val newRequest = request.newBuilder()
                .header(HEADER_NAME, HEADER_VALUE_BEARER + token)
                .build()
            chain.proceed(newRequest)
        } catch (e: Exception) {
            chain.proceed(chain.request())
        }
    }

    companion object {
        private const val HEADER_NAME = "Authorization"
        private const val HEADER_VALUE = "DUMMY"
        private const val HEADER_VALUE_BEARER = "bearer"
        const val HEADER_PLACEHOLDER = "$HEADER_NAME: $HEADER_VALUE"
    }
}
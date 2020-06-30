package com.br.bora.app.services.config

import android.util.Log
import com.br.bora.app.services.EventService
import com.br.bora.app.services.UploadService
import com.br.bora.app.services.UserService
import com.br.bora.app.services.ZipCodeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.apache.http.conn.ssl.SSLSocketFactory.SSL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*


object RetrofitInitializer {
    //    private const val BASE_URL: String = "https://3.223.69.85/api/"
//    private const val BASE_URL: String = "http://192.168.15.14:5555/"
//    private const val BASE_URL: String = "http://192.168.99.113/api/"
    private const val BASE_URL: String = "https://3.223.69.85/api/"


    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient? {
        return try {
            val trustAllCerts: Array<TrustManager> = arrayOf(
                object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<out X509Certificate>?,
                        authType: String?
                    ) {
                        Log.d("checkClientTrusted", chain.toString())
                    }

                    override fun checkServerTrusted(
                        chain: Array<out X509Certificate>?,
                        authType: String?
                    ) {
                        Log.d("checkServerTrusted", chain.toString())
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
            )
            val sslContext: SSLContext = SSLContext.getInstance(SSL)
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
            builder.addInterceptor(interceptor)
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getUnsafeOkHttpClient()!!)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val eventService: EventService = initRetrofit().create(EventService::class.java)
    val userService: UserService = initRetrofit().create(UserService::class.java)
    val zipCodeService: ZipCodeService = initRetrofit().create(ZipCodeService::class.java)
    val uploadService: UploadService = initRetrofit().create(UploadService::class.java)
}

/*
private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
 */
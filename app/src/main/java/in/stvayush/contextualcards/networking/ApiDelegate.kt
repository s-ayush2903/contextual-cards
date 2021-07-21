package `in`.stvayush.contextualcards.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiDelegate {
    val apiService: ApiService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofitClient = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
        apiService = retrofitClient.create(ApiService::class.java)
    }

    companion object {
        private const val BASE_URL = "http://www.mocky.io/v3/"
        private var apiDelegate: ApiDelegate? = null
        val instance: ApiDelegate
            get() {
                if (apiDelegate == null) apiDelegate = ApiDelegate()
                return apiDelegate as ApiDelegate
            }
    }
}

package `in`.stvayush.contextualcards.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A `singleton`(not exactly, but equivalent) that instantiates retrofit and other related
 * networking services that assist in fetching, serializing, deserializing & Observing the data in
 * the application. By this singleton it is made sure that we do not create multiple instances of
 * networking services & retrofit which reduces memory usage and improves performance as networking
 * sits at the core of the application and instantiating core-services for each use will definitely
 * result in increment in greater consumption of resources
 */
class ApiDelegate {
  val apiService: ApiService

  init {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
    val retrofitClient =
      Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    apiService = retrofitClient.create(ApiService::class.java)
  }

  companion object {
    private const val BASE_URL = "http://www.mocky.io/v3/"
    private var apiDelegate: ApiDelegate? = null

    // This getter is called each time `instance` is called
    val instance: ApiDelegate
      get() {
        // use existing instance(if exists) rather creating a new one
        if (apiDelegate == null) apiDelegate = ApiDelegate()
        return apiDelegate as ApiDelegate
      }
  }
}

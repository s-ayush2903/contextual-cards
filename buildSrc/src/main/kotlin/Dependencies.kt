object Dependencies {

  const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
  const val constraintLayout =
    "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

  const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
  const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.stdlib}"

  const val coreRetrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

  const val httpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.httpInterceptor}"

  const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
  const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
  const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
  const val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"
  const val sharedPreferencesKtx = "androidx.preference:preference-ktx:${Versions.preferences}"
  const val shimmerRecyclerView =
    "com.github.sharish:ShimmerRecyclerView:${Versions.shimmerRecyclerView}"
  const val swipeRefreshLayout =
    "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

  const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
  const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"

  const val junit = "junit:junit:${Versions.junit}"
  const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
  const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
  const val androidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"

  object Versions {
    const val appCompat = "1.3.0"
    const val constraintLayout = "2.0.4"

    const val androidxCoreKtx = "1.6.0"
    const val stdlib = "1.4.10"
    const val rxJava = "3.0.6"
    const val rxAndroid = "3.0.0"
    const val glide = "4.11.0"
    const val preferences = "1.1.1"
    const val shimmerRecyclerView = "v1.3"
    const val swipeRefreshLayout = "1.1.0"
    const val extJunit = "1.1.2"
    const val junit = "4.13"
    const val materialDesign = "1.4.0"

    const val retrofit = "2.9.0"
    const val httpInterceptor = "4.9.0"
    const val espresso = "3.3.0"
  }
}

/**
 * To define plugins
 */
/*object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}*/

/**
 * To define dependencies
 */
object Dependencies {
    //MAIN
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val kotlin = "org.jetbrains.kotlin.android:${Versions.kotlin}"
    val materialDesign = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    //LIFECYCLE
    val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidxLifecycle}"
    val lifecycleCoroutines = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.kotlinxCoroutines}"
    //HTTP
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    //LOTTIE
    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    //IMAGE
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    //ROOM
    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    //NAVIGATION
    val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigationFeature = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    //MAPS
    val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"
    //HILT
    val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    val daggerCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"


    //TEST
    val jUnit = "junit:junit:${Versions.jUnit}"
    val testExt = "androidx.test.ext:junit:${Versions.androidTestRunner}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
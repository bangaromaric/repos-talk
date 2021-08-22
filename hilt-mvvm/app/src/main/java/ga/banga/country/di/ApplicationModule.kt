package ga.banga.country.di

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ga.banga.country.BuildConfig
import ga.banga.country.data.api.ApiHelper
import ga.banga.country.data.api.ApiHelperImpl
import ga.banga.country.data.api.ApiService
import ga.banga.country.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

  @Provides
  fun provideBaseUrl(): String = BASE_URL

  @Provides
  @Singleton
  fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient
      .Builder()
      .addInterceptor(loggingInterceptor)
      .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
      .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
      .build()
  } else {
    OkHttpClient
      .Builder()
      .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
      .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
  ): Retrofit =
    Retrofit.Builder()
      .addConverterFactory(MoshiConverterFactory.create())
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .build()

  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

  @Provides
  @Singleton
  fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

  @Provides
  @Singleton
  fun provideImageLoader(@ApplicationContext mContext: Context) =
    ImageLoader.Builder(mContext).componentRegistry {
      add(SvgDecoder(mContext))
    }.build()

  @Provides
  @Singleton
  fun provideImageRequestBuilder(@ApplicationContext mContext: Context) =
    ImageRequest.Builder(mContext)

}
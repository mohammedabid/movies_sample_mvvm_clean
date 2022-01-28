package com.example.sampleapplistdetail.injection

import android.app.Application
import com.example.sampleapplistdetail.common.Constants
import com.example.sampleapplistdetail.data.remote.NetworkApi
import com.example.sampleapplistdetail.data.repository.GetMoviesDetailsImpl
import com.example.sampleapplistdetail.data.repository.GetMoviesListImpl
import com.example.sampleapplistdetail.domain.repository.GetMoviesDetailRepository
import com.example.sampleapplistdetail.domain.repository.GetMoviesListRepository
import com.example.sampleapplistdetail.domain.use_case.GetMoviesDetailUseCase
import com.example.sampleapplistdetail.domain.use_case.GetMoviesListUseCase
import com.example.sampleapplistdetail.presentation.movies_detail.MoviesDetailsViewModel
import com.example.sampleapplistdetail.presentation.movies_list.MoviesListViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }

    single { provideUserApi(get()) }
}

val repositoryModule = module {
    fun provideGetMoviesListRepository(networkApi: NetworkApi): GetMoviesListRepository {
        return GetMoviesListImpl(networkApi)
    }

    fun provideGetMoviesDetailsRepository(networkApi: NetworkApi): GetMoviesDetailRepository {
        return GetMoviesDetailsImpl(networkApi)
    }

    single { provideGetMoviesListRepository(get()) }
    single { provideGetMoviesDetailsRepository(get()) }
}

val useCaseModule = module {
    fun provideGetMoviesListUseCase(getMoviesListRepository: GetMoviesListRepository): GetMoviesListUseCase {
        return GetMoviesListUseCase(getMoviesListRepository)
    }

    fun provideGetMoviesDetailsUseCase(getMoviesDetailsRepository: GetMoviesDetailRepository): GetMoviesDetailUseCase {
        return GetMoviesDetailUseCase(getMoviesDetailsRepository)
    }

    single { provideGetMoviesListUseCase(get()) }
    viewModel { MoviesListViewModel(get()) }
    single { provideGetMoviesDetailsUseCase(get()) }
    viewModel { MoviesDetailsViewModel(get()) }

}


val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }


    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(150, TimeUnit.SECONDS)
            .connectTimeout(150, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val request: Request = original.newBuilder()
                    .method(original.method, original.body)
                    .addHeader("api_key", Constants.ServiceConstants.API_KEY)
                    .build()
                chain.proceed(request);
            }
        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }


    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.ServiceConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient() }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}
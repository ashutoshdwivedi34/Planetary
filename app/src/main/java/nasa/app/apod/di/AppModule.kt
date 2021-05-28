package nasa.app.apod.di

import android.content.Context

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import nasa.app.apod.data.remote.ApodRemoteDataSource
import nasa.app.apod.data.remote.ApodService
import nasa.app.apod.data.local.AppDatabase
import nasa.app.apod.utils.NetworkHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/planetary/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideApodService(retrofit: Retrofit): ApodService = retrofit.create(ApodService::class.java)

    @Singleton
    @Provides
    fun provideApodRemoteDataSource(apodService: ApodService) = ApodRemoteDataSource(apodService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Provides
    fun provideNetworkHelper(@ApplicationContext appContext: Context) = NetworkHelper(appContext)

    @Singleton
    @Provides
    fun provideApodDao(db: AppDatabase) = db.apodDao()


}
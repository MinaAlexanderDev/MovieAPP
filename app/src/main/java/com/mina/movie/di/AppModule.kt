package com.mina.movie.di

import com.mina.movie.api.ServiceAPI
import com.mina.movie.remotedata.RemoteRepository
import com.mina.movie.remotedata.RemoteRepositoryImp
import com.mina.movie.repository.MoviesDataRepository
import com.mina.movie.repository.MoviesDataRepositoryImp
import com.mina.movie.utilies.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ServiceAPI =
        retrofit.create(ServiceAPI::class.java)

    @Module
    @InstallIn(ActivityComponent::class)
    abstract class RemoteRepositoryModule {

        @Binds
        abstract fun bindRemoteRepository(
            remoteRepositoryImp: RemoteRepositoryImp
        ): RemoteRepository
    }

    @Module
    @InstallIn(ActivityComponent::class)
    abstract class ItemRepositoryInt {

        @Binds
        abstract fun bindItemRepository(
            ItemRepositoryImp: MoviesDataRepositoryImp
        ): MoviesDataRepository
    }

}
package fr.ekito.myweatherapp.di

import android.arch.persistence.room.Room
import fr.ekito.myweatherapp.data.datasource.room.WeatherDatabase
import fr.ekito.myweatherapp.data.repository.WeatherRepository
import fr.ekito.myweatherapp.data.repository.WeatherRepositoryImpl
import fr.ekito.myweatherapp.util.rx.ApplicationSchedulerProvider
import fr.ekito.myweatherapp.util.rx.SchedulerProvider
import fr.ekito.myweatherapp.view.detail.DetailViewModel
import fr.ekito.myweatherapp.view.splash.SplashViewModel
import fr.ekito.myweatherapp.view.weather.WeatherViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

/**
 * App Components
 */
val weatherAppModule = applicationContext {

    viewModel { DetailViewModel(get(), get()) }

    // ViewModel for Search View
    viewModel { SplashViewModel(get(), get()) }

    // WeatherViewModel declaration for Weather View components
    viewModel { WeatherViewModel(get(), get()) }

    // Weather Data Repository
    bean { WeatherRepositoryImpl(get(), get()) as WeatherRepository }

    // Rx Schedulers
    bean { ApplicationSchedulerProvider() as SchedulerProvider }

    // Room Database
    bean {
        Room.databaseBuilder(androidApplication(), WeatherDatabase::class.java, "weather-db")
            .build()
    }

    // Expose WeatherDAO directly
    bean { get<WeatherDatabase>().weatherDAO() }
}

// Gather all app modules
val onlineWeatherApp = listOf(weatherAppModule, remoteDatasourceModule)
val offlineWeatherApp = listOf(weatherAppModule, localAndroidDatasourceModule)
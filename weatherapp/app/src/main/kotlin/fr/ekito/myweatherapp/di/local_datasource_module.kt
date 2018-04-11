package fr.ekito.myweatherapp.di

import fr.ekito.myweatherapp.data.datasource.webservice.WeatherWebDatasource
import fr.ekito.myweatherapp.data.datasource.local.AndroidJsonReader
import fr.ekito.myweatherapp.data.datasource.webservice.local.JsonReader
import fr.ekito.myweatherapp.data.datasource.webservice.local.LocalFileDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

/**
 * Local Json Files Datasource
 */
val localAndroidDatasourceModule = applicationContext {
    bean { AndroidJsonReader(androidApplication()) as JsonReader }
    bean { LocalFileDataSource(get(), true) as WeatherWebDatasource }
}
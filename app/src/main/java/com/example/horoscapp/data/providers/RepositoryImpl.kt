package com.example.horoscapp.data.providers

import android.util.Log
import com.example.horoscapp.data.providers.network.HoroscopeApiService
import com.example.horoscapp.domain.model.PredictionModel
import com.example.horoscapp.domain.model.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("hola", "Ha ocurrido un error ${it.message}") }
        return null
    }
}
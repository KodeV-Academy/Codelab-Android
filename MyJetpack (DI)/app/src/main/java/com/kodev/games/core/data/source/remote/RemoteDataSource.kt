package com.kodev.games.core.data.source.remote

import android.util.Log
import com.kodev.games.core.data.source.remote.api.ApiService
import com.kodev.games.core.data.source.remote.response.ResponseGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

        suspend fun getGames(): Flow<ApiResponse<ResponseGame>> {
            return flow {
                try {
                    val response = apiService.getGames("d084045ca6164bbeb97021752a930416", "20")
                    if (response.results.isNotEmpty()){
                        emit(ApiResponse.Success(response))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                } catch (e: Exception) {
                    emit(ApiResponse.Error(e.toString()))
                    Log.e(TAG, e.toString())
                }
            }.flowOn(Dispatchers.IO)
        }
}
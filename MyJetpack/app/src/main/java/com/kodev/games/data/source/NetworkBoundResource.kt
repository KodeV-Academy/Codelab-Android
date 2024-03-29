package com.kodev.games.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.kodev.games.data.source.remote.ApiResponse
import com.kodev.games.utils.AppExecutors

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutor: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    protected fun onFetchFailed() { }

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.Loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiResponse.Success -> {
                    mExecutor.diskIO().execute {
                        saveCallResult(response.data)
                        mExecutor.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.Success(newData)
                            }
                        }
                    }
                }
                is ApiResponse.Empty -> {
                    mExecutor.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = Resource.Success(newData)
                        }
                    }
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.Error(newData, response.errorMessage)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result

    init {
        result.value = Resource.Loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.Success(newData)
                }
            }
        }
    }

}
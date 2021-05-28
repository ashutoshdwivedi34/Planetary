package nasa.app.apod.data.repository

import nasa.app.apod.data.remote.ApodRemoteDataSource
import nasa.app.apod.data.local.ApodDao
import nasa.app.apod.utils.performGetOperation
import javax.inject.Inject

class ApodRepository @Inject constructor(
    private val remoteDataSource: ApodRemoteDataSource,
    private val localDataSource: ApodDao
) {

    fun getCharacter(api_key: String) = performGetOperation(
            databaseQuery = { localDataSource.getLastInsertedApodData() },
            networkCall = { remoteDataSource.getApodData(api_key) },
            saveCallResult = { localDataSource.insert(it) }
    )

}
package nasa.app.apod.data.remote

import javax.inject.Inject

class ApodRemoteDataSource @Inject constructor(
    private val apodService: ApodService
): BaseDataSource() {

    suspend fun getApodData(api_key:String) = getResult { apodService.getApodData(api_key) }
}
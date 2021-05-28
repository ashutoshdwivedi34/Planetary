package nasa.app.apod.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import nasa.app.apod.data.entities.ApodData
import nasa.app.apod.data.repository.ApodRepository
import nasa.app.apod.utils.NetworkHelper
import nasa.app.apod.utils.Resource

@RequiresApi(Build.VERSION_CODES.M)
class APODViewModel @ViewModelInject constructor(private val repository: ApodRepository, networkHelper: NetworkHelper)  : ViewModel(){

    private val _networkConnected= MutableLiveData<Boolean>()
    val networkConnected: LiveData<Boolean> = _networkConnected

    init {
        if (!networkHelper.isNetworkConnected()){
            _networkConnected.value = false
        }
    }
    private val api_key = MutableLiveData<String>()

    private val _apodData = api_key.switchMap { id ->
        repository.getCharacter(id)
    }
    val apodData: LiveData<Resource<ApodData>> = _apodData






    fun start(id: String) {
        api_key.value = id
    }


}
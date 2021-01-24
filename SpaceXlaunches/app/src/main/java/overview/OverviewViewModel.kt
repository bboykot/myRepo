package overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import db.SpaceDao
import kotlinx.coroutines.launch
import repository.SpaceRepository

class OverviewViewModel (val dataSource: SpaceDao, application: Application): AndroidViewModel(application) {

    //show network error
    private val _networkResponse = MutableLiveData<String>()
    val networkResponse: LiveData<String>
        get()= _networkResponse


    private val spaceRepository = SpaceRepository(dataSource)

    val properties = spaceRepository.properties

    init{
        getSpaceXLaunchesProperties()

    }

    private fun getSpaceXLaunchesProperties(){
        viewModelScope.launch {
            try {
                spaceRepository.refreshLaunches()
                _networkResponse.value="ok"
            }
            catch (e: Exception){_networkResponse.value="Failure: ${e.message}"}
        }
    }
}
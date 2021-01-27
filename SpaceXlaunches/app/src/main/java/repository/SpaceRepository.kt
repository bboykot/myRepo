package repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import db.SpaceDao
import db.asDdomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import network.SpaceXApi
import network.asDatabaseModel

class SpaceRepository(private val dataSource: SpaceDao) {

suspend fun refreshLaunches(){
    withContext(Dispatchers.IO){
        //get network data
        val launchList = SpaceXApi.retrofitservice.getProperties()
        //mapping network data to entity
        dataSource.insertAll(launchList.asDatabaseModel())
    }
}
    //mapping entity data to data class which using in UI
    val properties: LiveData<List<Models>> = Transformations.map(dataSource.getLaunches()){
        it.asDdomainModel()}
}

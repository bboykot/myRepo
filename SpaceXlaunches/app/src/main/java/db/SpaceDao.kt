package db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SpaceDao {

    @Update
    suspend fun update (spaceEntity: SpaceEntity)

    @Query("select * from launches")
    fun getLaunches(): LiveData<List<SpaceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( launches: List<SpaceEntity>)
}
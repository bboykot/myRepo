package db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SpaceDao {
    @Insert
    suspend fun insert (spaceEntity: SpaceEntity)

    @Update
    suspend fun update (spaceEntity: SpaceEntity)

    @Query("Select * from launches")
     suspend fun getAll(): List<SpaceEntity>

    @Query("Select * from launches")
    suspend fun getData(): SpaceEntity?

    @Query("select * from launches order by launchDate desc")
    fun getLaunches(): LiveData<List<SpaceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( launches: List<SpaceEntity>)
}
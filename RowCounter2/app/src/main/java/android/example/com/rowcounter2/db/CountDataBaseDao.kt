package android.example.com.rowcounter2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CountDataBaseDao {

    @Insert
    suspend fun insert(myentity: MyEnt)

    @Update
     suspend fun update(myentity: MyEnt)

    @Query("Select * from my_table1 where countId=:key")
    suspend fun get(key: Long): MyEnt?

    @Query("Select * from my_table1 ORDER BY countId DESC LIMIT 1")
    suspend fun getCount(): MyEnt?

    @Query("Select * from my_table1")
    fun getAllCount(): LiveData<List<MyEnt>>

    @Query("DELETE FROM my_table1")
    suspend fun clear()
}

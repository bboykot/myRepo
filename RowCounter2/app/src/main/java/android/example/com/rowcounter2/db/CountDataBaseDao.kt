package android.example.com.rowcounter2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CountDataBaseDao {

    @Insert
    suspend fun insert(myentity: MyEnt) //инсертим сущность в БД, так что сущность является входным параметром
                                //вернее входной параметр имеет тип сущности

    @Update
     suspend fun update(myentity: MyEnt)

    @Query("Select * from my_table1 where countId=:key")
    suspend fun get(key: Long): MyEnt? //здесь мы селектим значения таблицы по ид, но делаем это по ключу и возвращаем строку - запись в сущности

    @Query("Select * from my_table1 ORDER BY countId DESC LIMIT 1")
    suspend fun getCount(): MyEnt?

    @Query("Select * from my_table1")
    fun getAllCount(): LiveData<List<MyEnt>> //здесь мы тоже селектим всю таблицу, но функция сохраняет все записи из таблицы в лайвдата объект, списокм

    @Query("DELETE FROM my_table1")
    suspend fun clear()
}

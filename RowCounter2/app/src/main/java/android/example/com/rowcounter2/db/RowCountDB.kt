package android.example.com.rowcounter2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//делаем аннотацию бд, указываем нашу сущность, номер версии схемы и то что мы ее не бекапим
@Database(entities = [MyEnt::class], version = 1, exportSchema = false)
abstract class RowCountDB : RoomDatabase() {

    abstract val countDataBaseDao: CountDataBaseDao

    companion object {

        @Volatile //синглтон, чтобы предотвратить одновременное создание нескольких экземпляров БД
        private var INSTANCE: RowCountDB? = null

        fun getInstance(context: Context): RowCountDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RowCountDB::class.java,
                        "my_count_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SpaceEntity::class], version = 5, exportSchema = false)
abstract class SpaceDatabase: RoomDatabase() {

    abstract val spaceDao: SpaceDao

    companion object {

        @Volatile
        private var INSTANCE: SpaceDatabase? = null

        fun getInstance(context: Context): SpaceDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SpaceDatabase::class.java,
                        "spacex_db1"
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
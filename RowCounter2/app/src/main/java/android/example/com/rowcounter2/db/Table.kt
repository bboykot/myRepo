package android.example.com.rowcounter2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="my_table1")
data class MyEnt (
    @PrimaryKey(autoGenerate = true)
    var countId: Long =0L,

    @ColumnInfo(name ="my_count")
    var countNum: Int=2

)




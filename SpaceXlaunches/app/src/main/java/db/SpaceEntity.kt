package db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import repository.Models


@Entity(tableName="launches")
data class SpaceEntity (

    @PrimaryKey
    var missionName: String = "",

    @ColumnInfo(name ="numb")
    var flyNumber : Int = 0,

    @ColumnInfo(name ="patch")
    var missionPatch: String? = "",

    @ColumnInfo(name = "video")
    var videoLink : String? = "",

    @ColumnInfo(name = "articleLink")
    var articleLink : String? = "",

    @ColumnInfo(name = "wikipediaLink")
    var wikipediaLink : String? = "",

    @ColumnInfo(name = "launchDate")
    var launchDate : String? = "",

    @ColumnInfo(name = "details")
    var details: String? =""

)

//mapping data to data class for UI
fun List<SpaceEntity>.asDdomainModel() = map {
    Models(
        flightNumber = it.flyNumber,
        missionName = it.missionName,
        launchDate = it.launchDate,
        details = it.details,
        Models.ModelsLinks(mission_patch = it.missionPatch,
                           videoLink = it.videoLink,
                           articleLink = it.articleLink,
                           wikipediaLink = it.wikipediaLink)
    )
}
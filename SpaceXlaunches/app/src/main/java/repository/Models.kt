package repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Models(val flightNumber: Int,
                  val missionName: String,
                  val launchDate : String?,
                  val details: String?,
                  val modelsLinks: ModelsLinks
): Parcelable {

    //cut data to yyyy.mm.dd
    var launchDateConverted: String= convert()
    fun convert ():String{
        var cycle: Int = 0
        var myDate: String = ""
        for(a in launchDate!!)
        {cycle = ++cycle
        myDate = myDate + a
        if (cycle==10) break}
        return myDate
    }
    @Parcelize
    data class ModelsLinks(val mission_patch: String?,
                           val videoLink: String?,
                           val articleLink : String?,
                           val wikipediaLink: String?): Parcelable
}
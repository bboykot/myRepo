package repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Parcelize
data class Models(val flightNumber: Int,
                  val missionName: String,
                  val launchDate : String?,
                  val details: String?,
                  val modelsLinks: ModelsLinks
): Parcelable {

    //format date to  "dd, MM, yyyy"
    val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
    val myDate = LocalDate.parse(launchDate, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("dd, MM, yyyy", Locale.US)
    var launchDateConverted: String= outputFormatter.format(myDate)

    @Parcelize
    data class ModelsLinks(val mission_patch: String?,
                           val videoLink: String?,
                           val articleLink : String?,
                           val wikipediaLink: String?): Parcelable
}
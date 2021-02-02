package network

import com.squareup.moshi.Json
import db.SpaceEntity

data class SpaceXProperty(
   @Json (name = "flight_number") val flyNumb: Int,
   @Json (name = "launch_year") val launchYear: Int,
   @Json (name = "mission_name") val missionName: String,
   @Json (name = "launch_date_local") val launchDate: String?,
   val details: String?,
   val links: Links
){

   data class Links(
      @Json (name = "mission_patch") val missionPatch: String?,
      @Json (name = "video_link") val videoLink: String?,
      @Json (name = "article_link") val articleLink: String?,
      @Json (name = "wikipedia") val wikipediaLink: String?)

}

//mapping data to database class
fun List<SpaceXProperty>.asDatabaseModel() = map{
   SpaceEntity(
      flyNumber = it.flyNumb,
      missionName = it.missionName,
      missionPatch = it.links.missionPatch,
      videoLink = it.links.videoLink,
      articleLink = it.links.articleLink,
      wikipediaLink = it.links.wikipediaLink,
      launchDate = it.launchDate,
      details = it.details
   )
}
package network

import db.SpaceEntity

data class SpaceXProperty(
   val  flight_number: Int,
   val launch_year: Int,
   val mission_name: String,
   val launch_date_local: String?,
   val details: String?,
   val links: Links
){

   data class Links(val mission_patch: String?,
                    val video_link: String?,
                    val article_link: String?,
                    val wikipedia: String?)


}

//mapping data to database class
fun List<SpaceXProperty>.asDatabaseModel() = map{
   SpaceEntity(
      flyNumber = it.flight_number,
      missionName = it.mission_name,
      missionPatch = it.links.mission_patch,
      videoLink = it.links.video_link,
      articleLink = it.links.article_link,
      wikipediaLink = it.links.wikipedia,
      launchDate = it.launch_date_local,
      details = it.details
   )
}
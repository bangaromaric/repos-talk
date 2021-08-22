package ga.banga.country.data.models.states

import com.squareup.moshi.Json

data class Data(
  @Json(name = "iso3")
  val iso3: String,
  @Json(name = "name")
  val name: String,
  @Json(name = "states")
  val states: List<State>
)
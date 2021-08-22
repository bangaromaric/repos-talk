package ga.banga.country.data.models.states

import com.squareup.moshi.Json

data class State(
  @Json(name = "name")
  val name: String,
  @Json(name = "state_code")
  val stateCode: String
)
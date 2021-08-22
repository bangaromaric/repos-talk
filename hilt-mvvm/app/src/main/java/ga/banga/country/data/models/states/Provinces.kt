package ga.banga.country.data.models.states

import com.squareup.moshi.Json

data class Provinces(
  @Json(name = "data")
  val `data`: Data,
  @Json(name = "error")
  val error: Boolean,
  @Json(name = "msg")
  val msg: String
)
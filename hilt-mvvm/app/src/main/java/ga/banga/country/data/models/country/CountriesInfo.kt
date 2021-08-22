package ga.banga.country.data.models.country

import com.squareup.moshi.Json

data class CountriesInfo(
  @Json(name = "data")
  val `data`: List<Data>,
  @Json(name = "error")
  val error: Boolean,
  @Json(name = "msg")
  val msg: String
)
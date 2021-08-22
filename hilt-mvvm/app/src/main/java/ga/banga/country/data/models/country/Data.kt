package ga.banga.country.data.models.country

import com.squareup.moshi.Json

data class Data(
  @Json(name = "currency")
  val currency: String,
  @Json(name = "dialCode")
  val dialCode: String,
  @Json(name = "flag")
  val flag: String,
  @Json(name = "name")
  val name: String,
  @Json(name = "unicodeFlag")
  val unicodeFlag: String
)
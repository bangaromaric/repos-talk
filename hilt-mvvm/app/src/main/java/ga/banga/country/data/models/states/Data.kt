package ga.banga.country.data.models.states

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
  @Json(name = "iso3")
  val iso3: String,
  @Json(name = "name")
  val name: String,
  @Json(name = "states")
  val states: List<State>
) : Parcelable
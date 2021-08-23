package ga.banga.country.data.models.states

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Provinces(
  @Json(name = "data")
  val `data`: Data,
  @Json(name = "error")
  val error: Boolean,
  @Json(name = "msg")
  val msg: String
) : Parcelable
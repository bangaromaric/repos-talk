package ga.banga.country.data.models.states

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class State(
  @Json(name = "name")
  val name: String,
  @Json(name = "state_code")
  val stateCode: String
) : Parcelable
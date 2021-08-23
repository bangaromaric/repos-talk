package ga.banga.country.data.api

import ga.banga.country.data.models.country.CountriesInfo
import ga.banga.country.data.models.states.Provinces
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Romaric BANGA on 8/21/21 at 8:27 AM
 */
interface ApiService {

  @GET("info")
  suspend fun getCountriesInfo(@Query("returns") returns: String): Response<CountriesInfo>

  @POST("states")
  suspend fun getStates(@Body country: Map<String, String>): Response<Provinces>

}
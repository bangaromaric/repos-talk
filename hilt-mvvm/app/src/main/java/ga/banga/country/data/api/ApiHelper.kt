package ga.banga.country.data.api

import ga.banga.country.data.models.country.CountriesInfo
import ga.banga.country.data.models.states.Provinces
import retrofit2.Response

/**
 * Created by Romaric BANGA on 8/21/21 at 8:42 AM
 */
interface ApiHelper {

  suspend fun getCountriesInfo(returns: String): Response<CountriesInfo>
  suspend fun getStates(country: Map<String, String>): Response<Provinces>

}
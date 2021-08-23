package ga.banga.country.data.api

import ga.banga.country.data.models.country.CountriesInfo
import ga.banga.country.data.models.states.Provinces
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
  override suspend fun getCountriesInfo(returns: String): Response<CountriesInfo> =
    apiService.getCountriesInfo(returns)

  override suspend fun getStates(country: Map<String, String>): Response<Provinces> =
    apiService.getStates(country)

}
package ga.banga.country.data.repository

import ga.banga.country.data.api.ApiHelper
import javax.inject.Inject

/**
 * Created by Romaric BANGA on 7/2/20 at 10:42 AM
 */
class StatesRepository @Inject constructor(private val apiHelper: ApiHelper) {
  suspend fun getStates(country: Map<String, String>) = apiHelper.getStates(country)
}
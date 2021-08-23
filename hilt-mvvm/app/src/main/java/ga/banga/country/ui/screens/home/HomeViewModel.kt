package ga.banga.country.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ga.banga.country.data.models.country.CountriesInfo
import ga.banga.country.data.models.states.Provinces
import ga.banga.country.data.repository.CountriesRepository
import ga.banga.country.data.repository.StatesRepository
import ga.banga.country.utils.Constants.filter
import ga.banga.country.utils.NetworkHelper
import ga.banga.country.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val countriesRepository: CountriesRepository,
  private val statesRepository: StatesRepository,
  private val networkHelper: NetworkHelper
) : ViewModel() {

  private val _countries = MutableLiveData<Resource<CountriesInfo>>()
  val countries: LiveData<Resource<CountriesInfo>>
    get() = _countries

  private val _provinces = MutableLiveData<Resource<Provinces>>()
  val provinces: LiveData<Resource<Provinces>>
    get() = _provinces

  private var _isLoading = MutableLiveData<Boolean>()
  val isLoading: LiveData<Boolean>
    get() = _isLoading

  init {
    loading(true)
    fetchCountries(filter)
  }

  private fun fetchCountries(filter: String) {
    viewModelScope.launch {
      _countries.postValue(Resource.loading(null))
      if (networkHelper.isNetworkConnected()) {
        countriesRepository.getCountriesInfo(filter).let {
          if (it.isSuccessful) {
            _countries.postValue(Resource.success(it.body()))
          } else {
            _countries.postValue(
              Resource.error(
                it.errorBody().toString(),
                null,
                it.code()
              )
            )
          }
        }
      } else
        _countries.postValue(Resource.error("No internet connection", null, null))
    }
  }

  fun fetchProvinces(country: Map<String, String>) {
    viewModelScope.launch {
      _provinces.postValue(Resource.loading(null))
      if (networkHelper.isNetworkConnected()) {
        statesRepository.getStates(country).let {
          if (it.isSuccessful) {
            _provinces.postValue(Resource.success(it.body()))
          } else {
            _provinces.postValue(
              Resource.error(
                it.errorBody().toString(),
                null,
                it.code()
              )
            )
          }
        }
      } else
        _provinces.postValue(Resource.error("No internet connection", null, null))
    }
  }

  fun loading(check: Boolean) {
    _isLoading.value = check
  }

}
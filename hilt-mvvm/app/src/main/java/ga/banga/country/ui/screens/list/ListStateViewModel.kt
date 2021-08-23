package ga.banga.country.ui.screens.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ga.banga.country.data.repository.StatesRepository
import ga.banga.country.utils.NetworkHelper
import javax.inject.Inject

@HiltViewModel
class ListStateViewModel @Inject constructor(
  private val statesRepository: StatesRepository,
  private val networkHelper: NetworkHelper
) : ViewModel() {

}
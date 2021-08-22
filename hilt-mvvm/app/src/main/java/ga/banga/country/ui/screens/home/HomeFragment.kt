package ga.banga.country.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest.Builder
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import ga.banga.country.R
import ga.banga.country.data.models.country.Data
import ga.banga.country.databinding.HomeFragmentBinding
import ga.banga.country.utils.Constants.GABON
import ga.banga.country.utils.Constants.IMG_LBV_LIST
import ga.banga.country.utils.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment : Fragment() {

  companion object {
    fun newInstance() = HomeFragment()
  }

  @Inject lateinit var imageRequestBuilder: Builder
  @Inject lateinit var imageLoader: ImageLoader
  private lateinit var binding: HomeFragmentBinding
  private val countriesViewModel: HomeViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = HomeFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupObserver()
  }

  private fun renderedBackgroudCard(data: Data) {
    binding.imageView.load(selectImg()) {
      crossfade(true)
      error(R.drawable.ic_launcher_background)
    }

    val imageRequest = imageRequestBuilder
      .data(data.flag)
      .target(binding.imgFlag)
      .transformations(CircleCropTransformation())
      .crossfade(true)
      .build()

    val disposable = imageLoader.enqueue(imageRequest)
    val myJo = startRepeatingJob(4000L)
  }

  private fun selectImg(): String {
    return IMG_LBV_LIST[Random.nextInt(IMG_LBV_LIST.size)]
  }

  private fun setupObserver() {
    binding.lifecycleOwner = this
    binding.viewModel = countriesViewModel
    countriesViewModel.countries.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.SUCCESS -> {
          it.data?.data?.forEach lit@{ data ->
            if (data.name == GABON) {
              renderCountry(data)
              return@lit
            }
          }
          countriesViewModel.loading(false)
//          it.data?.let { countries -> renderList(countries) }
        }
        Status.LOADING -> {
          countriesViewModel.loading(true)
        }
        Status.ERROR -> {
          countriesViewModel.loading(false)
          Timber.d("--------${it.message}")
        }
      }
    })
  }

  private fun renderCountry(data: Data) {
    renderedInfo(data)
    renderedBackgroudCard(data)
  }

  private fun renderedInfo(data: Data) {
    binding.primaryText.text = data.name
    binding.subTextDeviseValue.text = data.currency
    binding.subTextDialValue.text = data.dialCode

  }

  /**
   * start Job
   * val job = startRepeatingJob()
   * cancels the job and waits for its completion
   * job.cancelAndJoin()
   * Params
   * timeInterval: time milliSeconds
   */
  private fun startRepeatingJob(timeInterval: Long): Job {
    return CoroutineScope(Dispatchers.Default).launch {
      while (isActive) {
        // add your task here
        binding.imageView.load(selectImg()) {
          crossfade(true)
          error(R.drawable.ic_launcher_background)
        }
        delay(timeInterval)
      }
    }
  }

}
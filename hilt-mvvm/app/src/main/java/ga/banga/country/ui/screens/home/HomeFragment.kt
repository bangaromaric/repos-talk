package ga.banga.country.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest.Builder
import coil.transform.CircleCropTransformation
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import ga.banga.country.R
import ga.banga.country.R.drawable
import ga.banga.country.R.integer
import ga.banga.country.R.string
import ga.banga.country.data.models.country.Data
import ga.banga.country.data.models.states.State
import ga.banga.country.databinding.HomeFragmentBinding
import ga.banga.country.di.InCoilSvg
import ga.banga.country.utils.Constants.DRAWABLE_GABON
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

  private lateinit var mCountry: Data
  private var mStates: List<State>? = null
  @Inject lateinit var imageRequestBuilder: Builder

  @InCoilSvg
  @Inject lateinit var imageLoader: ImageLoader

//  @InCoilGif
//  @Inject lateinit var imageLoaderGif: ImageLoader
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

    postponeEnterTransition()
    view.doOnPreDraw { startPostponedEnterTransition() }
    activity?.title = "Mon Gabon"
    setupObserver()
    setUpActions()

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  private fun setUpActions() {
    binding.actionButton1.setOnClickListener {
      navigateToStates()
    }

    binding.cardViewHome.setOnClickListener {
      navigateToStates()
    }

    binding.actionButton2.setOnClickListener {
      loadBackgroundCard()
    }

  }

  private fun navigateToStates() {
    val provinsList = mStates?.toTypedArray()
    if (provinsList != null) {

      // Set exit and reenter transitions here as opposed to in onCreate because these transitions
      // will be set and overwritten on HomeFragment for other navigation actions.
      exitTransition = MaterialElevationScale(false).apply {
        duration = resources.getInteger(integer.reply_motion_duration_large).toLong()
      }
      reenterTransition = MaterialElevationScale(true).apply {
        duration = resources.getInteger(integer.reply_motion_duration_large).toLong()
      }

      val statesCardListTransitionName = getString(string.states_list_transition_name)
      val extras = FragmentNavigatorExtras(binding.cardViewHome to statesCardListTransitionName)
      val directions = HomeFragmentDirections.actionHomeFragmentToListStateFragment(provinsList)
      findNavController().navigate(directions, extras)
    }
  }

  private fun renderedBackgroudCard(data: Data) {

    loadBackgroundCard()

    val imageRequest = imageRequestBuilder
      .data(data.flag)
      .target(binding.imgFlag)
      .transformations(CircleCropTransformation())
      .crossfade(true)
      .build()

    val disposable = imageLoader.enqueue(imageRequest)

  }

  private fun selectImg(): String {
    return IMG_LBV_LIST[Random.nextInt(IMG_LBV_LIST.size)]
  }

  private fun selectImgDwb(): Int {

    return DRAWABLE_GABON[Random.nextInt(DRAWABLE_GABON.size)]
  }

  private fun setupObserver() {
    binding.lifecycleOwner = this
    binding.viewModel = countriesViewModel
    countriesViewModel.countries.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.SUCCESS -> {
          it.data?.data?.forEach lit@{ data ->
            if (data.name == GABON) {
              fetchStates(data)
              return@lit
            }
          }
//          countriesViewModel.loading(false)
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

    countriesViewModel.provinces.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.SUCCESS -> {
          renderCountry(it.data?.data?.states)
          countriesViewModel.loading(false)
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

  private fun fetchStates(data: Data) {
    mCountry = data
    if (mStates == null)
      countriesViewModel.fetchProvinces(mapOf("country" to data.name))
  }

  private fun renderCountry(states: List<State>?) {
    mStates = states
    renderedInfo(mCountry)
    renderedBackgroudCard(mCountry)
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
        loadBackgroundCard()
        delay(timeInterval)
      }
    }
  }

  private fun loadBackgroundCard() {
    binding.imageView.load(selectImgDwb()) {
      crossfade(true)
      crossfade(500)
      error(drawable.ic_launcher_background)
      placeholder(R.color.grey)
      listener(onStart = {
        Timber.e("---------------Coil download start")
      },
        onSuccess = { request, metadata ->
          Timber.e("---------------Coil download succes")
        },
        onError = { request, throwable ->
          Timber.e("---------------Coil download error")

        })
    }
  }

}
package ga.banga.country.ui.screens.list

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import ga.banga.country.R
import ga.banga.country.databinding.ListStateFragmentBinding
import ga.banga.country.ui.screens.list.adapter.StatesRecyclerViewAdapter
import ga.banga.country.utils.extension.themeColor
import kotlin.LazyThreadSafetyMode.NONE

@AndroidEntryPoint
class ListStateFragment : Fragment() {

  private val args: ListStateFragmentArgs by navArgs()
  private val provinceList by lazy(NONE) { args.states }
  private lateinit var binding: ListStateFragmentBinding
  private var columnCount = 1

  companion object {
    fun newInstance() = ListStateFragment()
  }

  private lateinit var viewModel: ListStateViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = ListStateFragmentBinding.inflate(inflater, container, false)

    // Set the adapter
    with(binding.recyclerView) {
      layoutManager = when {
        columnCount <= 1 -> LinearLayoutManager(context)
        else -> GridLayoutManager(context, columnCount)
      }
      adapter = StatesRecyclerViewAdapter(provinceList.toList())
    }



    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(ListStateViewModel::class.java)

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    sharedElementEnterTransition = MaterialContainerTransform().apply {
      // Scope the transition to a view in the hierarchy so we know it will be added under
      // the bottom app bar but over the elevation scale of the exiting HomeFragment.
      drawingViewId = R.id.nav_host_fragment
      duration = resources.getInteger(R.integer.reply_motion_duration_xlarge).toLong()
      scrimColor = Color.TRANSPARENT
      setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
    }

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    activity?.title = "Nos Provinces"

  }

}
package ga.banga.country.ui.screens.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ga.banga.country.data.models.states.State
import ga.banga.country.databinding.FragmentItemBinding

class StatesRecyclerViewAdapter(private val values: List<State>) :
  RecyclerView.Adapter<StatesRecyclerViewAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      FragmentItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.idView.text = "G${position + 1}" /* item.stateCode */
    holder.contentView.text = item.name.replace("Province", "")
  }

  override fun getItemCount(): Int = values.size

  inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val idView: TextView = binding.itemNumber
    val contentView: TextView = binding.content

    override fun toString(): String {
      return super.toString() + " '" + contentView.text + "'"
    }
  }

}
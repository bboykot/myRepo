package overview

import android.example.com.spacex_launches.databinding.GridViewItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import repository.Models

class SpaceXGridAdapter (private val onClickListener: OnClickListener): ListAdapter<Models, SpaceXGridAdapter.SpaceXPropertyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceXPropertyViewHolder{

        return SpaceXPropertyViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SpaceXPropertyViewHolder, position: Int){
        val spaceXProperty = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(spaceXProperty)
        }

        holder.bind(spaceXProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Models>() {
        override fun areItemsTheSame(oldItem: Models, newItem: Models): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Models, newItem: Models): Boolean {
            return oldItem.flightNumber == newItem.flightNumber
        }
    }


    class SpaceXPropertyViewHolder (private val binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(spaceXProperty: Models){
            binding.model=spaceXProperty
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener:(models:Models)-> Unit ){
        fun onClick(models: Models) = clickListener(models)
    }
}
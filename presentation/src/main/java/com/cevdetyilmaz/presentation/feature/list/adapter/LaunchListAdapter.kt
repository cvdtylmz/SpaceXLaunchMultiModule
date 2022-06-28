package com.cevdetyilmaz.presentation.feature.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cevdetyilmaz.domain.model.Launch
import com.cevdetyilmaz.presentation.databinding.ItemLaunchBinding
import com.cevdetyilmaz.presentation.util.show
import com.cevdetyilmaz.presentation.util.viewBinding

class LaunchListAdapter(private val callback: (Launch?) -> Unit) :
    PagingDataAdapter<Launch,
            LaunchListAdapter.LaunchListViewHolder>(LaunchListDiffUtil()) {

    class LaunchListViewHolder(val binding: ItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Launch?) {
            if (model?.images?.isNotEmpty() == true) {
                binding.ivLaunch.show(model.images.first())
            }
            binding.tvLaunchName.text = model?.missionName
            binding.tvLaunchDate.text = model?.launchDate
        }

    }

    override fun onBindViewHolder(holder: LaunchListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.root.setOnClickListener {
            callback.invoke(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchListViewHolder {
        return LaunchListViewHolder(parent.viewBinding(ItemLaunchBinding::inflate))
    }
}

class LaunchListDiffUtil : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(
        oldItem: Launch,
        newItem: Launch
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Launch,
        newItem: Launch
    ): Boolean {
        return oldItem == newItem
    }
}
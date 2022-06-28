package com.cevdetyilmaz.presentation.feature.list.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cevdetyilmaz.presentation.databinding.ItemBottomLoadStateBinding
import com.cevdetyilmaz.presentation.util.viewBinding

class LoadMoreStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadMoreStateAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(val binding: ItemBottomLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
        holder.binding.btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(parent.viewBinding(ItemBottomLoadStateBinding::inflate))
    }
}
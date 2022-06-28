package com.cevdetyilmaz.presentation.feature.list

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.cevdetyilmaz.presentation.R
import com.cevdetyilmaz.presentation.base.BaseFragment
import com.cevdetyilmaz.presentation.databinding.FragmentLaunchListBinding
import com.cevdetyilmaz.presentation.feature.list.adapter.LaunchListAdapter
import com.cevdetyilmaz.presentation.feature.list.adapter.LoadMoreStateAdapter
import com.cevdetyilmaz.presentation.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LaunchListFragment : BaseFragment<LaunchListViewModel>(R.layout.fragment_launch_list) {
    override val binding by viewBinding(FragmentLaunchListBinding::bind)
    override val viewModel: LaunchListViewModel by viewModels()

    private val launchListAdapter = LaunchListAdapter {
        it?.id ?: return@LaunchListAdapter
        it.missionId
        val directions =
            LaunchListFragmentDirections.actionLaunchListFragmentToLaunchDetailFragment(
                launchId = it.id,
                missionId = it.missionId
            )
        findNavController().navigate(directions)
    }

    override fun observeViewModel(viewModel: LaunchListViewModel) {
        super.observeViewModel(viewModel)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.launchResult.collectLatest {
                when (it) {
                    LaunchListEvent.Idle -> Unit
                    is LaunchListEvent.DataLoaded -> launchListAdapter.submitData(it.data)
                }
            }
        }
    }

    override fun viewDidLoad(savedInstanceState: Bundle?) {
        binding.rcyLaunches.run {
            adapter = launchListAdapter.withLoadStateFooter(
                footer = LoadMoreStateAdapter {
                    launchListAdapter.retry()
                }
            )
            layoutManager = GridLayoutManager(this@LaunchListFragment.requireContext(), 2)
        }

        launchListAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                lifecycleScope.launchWhenResumed {
                    binding.pbLoading.isVisible = true
                }

            } else {
                lifecycleScope.launchWhenResumed {
                    binding.pbLoading.isGone = true
                }

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
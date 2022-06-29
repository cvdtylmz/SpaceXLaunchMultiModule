package com.cevdetyilmaz.presentation.feature.detail

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cevdetyilmaz.domain.model.LaunchDetail
import com.cevdetyilmaz.presentation.R
import com.cevdetyilmaz.presentation.base.BaseFragment
import com.cevdetyilmaz.presentation.databinding.FragmentLaunchDetailBinding
import com.cevdetyilmaz.presentation.util.show
import com.cevdetyilmaz.presentation.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LaunchDetailFragment : BaseFragment<LaunchDetailViewModel>(R.layout.fragment_launch_detail) {
    override val binding by viewBinding(FragmentLaunchDetailBinding::bind)
    override val viewModel: LaunchDetailViewModel by viewModels()

    override fun observeViewModel(viewModel: LaunchDetailViewModel) {
        super.observeViewModel(viewModel)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.detailFlow.collectLatest {
                binding.pbLoading.isVisible = it is LaunchDetailEvent.Loading
                when (it) {
                    LaunchDetailEvent.Idle -> {
                        // NO-OPP
                    }
                    LaunchDetailEvent.Loading -> {
                        // NO-OPP
                    }
                    is LaunchDetailEvent.DataLoaded -> {
                        initUI(it.data)
                    }
                    is LaunchDetailEvent.Error -> {
                        Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    private fun initUI(data: LaunchDetail) {
        with(binding) {
            tvLaunchName.text = data.missionName
            tvLaunchDate.text = data.date
            tvDetail.text = data.description
            tvTwitterLink.text = getString(R.string.twitter_link, data.twitterLink)
            tvWikiLink.text = getString(R.string.wiki_link, data.wikiLink)
            ivLaunch.show(data.image)
        }
    }
}
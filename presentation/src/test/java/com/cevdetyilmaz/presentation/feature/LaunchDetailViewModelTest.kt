package com.cevdetyilmaz.presentation.feature

import com.cevdetyilmaz.domain.repository.LaunchRepository
import com.cevdetyilmaz.domain.usecase.GetLaunchDetailUseCase
import com.cevdetyilmaz.presentation.feature.detail.LaunchDetailEvent
import com.cevdetyilmaz.presentation.feature.detail.LaunchDetailViewModel
import com.cevdetyilmaz.presentation.model.DummyLaunchDetailViewModelResponse
import com.cevdetyilmaz.presentation.util.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LaunchDetailViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mainTestCoroutineRule = MainCoroutineRule()


    private lateinit var launchDetailViewModel: LaunchDetailViewModel
    private lateinit var launchDetailUseCase: GetLaunchDetailUseCase
    private var launchRepository = mockk<LaunchRepository>(relaxUnitFun = true, relaxed = true)

    @Before
    fun setUp() {
        launchDetailUseCase = GetLaunchDetailUseCase(launchRepository, testDispatcher)
        launchDetailViewModel = LaunchDetailViewModel(launchDetailUseCase, mockk(relaxed = true))
    }

    @Test
    fun `check that getLaunchDetail returns valid variable when user calls launchDetail`() =
        mainTestCoroutineRule.runBlockingTest {
            coEvery {
                launchRepository.getLaunchDetail(
                    DummyLaunchDetailViewModelResponse.id,
                    DummyLaunchDetailViewModelResponse.missionId
                )
            } returns DummyLaunchDetailViewModelResponse.getLaunchDetailSuccessResponse
            launchDetailViewModel.getLaunchDetail()
            val result = launchDetailViewModel.detailFlow.first()
            assertThat(result).isInstanceOf(LaunchDetailEvent.DataLoaded::class.java)
            result as LaunchDetailEvent.DataLoaded
            assertThat(result.data).isEqualTo(DummyLaunchDetailViewModelResponse.successResultData)
        }
}
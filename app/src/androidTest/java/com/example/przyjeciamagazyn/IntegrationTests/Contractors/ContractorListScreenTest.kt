package com.example.przyjeciamagazyn.IntegrationTests.Contractors

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.hasText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.data.repository.ContractorRepository
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Contractors.presentation.screens.ContractorListScreen
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class ContractorListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var contractorRepository: ContractorRepository
    lateinit var mockViewModel: ContractorViewModel

    private val mockContractors = listOf(
        Contractor(id = 0, symbol = "C1", name = "Contractor One"),
        Contractor(id = 1, symbol = "C2", name = "Contractor Two"),
        Contractor(id = 2, symbol = "C3", name = "Contractor Three")
    )

    @Before
    fun setup(){
        contractorRepository = mockk()

        every { contractorRepository.getAllContractors() } returns flowOf(mockContractors)
        coEvery { contractorRepository.insertContractor(any()) } just runs
        coEvery { contractorRepository.deleteAllContractors() } just runs
        coEvery { contractorRepository.updateContractor(any()) } just runs

        mockViewModel = ContractorViewModel(contractorRepository, mockk())

        runBlocking {
            mockViewModel.getAllContractors()
        }
    }

    @Test
    fun testContractorListScreen_displaysContractors() = runTest {
        composeTestRule.setContent {
            ContractorListScreen(contractorViewModel = mockViewModel, onNavigate = {})
        }

        composeTestRule.awaitIdle()

        mockContractors.forEach { contractor ->
            composeTestRule.onNode(hasText("Symbol: ${contractor.symbol}")).assertExists()
            composeTestRule.onNode(hasText("Nazwa: ${contractor.name}")).assertExists()
        }
    }

    @Test
    fun testAddContractorFab_navigatesToAddContractorScreen() = runTest {
        var navigateToAddContractorScreen = false

        composeTestRule.setContent {
            ContractorListScreen(
                contractorViewModel = mockViewModel,
                onNavigate = { route ->
                    if (route == AddScreens.AddContractorScreen.route) {
                        navigateToAddContractorScreen = true
                    }
                }
            )
        }

        composeTestRule.onNodeWithContentDescription("Add Contractor").performClick()

        assert(navigateToAddContractorScreen)
    }

    @Test
    fun testContractorRow_navigatesToEditContractorScreen() = runTest {
        var navigateToEditContractorScreen = false

        composeTestRule.setContent {
            ContractorListScreen(
                contractorViewModel = mockViewModel,
                onNavigate = { route ->
                    if (route == EditScreens.EditContractorScreen.route) {
                        navigateToEditContractorScreen = true
                    }
                }
            )
        }

        composeTestRule.onNode(hasText("Symbol: ${mockContractors[0].symbol}")).performClick()

        assert(navigateToEditContractorScreen)
    }
}
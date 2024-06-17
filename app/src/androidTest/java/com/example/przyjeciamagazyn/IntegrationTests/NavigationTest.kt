package com.example.przyjeciamagazyn.IntegrationTests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.MainActivity
import com.example.przyjeciamagazyn.Core.presentation.Navigation.NavigationNavGraph
import com.example.przyjeciamagazyn.Core.presentation.theme.PrzyjeciaMagazynTheme
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavigationIntegrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var contractorViewModel: ContractorViewModel
    private lateinit var documentViewModel: DocumentViewModel

    @Before
    fun setup() {
        hiltRule.inject()

        // Mock ViewModels
        contractorViewModel = mock(ContractorViewModel::class.java)
        documentViewModel = mock(DocumentViewModel::class.java)
    }

    @Test
    fun testNavigationToContractorListScreen() {
        composeTestRule.setContent {
            PrzyjeciaMagazynTheme {
                val navController = rememberNavController()
                NavigationNavGraph(navController = navController)
            }
        }

        // Navigate to Contractor List Screen
        composeTestRule.onNodeWithText("Go to Contractor List").performClick()
        composeTestRule.onNodeWithText("Contractor List Screen Content").assertExists()
    }

    @Test
    fun testNavigationToAddContractorScreen() {
        composeTestRule.setContent {
            PrzyjeciaMagazynTheme {
                val navController = rememberNavController()
                NavigationNavGraph(navController = navController)
            }
        }

        // Navigate to Contractor List Screen first
        composeTestRule.onNodeWithText("Go to Contractor List").performClick()
        // Navigate to Add Contractor Screen
        composeTestRule.onNodeWithText("Add Contractor").performClick()
        composeTestRule.onNodeWithText("Add Contractor Screen Content").assertExists()
    }

    @Test
    fun testNavigationToEditContractorScreen() {
        composeTestRule.setContent {
            PrzyjeciaMagazynTheme {
                val navController = rememberNavController()
                NavigationNavGraph(navController = navController)
            }
        }

        // Navigate to Contractor List Screen first
        composeTestRule.onNodeWithText("Go to Contractor List").performClick()
        // Navigate to Edit Contractor Screen
        composeTestRule.onNodeWithText("Edit Contractor").performClick()
        composeTestRule.onNodeWithText("Edit Contractor Screen Content").assertExists()
    }

    // Add more navigation tests for Document screens and other navigation paths...
}
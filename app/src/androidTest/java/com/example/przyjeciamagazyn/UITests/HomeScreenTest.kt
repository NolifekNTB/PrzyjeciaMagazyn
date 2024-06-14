package com.example.przyjeciamagazyn.UITests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Home.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInitialDisplay() {
        composeTestRule.setContent {
            HomeScreen(onNavigate = {})
        }

        composeTestRule.onNodeWithText("Przyjecia Magazyn").assertIsDisplayed()
        composeTestRule.onNodeWithText("Lista Dokumentów").assertIsDisplayed()
        composeTestRule.onNodeWithText("Kontrahenci").assertIsDisplayed()
    }

    @Test
    fun testDocumentListNavigation() {
        var navigatedToDocumentList = false

        composeTestRule.setContent {
            HomeScreen(onNavigate = { route ->
                if (route == Screen.DocumentListScreen.route) {
                    navigatedToDocumentList = true
                }
            })
        }

        composeTestRule.onNodeWithText("Lista Dokumentów").performClick()
        assert(navigatedToDocumentList)
    }

    @Test
    fun testContractorListNavigation() {
        var navigatedToContractorList = false

        composeTestRule.setContent {
            HomeScreen(onNavigate = { route ->
                if (route == Screen.ContractorListScreen.route) {
                    navigatedToContractorList = true
                }
            })
        }

        composeTestRule.onNodeWithText("Kontrahenci").performClick()
        assert(navigatedToContractorList)
    }
}
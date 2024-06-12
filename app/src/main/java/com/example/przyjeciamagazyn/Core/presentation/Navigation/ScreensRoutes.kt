package com.example.przyjeciamagazyn.Core.presentation.Navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object DocumentListScreen : Screen("document_list_screen")
    data object PositionsListScreen : Screen("document_detail_screen")
    data object PositionDetailScreen : Screen("document_position_detail_screen")
    data object ContractorListScreen : Screen("contractor_list_screen")
}

sealed class AddScreens(val route: String) {
    data object AddDocumentScreen : AddScreens("add_document_sheet")
    data object AddPositionScreen : AddScreens("add_position_sheet")
    data object AddContractorScreen : AddScreens("add_contractor_sheet")
}

sealed class EditScreens(val route: String) {
    data object EditDocumentScreen : EditScreens("edit_document_sheet")
    data object EditPositionScreen : EditScreens("edit_position_sheet")
    data object EditContractorScreen : EditScreens("edit_contractor_sheet")
}
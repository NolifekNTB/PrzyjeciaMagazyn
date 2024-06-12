package com.example.przyjeciamagazyn.Core.presentation.Navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object DocumentListScreen : Screen("document_list_screen")
    data object DocumentDetailScreen : Screen("document_detail_screen")
    data object DocumentPositionDetailScreen : Screen("document_position_detail_screen")
    data object ContractorListScreen : Screen("contractor_list_screen")
}

sealed class AddScreens(val route: String) {
    data object AddDocumentSheet : AddScreens("add_document_sheet")
    data object AddPositionSheet : AddScreens("add_position_sheet")
    data object AddContractorSheet : AddScreens("add_contractor_sheet")
}

sealed class EditScreens(val route: String) {
    data object EditDocumentSheet : EditScreens("edit_document_sheet")
    data object EditPositionSheet : EditScreens("edit_position_sheet")
    data object EditContractorSheet : EditScreens("edit_contractor_sheet")
}
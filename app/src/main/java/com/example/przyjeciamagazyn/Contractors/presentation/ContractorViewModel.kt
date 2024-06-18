package com.example.przyjeciamagazyn.Contractors.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.data.repository.ContractorRepository
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContractorViewModel @Inject constructor (
    private val contractorRepository: ContractorRepository,
    private val documentRepository: DocumentRepository
): ViewModel() {
    private var _contractors = MutableStateFlow<List<Contractor>>(emptyList())
    var contractors: StateFlow<List<Contractor>> = _contractors

    private var _selectedContractor = MutableStateFlow<Contractor?>(null)
    var selectedContractor: StateFlow<Contractor?> = _selectedContractor

    init {
        getAllContractors()
    }

    fun getAllContractors() {
        viewModelScope.launch {
            val result = contractorRepository.getAllContractors().first()
            _contractors.value = result
        }
    }

    fun insertContractor(contractor: Contractor) {
        viewModelScope.launch {
            contractorRepository.insertContractor(contractor)
            getAllContractors()
        }
    }

    fun updateContractor(contractor: Contractor) {
        viewModelScope.launch {
            contractorRepository.updateContractor(contractor)
            updatedocumentsWithContractor(contractor)
            getAllContractors()
        }
    }

    //Updates documents to reflect changes in the specified contractor
    private fun updatedocumentsWithContractor(updatedContractor: Contractor) {
        viewModelScope.launch {
            val documents = documentRepository.getDocumentsContainingContractor(updatedContractor.id).first()

            documents.forEach { document ->
                val updatedContractors = document.contractors.map {
                    if (it.id == updatedContractor.id) updatedContractor else it
                }
                val updateddocument = document.copy(contractors = updatedContractors)
                documentRepository.updateDocument(updateddocument)
            }
        }
    }

    fun selectContractor(contractor: Contractor) {
        _selectedContractor.value = contractor
    }
}
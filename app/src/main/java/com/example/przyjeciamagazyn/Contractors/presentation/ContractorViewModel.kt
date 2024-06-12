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
    val contractorRepository: ContractorRepository,
    val receiptRepository: DocumentRepository
): ViewModel() {
    private var _contractors = MutableStateFlow<List<Contractor>>(emptyList())
    var contractors: StateFlow<List<Contractor>> = _contractors

    private var _selectedContractor = MutableStateFlow<Contractor?>(null)
    var selectedContractor: StateFlow<Contractor?> = _selectedContractor

    init {
        getAllContractors()
    }

    private fun updateReceiptsWithContractor(updatedContractor: Contractor) {
        viewModelScope.launch {
            val receipts = receiptRepository.getReceiptsContainingContractor(updatedContractor.id).first()

            receipts.forEach { receipt ->
                val updatedContractors = receipt.contractors.map {
                    if (it.id == updatedContractor.id) updatedContractor else it
                }
                val updatedReceipt = receipt.copy(contractors = updatedContractors)
                receiptRepository.updateReceipt(updatedReceipt)
            }
        }
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
            updateReceiptsWithContractor(contractor)
            getAllContractors()
        }
    }

    fun selectContractor(contractor: Contractor) {
        _selectedContractor.value = contractor
    }

    fun deleteContractors() {
        viewModelScope.launch {
            contractorRepository.deleteAllContractors()
        }
    }
}
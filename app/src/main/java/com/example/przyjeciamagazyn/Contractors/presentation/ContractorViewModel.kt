package com.example.przyjeciamagazyn.Contractors.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.data.repository.ContractorRepository
import com.example.przyjeciamagazyn.Core.data.sampleContractors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContractorViewModel @Inject constructor (
    val contractorRepository: ContractorRepository
): ViewModel() {
    private var _contractors = MutableStateFlow<List<Contractor>>(emptyList())
    var contractors: Flow<List<Contractor>> = _contractors

    init {
        deleteContractors()
        insertContractor(sampleContractors[0])
        insertContractor(sampleContractors[1])
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
        }
    }

    fun deleteContractors() {
        viewModelScope.launch {
            contractorRepository.deleteAllContractors()
        }
    }

}
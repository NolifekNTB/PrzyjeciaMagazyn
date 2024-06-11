package com.example.przyjeciamagazyn.Receipts.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import com.example.przyjeciamagazyn.Receipts.data.repository.ReceiptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    private val repository: ReceiptRepository,
): ViewModel() {
    private var _receiptDocuments = MutableStateFlow<List<ReceiptDocument>>(emptyList())
    var receiptDocuments: Flow<List<ReceiptDocument>> = _receiptDocuments

    var selectedDocument = MutableStateFlow<ReceiptDocument?>(null)
    var selectedDocumentPosition = MutableStateFlow<ReceiptPosition?>(null)

    init {
        //insertReceipt(sampleDocuments[1])
        getALlReceipts()
    }

    fun insertReceipt(receipt: ReceiptDocument) = viewModelScope.launch {
        repository.insertReceipt(receipt)
    }

    fun getALlReceipts() {
        viewModelScope.launch {
            val result = repository.getAllReceipts().first()
            _receiptDocuments.value = result
        }
    }

    fun deleteAllReceipts() = viewModelScope.launch {
        repository.deleteAllReceipts()
    }

    fun insertReceiptPosition(position: ReceiptPosition) = viewModelScope.launch {
        repository.insertReceiptPosition(position)
        refreshReceiptPositions(position.receiptId)
    }


    fun updateReceipt(updatedReceipt: ReceiptDocument) {
        viewModelScope.launch {
            repository.updateReceipt(updatedReceipt)
        }
    }

    fun updateReceiptPosition(position: ReceiptPosition){
        viewModelScope.launch {
            repository.updateReceiptPosition(position)
            refreshReceiptPositions(position.receiptId)
            refreshPosition(position.id)
        }
    }

    fun updatedReceipt(receiptId: Int) {
        viewModelScope.launch {
            val receipt = repository.getReceipt(receiptId).first()
            selectedDocument.value = receipt
        }
    }

    fun refreshReceiptPositions(receiptId: Int) {
        viewModelScope.launch {
            val positions = repository.getPositionsForReceipt(receiptId).first()
            repository.updateReceiptPositions(selectedDocument.value!!.id, positions)
            updatedReceipt(receiptId)
        }
    }

    fun refreshPosition(positionId: Int) {
        viewModelScope.launch {
            val position = repository.getPosition(positionId).first()
            selectedDocumentPosition.value = position
        }
    }
}
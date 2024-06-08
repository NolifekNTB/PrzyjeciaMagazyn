package com.example.przyjeciamagazyn.Receipts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.sampleDocuments
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

    init {
        //insertReceipt(sampleDocuments[1])
        getALlReceipts()
    }

    fun insertReceipt(receipt: ReceiptDocument) = viewModelScope.launch {
        repository.insertReceipt(receipt)
    }

    private fun getALlReceipts() {
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
    }

    fun getPositionsForReceipt(receiptId: Int) {
        viewModelScope.launch {
            var result = repository.getPositionsForReceipt(receiptId).first()
        }
    }
}
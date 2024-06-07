package com.example.przyjeciamagazyn.Receipts.presentation

import androidx.lifecycle.ViewModel
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.data.repository.ReceiptRepository
import kotlinx.coroutines.flow.Flow

class ReceiptViewModel(
    private val repository: ReceiptRepository,
    val allReceipts: Flow<List<ReceiptDocument>>
): ViewModel() {

    init {
        repository = ReceiptRepository(receiptDao, receiptPositionDao)
        allReceipts = repository.getAllReceipts()
    }

    fun insertReceipt(receipt: Receipt) = viewModelScope.launch {
        repository.insertReceipt(receipt)
    }

    fun insertReceiptPosition(position: ReceiptPosition) = viewModelScope.launch {
        repository.insertReceiptPosition(position)
    }

    fun getPositionsForReceipt(receiptId: Int): LiveData<List<ReceiptPosition>> {
        return repository.getPositionsForReceipt(receiptId)
    }
}
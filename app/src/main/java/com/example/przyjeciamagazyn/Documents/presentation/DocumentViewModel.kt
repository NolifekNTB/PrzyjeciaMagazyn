package com.example.przyjeciamagazyn.Documents.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val repository: DocumentRepository,
): ViewModel() {
    private var _receiptDocuments = MutableStateFlow<List<Document>>(emptyList())
    var receiptDocuments: Flow<List<Document>> = _receiptDocuments

    var selectedDocument = MutableStateFlow<Document?>(null)
    var selectedDocumentPosition = MutableStateFlow<DocumentPosition?>(null)

    init {
        //insertReceipt(sampleDocuments[1])
        getALlReceipts()
    }

    fun insertReceipt(receipt: Document) = viewModelScope.launch {
        repository.insertDocument(receipt)
    }

    fun getALlReceipts() {
        viewModelScope.launch {
            val result = repository.getAllDocuments().first()
            _receiptDocuments.value = result
        }
    }

    fun deleteAllReceipts() = viewModelScope.launch {
        repository.deleteAllDocuments()
    }

    fun insertReceiptPosition(position: DocumentPosition) = viewModelScope.launch {
        repository.insertPosition(position)
        refreshReceiptPositions(position.receiptId)
    }


    fun updateReceipt(updatedReceipt: Document) {
        viewModelScope.launch {
            repository.updateDocument(updatedReceipt)
        }
    }

    fun updateReceiptPosition(position: DocumentPosition){
        viewModelScope.launch {
            repository.updatePosition(position)
            refreshReceiptPositions(position.receiptId)
            refreshPosition(position.id)
        }
    }

    fun refreshReceiptPositions(receiptId: Int) {
        viewModelScope.launch {
            val positions = repository.getPositionsForDocument(receiptId).first()
            repository.updateDocumentPositions(selectedDocument.value!!.id, positions)
            updatedReceipt(receiptId)
        }
    }

    fun updatedReceipt(receiptId: Int) {
        viewModelScope.launch {
            val receipt = repository.getDocument(receiptId).first()
            selectedDocument.value = receipt
        }
    }

    fun refreshPosition(positionId: Int) {
        viewModelScope.launch {
            val position = repository.getPosition(positionId).first()
            selectedDocumentPosition.value = position
        }
    }
}
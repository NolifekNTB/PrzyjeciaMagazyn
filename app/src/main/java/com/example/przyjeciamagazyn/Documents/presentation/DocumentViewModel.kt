package com.example.przyjeciamagazyn.Documents.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.example.przyjeciamagazyn.Documents.data.repository.PositionsRepository
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val positionRepository: PositionsRepository
): ViewModel() {
    private var _receiptDocuments = MutableStateFlow<List<Document>>(emptyList())
    var receiptDocuments: Flow<List<Document>> = _receiptDocuments

    var selectedDocument = MutableStateFlow<Document?>(null)
    var selectedDocumentPosition = MutableStateFlow<Position?>(null)

    fun getALlDocuments() {
        viewModelScope.launch {
            val result = documentRepository.getAllDocuments().first()
            _receiptDocuments.value = result
        }
    }

    fun insertDocument(document: Document) = viewModelScope.launch {
        documentRepository.insertDocument(document)
    }

    fun insertPosition(position: Position) = viewModelScope.launch {
        positionRepository.insertPosition(position)
        refreshReceiptPositions(position.documentId)
    }


    fun updateDocument(updatedDocument: Document) {
        viewModelScope.launch {
            documentRepository.updateDocument(updatedDocument)
        }
    }

    fun updateDocumentPositions(position: Position){
        viewModelScope.launch {
            positionRepository.updatePosition(position)
            refreshReceiptPositions(position.documentId)
            updateSelectedDocumentPositions(position.id)
        }
    }

    private fun refreshReceiptPositions(documentId: Int) {
        viewModelScope.launch {
            val positions = positionRepository.getPositionsForDocument(documentId).first()
            documentRepository.updateDocumentPositions(selectedDocument.value!!.id, positions)
            updateSelectedDocument(documentId)
        }
    }

    private fun updateSelectedDocument(documentId: Int) {
        viewModelScope.launch {
            val document = documentRepository.getDocument(documentId).first()
            selectedDocument.value = document
        }
    }

    private fun updateSelectedDocumentPositions(positionId: Int) {
        viewModelScope.launch {
            val position = positionRepository.getPosition(positionId).first()
            selectedDocumentPosition.value = position
        }
    }
}
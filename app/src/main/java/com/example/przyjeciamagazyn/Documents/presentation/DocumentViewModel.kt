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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val positionRepository: PositionsRepository
): ViewModel() {
    private var _documentDocuments = MutableStateFlow<List<Document>>(emptyList())
    var documentDocuments: Flow<List<Document>> = _documentDocuments


    private var _selectedDocument = MutableStateFlow<Document?>(null)
    val selectedDocument: StateFlow<Document?> = _selectedDocument

    var selectedDocumentPosition = MutableStateFlow<Position?>(null)

    fun selectDocument(document: Document) {
        _selectedDocument.value = document
    }
    
    fun getALlDocuments() {
        viewModelScope.launch {
            val result = documentRepository.getAllDocuments().first()
            _documentDocuments.value = result
        }
    }

    fun insertDocument(document: Document) = viewModelScope.launch {
        documentRepository.insertDocument(document)
    }

    fun insertPosition(position: Position) = viewModelScope.launch {
        positionRepository.insertPosition(position)
        updateDocumentPositions(position.documentId, position.id)
    }


    fun updateDocument(updatedDocument: Document) {
        viewModelScope.launch {
            documentRepository.updateDocument(updatedDocument)
            _selectedDocument.value = updatedDocument
        }
    }

    fun updateDocumentPositions(position: Position){
        viewModelScope.launch {
            positionRepository.updatePosition(position)
            updateDocumentPositions(position.documentId, position.id)
        }
    }

    private fun updateDocumentPositions(documentId: Int, positionId: Int) {
        viewModelScope.launch {
            val positions = positionRepository.getPositionsForDocument(documentId).first()
            documentRepository.updateDocumentPositions(selectedDocument.value!!.id, positions)
            val document = documentRepository.getDocument(documentId).first()
            _selectedDocument.value = document
            val position = positionRepository.getPosition(positionId).first()
            selectedDocumentPosition.value = position
        }
    }
}
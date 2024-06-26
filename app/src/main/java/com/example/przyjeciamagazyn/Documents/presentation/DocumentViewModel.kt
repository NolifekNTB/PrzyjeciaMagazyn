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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val positionRepository: PositionsRepository
): ViewModel() {
    private var _documentList = MutableStateFlow<List<Document>>(emptyList())
    var documentList: Flow<List<Document>> = _documentList


    private var _selectedDocument = MutableStateFlow<Document?>(null)
    val selectedDocument: StateFlow<Document?> = _selectedDocument

    var selectedPosition = MutableStateFlow<Position?>(null)

    fun selectDocument(document: Document) {
        _selectedDocument.value = document
    }
    
    fun getALlDocuments() {
        viewModelScope.launch {
            val result = documentRepository.getAllDocuments().first()
            _documentList.value = result
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

    //Updates the positions associated with a document and refreshes the selected document and position states.
    private fun updateDocumentPositions(documentId: Int, positionId: Int) {
        viewModelScope.launch {
            val positions = positionRepository.getPositionsForDocument(documentId).firstOrNull() ?: emptyList()
            //here was modified
            val selectedDoc = selectedDocument.value ?: return@launch
            documentRepository.updateDocumentPositions(selectedDoc.id, positions)

            val document = documentRepository.getDocument(documentId).firstOrNull() ?: return@launch
            _selectedDocument.value = document

            val position = positionRepository.getPosition(positionId).firstOrNull() ?: return@launch
            selectedPosition.value = position
        }
    }
}
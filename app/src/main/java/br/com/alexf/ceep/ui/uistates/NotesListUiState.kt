package br.com.alexf.ceep.ui.uistates

import br.com.alexf.ceep.model.Note

sealed class NotesListUiState {

    object Loading : NotesListUiState()

    data class Success(val notes: List<Note>) : NotesListUiState()

}
package br.com.alexf.ceep.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.model.toNoteEntity
import br.com.alexf.ceep.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteFormScreenViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    var uiState by mutableStateOf(NoteFormUiState())
        private set

    fun setTitle(title: String) {
        uiState = uiState.copy(title = title)
    }

    fun setMessage(message: String) {
        uiState = uiState.copy(message = message)
    }

    suspend fun save() {
        val note = Note(
            title = uiState.title,
            message = uiState.message
        )
        noteRepository.save(note.toNoteEntity())
    }

}

data class NoteFormUiState(
    var title: String = "",
    var message: String = ""
)
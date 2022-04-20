package br.com.alexf.ceep.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.model.toNoteEntity
import br.com.alexf.ceep.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteFormScreenViewModel @Inject constructor(
    private val repository: NoteRepository
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
            id = uiState.id,
            title = uiState.title,
            message = uiState.message
        )
        repository.save(note.toNoteEntity())
    }

    fun findById(id: String) {
        viewModelScope.launch {
            val entity = repository.findById(id)
                .filterNotNull()
                .first()
            uiState = uiState.copy(
                id = id,
                title = entity.title,
                message = entity.message,
                isNewNote = false
            )
        }
    }

}

data class NoteFormUiState(
    var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var message: String = "",
    var isNewNote: Boolean = true
)
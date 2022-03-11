package br.com.alexf.ceep.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.alexf.ceep.database.entity.toNote
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NotesListScreenViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    var uiState by mutableStateOf(NotesListUiState())
        private set

    suspend fun findAll() {
        uiState = uiState.copy(
            loading = true
        )
        return repository
            .findAll()
            .map { entities ->
                entities.map { entity ->
                    entity.toNote()
                }
            }.collect { notes ->
                uiState = uiState.copy(
                    notes = notes,
                    loading = false
                )
            }
    }

}

data class NotesListUiState(
    val notes: List<Note> = emptyList(),
    val loading: Boolean = false
)
package br.com.alexf.ceep.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alexf.ceep.database.entity.toNote
import br.com.alexf.ceep.repository.NoteRepository
import br.com.alexf.ceep.ui.uistates.NotesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListScreenViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<NotesListUiState>(
        NotesListUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .findAll()
                .map { entities ->
                    entities.map { entity ->
                        entity.toNote()
                    }
                }.collect { notes ->
                _uiState.value = NotesListUiState.Success(notes)
            }
        }
    }

}
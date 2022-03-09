package br.com.alexf.ceep.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alexf.ceep.database.entity.toNote
import br.com.alexf.ceep.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailsScreenViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    fun showSnackBar() {
        uiState = uiState.copy(showSnackbar = true)
    }

    fun findById(noteId: String) {
        viewModelScope.launch {
            repository.findById(noteId)
                .collect {
                    uiState = uiState.copy(
                        title = it.title,
                        message = it.message
                    )
                }
        }
    }

    var uiState: NoteDetailsState by mutableStateOf(NoteDetailsState())
        private set

}

data class NoteDetailsState(
    val title: String = "",
    val message: String = "",
    val showSnackbar: Boolean = false
)
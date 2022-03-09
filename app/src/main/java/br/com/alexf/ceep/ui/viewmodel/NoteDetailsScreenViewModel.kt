package br.com.alexf.ceep.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NoteDetailsScreenViewModel : ViewModel() {

    fun showSnackBar() {
        uiState = uiState.copy(showSnackbar = true)
    }

    var uiState: NoteDetailsState by mutableStateOf(NoteDetailsState())
        private set

}

data class NoteDetailsState(
    val title: String = "",
    val message: String = "",
    val showSnackbar: Boolean = false
)
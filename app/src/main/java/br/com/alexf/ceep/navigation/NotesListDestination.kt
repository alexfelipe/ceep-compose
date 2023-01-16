package br.com.alexf.ceep.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alexf.ceep.ui.screens.NotesListScreen
import br.com.alexf.ceep.ui.viewmodel.NotesListScreenViewModel

internal const val notesListRoute = "noteList"

fun NavGraphBuilder.notesListScreen(
    navigateToNoteform: () -> Unit,
    navigateToNoteDetails: (String) -> Unit
) {
    composable(notesListRoute) {
        val viewModel = hiltViewModel<NotesListScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        NotesListScreen(
            uiState = uiState,
            onNewNoteClick = navigateToNoteform,
            onNoteClick = { note ->
                navigateToNoteDetails(note.id)
            }
        )
    }
}

fun NavController.navigateToNotesList() {
    navigate(notesListRoute)
}
package br.com.alexf.ceep.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alexf.ceep.ui.screens.NotesListScreen

internal const val notesListRoute = "noteList"

fun NavGraphBuilder.notesListScreen(
    navController: NavHostController
) {
    composable(notesListRoute) {
        NotesListScreen(
            onNewNoteClick = {
                navController.navigateToNoteForm()
            },
            onNoteClick = { note ->
                navController.navigateToNoteDetails(note.id)
            })
    }
}

fun NavController.navigateToNotesList() {
    navigate(notesListRoute)
}
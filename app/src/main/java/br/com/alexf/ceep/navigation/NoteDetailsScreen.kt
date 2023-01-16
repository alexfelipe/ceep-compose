package br.com.alexf.ceep.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import br.com.alexf.ceep.ui.screens.NoteDetails

private const val noteDetailsRoute = "noteDetails"
private const val noteIdArgument = "noteId"

fun NavGraphBuilder.noteDetailsScreen(navController: NavHostController) {
    composable(
        "$noteDetailsRoute/{$noteIdArgument}",
        arguments = listOf(navArgument(noteIdArgument) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val noteId = backStackEntry.arguments?.getString("noteId")
        noteId?.let {
            NoteDetails(
                noteId = it,
                onDeleteNoteClick = {
                    navController.popBackStack()
                },
                onEditNoteClick = { noteId ->
                    navController.navigateToNoteForm(noteId)
                })
        } ?: navController.popBackStack()
    }
}

fun NavController.navigateToNoteDetails(id: String) {
    navigate("$noteDetailsRoute/$id")
}
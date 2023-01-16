package br.com.alexf.ceep.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.alexf.ceep.ui.screens.NoteFormScreen

private const val noteFormRoute = "noteForm"
private const val noteIdArgument = "noteId"

fun NavGraphBuilder.noteFormScreen(navController: NavHostController) {
    composable(
        noteFormRoute,
        arguments = listOf(navArgument(noteIdArgument) { nullable = true })
    ) {
        val noteId = it.arguments?.getString(noteIdArgument)
        NoteFormScreen(
            noteId,
            onSaveClick = {
                navController.popBackStack()
            }
        )
    }
}

fun NavController.navigateToNoteForm(
    id: String? = null
) {
    navigate("$noteFormRoute?$noteIdArgument=$id")
}
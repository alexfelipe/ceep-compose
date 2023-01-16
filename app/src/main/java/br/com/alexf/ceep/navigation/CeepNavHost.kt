package br.com.alexf.ceep.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun CeepNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = notesListRoute
    ) {
        notesListScreen(
            navigateToNoteform = {
                navController.navigateToNoteForm()
            },
            navigateToNoteDetails = { id ->
                navController.navigateToNoteDetails(id)
            },
        )
        noteFormScreen(navController)
        noteDetailsScreen(navController)
    }
}
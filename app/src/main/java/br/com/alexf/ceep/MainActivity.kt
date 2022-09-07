package br.com.alexf.ceep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.alexf.ceep.navigation.*
import br.com.alexf.ceep.ui.screens.NoteDetails
import br.com.alexf.ceep.ui.screens.NoteFormScreen
import br.com.alexf.ceep.ui.screens.NotesListScreen
import br.com.alexf.ceep.ui.theme.CeepTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CeepApp {
                NavigationRouting(navController)
            }
        }
    }

}

@Composable
private fun CeepApp(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    CeepTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
private fun NavigationRouting(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NOTES_LIST
    ) {
        composable(NOTES_LIST) {
            NotesListScreen(
                onNewNoteClick = {
                    navController.navigate(NOTE_FORM)
                },
                onNoteClick = { note ->
                    navController.navigate("$NOTE_DETAILS/${note.id}")
                })
        }
        composable(
            NOTE_FORM_WITH_PARAMETER,
            arguments = listOf(navArgument("noteId") { defaultValue = "" })
        ) {
            NoteFormScreen(
                it.arguments?.getString("noteId"),
                onSaveClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            NOTE_DETAILS_WITH_ARGUMENT,
            arguments = listOf(navArgument("noteId") {
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
                        navController.navigate("${NOTE_FORM}noteId={$noteId}")
                    })
            } ?: navController.popBackStack()
        }
    }
}

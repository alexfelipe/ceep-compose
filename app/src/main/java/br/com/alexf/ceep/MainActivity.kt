package br.com.alexf.ceep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.alexf.ceep.database.dao.NoteDao
import br.com.alexf.ceep.navigation.*
import br.com.alexf.ceep.screen.*
import br.com.alexf.ceep.ui.theme.CeepTheme
import br.com.alexf.ceep.ui.viewmodel.NoteFormUiState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationRouting(navController)
        }
    }

}

@Composable
private fun NavigationRouting(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = START
    ) {
        composable(START) {
            CeepApp {
                NotesListScreen(navController)
            }
        }
        composable(NOTES_LIST) {
            NotesListScreen(navController)
        }
        composable(
            NOTE_FORM_WITH_PARAMETER,
            arguments = listOf(navArgument("noteId") { defaultValue = "" })
        ) {
            NoteFormScreen(
                navController,
                it.arguments?.getString("noteId")
            )
        }
        composable(
            NOTE_DETAILS_WITH_ARGUMENT,
            arguments = listOf(navArgument("noteId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            NoteDetails(
                navController,
                backStackEntry.arguments?.getString("noteId")
            )
        }
    }
}

@Composable
private fun CeepApp(
    content: @Composable () -> Unit = {}
) {
    CeepTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CeepAppPreview() {
    CeepApp()
}
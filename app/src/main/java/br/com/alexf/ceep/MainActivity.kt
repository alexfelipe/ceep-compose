package br.com.alexf.ceep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.alexf.ceep.database.dao.NoteDao
import br.com.alexf.ceep.database.entity.NoteEntity
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.screen.NoteFormScreen
import br.com.alexf.ceep.screen.NotesListScreen
import br.com.alexf.ceep.ui.theme.CeepTheme
import br.com.alexf.ceep.ui.viewmodel.NoteViewModel
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
        startDestination = "home"
    ) {
        composable("home") {
            CeepApp(navController) {
                NotesListScreen(navController)
            }
        }
        composable("notesList") {
            NotesListScreen(navController)
        }
        composable("formNote") {
            NoteFormScreen(navController)
        }
    }
}

@Composable
private fun CeepApp(
    navController: NavController,
    content: @Composable () -> Unit = {}
) {
    CeepTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        onClick = {
                            navController.navigate("formNote")
                        },
                        icon = {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Add new note"
                            )
                        }, text = { Text("new note") })
                }) {
                content()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CeepAppPreview() {
    CeepApp(rememberNavController())
}
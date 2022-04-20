package br.com.alexf.ceep.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.alexf.ceep.ui.viewmodel.NoteDetailsScreenViewModel
import br.com.alexf.ceep.ui.viewmodel.NoteDetailsState
import kotlinx.coroutines.launch

@Composable
fun NoteDetails(
    navController: NavController,
    noteId: String?
) {
    val viewModel = hiltViewModel<NoteDetailsScreenViewModel>()
    LaunchedEffect(keys = emptyArray()) {
        noteId?.let {
            viewModel.findById(noteId)
        }
    }
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState
    NoteDetails(uiState,
        onDeleteNoteClick = {
            scope.launch {
                noteId?.let {
                    viewModel.remove(noteId)
                    navController.popBackStack()
                }
            }
        },
        onEditNoteClick = {
            scope.launch {
                noteId?.let {
                    navController.navigate("noteForm?noteId=$noteId")
                }
            }
        })
}

@Composable
private fun NoteDetails(
    uiState: NoteDetailsState,
    onDeleteNoteClick: () -> Unit = {},
    onEditNoteClick: () -> Unit = {}
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note details") },
                actions = {
                    IconButton(onClick = { onDeleteNoteClick() }) {
                        Icon(Icons.Default.Delete, "Delete the note")
                    }
                    IconButton(onClick = { onEditNoteClick() }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit the note")
                    }
                }
            )
        },
        scaffoldState = scaffoldState
    ) {
        val scrollState = rememberScrollState(0)
        Column(Modifier.verticalScroll(scrollState)) {
            Text(
                text = uiState.title,
                Modifier
                    .fillMaxWidth()
                    .padding(
                        16.dp
                    ),
                fontSize = 36.sp
            )
            Text(
                text = uiState.message,
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                fontSize = 24.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NoteDetailsPreview() {
    NoteDetails(
        uiState = NoteDetailsState(
            title = LoremIpsum(10).values.first(),
            message = LoremIpsum(50).values.first()
        )
    ) {

    }
}


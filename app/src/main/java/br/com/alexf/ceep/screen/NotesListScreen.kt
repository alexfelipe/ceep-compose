@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package br.com.alexf.ceep.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.navigation.NotesListDirections
import br.com.alexf.ceep.ui.viewmodel.NotesListScreenViewModel
import br.com.alexf.ceep.ui.viewmodel.NotesListUiState

@Composable
fun NotesListScreen(
    navController: NavController,
    directions: NotesListDirections = NotesListDirections(navController)
) {
    val viewModel = hiltViewModel<NotesListScreenViewModel>()
    val uiState = viewModel.uiState
    LaunchedEffect(null) {
        viewModel.findAll()
    }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    directions.goToNoteForm()
                },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add new note"
                    )
                }, text = { Text("new note") })
        }) {
        NotesListWithProgress(uiState) { note ->
            directions.goToDetails(note.id)
        }
    }

}

@Composable
private fun NotesListWithProgress(
    uiState: NotesListUiState,
    onItemClick: (note: Note) -> Unit = {}
) {
    Column {
        if (uiState.loading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
        NotesListScreen(
            uiState, onItemClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NotesListScreen(
    uiState: NotesListUiState,
    onItemClick: (note: Note) -> Unit = {}
) {
    val notes = uiState.notes
    if (notes.isNotEmpty()) {
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(notes, spans = null) { note ->
                NoteItem(note, onItemClick)
            }
        }
    }
    if (!uiState.loading && uiState.notes.isEmpty()
    ) {
        NotFoundNotesMessage()
    }

}

@Composable
private fun NotFoundNotesMessage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Notes not found",
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            Icons.Filled.Warning,
            contentDescription = "warning icon",
            Modifier.size(48.dp)
        )
    }
}

@Composable
private fun NoteItem(
    note: Note,
    onNoteClick: (clickedNote: Note) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(100.dp)
            .padding(8.dp)
            .clickable(onClick = { onNoteClick(note) }),
        elevation = 4.dp
    ) {
        Column {
            Text(
                text = note.title,
                modifier = Modifier.padding(8.dp),
                fontSize = 24.sp
            )
            Text(
                text = note.message,
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NotesListWithProgress() {
    NotesListWithProgress(
        uiState = NotesListUiState(
            notes = List(3, init = {
                Note(
                    id = it.toString(),
                    title = "title $it",
                    message = "message $it"
                )
            }), loading = true
        )
    )
}

@Preview(showSystemUi = true)
@Composable
private fun NotesListWithNotesPreview() {
    NotesListScreen(
        uiState = NotesListUiState(
            notes = List(10, init = {
                Note(
                    id = it.toString(),
                    title = "title $it",
                    message = "message $it"
                )
            })
        )
    )
}

@Preview(showSystemUi = true)
@Composable
private fun NotesListWithoutNotesPreview() {
    NotesListScreen(
        uiState = NotesListUiState(
            emptyList(),
            loading = false
        )
    )
}
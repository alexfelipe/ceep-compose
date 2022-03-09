package br.com.alexf.ceep.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.ui.viewmodel.NoteViewModel

@Composable
fun NotesListScreen(
    navController: NavController
) {
    val hiltViewModel = hiltViewModel<NoteViewModel>()
    val notes by hiltViewModel
        .findAll()
        .collectAsState(initial = emptyList())
    NotesListScreen(notes)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NotesListScreen(notes: List<Note>) {
    if (notes.isNotEmpty()) {
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(notes, spans = null) { note ->
                NoteItem(note)
            }
        }
    } else {
        NotFoundNotesMessage()
    }
}

@Composable
private fun NotFoundNotesMessage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
private fun NoteItem(note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(100.dp)
            .padding(8.dp)
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
private fun NotesListScreenWithNotesPreview() {
    NotesListScreen(
        List(10, init = {
            Note(
                id = it.toString(),
                title = "title $it",
                message = "message $it"
            )
        })
    )
}

@Preview(showSystemUi = true)
@Composable
private fun NotesListScreenWithoutNotesPreview() {
    NotesListScreen(emptyList())
}
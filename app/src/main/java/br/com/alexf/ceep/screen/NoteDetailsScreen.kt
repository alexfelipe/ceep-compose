package br.com.alexf.ceep.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.alexf.ceep.ui.viewmodel.NoteDetailsScreenViewModel
import br.com.alexf.ceep.ui.viewmodel.NoteDetailsState

@Composable
fun NoteDetailsScreen(
    noteId: String?
) {
    val viewModel = hiltViewModel<NoteDetailsScreenViewModel>()
    noteId?.let {
        viewModel.findById(noteId)
    }
    val uiState = viewModel.uiState
    NoteDetailsScreen(uiState, onDeleteNoteClick = {
        viewModel.showSnackBar()
    })
}

@Composable
private fun NoteDetailsScreen(
    uiState: NoteDetailsState,
    onDeleteNoteClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note details") },
                actions = {
                    IconButton(onClick = onDeleteNoteClick) {
                        Icon(Icons.Default.Delete, "Delete the note")
                    }
                }
            )
        }
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
            if (uiState.showSnackbar) {

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NoteDetailsScreenPreview() {
    NoteDetailsScreen(
        uiState = NoteDetailsState(
            title = LoremIpsum(10).values.first(),
            message = LoremIpsum(50).values.first()
        )
    ) {

    }
}


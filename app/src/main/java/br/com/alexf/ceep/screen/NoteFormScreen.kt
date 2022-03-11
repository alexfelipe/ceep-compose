package br.com.alexf.ceep.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.alexf.ceep.ui.viewmodel.NoteFormScreenViewModel
import br.com.alexf.ceep.ui.viewmodel.NoteFormUiState
import kotlinx.coroutines.launch

@Composable
fun NoteFormScreen(
    navController: NavController,
    noteId: String? = null
) {
    val viewModel = hiltViewModel<NoteFormScreenViewModel>()
    val scope = rememberCoroutineScope()
    LaunchedEffect(keys = emptyArray(), block = {
        noteId?.let {
            viewModel.findById(it)
        }
    })
    val uiState = viewModel.uiState
    NoteForm(uiState,
        onTitleChange = {
            viewModel.setTitle(it)
        },
        onMessageChange = {
            viewModel.setMessage(it)
        },
        onClickSave = {
            scope.launch {
                viewModel.save()
                navController.popBackStack()
            }
        })
}

@Composable
private fun NoteForm(
    uiState: NoteFormUiState,
    onMessageChange: (String) -> Unit = {},
    onClickSave: () -> Unit = {},
    onTitleChange: (String) -> Unit = {}
) {
    Column(
        Modifier
            .padding(8.dp)
            .verticalScroll(ScrollState(0))
    ) {
        OutlinedTextField(
            value = uiState.title,
            onValueChange = onTitleChange,
            label = { Text("Title") },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.message,
            onValueChange = onMessageChange,
            label = {
                Text("Message")
            },
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(200.dp)
        )
        Button(
            onClick = onClickSave,
            Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Done, "save the note")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NoteFormPreview() {
    NoteForm(
        NoteFormUiState(
            "My first note",
            "this is a note for test"
        )
    )
}
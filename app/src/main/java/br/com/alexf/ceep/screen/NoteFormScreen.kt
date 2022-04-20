package br.com.alexf.ceep.screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    NoteForm(
        uiState,
        onMessageChange = {
            viewModel.setMessage(it)
        },
        onSaveClick = {
            scope.launch {
                viewModel.save()
                navController.popBackStack()
            }
        },
        onTitleChange = {
            viewModel.setTitle(it)
        },
    )
}

@Composable
fun NoteForm(
    uiState: NoteFormUiState,
    onMessageChange: (String) -> Unit = {},
    onTitleChange: (String) -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                val title = if (uiState.isNewNote) "New note" else "Edit note"
                Text(title)
            }, actions = {
                IconButton(onClick = onSaveClick) {
                    Icon(Icons.Default.Done, "Save the note")
                }
            })
        },
    ) {
        Column(
            modifier = Modifier.scrollable(
                rememberLazyListState(),
                orientation = Orientation.Vertical
            )
        ) {
            NoteFormTextField(
                uiState.title,
                onTitleChange,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                label = "Title",
                textStyle = TextStyle(fontSize = 32.sp)
            )
            NoteFormTextField(
                value = uiState.message,
                onValueChange = onMessageChange,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                    .requiredHeight(200.dp),
                label = "Message",
                textStyle = TextStyle(fontSize = 20.sp)
            )
        }
    }
}

@Composable
private fun NoteFormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    textStyle: TextStyle = TextStyle()
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    label,
                    fontSize = textStyle.fontSize,
                    color = Color.Gray.copy(alpha = 0.7f)
                )
            }
            innerTextField()
        },
        modifier = modifier,
        textStyle = textStyle
    )
}

@Preview(showSystemUi = true)
@Composable
fun NoteFormPreview() {
    NoteForm(
        NoteFormUiState(
            "My first note",
            "this is a note for test"
        ),
    )
}
package br.com.alexf.ceep.navigation

import androidx.navigation.NavController

const val START = "home"
const val NOTES_LIST = "notesList"
const val NOTE_FORM = "noteForm"
const val NOTE_FORM_WITH_PARAMETER = "$NOTE_FORM?noteId={noteId}"
const val NOTE_DETAILS = "noteDetails"
const val NOTE_DETAILS_WITH_ARGUMENT = "$NOTE_DETAILS/{noteId}"

class NotesListDirections(
    private val navController: NavController
) {

    fun goToDetails(id: String) {
        navController.navigate("$NOTE_DETAILS/$id")
    }

    fun goToNoteForm() {
        navController.navigate(NOTE_FORM_WITH_PARAMETER)
    }

}
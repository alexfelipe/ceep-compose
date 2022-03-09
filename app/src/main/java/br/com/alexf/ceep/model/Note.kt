package br.com.alexf.ceep.model

import br.com.alexf.ceep.database.entity.NoteEntity
import java.util.*

class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val message: String
)

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = this.id,
        title = this.title,
        message = this.message
    )
}

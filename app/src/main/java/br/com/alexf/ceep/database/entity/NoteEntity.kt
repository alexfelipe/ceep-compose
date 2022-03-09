package br.com.alexf.ceep.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.alexf.ceep.model.Note
import java.util.*

@Entity(tableName = "note")
class NoteEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val message: String
)

fun NoteEntity.toNote(): Note {
    return Note(
        this.id,
        this.title,
        this.message
    )
}

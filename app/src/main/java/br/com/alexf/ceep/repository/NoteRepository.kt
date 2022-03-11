package br.com.alexf.ceep.repository

import br.com.alexf.ceep.database.dao.NoteDao
import br.com.alexf.ceep.database.entity.NoteEntity
import br.com.alexf.ceep.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val dao: NoteDao
) {

    fun findAll(): Flow<List<NoteEntity>> = dao.findAll()

    suspend fun save(note: NoteEntity) {
        dao.save(note)
    }

    fun findById(noteId: String): Flow<NoteEntity> = dao.findById(noteId)

    suspend fun remove(id: String) {
        dao.remove(id)
    }

}


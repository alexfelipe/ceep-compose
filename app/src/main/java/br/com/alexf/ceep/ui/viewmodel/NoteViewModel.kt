package br.com.alexf.ceep.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alexf.ceep.database.entity.NoteEntity
import br.com.alexf.ceep.database.entity.toNote
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    fun findAll(): Flow<List<Note>> {
        return repository
            .findAll()
            .map { entities ->
                entities.map { entity ->
                    entity.toNote()
                }
            }
    }

}
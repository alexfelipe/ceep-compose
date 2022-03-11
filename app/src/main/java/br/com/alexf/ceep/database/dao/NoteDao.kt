package br.com.alexf.ceep.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.alexf.ceep.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun findAll(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :noteId")
    fun findById(noteId: String): Flow<NoteEntity>

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun remove(id: String)

}

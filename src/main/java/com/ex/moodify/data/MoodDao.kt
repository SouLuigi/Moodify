package com.ex.moodify.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface MoodDao {
    @Insert
    suspend fun insert(moodEntry: MoodEntry)

    @Query("SELECT * FROM mood_entries ORDER BY timestamp DESC")
    fun getAllEntries(): StateFlow<List<MoodEntry>>

    @Query("DELETE FROM mood_entries")
    suspend fun deleteAll() // útil para testes ou limpar dados
}
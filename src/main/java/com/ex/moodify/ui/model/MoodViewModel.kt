package com.ex.moodify.ui.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ex.moodify.data.MoodEntry
import com.ex.moodify.data.MoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MoodViewModel(private val repository: MoodRepository) : ViewModel() {

    private val _saveSuccessful = MutableStateFlow<Boolean>(false)
    val saveSuccessful: StateFlow<Boolean?> = _saveSuccessful.asStateFlow()
    val moods: StateFlow<List<MoodEntry>> = repository.getAllMoods()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun debugPrintAllEntries() {
        viewModelScope.launch {
            repository.getAllMoods()
                .collect { entries ->
                    entries.forEach { entry ->
                        Log.d(
                            "MoodDebug",
                            "ID: ${entry.id}, Mood: ${entry.mood}, Desc: ${entry.description}, Time: ${entry.timestamp}"
                        )
                    }
                }
        }
    }

    fun saveMood(mood: String, description: String?) {
        viewModelScope.launch {
            try {
                val entry = MoodEntry(
                    mood = mood,
                    description = description,
                    timestamp = LocalDateTime.now()
                )
                repository.insertMood(entry)
                _saveSuccessful.value = true
            } catch (e: Exception) {
                _saveSuccessful.value = false
            }
        }
    }
}

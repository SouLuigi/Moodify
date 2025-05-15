package com.ex.moodify.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDateTime

@Entity(tableName = "mood_entries")
data class MoodEntry(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mood: String,
    val description: String?,
    val timestamp: LocalDateTime = LocalDateTime.now()
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }
}

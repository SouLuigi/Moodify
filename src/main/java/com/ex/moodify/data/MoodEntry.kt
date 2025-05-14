package com.ex.moodify.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "mood_entries")
data class MoodEntry(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mood: String,
    val description: String?,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
object Converters {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String? {
        return value?.format(formatter)
    }

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            LocalDateTime.parse(it, formatter)
        }
    }
}

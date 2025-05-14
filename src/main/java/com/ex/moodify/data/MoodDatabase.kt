package com.ex.moodify.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MoodEntry::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class MoodDatabase: RoomDatabase() {
    abstract fun moodDao(): MoodDao
}
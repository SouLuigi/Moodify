package com.ex.moodify.data

import kotlinx.coroutines.flow.Flow

class MoodRepository(private val moodDao: MoodDao) {
    suspend fun saveMoodEntry(moodEntry: MoodEntry) {
        moodDao.insert(moodEntry)
    }

    fun getHistory(): Flow<List<MoodEntry>> {
        return moodDao.getAllEntries()
    }
    suspend fun clearHistory() {
        moodDao.deleteAll()
    }
}

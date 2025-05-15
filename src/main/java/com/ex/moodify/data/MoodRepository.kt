package com.ex.moodify.data

import kotlinx.coroutines.flow.Flow

class MoodRepository(private val moodDao: MoodDao) {

    fun getAllMoods(): Flow<List<MoodEntry>> = moodDao.getAllMoods()

    suspend fun insertMood(mood: MoodEntry) {
        moodDao.insertMood(mood)
    }

    suspend fun deleteMood(mood: MoodEntry) {
        moodDao.deleteMood(mood)
    }
}

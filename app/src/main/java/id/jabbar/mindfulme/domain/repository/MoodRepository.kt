package id.jabbar.mindfulme.domain.repository

import id.jabbar.mindfulme.domain.model.Mood

interface MoodRepository {
  suspend fun insertMood(mood: Mood)
  suspend fun getMoodsByDateRange(startDate: String, endDate: String): List<Mood>
  suspend fun getMoodByDate(date: String): Mood?
  suspend fun getAllMoods(): List<Mood>
  suspend fun deleteMood(mood: Mood)
}
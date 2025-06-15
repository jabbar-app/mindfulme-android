package id.jabbar.mindfulme.domain.model

data class Mood(
  val id: Long = 0,
  val date: String, // yyyy-MM-dd format
  val moodLevel: Int, // 1-5 (Sad to Happy)
  val note: String = "",
  val timestamp: Long = System.currentTimeMillis()
)
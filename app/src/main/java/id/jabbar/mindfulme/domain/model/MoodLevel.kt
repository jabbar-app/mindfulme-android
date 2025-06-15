package id.jabbar.mindfulme.domain.model

enum class MoodLevel(val value: Int, val emoji: String, val description: String) {
  VERY_SAD(1, "😢", "Very Sad"),
  SAD(2, "😔", "Sad"),
  NEUTRAL(3, "😐", "Neutral"),
  HAPPY(4, "🙂", "Happy"),
  VERY_HAPPY(5, "😄", "Very Happy");

  companion object {
    fun fromValue(value: Int): MoodLevel = entries.first { it.value == value }
  }
}

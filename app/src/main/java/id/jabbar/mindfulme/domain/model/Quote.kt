package id.jabbar.mindfulme.domain.model

data class Quote(
  val id: String,
  val content: String,
  val author: String,
  val dateReceived: String,
  val isFavorite: Boolean = false
)

package id.jabbar.mindfulme.domain.repository

import id.jabbar.mindfulme.domain.model.Quote

interface QuoteRepository {
  suspend fun getQuoteOfTheDay(): Quote
  suspend fun getAllQuotes(): List<Quote>
  suspend fun toggleFavorite(quoteId: String)
  suspend fun getQuoteById(id: String): Quote?
}
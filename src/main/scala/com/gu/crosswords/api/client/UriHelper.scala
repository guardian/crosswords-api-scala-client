package com.gu.crosswords.api.client

import org.joda.time.LocalDate
import com.gu.crosswords.api.client.models.Type

object UriHelper {
  def forDate(endpoint: String, date: LocalDate, apiKey: String): String =
    s"$endpoint/${date.toString()}.json?api-key=$apiKey"

  def forCrossword(endpoint: String, crosswordType: Type, id: Int, apiKey: String): String =
    s"$endpoint/${Type.byType(crosswordType)}/$id.json?api-key=$apiKey"
}

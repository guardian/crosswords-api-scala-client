package com.theguardian.crosswords.api.client

import org.joda.time.LocalDate

object UriHelper {
  def forDate(endpoint: String, date: LocalDate, apiKey: String): String =
    s"$endpoint/${date.toString()}.json?api-key=$apiKey"

}

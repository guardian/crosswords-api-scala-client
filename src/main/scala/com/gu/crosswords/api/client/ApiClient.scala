package com.gu.crosswords.api.client

import scala.concurrent.{ExecutionContext, Future}
import org.joda.time.LocalDate
import com.gu.crosswords.api.client.models.{Crossword, Type, DateResponse}
import models.JsonImplicits._
import play.api.libs.json.{JsError, JsSuccess, Reads, Json}

object ApiClient {
  val apiEndpoint = "http://crosswords.guardianapis.com"
}

case class StatusError(statusCode: Int, statusLine: String)
  extends Exception(s"Expected 200 OK, got $statusCode $statusLine")
case class ParseError(cause: JsError)
  extends Exception("Unable to parse JSON returned by Crosswords API")

case class ApiClient(apiKey: String, http: Http, apiEndpoint: String = ApiClient.apiEndpoint) {
  /** Extracts A from the JSON string or throws ParseError */
  private def extract[A: Reads](input: String): A =
    Json.fromJson[A](Json.parse(input)) match {
      case JsSuccess(a, _) => a
      case error @ JsError(_) => throw new ParseError(error)
    }

  private def extract[A: Reads](response: Response): A = response match {
    case Response(200, _, body) => extract[A](body)
    case Response(errorCode, _, errorLine) => throw new StatusError(errorCode, errorLine)
  }

  def forDate(date: LocalDate)(implicit executionContext: ExecutionContext): Future[DateResponse] =
    http.get(UriHelper.forDate(apiEndpoint, date, apiKey)).map(extract[DateResponse])

  def forToday(implicit executionContext: ExecutionContext): Future[DateResponse] = forDate(LocalDate.now())

  /** For some horrible reason you have to know the type of the crossword to look it up by ID */
  def getCrossword(crosswordType: Type, number: Int)(implicit executionContext: ExecutionContext): Future[Crossword] =
    http.get(UriHelper.forCrossword(apiEndpoint, crosswordType, number, apiKey)).map(extract[Crossword])
}

package com.gu.crosswords.api.client

import scala.concurrent.Future

case class Response(statusCode: Int, statusLine: String, body: String)

trait Http {
  def get(uri: String): Future[Response]
}

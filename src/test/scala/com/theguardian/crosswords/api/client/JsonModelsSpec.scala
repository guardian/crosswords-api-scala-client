package com.theguardian.crosswords.api.client

import org.specs2.mutable.Specification
import play.api.libs.json.{JsSuccess, Json}
import com.theguardian.crosswords.api.client.lib.ResourcesHelper
import models.JsonImplicits._
import com.theguardian.crosswords.api.client.models._
import com.theguardian.crosswords.api.client.models.DateResponse
import scala.Some
import com.theguardian.crosswords.api.client.models.Creator
import play.api.libs.json.JsSuccess

class JsonModelsSpec extends Specification with ResourcesHelper {
  def getTestResource(path: String) =
    slurp(path).getOrElse(throw new RuntimeException(s"Could not find test resource $path"))

  "Json.parse" should {
    "be able to parse a response for a given date" in {
      val resource = getTestResource("2014-01-24.json")

      val JsSuccess(result, _) = Json.fromJson[DateResponse](Json.parse(resource))

      val crosswords = result.crosswords.values.toList

      crosswords.length mustEqual 2

      val Some(cryptic) = result.crosswords.get("/cryptic/26165")
      cryptic.`type` mustEqual Cryptic
      cryptic.creator mustEqual Some(Creator("Chifonie", "http://www.theguardian.com/profile/chifonie"))

      val Some(quick) = result.crosswords.get("/quick/13638")
      quick.`type` mustEqual Quick
      quick.hasNumbers mustEqual true
    }

    "be able to parse a response containing 'quiptic' crosswords" in {
      val resource = getTestResource("2014-01-27.json")

      val JsSuccess(result, _) = Json.fromJson[DateResponse](Json.parse(resource))

      val Some(quiptic) = result.crosswords.get("/quiptic/741")
      quiptic.`type` mustEqual Quiptic
      quiptic.number mustEqual 741
      quiptic.pdf mustEqual None
    }

    "be able to parse an entry without a format" in {
      val resource = getTestResource("2014-01-29.json")

      val JsSuccess(result, _) = Json.fromJson[DateResponse](Json.parse(resource))

      result.crosswords("/cryptic/26169").entries(27).format mustEqual None
    }
  }
}

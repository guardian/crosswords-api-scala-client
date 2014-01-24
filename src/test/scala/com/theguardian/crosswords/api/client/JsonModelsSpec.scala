package com.theguardian.crosswords.api.client

import org.specs2.mutable.Specification
import play.api.libs.json.{JsSuccess, Json}
import com.theguardian.crosswords.api.client.lib.ResourcesHelper
import models.JsonImplicits._
import com.theguardian.crosswords.api.client.models.{Quick, Creator, Cryptic, DateResponse}

class JsonModelsSpec extends Specification with ResourcesHelper {
  "Json.parse" should {
    "be able to parse a response for a given date" in {
      val resource = slurp("2014-01-24.json")
        .getOrElse(throw new RuntimeException("Could not find test resource 2014-01-24.json"))

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
  }
}

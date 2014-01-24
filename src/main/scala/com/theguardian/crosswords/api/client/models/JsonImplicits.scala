package com.theguardian.crosswords.api.client.models

import play.api.libs.json._

object JsonImplicits {
  implicit val creatorFormats = Json.format[Creator]
  implicit val dimensionsFormats = Json.format[Dimensions]
  implicit val positionFormats = Json.format[Position]

  implicit val directionFormats = new Format[Direction] {
    def reads(json: JsValue): JsResult[Direction] = json match {
      case JsString("across") => JsSuccess(Across)
      case JsString("down") => JsSuccess(Down)
      case _ => JsError("Direction must be either 'across' or 'down'")
    }

    def writes(o: Direction): JsValue = o match {
      case Across => JsString("across")
      case Down => JsString("down")
    }
  }

  implicit val separatorLocationsFormats = Json.format[SeparatorLocations]

  implicit val entryFormats = Json.format[Entry]

  implicit val typeFormats = new Format[Type] {
    def reads(json: JsValue): JsResult[Type] = json match {
      case JsString("cryptic") => JsSuccess(Cryptic)
      case JsString("quick") => JsSuccess(Quick)
      case _ => JsError("Type must be either 'cryptic' or 'quick'")
    }

    def writes(o: Type): JsValue = o match {
      case Cryptic => JsString("cryptic")
      case Quick => JsString("quick")
    }
  }

  implicit val crosswordFormats = Json.format[Crossword]
  implicit val dateResponseFormats = Json.format[DateResponse]
}

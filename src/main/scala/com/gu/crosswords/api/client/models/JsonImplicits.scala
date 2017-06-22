package com.gu.crosswords.api.client.models

import play.api.libs.json._
import com.gu.crosswords.api.client.lib.Maps._

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

  private val typeMap: Map[String, Type] = Map(
    "cryptic" -> Cryptic,
    "quick" -> Quick,
    "prize" -> Prize,
    "everyman" -> Everyman,
    "azed" -> Azed,
    "genius" -> Genius,
    "speedy" -> Speedy,
    "special" -> Special,
    "quiptic" -> Quiptic,
    "weekend" -> Weekend
  )

  private val inverseTypeMap = typeMap.inverse

  implicit val typeFormats = new Format[Type] {
    def reads(json: JsValue): JsResult[Type] = json match {
      case JsString(s) if typeMap.contains(s) => JsSuccess(typeMap(s))
      case _ => JsError(s"Crossword type string must be one of ${typeMap.keys.mkString(",")}")
    }

    def writes(o: Type): JsValue = JsString(inverseTypeMap(o))
  }

  implicit val crosswordFormats = Json.format[Crossword]
  implicit val dateResponseFormats = Json.format[DateResponse]
}

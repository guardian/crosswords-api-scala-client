package com.gu.crosswords.api.client.models

import com.gu.crosswords.api.client.lib.Maps._
import org.joda.time._

case class Creator(
  name: String,
  webUrl: String
)

case class Dimensions(
  cols: Int,
  rows: Int
)

case class Position(
  x: Int,
  y: Int
)

sealed trait Direction

case object Across extends Direction
case object Down extends Direction

case class SeparatorLocations(
  `,`: Option[List[Int]]
)

case class Entry(
  clue: String,
  direction: Direction,
  format: Option[String],
  group: List[String],
  humanNumber: String,
  id: String,
  length: Int,
  number: Int,
  position: Position,
  separatorLocations: SeparatorLocations,
  solution: Option[String]
)

object Type {
  val byString: Map[String, Type] = Map(
    "cryptic" -> Cryptic,
    "quick" -> Quick,
    "quiptic" -> Quiptic,
    "prize" -> Prize,
    "everyman" -> Everyman,
    "azed" -> Azed,
    "special" -> Special,
    "genius" -> Genius,
    "speedy" -> Speedy
  )

  val byType = byString.inverse

  def fromString(typeString: String): Option[Type] = byString.get(typeString)
}

sealed trait Type

case object Cryptic extends Type
case object Quick extends Type
case object Quiptic extends Type
case object Prize extends Type
case object Everyman extends Type
case object Azed extends Type
case object Special extends Type
case object Genius extends Type
case object Speedy extends Type

case class Crossword(
  creator: Option[Creator],
  date: LocalDate,
  dimensions: Dimensions,
  entries: List[Entry],
  hasNumbers: Boolean,
  name: String,
  number: Int,
  pdf: Option[String],
  randomCluesOrdering: Boolean,
  solutionAvailable: Boolean,
  `type`: Type
)
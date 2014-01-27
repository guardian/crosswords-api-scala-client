package com.theguardian.crosswords.api.client.models

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
  format: String,
  group: List[String],
  humanNumber: String,
  id: String,
  length: Int,
  number: Int,
  position: Position,
  separatorLocations: SeparatorLocations,
  solution: String
)

sealed trait Type

case object Cryptic extends Type
case object Quick extends Type
case object Quiptic extends Type

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
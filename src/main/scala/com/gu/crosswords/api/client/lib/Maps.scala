package com.gu.crosswords.api.client.lib

object Maps {
  implicit class RichMap[A, B](map: Map[A, B]) {
    def inverse: Map[B, A] = (map map { case (k, v) => v -> k }).toMap
  }
}

package com.theguardian.crosswords.api.client.lib

object Maps {
  implicit class RichMap[A, B](map: Map[A, B]) {
    // LOL MAP MAP MAP
    def inverse: Map[B, A] = (map map { case (k, v) => v -> k }).toMap
  }
}

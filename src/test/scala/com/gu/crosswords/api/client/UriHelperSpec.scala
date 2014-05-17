package com.gu.crosswords.api.client

import org.specs2.mutable.Specification
import org.joda.time.LocalDate
import com.gu.crosswords.api.client.models.Cryptic

class UriHelperSpec extends Specification {
  "forDate" should {
    "correctly construct date urls" in {
      UriHelper.forDate("http://crosswords.guardianapis.com", new LocalDate(1987, 2, 5), "robbo") mustEqual
        "http://crosswords.guardianapis.com/1987-02-05.json?api-key=robbo"
    }

    "correctly construct crossword urls" in {
      UriHelper.forCrossword("http://crosswords.guardianapis.com", Cryptic, 20172, "robbo") mustEqual
        "http://crosswords.guardianapis.com/cryptic/20172.json?api-key=robbo"
    }
  }
}

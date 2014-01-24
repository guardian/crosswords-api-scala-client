package com.theguardian.crosswords.api.client

import org.specs2.mutable.Specification
import org.joda.time.LocalDate

class UriHelperSpec extends Specification {
  "forDate" should {
    "correctly construct date urls" in {
      UriHelper.forDate("http://crosswords.guardianapis.com", new LocalDate(1987, 2, 5), "robbo") mustEqual
        "http://crosswords.guardianapis.com/1987-02-05.json?api-key=robbo"
    }
  }
}

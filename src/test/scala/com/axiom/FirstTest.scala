package com.axiom

import org.scalatest._, wordspec._, matchers._



class FirstTest extends AnyWordSpec with should.Matchers{
  "this" should {
    "work" in {
        import  com.axiom.model.shared._, dto._

        
        // Patient("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")

        true should be(true)
    }
  }
}

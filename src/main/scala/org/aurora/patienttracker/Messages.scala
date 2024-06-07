package org.aurora.patienttracker

import scala.scalajs.js.JSON
import scala.scalajs.js.Any

import utilities.JsonImplicits
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._

trait Message {
    val command: String
}

case class BasicMessage(content: String) extends Message {
    val command: String = "basicMessage"
    def toJson(): String = {
        Printer.noSpaces
            .copy(dropNullValues = true)
            .print(
              Encoder[BasicMessage]
                  .apply(this)
            )
    }
}

case class OpenFileMessage(
    firstName: String,
    lastName: String,
    unitNumber: String,
    command: String = "openFile"
) extends Message {

    def toJson(): String = {
        Printer.noSpaces
            .copy(dropNullValues = true)
            .print(
              Encoder[OpenFileMessage]
                  .apply(this)
            )
    }
}

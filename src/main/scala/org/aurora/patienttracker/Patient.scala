package org.aurora.patienttracker

import scala.scalajs.js.Date
import scala.scalajs.js.JSON
import scala.scalajs.js.Any

import utilities.JsonImplicits
import io.circe._, generic.auto._, parser._


case class Patient(
    unitNumber: String,
    var lastName: String,
    var firstName: String,
    var sex: String,
    var dob: String,
    var hcn: Option[String],
    var family: Option[String],
    var famPriv: Option[String],
    var hosp: Option[String],
    var flag: Option[String],
    var address1: Option[String],
    var address2: Option[String],
    var city: Option[String],
    var province: Option[String],
    var postalCode: Option[String],
    var homePhoneNumber: Option[String],
    var workPhoneNumber: Option[String],
    var OHIP: Option[String],
    var familyPhysician: Option[String],
    var attending: Option[String],
    var collab1: Option[String],
    var collab2: Option[String]
) {
    def toJson(): String = {
        Printer.noSpaces
            .copy(dropNullValues = false)
            .print(
              Encoder[Patient]
                  .apply(this)
            )
    }
}

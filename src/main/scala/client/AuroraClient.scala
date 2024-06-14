package client

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import org.scalajs.dom.HttpMethod
import org.aurora.shared.dto.Patient
import zio.json._
import scala.util.Random


case class AuroraClient() {

    val dataModelVar = Var(List.empty[Patient])

    // val GETUrl: String = "http://localhost:9000/patients"

    def populateTable(respString: String): Unit = {
        jsonDecoder(respString).map(item => dataModelVar.update(_ :+ item))
    }

    def jsonDecoder(jsonString: String): List[Patient] =
        jsonString.fromJson[List[Patient]].toOption.getOrElse(Nil)
        // decode[List[Patient]](jsonString) match {
        //     case Right(patients) => patients
        //     case Left(error) =>
        //         throw new RuntimeException(s"Failed to parse JSON: $error")
        // }

    def addEntryToDataModelVar(): EventStream[String] = {
        val random = new Random()
        val randomNumber = random.nextInt(90000000) + 10000000
        val newPatient = Patient(
          "TB" + randomNumber.toString(),
          "",
          "",
          "",
          "2023-04-04",
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None,
          None
        )
        dataModelVar.update((items) => {
            newPatient :: items
        })
        FetchStream.post(
          "http://localhost:9000/patients",
          _.body(newPatient.toJson)
        )
    }

    def updateEntryInDataModelVar(
        itemId: String,
        fieldName: String,
        newValue: String
    ): EventStream[String] = {
        val item = dataModelVar.now().find(_.unitNumber == itemId).get
        println(s"Updating ${item.unitNumber} at field ${fieldName}...")
        dataModelVar
            .update((items) => {
                items.map(patient => {
                    patient.unitNumber == item.unitNumber match {
                        case true => {
                            updatePatient(
                              patient,
                              fieldName,
                              newValue
                            )
                        }
                        case false => patient
                    }
                })
            })
        FetchStream.put(
          s"http://localhost:9000/patients/${item.unitNumber}",
          _.body(
            updatePatient(
              item,
              fieldName,
              newValue
            )
                .toJson
          )
        )
    }

    def deleteEntryInDataModelVar(unitNumber: String): EventStream[String] = {

        println("Deleting " + unitNumber + "...")
        dataModelVar.update((items) => {
            items.filter(_.unitNumber != unitNumber)
        })
        FetchStream.apply(
          method = _.DELETE,
          url = s"http://localhost:9000/patients/${unitNumber}"
        )
    }

    def updatePatient(
        patient: Patient,
        fieldName: String,
        newValue: String
    ): Patient = {
        fieldName match {
            case "unitNumber" =>
                println("Cannot change unitnumber")
            case "lastName"  => patient.copy(lastName = newValue)
            case "firstName" => patient.copy(firstName = newValue)
            case "sex"       => patient.copy(sex = newValue)
            case "dob"       => patient.copy(dob = newValue)
            case "hcn"       => patient.copy(hcn = Option(newValue))
            case "family"    => patient.copy(family = Option(newValue))
            case "famPriv"   => patient.copy(famPriv = Option(newValue))
            case "hosp"      => patient.copy(hosp = Option(newValue))
            case "flag"      => patient.copy(flag = Option(newValue))
            case "address1" =>
                patient.copy(address1 = Option(newValue))
            case "address2" =>
                patient.copy(address2 = Option(newValue))
            case "city" => patient.copy(city = Option(newValue))
            case "province" =>
                patient.copy(province = Option(newValue))
            case "postalCode" =>
                patient.copy(postalCode = Option(newValue))
            case "homePhoneNumber" =>
                patient.copy(homePhoneNumber = Option(newValue))
            case "workPhoneNumber" =>
                patient.copy(workPhoneNumber = Option(newValue))
            case "OHIP" => patient.copy(OHIP = Option(newValue))
            case "familyPhysician" =>
                patient.copy(familyPhysician = Option(newValue))
            case "attending" =>
                patient.copy(attending = Option(newValue))
            case "collab1" => patient.copy(collab1 = Option(newValue))
            case "collab2" => patient.copy(collab2 = Option(newValue))
        }
        patient
    }

}

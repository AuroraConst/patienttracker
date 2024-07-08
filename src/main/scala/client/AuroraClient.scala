package client

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import org.scalajs.dom.HttpMethod
import com.axiom.model.shared.dto.Patient
import zio.json._
import scala.util.Random

import com.axiom.patienttracker._, roughdraft._
import com.axiom.model.js.DataModel
import com.axiom.model.js.Fetch
import scala.util.Try
import com.raquo.airstream.ownership.OneTimeOwner
import org.scalajs.dom
object AuroraClient {

    // val dataModelVar = Var(List.empty[Patient])
    // DataModel.periodicFetch .addObserver(dataModelVar.writer)
      

    
    //TODO move to filtered l)



    // import com.axiom.model._, patientfilter._
    // def updateFilteredList(searchstring:String) = 
    //     val search = parseSearchTermsForPatient(searchstring)
    //     dom.console.log(s"searching for... $search"  )
    //     filteredList.set(dataModelVar.now())//.filter(search.include(_)))
        // filteredList.set(dataModelVar.now().filter(search.include(_)))
    

    def addEntryToDataModelVar(): EventStream[String] = {
        //TODO this belongs in the model (dataimportcsv3s)
        // val random = new Random()
        // val randomNumber = random.nextInt(90000000) + 10000000
        // val newPatient = Patient(
        //   "TB" + randomNumber.toString(), 
        //   "TB" + randomNumber.toString(),
        //   "",
        //   "",
        //   "",
        //   "2023-04-04",
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None,
        //   None
        // )
        // dataModelVar.update((items) => {
        //     newPatient :: items
        // })
        // FetchStream.post(
        //   "http://localhost:9000/patients",
        //   _.body(newPatient.toJson)
        // )
        EventStream.empty
    }

    def updateEntryInDataModelVar(
        itemId: String,
        fieldName: String,
        newValue: String
    ): EventStream[String] = {
        //TODO this belongs in the model (dataimportcsv3s)
        // val item = dataModelVar.now().find(_.unitNumber == itemId)
        // println(s"Updating ${item} at field ${fieldName}...")
        // dataModelVar
        //     .update((items) => {
        //         items.map(patient => {
        //             if (item.isEmpty) patient
        //             else
        //             patient.unitNumber == item.get.unitNumber match {
        //                 case true => {
        //                     updatePatient(
        //                       patient,
        //                       fieldName,
        //                       newValue
        //                     )
        //                 }
        //                 case false => patient
        //             }
        //         })
        //     })

         EventStream.empty   
        // FetchStream.put(
        //   s"http://localhost:9000/patients/${item.unitNumber}",
        //   _.body(
        //     updatePatient(
        //       item,
        //       fieldName,
        //       newValue
        //     )
        //         .toJson
        //   )
        // )
    }

    //FIXME  Tthis does not use split 
    def deleteEntryInDataModelVar(unitNumber: String): EventStream[String] = {

        println("Deleting " + unitNumber + "...")
        //TODO this belongs in the model (dataimportcsv3s)
        // dataModelVar.update((items) => {
        //     items.filter(_.unitNumber != unitNumber)
        // })
        // FetchStream.apply(
        //     method = _.DELETE,
        //     url = s"http://localhost:9000/patients/${unitNumber}"
        //     )
        EventStream.empty
    }

    def updatePatient(
        patient: Patient,
        fieldName: String,
        newValue: String
    ): Patient = {
        import com.axiom.dataimport.utils.*
  

        import com.axiom.dataimport.utils.localDate
        fieldName match {
            case "unitNumber" =>
                println("Cannot change unitnumber")
            case "lastName"  => patient.copy(lastName = newValue)
            case "firstName" => patient.copy(firstName = newValue)
            case "sex"       => patient.copy(sex = newValue)
            case "dob"       => patient.copy(dob = Try(localDate(newValue)).toOption )
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
                patient.copy(family = Option(newValue))
            case "attending" =>
                patient.copy(attending = Option(newValue))
            case "collab1" => patient.copy(collab1 = Option(newValue))
            case "collab2" => patient.copy(collab2 = Option(newValue))
        }
        patient
    }

}

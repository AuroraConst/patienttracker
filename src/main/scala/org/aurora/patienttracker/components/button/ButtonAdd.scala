package org.aurora.patienttracker.components.button

import com.raquo.laminar.api.L.{*, given}
import org.aurora.patienttracker.components.AuroraElement
import java.sql.Date
import client.AuroraClient

case class ButtonAdd(value: String, client: AuroraClient)
    extends AuroraElement {

    def render(): Element = {

        button(
          value,
          onClick.flatMap(_ => client.addEntryToDataModelVar()) --> {
              responseText =>
                  println(responseText)
          }
        )
    }

}

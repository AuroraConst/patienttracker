package com.axiom.patienttracker.components.button

import com.raquo.laminar.api.L.{*, given}
import com.axiom.patienttracker.components.AuroraElement
import java.sql.Date
import client.AuroraClient

case class ButtonAdd(value: String)
    extends AuroraElement {

    def render(): Element = {

        button(
          value,
        //   onClick.flatMap(_ => AuroraClient.addEntryToDataModelVar()) --> {
        //       responseText =>
        //           println(responseText)
        //   }
        )
    }

}

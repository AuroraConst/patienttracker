package org.aurora.patienttracker.components.button

import com.raquo.laminar.api.L.{*, given}
import org.aurora.patienttracker.components.AuroraElement
import java.sql.Date

import client.AuroraClient

case class DeleteButton(rowId: String, value: String)
    extends AuroraElement {

    val showConfirmVar = Var(false)

    def renderConfirmButtons() = {
        div(
          display := "flex",
          flexDirection := "column",
          "Confirm delete:",
          div(
            button(
              "No",
              onClick --> { (e) =>
                  showConfirmVar.update(bool => !bool)
              }
            ),
            button(
              "Yes",
              onClick.flatMap(_ =>
                  showConfirmVar.update(bool => !bool)
                  AuroraClient.deleteEntryInDataModelVar(
                    rowId
                  )
              ) --> { resp => println(resp) }
            )
          )
        )
    }

    def ToggleableCofirmButtons(
        showInput: Signal[Boolean]
    ): Signal[HtmlElement] = {
        showInput.map {
            case true => renderConfirmButtons()
            case false =>
                button(
                  value,
                  onClick --> { (e) =>
                      showConfirmVar.update(bool => !bool)
                  }
                )
        }
    }

    def render(): Element = {
        div(
          child <-- ToggleableCofirmButtons(showConfirmVar.signal)
        )
    }

}

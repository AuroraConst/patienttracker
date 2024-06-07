package org.aurora.patienttracker.components.cells

import org.aurora.patienttracker.components.AuroraElement
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.HTMLTableCellElement
import org.scalajs.dom
import org.scalajs.dom.KeyboardEvent
import org.scalajs.dom.FocusEvent
import org.aurora.patienttracker.components.utils.DomUtils.removeClassnameFromAll
import org.aurora.patienttracker.components.utils.DomUtils.addClassnameToElement

import org.scalajs.dom.Element
import org.scalajs.dom.HTMLElement
import com.raquo.laminar.nodes.ReactiveHtmlElement
import cats.instances.boolean
import client.AuroraClient
import org.aurora.patienttracker.components.utils.Icons._

case class FlagIcon(
    content: String,
    model: AuroraClient,
    fieldName: String,
    rowId: String
) extends AuroraElement {

    val showIconSelectVar = Var(false)
    val flagId = Var(content)
    val cellContent = Var(greenFlagElement)

    val greenFlagElement = {
        val cell = div()
        cell.ref.innerHTML = flagGreen
        cell
    }

    val yellowFlagElement = {
        val cell = div()
        cell.ref.innerHTML = flagYellow
        cell
    }
    val redFlagElement = {
        val cell = div()
        cell.ref.innerHTML = flagRed
        cell
    }
    val blueFlagElement = {
        val cell = div()
        cell.ref.innerHTML = flagBlue
        cell
    }

    def handleDblClick(e: MouseEvent) = {
        showIconSelectVar.update(bool => !bool)
    }

    def handleSelectButtonClick(e: MouseEvent) = {}

    def ShowIcon(
        iconId: Signal[String]
    ): Signal[HtmlElement] = {
        iconId.map {
            case "1" => greenFlagElement
            case "2" => yellowFlagElement
            case "3" => redFlagElement
            case "4" => blueFlagElement
            case _   => div()
        }
    }

    def renderIconSelect() = {
        div(
          display := "flex",
          flexDirection := "column",
          "Select:",
          div(
            div(
              greenFlagElement,
              onClick.flatMap(_ =>
                  showIconSelectVar.update(bool => !bool)
                  flagId.update(value => "1")
                  model.updateEntryInDataModelVar(
                    rowId,
                    fieldName,
                    "1"
                  )
              ) --> { resp => println(resp) }
            ),
            div(
              yellowFlagElement,
              onClick.flatMap(_ =>
                  showIconSelectVar.update(bool => !bool)
                  flagId.update(value => "2")
                  model.updateEntryInDataModelVar(
                    rowId,
                    fieldName,
                    "2"
                  )
              ) --> { resp => println(resp) }
            ),
            div(
              redFlagElement,
              onClick.flatMap(_ =>
                  showIconSelectVar.update(bool => !bool)
                  flagId.update(value => "3")
                  model.updateEntryInDataModelVar(
                    rowId,
                    fieldName,
                    "3"
                  )
              ) --> { resp => println(resp) }
            ),
            div(
              blueFlagElement,
              onClick.flatMap(_ =>
                  showIconSelectVar.update(bool => !bool)
                  flagId.update(value => "4")
                  model.updateEntryInDataModelVar(
                    rowId,
                    fieldName,
                    "4"
                  )
              ) --> { resp => println(resp) }
            ),
            div(
              "No Flag",
              onClick.flatMap(_ =>
                  showIconSelectVar.update(bool => !bool)
                  flagId.update(value => "")
                  model.updateEntryInDataModelVar(
                    rowId,
                    fieldName,
                    ""
                  )
              ) --> { resp => println(resp) }
            )
          )
        )
    }

    def ToggleableIconSelect(
        showInput: Signal[Boolean]
    ): Signal[HtmlElement] = {
        showInput.map {
            case true => renderIconSelect()
            case false =>
                div(
                  children <-- flagId.signal.map(id => {
                      id match {
                          case "1" => List(greenFlagElement)
                          case "2" => List(yellowFlagElement)
                          case "3" => List(redFlagElement)
                          case "4" => List(blueFlagElement)
                          case _   => List(div())
                      }
                  })
                )
        }
    }

    def render() = {
        td(
          child <-- ToggleableIconSelect(showIconSelectVar.signal),
          tabIndex := 0,
          zIndex := 100,
          onDblClick --> handleDblClick
        )
    }

}

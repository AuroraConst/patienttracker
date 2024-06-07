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

case class ToggleableInput(
    content: String,
    model: AuroraClient,
    fieldName: String,
    rowId: String
) extends AuroraElement {

    val showInputVar = Var(false)
    val cellContent = Var(content)

    def renderInput(content: String) = {
        input(
          idAttr := "toggledInput",
          value := content,
          width := "90%",
          onKeyDown
              .filter(_.key == "Enter")
              .flatMap(e =>
                  cellContent.set(
                    e.target.asInstanceOf[dom.html.Input].value
                  )
                  showInputVar.update(bool => !bool)
                  model.updateEntryInDataModelVar(
                    rowId,
                    fieldName,
                    e.target.asInstanceOf[dom.html.Input].value
                  )
              ) --> { resp => println(resp) },
          onKeyUp --> (e => {
              e.key match {
                  case "Escape" => showInputVar.update(bool => !bool)
                  case _        =>
              }
          }),
          onBlur --> (e => showInputVar.update(bool => !bool))
        )
    }

    def ToggleInput(
        showInput: Signal[Boolean]
    ): Signal[HtmlElement] = {
        showInput.map {
            case true  => renderInput(content)
            case false => div(child.text <-- cellContent)
        }
    }

    def render() = {
        td(
          child <-- ToggleInput(showInputVar.signal),
          tabIndex := 0,
          onClick --> (e =>
              e.ctrlKey match {
                  case true => {
                      showInputVar.update(bool => !bool)
                      Option(dom.document.getElementById("toggledInput"))
                          .map(_.asInstanceOf[HTMLElement].focus())
                  }
                  case false => e.target.asInstanceOf[HTMLElement].focus()
              }
          )
          //   onDblClick --> (e =>
          //       showInputVar.update(bool => !bool)
          //       Option(dom.document.getElementById("toggledInput"))
          //           .map(_.asInstanceOf[HTMLElement].focus())
          //   )
        )
    }

}

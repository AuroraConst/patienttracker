package com.axiom.patienttracker.components.cells

import com.axiom.patienttracker.components.AuroraElement 
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.HTMLTableCellElement
import org.scalajs.dom
import org.scalajs.dom.KeyboardEvent
import org.scalajs.dom.FocusEvent
import com.axiom.patienttracker.components.utils.DomUtils.removeClassnameFromAll
import com.axiom.patienttracker.components.utils.DomUtils.addClassnameToElement

import org.scalajs.dom.Element
import org.scalajs.dom.HTMLElement
import com.raquo.laminar.nodes.ReactiveHtmlElement
// import cats.instances.boolean
import client.AuroraClient

case class UneditableDiv(
    content: String,
    fieldName: String,
    rowId: String
) extends AuroraElement {

    def render() = {
        td(
          textAlign := "left",
          content,
          tabIndex := 0,
          onClick --> (e => e.target.asInstanceOf[HTMLElement].focus()),
          onDblClick --> (e => println("Cannot edit this field."))
        )
    }

}

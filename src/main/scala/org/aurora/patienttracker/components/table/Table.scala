package com.axiom.patienttracker.components.table

import com.axiom.patienttracker.components.AuroraElement 
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLTableRowElement
import com.raquo.airstream.state.Var

import org.scalajs.dom
import org.scalajs.dom.HTMLTableCellElement
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.MouseEvent
import scala.scalajs.js
import com.axiom.patienttracker.components.utils.DomUtils.getHTMLTableRowElementOpt
import client.AuroraClient
import com.axiom.patienttracker._,  config._

case class Table[A](tableConfig: TableConfig[A]) extends AuroraElement {

    val headers: List[ColumnConfig[A]] = tableConfig.columnConfigs

    def render(): Element = {
        div(
          className := "table-container",
          table(
            // tabIndex := 0,
            idAttr := "myTable",
            TableHeader(headers).render(),
            TableBody[A](
              tableConfig
            ).render(),
            TableFooter().render(),
            onKeyDown --> (e =>
                val activeCell = dom.document.activeElement
                    .asInstanceOf[HTMLTableCellElement]
                (e.ctrlKey, e.key) match {

                    case (_, "ArrowDown") => {
                        getHTMLTableRowElementOpt(
                          activeCell.closest("tr").nextElementSibling
                        ).map(
                          _.cells.find(cell =>
                              cell.asInstanceOf[HTMLTableCellElement]
                                  .cellIndex == activeCell.cellIndex
                          )
                        ).map(_.map(_.asInstanceOf[HTMLElement].focus()))
                    }
                    case (_, "ArrowUp") =>
                        getHTMLTableRowElementOpt(
                          activeCell.closest("tr").previousElementSibling
                        ).map(
                          _.cells.find(cell =>
                              cell.asInstanceOf[HTMLTableCellElement]
                                  .cellIndex == activeCell.cellIndex
                          )
                        ).map(_.map(_.asInstanceOf[HTMLElement].focus()))
                    case (_, "ArrowLeft") =>
                        Option(activeCell.previousElementSibling).map(
                          _.asInstanceOf[HTMLElement]
                              .focus()
                        )
                    case (_, "ArrowRight") =>
                        activeCell.nextElementSibling
                            .asInstanceOf[HTMLElement]
                            .focus()
                    case (true, "Enter") => {
                        e.preventDefault()
                        val simulatedEvent = new MouseEvent(
                          "dblclick",
                          js.undefined
                        )
                        activeCell.dispatchEvent(simulatedEvent)
                    }

                    case _ =>
                }
            )
          )
        )
    }

}

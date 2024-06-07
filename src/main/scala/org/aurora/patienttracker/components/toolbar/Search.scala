package org.aurora.patienttracker.components.toolbar

import org.aurora.patienttracker.components.AuroraElement
import com.raquo.laminar.api.L.{*, given}

import org.scalajs.dom.KeyboardEvent
import org.scalajs.dom
import org.scalajs.dom.HTMLInputElement
import org.scalajs.dom.HTMLTableElement
import org.scalajs.dom.HTMLTableRowElement
import org.scalajs.dom.HTMLTableCellElement
import org.scalajs.dom.Event
import org.scalajs.dom.HTMLElement
import com.raquo.airstream.state.StrictSignal
import org.scalajs.dom.KeyboardEvent

// TODO Refactor search
case class Search(options: List[String]) extends AuroraElement {

    val optionsVar = Var(initial = options.headOption.getOrElse("All"))

    def searchGrid(event: KeyboardEvent, opt: StrictSignal[String]): Unit = {
        val input = dom.document
            .getElementById("search-input")
            .asInstanceOf[HTMLInputElement]
        val filter = input.value.toLowerCase()
        val table = dom.document
            .getElementById("myTableBody")
            .asInstanceOf[HTMLTableElement];
        val rows = table.getElementsByTagName("tr");

        opt.now() match {
            case "All" =>
                rows.map(row => {
                    // Grabbing content from all cells in row
                    val contentToSearch = row
                        .asInstanceOf[HTMLTableRowElement]
                        .cells
                        .map(cell =>
                            cell.asInstanceOf[HTMLTableCellElement].innerText
                        )
                        .toList
                        .mkString(" ")
                    contentToSearch.toLowerCase().contains(filter) match {
                        case true =>
                            row.asInstanceOf[HTMLTableRowElement]
                                .style
                                .display = ""
                        case false =>
                            row.asInstanceOf[HTMLTableRowElement]
                                .style
                                .display = "none"
                    }
                })
            case _ => {
                val headerIndex = dom.document
                    .getElementById("myTableHeader")
                    .asInstanceOf[HTMLTableRowElement]
                    .cells
                    .filter(
                      _.asInstanceOf[HTMLTableCellElement].innerText == opt
                          .now()
                    )
                    .head
                    .asInstanceOf[HTMLTableCellElement]
                    .cellIndex
                rows.map(row => {
                    // Only grabbing content from cell in selected column
                    val contentToSearch = row
                        .asInstanceOf[HTMLTableRowElement]
                        .cells
                        .filter(cell =>
                            cell.asInstanceOf[HTMLTableCellElement]
                                .cellIndex == headerIndex
                        )
                        .map(cell => {
                            cell.asInstanceOf[HTMLTableCellElement].innerText
                        })
                        .toList
                        .mkString(" ")
                    contentToSearch.toLowerCase().contains(filter) match {
                        case true =>
                            row.asInstanceOf[HTMLTableRowElement]
                                .style
                                .display = ""
                        case false =>
                            row.asInstanceOf[HTMLTableRowElement]
                                .style
                                .display = "none"
                    }
                })
            }
        }

    }

    def render(): Element = {
        div(
          className := "toolbar-search",
          select(
            onChange.mapToValue --> optionsVar.writer,
            value <-- optionsVar.signal,
            options.map(opt => option(value(opt), opt))
          ),
          input(
            idAttr := "search-input",
            padding := "10px",
            width := "300px",
            placeholder("Search"),
            onKeyUp --> { (e) =>
                searchGrid(e, optionsVar.signal)
            }
          )
        )
    }

}

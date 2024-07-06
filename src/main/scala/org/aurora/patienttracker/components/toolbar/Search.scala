package org.aurora.patienttracker.components.toolbar

import org.aurora.patienttracker._, components._
import com.raquo.laminar.api.L.{*, given}

import org.scalajs.dom
import com.raquo.airstream.state.StrictSignal


case class Search() extends AuroraElement {

  def searchGrid(event: dom.KeyboardEvent, opt: StrictSignal[String]): Unit = {
    val input = dom.document
        .getElementById("search-input")
        .asInstanceOf[dom.HTMLInputElement]
    val filter = input.value.toLowerCase()
    val table = dom.document
        .getElementById("myTableBody")
        .asInstanceOf[dom.HTMLTableElement];
    val rows = table.getElementsByTagName("tr");

    opt.now() match {
        case "All" =>
            rows.map(row => {
                // Grabbing content from all cells in row
                val contentToSearch = row
                    .asInstanceOf[dom.HTMLTableRowElement]
                    .cells
                    .map(cell =>
                        cell.asInstanceOf[dom.HTMLTableCellElement].innerText
                    )
                    .toList
                    .mkString(" ")
                contentToSearch.toLowerCase().contains(filter) match {
                    case true =>
                        row.asInstanceOf[dom.HTMLTableRowElement]
                            .style
                            .display = ""
                    case false =>
                        row.asInstanceOf[dom.HTMLTableRowElement]
                            .style
                            .display = "none"
                }
            })
        case _ => {
            val headerIndex = dom.document
                .getElementById("myTableHeader")
                .asInstanceOf[dom.HTMLTableRowElement]
                .cells
                .filter(
                  _.asInstanceOf[dom.HTMLTableCellElement].innerText == opt
                      .now()
                )
                .head
                .asInstanceOf[dom.HTMLTableCellElement]
                .cellIndex
            rows.map(row => {
                // Only grabbing content from cell in selected column
                val contentToSearch = row
                    .asInstanceOf[dom.HTMLTableRowElement]
                    .cells
                    .filter(cell =>
                        cell.asInstanceOf[dom.HTMLTableCellElement]
                            .cellIndex == headerIndex
                    )
                    .map(cell => {
                        cell.asInstanceOf[dom.HTMLTableCellElement].innerText
                    })
                    .toList
                    .mkString(" ")
                contentToSearch.toLowerCase().contains(filter) match {
                    case true =>
                        row.asInstanceOf[dom.HTMLTableRowElement]
                            .style
                            .display = ""
                    case false =>
                        row.asInstanceOf[dom.HTMLTableRowElement]
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
        
        input(
          idAttr := "search-input",
          padding := "10px",
          width := "300px",
          placeholder("Search"),
          onChange.mapToValue --> org.aurora.model.js.DataModel.filterQuery
          
        
        //   onKeyUp --> { (e) =>
        //     //TODO update my filterVar
        //   }
        )
      )
  }

}

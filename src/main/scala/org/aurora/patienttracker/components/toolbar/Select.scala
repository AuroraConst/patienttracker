package org.aurora.patienttracker.components.toolbar

import org.aurora.patienttracker._, config._
import org.aurora.patienttracker.components.AuroraElement
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.HTMLSelectElement
import org.scalajs.dom
import org.scalajs.dom.HTMLTableElement
import org.scalajs.dom.HTMLTableRowElement
import org.scalajs.dom.HTMLTableCellElement

case class Select[A](options: List[ShowFilter], config: TableConfig[A])
    extends AuroraElement {

    val optionsVar =
        Var(initial = options.headOption.map(_.display).getOrElse("All"))
    val optionsDisplay = options.map(_.display)

    def render(): Element = {
        select(
          onChange.mapToValue --> optionsVar.writer,
          value <-- optionsVar.signal,
          optionsDisplay.map(opt => option(value(opt), opt)),
          onChange --> { e =>
              {
                  val filter = options
                      .filter(
                        _.display == e.target
                            .asInstanceOf[HTMLSelectElement]
                            .value
                      )
                      .head
                  val table = dom.document
                      .getElementById("myTableBody")
                      .asInstanceOf[HTMLTableElement];
                  val rows = table.getElementsByTagName("tr");
                  filter.value match {
                      case "-1" =>
                          rows.map(row => {
                              row.asInstanceOf[HTMLTableRowElement]
                                  .style
                                  .display = ""

                          })
                      case _ => {
                          val itemsToShow = config.client.dataModelVar.signal
                              .now()
                              .filter(items => {
                                  items.flag.getOrElse("") == filter.value
                              })
                              .map(_.unitNumber)
                          rows.map(row => {

                              // Grabbing content from all cells in row
                              val contentToSearch = row
                                  .asInstanceOf[HTMLTableRowElement]
                                  .cells
                                  .map(cell =>
                                      cell.asInstanceOf[HTMLTableCellElement]
                                          .innerText
                                  )
                                  .toList
                                  .mkString(" ")

                              itemsToShow
                                  .map(itemId => {
                                      contentToSearch.contains(itemId)
                                  })
                                  .contains(true) match {
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
          }
        )
    }

}

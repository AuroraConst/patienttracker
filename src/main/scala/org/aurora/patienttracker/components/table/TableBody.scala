package org.aurora.patienttracker.components.table

import org.aurora.patienttracker.components.AuroraElement
import org.aurora.patienttracker._, config._
import com.raquo.laminar.api.L.{*, given}

import scala.scalajs.js.JSON
import scala.scalajs.js
import io.circe.parser._
import io.circe.generic.auto._
import utilities.JsonImplicits._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLTableRowElement
import client.AuroraClient
import org.aurora.patienttracker.components.button.DeleteButton
import org.aurora.patienttracker.components.cells.FlagIcon
import org.aurora.patienttracker.components.cells.UneditableDiv
import org.aurora.patienttracker.components.cells.ToggleableInput
import org.aurora.patienttracker.components.utils.VscodeAPI.getVscodeApi

case class TableBody[T](config: TableConfig[T]) extends AuroraElement {

    def render(): Element = {

        tbody(
          // Fetch the data on component mount, update table
          FetchStream.get(config.client.GETUrl) --> { responseText =>
              config.client.populateTable(responseText)
          },
          idAttr := "myTableBody",
          children <-- config.client.dataModelVar.signal.map(data =>
              data.map { item =>
                  val children = config.columnConfigs.map(column => {
                      column.cellHTML(
                        config,
                        item.asInstanceOf[T]
                      )
                  })
                  tr(
                    width := "100%",
                    idAttr := item.unitNumber + "_" + item.firstName + "_" + item.lastName,
                    onClick --> { (e) =>
                        e.ctrlKey match {
                            case true =>
                            case false =>
                                getVscodeApi().postMessage(
                                  OpenFileMessage(
                                    item.firstName,
                                    item.lastName,
                                    item.unitNumber
                                  )
                                      .toJson()
                                )
                        }

                    },
                    children :+ DeleteButton(
                      config.rowIdentifier(item.asInstanceOf[T]),
                      "➖",
                      config.client
                    )
                        .render()
                  )
              }
          )
        )
    }

}

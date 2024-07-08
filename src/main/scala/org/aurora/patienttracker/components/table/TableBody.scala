package com.axiom.patienttracker.components.table

import com.axiom.patienttracker.components.AuroraElement
import com.axiom.patienttracker._, config._, messaging._
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import com.axiom.patienttracker.components.button.DeleteButton
import com.axiom.patienttracker.components.utils.VscodeAPI.getVscodeApi
import zio.json._
import client.AuroraClient
import com.axiom.model.js.DataModel
case class TableBody[T](config: TableConfig[T]) extends AuroraElement {
    import com.axiom.patienttracker._ //, roughdraft._
    def render(): Element = {
        

        tbody(
         

          // Fetch the data on component mount, update table
          idAttr := "myTableBody",
          children <-- DataModel.filteredPatientTableData.signal.map(data =>
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
                                      .toJson
                                )
                        }

                    },
                    children :+ DeleteButton(
                      config.rowIdentifier(item.asInstanceOf[T]),
                      "➖"
                    )
                        .render()
                  )
              }
          )
        )
    }

}

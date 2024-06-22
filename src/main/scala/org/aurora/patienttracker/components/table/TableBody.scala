package org.aurora.patienttracker.components.table

import org.aurora.patienttracker.components.AuroraElement
import org.aurora.patienttracker._, config._, messaging._
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import org.aurora.patienttracker.components.button.DeleteButton
import org.aurora.patienttracker.components.utils.VscodeAPI.getVscodeApi
import zio.json._
import client.AuroraClient
case class TableBody[T](config: TableConfig[T]) extends AuroraElement {
    import org.aurora.patienttracker._, roughdraft._
    def render(): Element = {
        

        tbody(
          roughdraft.patients --> { plist =>
            AuroraClient.dataModelVar.set(plist.getOrElse(Nil))
            AuroraClient.updateFilteredList("")
          },

          // Fetch the data on component mount, update table
          idAttr := "myTableBody",
          children <-- AuroraClient.filteredList.signal.map(data =>
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

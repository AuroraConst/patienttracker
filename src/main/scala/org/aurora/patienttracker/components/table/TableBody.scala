package org.aurora.patienttracker.components.table

import org.aurora.patienttracker.components.AuroraElement
import org.aurora.patienttracker._, config._
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import org.aurora.patienttracker.components.button.DeleteButton
import org.aurora.patienttracker.components.utils.VscodeAPI.getVscodeApi

case class TableBody[T](config: TableConfig[T]) extends AuroraElement {
    import org.aurora.patienttracker._, roughdraft._
    def render(): Element = {

        tbody(
          // Fetch the data on component mount, update table
          roughdraft.patients --> { plist =>
            config.client.dataModelVar.set(plist.getOrElse(Nil))
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

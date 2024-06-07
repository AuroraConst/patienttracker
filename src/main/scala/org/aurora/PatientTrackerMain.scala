package org.aurora.temp

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}
import typings.vscodeWebview.mod.global.*

import org.aurora.patienttracker.Patient
// import types.BasicMessage
// import client.AuroraClient
import org.aurora.patienttracker.components.toolbar.Toolbar
import org.aurora.patienttracker.components.table.Table
import org.aurora.patienttracker._, config._
import org.aurora.patienttracker.given
import org.scalajs.dom.MessageEvent

import configs.PatientTrackerConfig.config
import org.aurora.patienttracker.components.toolbar.Toolbar
import org.aurora.patienttracker.components.table.Table

@main
def PatientTrackerMain(): Unit =
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
        div(
      idAttr := "patient-tracker-main",
      width := "100%",
      div(
        width := "100%",
        Toolbar[Patient](Main.tableConfig).render(),
        Table[Patient](Main.tableConfig).render()
      )
    ) //,
    // Main.appElement()
  )
end PatientTrackerMain

object Main:
  val tableConfig = config
  renderOnDomContentLoaded(
    dom.document.body,
    div(
      idAttr := "patient-tracker-main",
      width := "100%",
      div(
        width := "100%",
        Toolbar[Patient](tableConfig).render(),
        Table[Patient](tableConfig).render()
      )
    )
  )

  // Add message listeners
  dom.document.addEventListener(
    "message",
    (e) => {
        println("Got message from webview!")
        println(e.asInstanceOf[MessageEvent].data)
    }
    )

  def appElement(): Element =
    div(
       h1("Chart", img(src:= "/vite.svg")),
       div("Hello, world 121111xxx!")
      // renderDataTable(),
      // renderDataChart(),
      // renderDataList(),
    )
  end appElement

  // def renderDataList(): Element =
  //   ul(
  //     children <-- dataSignal.split(_.id) { (id, initial, itemSignal) =>
  //       li(child.text <-- itemSignal.map(item => s"${item.count} ${item.label}"))
  //     }
  //   )
  // end renderDataList

  // def renderDataTable(): Element =
  //   table(
  //     thead(tr(th("Label"), th("Price"), th("Count"), th("Full price"), th("Action"))),
  //     tbody(
  //       children <-- dataSignal.split(_.id) { (id, initial, itemSignal) =>
  //         renderDataItem(id, itemSignal)
  //       },
  //     ),
  //     tfoot(tr(
  //       td(button("➕", onClick --> (_ => addDataItem(DataItem())))),
  //       td(),
  //       td(),
  //       td(child.text <-- dataSignal.map(data => "%.2f".format(data.map(_.fullPrice).sum))),
  //     )),
  //   )
  // end renderDataTable

  // def renderDataItem(id: DataItemID, itemSignal: Signal[DataItem]): Element =
  //   tr(
  //     td(
  //       inputForString(
  //         itemSignal.map(_.label),
  //         makeDataItemUpdater(id, { (item, newLabel) =>
  //           item.copy(label = newLabel)
  //         })
  //       )
  //     ),
  //     td(
  //       inputForDouble(
  //         itemSignal.map(_.price),
  //         makeDataItemUpdater(id, { (item, newPrice) =>
  //           item.copy(price = newPrice)
  //         })
  //       )
  //     ),
  //     td(
  //       inputForInt(
  //         itemSignal.map(_.count),
  //         makeDataItemUpdater(id, { (item, newCount) =>
  //           item.copy(count = newCount)
  //         })
  //       )
  //     ),
  //     td(
  //       child.text <-- itemSignal.map(item => "%.2f".format(item.fullPrice))
  //     ),
  //     td(button("🗑️", onClick --> (_ => removeDataItem(id)))),
  //   )
  // end renderDataItem

  // def inputForString(valueSignal: Signal[String],
  //     valueUpdater: Observer[String]): Input =
  //   input(
  //     typ := "text",
  //     value <-- valueSignal,
  //     onInput.mapToValue --> valueUpdater,
  //   )
  // end inputForString

  // def inputForDouble(valueSignal: Signal[Double],
  //     valueUpdater: Observer[Double]): Input =
  //   val strValue = Var[String]("")
  //   input(
  //     typ := "text",
  //     value <-- strValue.signal,
  //     onInput.mapToValue --> strValue,
  //     valueSignal --> strValue.updater[Double] { (prevStr, newValue) =>
  //       if prevStr.toDoubleOption.contains(newValue) then prevStr
  //       else newValue.toString
  //     },
  //     strValue.signal --> { valueStr =>
  //       valueStr.toDoubleOption.foreach(valueUpdater.onNext)
  //     },
  //   )
  // end inputForDouble

  // def inputForInt(valueSignal: Signal[Int],
  //     valueUpdater: Observer[Int]): Input =
  //   input(
  //     typ := "text",
  //     controlled(
  //       value <-- valueSignal.map(_.toString),
  //       onInput.mapToValue.map(_.toIntOption).collect {
  //         case Some(newCount) => newCount
  //       } --> valueUpdater,
  //     ),
  //   )
  // end inputForInt

  // def renderDataChart(): Element = ChartConfig.renderDataChart()

end Main

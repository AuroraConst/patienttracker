package org.aurora.patienttracker
import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}
import configs.PatientTrackerConfig.config
import org.aurora.patienttracker._, config._
import org.aurora.patienttracker.components.toolbar.Toolbar
import org.aurora.patienttracker.components.table.Table


package object roughdraft:
  val fetchstream = FetchStream.get("http://localhost:8080/patientsjson")
  val hello = Var("hello world VARRRRR")
  def patienttracker = 
     div(
      idAttr := "patient-tracker-main",
      width := "100%",
      div(
        width := "100%",
        Toolbar[Patient](Main.tableConfig).render(),
        Table[Patient](Main.tableConfig).render()
      )
    )

  def roughstuff = 
    div(
      idAttr := "rough draft",
      width := "100%",
      div(
        width := "100%",
        text <-- fetchstream.map(_.toString)
      )
    )   



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
        println(e.asInstanceOf[dom.MessageEvent].data)
    }
    )

  def appElement(): Element =
    div(
       h1("Chart", img(src:= "/vite.svg")),
       div("Hello, world 121111xxx!")
    )
  end appElement

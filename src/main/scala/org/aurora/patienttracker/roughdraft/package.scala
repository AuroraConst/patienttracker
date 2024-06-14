package org.aurora.patienttracker
import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}
import configs.PatientTrackerConfig.config
import org.aurora.patienttracker._, config._
import org.aurora.patienttracker.components.toolbar.Toolbar
import org.aurora.patienttracker.components.table.Table
import zio.json._

import org.aurora.shared.dto.Patient

package object roughdraft:
  val patients = FetchStream.get("http://localhost:8080/patientsjson")
    .map(_.fromJson[List[Patient]])
    .map(_.toOption)

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
        text <--patients.map(p => s"$p")
      )
    )   



object Main:
  val tableConfig = config

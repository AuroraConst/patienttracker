package com.axiom.patienttracker
import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}
import configs.PatientTrackerConfig.config
import com.axiom.patienttracker._, config._
import com.axiom.patienttracker.components.toolbar.Toolbar
import com.axiom.patienttracker.components.table.Table
import zio.json._

import com.axiom.model.shared.dto.Patient
import client.AuroraClient

package object roughdraft:
  val patients = FetchStream.get("http://localhost:8080/patientsjson")
    .map(_.fromJson[List[Patient]])
    .map(_.toOption)

  val fetchstream = FetchStream.get("http://localhost:8080/patientsjson")
  val hello = Var("hello world VARRRRR")
  def patienttracker = 

    import com.axiom.model.js.DataModel
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

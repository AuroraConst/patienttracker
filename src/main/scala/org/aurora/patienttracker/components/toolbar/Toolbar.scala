package com.axiom.patienttracker.components.toolbar

import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.HTMLTableCellElement
import org.scalajs.dom.MouseEvent
// import models.*
import com.axiom.patienttracker._
import com.axiom.patienttracker.components.toolbar.Search
import com.axiom.patienttracker.components.button.ButtonAdd

import com.axiom.patienttracker._, config._
import client.AuroraClient
import com.axiom.patienttracker.components.AuroraElement
import com.axiom.model.js.DataModel

case class Toolbar[A](config: TableConfig[A]) extends AuroraElement {

    val searchByOption: List[String] = config.columnConfigs.map(_.headerTitle)

    val showOptions: List[ShowFilter] =
        config.columnConfigs
            .flatMap(_.showFilterable.getOrElse(List()))

    def render(): Element = {
        import com.axiom.model.js.DataModel
        div(
          className := "toolbar",
          Text("Search By:", ml = "").render(),
          Search().render(),
          Text("Show:").render(),
        //   Select[A](ShowFilter("", "All", "-1") :: showOptions, config)
        //       .render(),
          ButtonAdd("➕").render() 
        )
    }

}

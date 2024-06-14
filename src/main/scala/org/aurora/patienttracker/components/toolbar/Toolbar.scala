package org.aurora.patienttracker.components.toolbar

import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.HTMLTableCellElement
import org.scalajs.dom.MouseEvent
// import models.*
import org.aurora.patienttracker._
import org.aurora.patienttracker.components.toolbar.Search
import org.aurora.patienttracker.components.button.ButtonAdd

import org.aurora.patienttracker._, config._
import client.AuroraClient
import org.aurora.patienttracker.components.AuroraElement

case class Toolbar[A](config: TableConfig[A]) extends AuroraElement {

    val searchByOption: List[String] = config.columnConfigs.map(_.headerTitle)

    val showOptions: List[ShowFilter] =
        config.columnConfigs
            .flatMap(_.showFilterable.getOrElse(List()))

    def render(): Element = {
        div(
          className := "toolbar",
          Text("Search By:", ml = "").render(),
          Search("All" :: searchByOption).render(),
          Text("Show:").render(),
          Select[A](ShowFilter("", "All", "-1") :: showOptions, config)
              .render(),
          ButtonAdd("➕").render() 
        )
    }

}

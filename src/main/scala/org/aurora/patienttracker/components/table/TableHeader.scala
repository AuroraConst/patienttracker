package org.aurora.patienttracker.components.table

import org.aurora.patienttracker._, config._
import org.aurora.patienttracker.components.AuroraElement
import com.raquo.laminar.api.L.{*, given}
import utilities.SortGrid.SortOrder.*
import utilities.SortGrid.*
import org.scalajs.dom

case class TableHeader(headers: List[ColumnConfig[_]]) extends AuroraElement {

    var ascendingSort = true
    def setAscendingSort(value: Boolean): Unit = {
        ascendingSort = value
    }
    var sortOrderMap: Map[Int, SortOrder] = Map()

    def onHeaderClick(event: dom.Event): Unit = {
        val clickedHeader = event.currentTarget.asInstanceOf[dom.html.TableCell]
        val columnIndex = clickedHeader.cellIndex
        val currentSortOrder = sortOrderMap.getOrElse(columnIndex, Ascending)
        val tableRows = dom.document.querySelectorAll("tbody tr")
        val rowsList = List.tabulate(tableRows.length)(i => tableRows.item(i))

        val sortedRows = rowsList.sortBy { row =>
            val cellValue = row.children.item(columnIndex).textContent
            cellValue
        }(sortGrid(currentSortOrder))

        val newSortOrder =
            if (currentSortOrder == Ascending) Descending else Ascending
        sortOrderMap += (columnIndex -> newSortOrder)

        val tableBody = dom.document.querySelector("tbody")
        tableBody.innerHTML = ""

        sortedRows.foreach(tableBody.appendChild)
    }

    def render(): Element = {
        thead(
          tr(
            idAttr := "myTableHeader",
            headers.map(columnConfig => {
                th(
                  columnConfig.headerTitle,
                  width := columnConfig.width,
                  onClick --> onHeaderClick
                )
            })
          )
        )
    }
}

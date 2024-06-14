package org.aurora.patienttracker
import org.aurora.patienttracker.components.cells.{UneditableDiv, FlagIcon, ToggleableInput}




package object config :
  import org.aurora.patienttracker.components.cells._
  import client.AuroraClient


  sealed trait CellType
  case object ToggleableInputType extends CellType
  case object FlagIconType extends CellType
  case object UneditableDivType extends CellType

  case class TableConfig[T](
      val client: AuroraClient,
      val rowIdentifier: T => String,
      val columnConfigs: List[ColumnConfig[T]]
  )

  case class ShowFilter(
      fieldName: String,
      display: String,
      value: String
  )

  case class ColumnConfig[T](
    cellType: CellType,
    headerTitle: String,
    width: String,
    cellContent: T => String,
    fieldName: String,
    showFilterable: Option[List[ShowFilter]] = None
  ) {
      // Remeber to implement any new cell types here
      def cellHTML(config: TableConfig[T], item: T) = {
        cellType match {
            case ToggleableInputType =>
                ToggleableInput(
                  cellContent(item),
                  config.client,
                  fieldName,
                  config.rowIdentifier(item)
                ).render()
            case FlagIconType =>
                FlagIcon(
                  cellContent(item),
                  config.client,
                  fieldName,
                  config.rowIdentifier(item)
                ).render()
            case UneditableDivType =>
                UneditableDiv(
                  cellContent(item),
                  config.client,
                  fieldName,
                  config.rowIdentifier(item)
                ).render()
        }
    }
  }     
end config

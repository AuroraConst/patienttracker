// package components.cells

// import components.AuroraElement
// import com.raquo.laminar.api.L.{*, given}
// import org.scalajs.dom.MouseEvent
// import org.scalajs.dom.HTMLTableCellElement
// import org.scalajs.dom
// import org.scalajs.dom.KeyboardEvent
// import org.scalajs.dom.FocusEvent
// import components.utils.DomUtils.removeClassnameFromAll
// import components.utils.DomUtils.addClassnameToElement

// import types.Patient
// import org.scalajs.dom.Element
// import org.scalajs.dom.HTMLElement
// import com.raquo.laminar.nodes.ReactiveHtmlElement
// import cats.instances.boolean
// import client.AuroraClient
// import components.utils.Icons._

// case class PinIcon(
//     content: String,
//     model: AuroraClient,
//     fieldName: String,
//     item: Patient
// ) extends AuroraElement {

//     val showIconSelectVar = Var(false)
//     val flagId = Var(content)
//     val cellContent = Var(greenFlagElement)

//     val greenFlagElement = {
//         val cell = div()
//         cell.ref.innerHTML = flagGreen
//         cell
//     }

//     val yellowFlagElement = {
//         val cell = div()
//         cell.ref.innerHTML = flagYellow
//         cell
//     }
//     val redFlagElement = {
//         val cell = div()
//         cell.ref.innerHTML = flagRed
//         cell
//     }
//     val blueFlagElement = {
//         val cell = div()
//         cell.ref.innerHTML = flagBlue
//         cell
//     }

//     def handleDblClick(e: MouseEvent) = {
//         showIconSelectVar.update(bool => !bool)
//         println(showIconSelectVar.signal.now())
//     }

//     def handleSelectButtonClick(e: MouseEvent) = {}

//     def ShowIcon(
//         iconId: Signal[String]
//     ): Signal[HtmlElement] = {
//         iconId.map {
//             case "1" => greenFlagElement
//             case "2" => yellowFlagElement
//             case "3" => redFlagElement
//             case "4" => blueFlagElement
//             case _   => div()
//         }
//     }

//     def renderIconSelect() = {
//         div(
//           display := "flex",
//           flexDirection := "column",
//           "Select:",
//           div(
//             button(
//               greenFlagElement,
//               onClick.flatMap(_ =>
//                   showIconSelectVar.update(bool => !bool)
//                   flagId.update(value => "1")
//                   model.updateEntryInDataModelVar(
//                     item.asInstanceOf[Patient],
//                     fieldName,
//                     "1"
//                   )
//               ) --> { resp => println(resp) }
//             ),
//             button(
//               yellowFlagElement,
//               onClick.flatMap(_ =>
//                   showIconSelectVar.update(bool => !bool)
//                   flagId.update(value => "2")
//                   model.updateEntryInDataModelVar(
//                     item.asInstanceOf[Patient],
//                     fieldName,
//                     "2"
//                   )
//               ) --> { resp => println(resp) }
//             ),
//             button(
//               redFlagElement,
//               onClick.flatMap(_ =>
//                   showIconSelectVar.update(bool => !bool)
//                   flagId.update(value => "3")
//                   model.updateEntryInDataModelVar(
//                     item.asInstanceOf[Patient],
//                     fieldName,
//                     "3"
//                   )
//               ) --> { resp => println(resp) }
//             ),
//             button(
//               blueFlagElement,
//               onClick.flatMap(_ =>
//                   showIconSelectVar.update(bool => !bool)
//                   flagId.update(value => "4")
//                   model.updateEntryInDataModelVar(
//                     item.asInstanceOf[Patient],
//                     fieldName,
//                     "4"
//                   )
//               ) --> { resp => println(resp) }
//             ),
//             button(
//               "No Flag",
//               onClick.flatMap(_ =>
//                   showIconSelectVar.update(bool => !bool)
//                   flagId.update(value => "")
//                   model.updateEntryInDataModelVar(
//                     item.asInstanceOf[Patient],
//                     fieldName,
//                     ""
//                   )
//               ) --> { resp => println(resp) }
//             )
//           )
//         )
//     }

//     def ToggleableIconSelect(
//         showInput: Signal[Boolean]
//     ): Signal[HtmlElement] = {
//         showInput.map {
//             case true => renderIconSelect()
//             case false =>
//                 div(
//                   children <-- flagId.signal.map(id => {
//                       id match {
//                           case "1" => List(greenFlagElement)
//                           case "2" => List(yellowFlagElement)
//                           case "3" => List(redFlagElement)
//                           case "4" => List(blueFlagElement)
//                           case _   => List(div())
//                       }
//                   })
//                 )
//         }
//     }

//     def render() = {
//         td(
//           child <-- ToggleableIconSelect(showIconSelectVar.signal),
//           tabIndex := 0,
//           zIndex := 100,
//           onDblClick --> handleDblClick
//         )
//     }

// }

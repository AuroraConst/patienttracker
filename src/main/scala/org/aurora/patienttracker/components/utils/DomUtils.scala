package com.axiom.patienttracker.components.utils

import org.scalajs.dom
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.HTMLTableRowElement

object DomUtils {
    def removeClassnameFromAll(classname: String): Unit = {
        dom.document
            .getElementsByClassName(classname)
            .map(element => element.classList.remove(classname))
    }

    def addClassnameToElement(classname: String, element: HTMLElement): Unit = {
        element.classList.add(classname)
    }

    def getHTMLTableRowElementOpt(
        e: dom.Element
    ): Option[HTMLTableRowElement] = {
        e match {
            case row: HTMLTableRowElement => Some(row)
            case _                        => None
        }
    }
}

package org.aurora.patienttracker.components.toolbar

import org.aurora.patienttracker.components.AuroraElement 
import com.raquo.laminar.api.L.{*, given}

case class Text(
    value: String,
    as: String = "center",
    ml: String = "25px",
    mr: String = "5px"
) extends AuroraElement {

    def render(): Element = {
        div(
          alignSelf := as,
          marginLeft := ml,
          marginRight := mr,
          value
        )
    }

}

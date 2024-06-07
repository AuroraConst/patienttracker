package org.aurora.patienttracker.components.table

import org.aurora.patienttracker.components.AuroraElement 
import com.raquo.laminar.api.L.{*, given}

case class TableFooter() extends AuroraElement {
    def render(): Element = {
        tfoot(
          tr(
            // td(button("➕"))
            // td(),
            // td(),
            // td(child.text <-- model.dataSignal.map(data => "%.2f".format(data.map(_.fullPrice).sum))),
          )
        )
    }
}

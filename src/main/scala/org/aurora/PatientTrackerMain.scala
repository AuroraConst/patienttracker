package org.aurora.temp

import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}



@main
def PatientTrackerMain(): Unit =
  import org.aurora.patienttracker._,  roughdraft._
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    patienttracker 
    // roughstuff
  )
end PatientTrackerMain

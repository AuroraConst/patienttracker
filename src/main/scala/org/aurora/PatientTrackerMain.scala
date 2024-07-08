package com.axiom.temp

import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}

 

@main
def PatientTrackerMain(): Unit =
  import com.axiom.patienttracker._, roughdraft._
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    patienttracker,

    // roughstuff,
  )
end PatientTrackerMain

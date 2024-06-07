package org.aurora.patienttracker.components

import com.raquo.laminar.api.L.{*, given}

trait AuroraElement {
    def render(): Element
}

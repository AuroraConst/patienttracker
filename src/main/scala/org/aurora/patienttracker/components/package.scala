package com.axiom.patienttracker


import com.raquo.airstream.core.EventStream

package object components :
  import com.raquo.laminar.api.L.{*, given}
  import org.scalajs.dom

  //TODO darien: replace Add and Delete Buttons with this generic button
  def buttoncomponent[T](label:String, handler:( e:dom.MouseEvent) => EventStream[T], onNext:T => Unit)  = 
    button(
      label,
      onClick.flatMap( handler) --> onNext
    )

  //TODO darien: vs this method of creating a component?  I think I favor the above method  
  class Button [T](value: String, handler :( e:dom.MouseEvent) => EventStream[T]) extends AuroraElement :
    def render(): Element = {

    button(
      value,
      onClick.flatMap( handler) --> {
          responseText =>
              println(responseText)
      }
    )
  }


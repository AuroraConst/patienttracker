package org.aurora.patienttracker
import zio.json._
package object messaging:
  trait Message :
    val command: String
  end Message

  case class BasicMessage(content: String) extends Message :
    val command: String = "basicMessage"
  end BasicMessage  

  case class OpenFileMessage( 
      firstName: String,
      lastName: String,
      unitNumber: String,
      command: String = "openFile"
  ) extends Message


  object OpenFileMessage :
    given  decoder: JsonDecoder[OpenFileMessage] = DeriveJsonDecoder.gen[OpenFileMessage]  
    given encoder: JsonEncoder[OpenFileMessage] = DeriveJsonEncoder.gen[OpenFileMessage]

  object BasicMessage :
    given decoder: JsonDecoder[BasicMessage] = DeriveJsonDecoder.gen[BasicMessage]  
    given encoder: JsonEncoder[BasicMessage] = DeriveJsonEncoder.gen[BasicMessage]


end messaging
package utilities

import io.circe.generic.semiauto._
import io.circe._
// import org.aurora.patienttracker.Patient
import scala.scalajs.js.Date
import org.aurora.patienttracker.Message 
import org.aurora.patienttracker.BasicMessage

object JsonImplicits {

    // implicit val patientDecoder: Decoder[Patient] = deriveDecoder[Patient]
    // implicit val patientEncoder: Encoder[Patient] = deriveEncoder[Patient]

    implicit val messageDecoder: Decoder[BasicMessage] =
        deriveDecoder[BasicMessage]
    implicit val messageEncoder: Encoder[BasicMessage] =
        deriveEncoder[BasicMessage]

    implicit val dateEncoder: Encoder[Date] =
        Encoder.encodeString.contramap((date: Date) => date.toString())
    implicit val dateDecoder: Decoder[Date] = Decoder.instance { cursor =>
        cursor.as[String].flatMap { str =>
            try Right(new Date(str))
            catch {
                case _: IllegalArgumentException =>
                    Left(
                      DecodingFailure(
                        s"Invalid date format: $str",
                        cursor.history
                      )
                    )
            }
        }
    }

}

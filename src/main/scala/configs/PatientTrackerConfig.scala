package configs
import org.aurora.shared.dto.Patient
import org.aurora.patienttracker._, config._
import org.aurora.patienttracker.given
import client.AuroraClient

object PatientTrackerConfig {
    val config = TableConfig[Patient](
      new AuroraClient(),
      _.unitNumber,
      List(
        ColumnConfig[Patient](
          FlagIconType,
          "Flag",
          "50px",
          _.flag.getOrElse(""),
          "flag",
          Some(
            List(
              ShowFilter("flag", "Good Condition", "1"),
              ShowFilter("flag", "Caution", "2"),
              ShowFilter("flag", "Critical Condition", "3"),
              ShowFilter("flag", "Discharge Early", "4")
            )
          )
        ),
        ColumnConfig[Patient](
          UneditableDivType,
          "Unit Number",
          "150px",
          _.unitNumber,
          "unitNumber"
        ),
        ColumnConfig[Patient](
          ToggleableInputType,
          "First Name",
          "150px",
          _.firstName,
          "firstName"
        ),
        ColumnConfig[Patient](
          ToggleableInputType,
          "Last Name",
          "150px",
          _.lastName,
          "lastName"
        ),
        ColumnConfig[Patient](
          ToggleableInputType,
          "Sex",
          "50px",
          _.sex,
          "sex"
        ),
        ColumnConfig[Patient](
          ToggleableInputType,
          "Date of Birth",
          "150px",
          _.dob,
          "dob"
        ),
        ColumnConfig[Patient](
          ToggleableInputType,
          "In Hospital",
          "100px",
          _.hosp.getOrElse(""),
          "hosp"
        )
      )
    )
}

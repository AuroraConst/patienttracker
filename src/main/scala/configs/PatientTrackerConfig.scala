package configs
import org.aurora.model.shared.dto.Patient
import org.aurora.patienttracker._, config._
import org.aurora.patienttracker.given
import client.AuroraClient
import org.aurora.dataimport.utils

object PatientTrackerConfig {
    val config = TableConfig[Patient](
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
          "Unit No.",
          "150px",
          _.unitNumber,
          "unitNumber"
        ),
        ColumnConfig[Patient](
          UneditableDivType,
          "Account",
          "150px",
          _.accountNumber,
          "accountNumber"
        ),

        ColumnConfig[Patient](
          UneditableDivType,
          "Last Name",
          "150px",
          _.lastName,
          "lastName"
        ),
        ColumnConfig[Patient](
          UneditableDivType,
          "First Name",
          "350px",
          _.firstName,
          "firstName"
        ),
        ColumnConfig[Patient](
          UneditableDivType,
          "Sex",
          "50px",
          _.sex,
          "sex"
        ),
        ColumnConfig[Patient](
          UneditableDivType,
          "Date of Birth",
          "150px",
          _.dob.map{utils.formattedString}.getOrElse(""),
          "dob"
        ),
        ColumnConfig[Patient](
          UneditableDivType,
          "Adm.Date",
          "150px",
          _.admitDate.map{d => utils.formattedString(d.toLocalDate()) }.getOrElse(""),
          "admissionDate"
        ),
        ColumnConfig[Patient](
          UneditableDivType,
          "MRP",
          "150px",
          _.attending.getOrElse(""),
          "mrp"
        ),

        ColumnConfig[Patient](
          ToggleableInputType,
          "Floor",
          "150px",
          _.floor.getOrElse(""),
          "floor"
        ),
        ColumnConfig[Patient](
          ToggleableInputType,
          "Room",
          "150px",
          _.room.getOrElse(""),
          "room"
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

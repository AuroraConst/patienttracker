import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._

object Dependencies {


  val aurorajslibs = Def.setting {
    Seq("org.aurora" %%% "parserjs" % "0.0.1-SNAPSHOT",
       "org.aurora" %%% "dataimportcsv3s" % "0.0.1-SNAPSHOT"
    )
  }

  val scalajsdom  = Def.setting {
    Seq("org.scala-js" %%% "scalajs-dom" % DependencyVersions.scalajsdom)
  }
  val scalatest   :     Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "org.scalactic" %%% "scalactic"  % DependencyVersions.scalatest,
      "org.scalatest" %%% "scalatest" % DependencyVersions.scalatest % "test"
    )
  }


  val laminar: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "com.raquo" %%% "laminar" % DependencyVersions.laminar
    )
  }

  val upickle: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "com.lihaoyi" %%% "upickle" % DependencyVersions.`upickle`
    )
  }

}

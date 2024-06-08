import org.scalajs.linker.interface.ModuleSplitStyle

organization := "org.aurora"
name := "patienttracker"
version := "0.0.1"


lazy val PatientTracker = project.in(file("."))
  .enablePlugins(ScalaJSPlugin) // Enable the Scala.js plugin in this project
  .enablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    scalaVersion := DependencyVersions.scala,

    // Tell Scala.js that this is an application with a main method
    scalaJSUseMainModuleInitializer := true,

    /* Configure Scala.js to emit modules in the optimal way to
     * connect to Vite's incremental reload.
     * - emit ECMAScript modules
     * - emit as many small modules as possible for classes in the "livechart" package
     * - emit as few (large) modules as possible for all other classes
     *   (in particular, for the standard library)
     */
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
        .withModuleSplitStyle(
          ModuleSplitStyle.SmallModulesFor(List("patienttracker")))
    },
    Compile / npmDependencies ++= Seq(
        // "vscode-webview" -> "1.57.0",
        "@types/vscode-webview" -> "^1.57.0"
    ),


    /*
     *add resolver for scalatest
     */
    resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases",


    /* Depend on the scalajs-dom library.
     * It provides static types for the browser DOM APIs.
     */
    libraryDependencies ++= Dependencies.scalajsdom.value,
    libraryDependencies ++= Dependencies.laminar.value,
    libraryDependencies ++= Dependencies.upickle.value,  //TODO ? circe vs upickle
    libraryDependencies ++= Dependencies.scalatest.value,
    libraryDependencies ++=  Dependencies.aurorashared.value,
    libraryDependencies += "io.circe" %%% "circe-core" % "0.14.3",
    libraryDependencies += "io.circe" %%% "circe-generic" % "0.14.3",
    libraryDependencies += "io.circe" %%% "circe-parser" % "0.14.3",
      

    // Tell ScalablyTyped that we manage `npm install` ourselves
    externalNpm := baseDirectory.value,
  )

val scalaV = "2.12.2"

val scalajsScriptsV = "1.1.1"

val webjarsPlayV = "2.6.1"

val webjarsVueV = "2.4.2"

val webjarsFontAwesomeV = "4.7.0"

val webjarsBulmaV = "0.5.3"

val scalajsDomV = "0.9.1"

val autowireV = "0.2.6"

val boopickleV = "1.2.6"

lazy val server = (project in file("server")).settings(
  scalaVersion := scalaV,
  scalaJSProjects := Seq(client),
  scalaJSStage := FullOptStage,
  pipelineStages in Assets := Seq(scalaJSPipeline, digest, gzip),
  pipelineStages := Seq(digest, gzip),
  // triggers scalaJSPipeline when using compile or continuous compilation
  compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
  libraryDependencies ++= Seq(
    "com.vmunier" %% "scalajs-scripts" % scalajsScriptsV,
    "org.webjars" %% "webjars-play" % webjarsPlayV,
    "org.webjars" % "vue" % webjarsVueV,
    "org.webjars" % "font-awesome" % webjarsFontAwesomeV,
    "org.webjars.npm" % "bulma" % webjarsBulmaV,
    guice,
    specs2 % Test
  )
).enablePlugins(PlayScala).
  dependsOn(sharedJvm)

lazy val client = (project in file("client")).settings(
  scalaVersion := scalaV,
  scalaJSUseMainModuleInitializer := true,
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % scalajsDomV
  )
).enablePlugins(ScalaJSPlugin, ScalaJSWeb).
  dependsOn(sharedJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(scalaVersion := scalaV,
    libraryDependencies ++= Seq (
      "com.lihaoyi" %%% "autowire" % autowireV,
      "io.suzaku" %%% "boopickle" % boopickleV
    )
  )

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

// loads the server project at sbt startup
onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value

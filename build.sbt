name := "manolesiai-automatization"

version := "1.0"

scalaVersion := "2.11.5"

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.2.0"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.1.1"
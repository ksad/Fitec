name := "Twitter_Spark_Streaming"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.2.0",
  "org.apache.spark" %% "spark-sql" % "2.2.0",
  //"org.apache.spark" %% "spark-streaming" % "2.2.0",
  "org.apache.spark" %% "spark-streaming" % "2.2.0" % "provided",
  "org.apache.bahir" %% "spark-streaming-twitter" % "2.2.0",
  "commons-dbutils" % "commons-dbutils" % "1.7"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case PathList("META-INF", "*.RSA") => MergeStrategy.discard
  case PathList("META-INF", "*.SF") => MergeStrategy.discard
  case PathList("META-INF/*.inf") => MergeStrategy.discard
  case x => MergeStrategy.first
}
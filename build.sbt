name := "debezium_kafkaStream"

version := "0.1"

scalaVersion := "2.12.3"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams-scala

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "0.10.2.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.13"
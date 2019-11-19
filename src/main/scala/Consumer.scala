import java.util.Properties


import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.KStreamBuilder
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.KTable
import java.util.{Locale, Properties}
import play.api.libs.json._

object Consumer extends App {

  private def configuration: Properties = {
    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "dbserver1.inventory.customers")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props
  }
  import scala.collection.JavaConverters._

  val builder = new KStreamBuilder
  val source: KStream[String, String] = builder.stream("dbserver1.inventory.customers")

  source.foreach((key,value)=>{
    val json = Json.parse(value)
    val code = (json \ "payload")
    val code2 = (code \ "after")
    print(code2)
  })

  val streams: KafkaStreams = new KafkaStreams(builder, configuration)
  streams.start()
  // usually the stream application would be running forever,
  // in this example we just let it run for some time and stop since the input data is finite.
  //Thread.sleep(5000L)
  //streams.close()
}
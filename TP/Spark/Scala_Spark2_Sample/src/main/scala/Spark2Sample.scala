import org.apache.spark.sql.SparkSession
import java.text.SimpleDateFormat
import java.util.Calendar

import org.apache.log4j.{Level, Logger}

object Spark2Sample {
  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val spark = SparkSession.builder().appName("Spark2Sample").master("local").getOrCreate

    import spark.implicits._

    println("Source :")
    println("[1] Localhost (ex : /home/user/workingfolder)")
    println("[2] Local hdfs (ex : hdfs:///user/spark/)")
    println("[3] Remote hdfs (ex : hdfs:///34.244.156.85:8020/user/spark/ - need ssh configuration")
    val source = scala.io.StdIn.readLine("Please select your source file > ")

    var input_folder: String =""

    source match {
      /* Local path */
      case "1"  => input_folder= "file:///home/ksad/MY-DATA/Fitec-Work/TP/Spark/Spark_dataSets/"
      /* Local hdfs path */
      case "2"  => input_folder= "hdfs:///user/spark/"
      /* Remote hdfs path */
      case "3"  => input_folder= "hdfs:///34.244.156.85:8020/user/spark/"
      case whoa  => println("Unexpected case: " + whoa.toString)
    }

    val input_file = "Police_Incidents_Year_2017.csv"

    println("********* Loading data from : "+input_folder+input_file+"  *********")

    val policeInc = spark.read.option("header", "true").option("delimiter", ",").csv(input_folder+input_file)
    policeInc.printSchema()

    val records = policeInc.filter(policeInc("Resolution").contains("ARREST"))

    records.count

    val incCateg = policeInc.groupBy("Category").count.orderBy($"count".desc)
    incCateg.show()

    val output_folder = input_folder+"Incidents_by_Category_2017"

    val format = new SimpleDateFormat("ddMMYYY_HHmmss")
    val now = format.format(Calendar.getInstance().getTime())
    incCateg.write.format("csv").save(output_folder+"_"+now+"/")

    println("******** Result saved on : "+output_folder+"_"+now+"/ ********")
  }
}
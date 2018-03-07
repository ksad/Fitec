import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._ //for count()


import java.text.SimpleDateFormat
import java.util.Calendar

import org.apache.log4j.{Logger, Level}

object sqlSpark {
  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    /*val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)*/

    val spark = SparkSession.builder().appName("sqlSpark").master("local").getOrCreate
    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    println("Source :")
    println("[1] Localhost (ex : /home/user/workingfolder)")
    println("[2] Local hdfs (ex : hdfs:///user/spark/)")
    println("[3] Remote hdfs (ex : hdfs:///34.244.156.85:8020/user/spark/ - need ssh configuration")
    //val source = scala.io.StdIn.readLine("Please select your source file > ")
    val source = "1";

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

    /*val pi = spark.read.option("header", "true").option("deLIMITer", ",").csv(input_folder+input_file)
    pi.printSchema()
    pi.show()

    // Suicide count on 2017
    println("Nbr_Suicide = "+pi.filter(pi("Category") === "SUICIDE").count())


    pi.filter(pi("Category")==="SUICIDE").select($"Descript", $"Date", $"Time", $"Resolution").show


    // Crimes count by day of the week
    pi.select($"Descript", $"Date", $"Time", $"DayOfWeek", $"Resolution").groupBy($"DayOfWeek").agg(count("DayOfWeek").alias("NbrCrimes")).orderBy($"NbrCrimes".desc).show()

    //pi.describe("IncidntNum").show*/
    val pisql = spark.read.option("header", "true").option("delimiter", ",").csv(input_folder+input_file)
    pisql.createOrReplaceTempView("police")

    /*spark.sql("SELECT count(*) as Incidents_Nbr FROM police LIMIT 10").show
    spark.sql("SELECT Category, Descript, Date, Time, DayOfWeek, Resolution FROM police LIMIT 10").show*/
    spark.sql("SELECT * FROM police WHERE Category = 'SUICIDE'").show


  }
}

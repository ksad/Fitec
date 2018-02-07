package footballapi

import footballapi.futballscrapper.{allCountries, country, idCountries}
import play.api.libs.json._

import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.ListMap

object testing extends App{

  var ratings : Map[String, Int] = Map()


  ratings += ("Karim" -> 78, "Syrine" -> 38, "Fatima" -> 33, "Karim" -> 11, "Karim" -> 23)




  for ((k,v) <- ratings) println(s"key: $k, value: $v")

}

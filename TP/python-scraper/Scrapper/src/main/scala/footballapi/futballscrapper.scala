package footballapi

import play.api.libs.json._
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.ListMap


object futballscrapper extends App {

  val Autorization = "565ec012251f932ea4000001fa542ae9d994470e73fdb314a8a56d76"

  val uri = "http://api.football-api.com/2.0/competitions?Authorization=" + Autorization
  val query = scala.io.Source.fromURL(uri).mkString

  val json: JsValue = Json.parse(query)
  val allCountries = (json \\ "region").map(_.as[String])
  val idCountries = (json \\ "id").map(_.as[String])

  var country: Map[String, Int] = Map()

  for (i <- 0 to (allCountries.length - 1)) {
      println(idCountries(i).toInt + "->" + allCountries(i))
      country = country.updated(allCountries(i), idCountries(i).toInt)
    //
  }

  //country = ListMap(country.toSeq.sortBy(_._1):_*)

  println("Please choose a country : ")
  for ((key, value) <- country) {
    println(key, value)
  }

  country.keys.foreach { i =>
    println(country(i))
  }

  var selectedCountry = scala.io.StdIn.readLine("--> ")





  /*
  var checkKey = false
  var selectedCountry = ""
  var checkCountry = ""

  while (! checkKey) {


    if (country.exists(_._1 == selectedCountry))
      checkKey = true
    else
      println("*** Invalid entry ***\n")

    country.keys.foreach { i =>
      println(country(i))
    }

  //var countryKey = country.filter(_._1 == selectedCountry).map(_._1)
  //println(countryKey.tails)

  //for (x <- countryKey) {
    //println(x)
  //}*/



}

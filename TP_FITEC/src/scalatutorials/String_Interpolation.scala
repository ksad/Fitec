package scalatutorials

object String_Interpolation {
      def main (args:Array[String]){
      import scala.math._  
      val Tau: Double = Pi
      println(s"Happy $Tau Day")
      
      println(s"Two times three: ${2 * 3}")
      
      val name = "James"
      println(s"Hello, $name")  // Hello, James
      
      println(s"1 + 1 = ${1 + 1}")
      
      val height = 1.9d
      val firstname = "James"
      
      println(f"$firstname%s is $height%2.2f meters tall")  // James is 1.90 meters tall ==> f to format
      println(s"$firstname is $height%2.2f meters tall")  // James is 1.9%2.2f meters tall
      
      val newHeight: Double = 1.9d
      //println(f"newHeight = $newHeight%4d")   // %4d is an integer output ==> error
      println(f"newHeight = $newHeight%2.3f")   // %2.3f is a float output ==> newHeight = 1,900
      
      //QUESTION ????
      val str: String = "hello\nworld"
      println(s"$str")
      println(raw"$str")
    }
}
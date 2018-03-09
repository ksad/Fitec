package scalatutorials

object Tuples {
  def swap(x:String, y:String) = (y, x)  
  val (a,b) = swap("hello","world")  
  
  def main (args:Array[String]){
    println(a, b)
    
    // TUPLES
    val t = (1, "hello", Console)    // syntactic sugar
    val t2 = new Tuple3(1, "hello", Console)
    
    val t3 = (4,3,2,1)
    
    val sum = t3._1 + t3._2 + t3._3 + t3._4
    println( "Sum of elements: " + sum )
    
    t3.productIterator.foreach{ i =>println("Value = " + i )}
    
    println("Concatenated String: " + t.toString() )
    
    // QUESTION ???
    val tu = new Tuple2("Scala", "hello")
    println("Swapped Tuple: " + tu.swap)
  }
}
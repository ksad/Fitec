package scalatutorials

object methods {
      // this method takes two params and return an int
      def add(x:Int, y:Int):Int = {  
        return x + y  
      }
      
      def otherAdd(x:Int, y:Int) = { //result type is inferred   
        x + y //"return" keyword is optional  
      } 
      
      // this method takes multi params, defined on one line
      def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = (x + y) * multiplier
      
      //method without any params
      def name: String = {System.getProperty("user.name")}
      
      
      def main (args:Array[String]){
      
        println(add(4, 8))
        println(otherAdd(4, 8))
        println(addThenMultiply(1, 2)(3)) // 9
        println("Hello, " + name + "!")
    }
}
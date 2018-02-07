package scalatutorials

object Variables {
      def main (args:Array[String]){
        var x: Int = 16 // For best practice we use this syntax.
        println(x)
        
        var y = 1 + 2 //> x = 3  
        println(y)
        y = 3 * 4 //> x = 12  
        println(y)
        
        val a = 1 + 2 //val instead of var
        println(a)
        /* a = 3 * 4 //error: reassignment to val
        println(a)*/
    }
}